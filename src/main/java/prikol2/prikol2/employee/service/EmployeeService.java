package prikol2.prikol2.employee.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import prikol2.prikol2.employee.mapper.EmployeeMapper;
import prikol2.prikol2.employee.model.Employee;
import prikol2.prikol2.employee.model.EmployeeDto;
import prikol2.prikol2.employee.repository.EmployeeRepositoryImpl;
import prikol2.prikol2.employee.EmployeeDetailsImpl;
import prikol2.prikol2.security.token.JwtToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@Service
public class EmployeeService implements UserDetailsService {


    @Autowired
    private EmployeeRepositoryImpl employeeRepositoryimpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(EmployeeRepositoryImpl employeeRepositoryimpl) {
        this.employeeRepositoryimpl = employeeRepositoryimpl;
    }


    public UserDetails loadUserByUsername(String employeeName) throws UsernameNotFoundException {
        Employee employee = employeeRepositoryimpl.findEmployeeByEmployeeName(employeeName).orElseThrow(
                ()-> new UsernameNotFoundException(
                        String.format("User '%s' not found", employeeName)
                ));
        return EmployeeDetailsImpl.build(employee);
    }


    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        log.info("Регистрация нового сотрудника");
        validateEmployee(employeeDto);

        employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employeeRepositoryimpl.save(employee);
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    public void validateEmployee(EmployeeDto employeeDto){
        log.trace("Проверка существования работника {}",employeeDto.getLogin());
        if(employeeRepositoryimpl.existsByLogin(employeeDto.getLogin())) {
            log.error("Работник с таким номером уже существует {}",employeeDto.getLogin());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException responseStatusException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseStatusException.getMessage());
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;


    public ResponseEntity<?> authorizeEmployee(EmployeeDto employeeDto) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    employeeDto.getEmployeeName(), employeeDto.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtToken.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }
}
