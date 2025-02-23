package prikol2.prikol2.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import prikol2.prikol2.employee.model.EmployeeDto;
import prikol2.prikol2.employee.repository.EmployeeRepositoryImpl;
import prikol2.prikol2.employee.service.EmployeeService;
import prikol2.prikol2.security.token.JwtToken;


@RestController
@RequestMapping("/auth")
public class SecurityController {


    @Autowired
    private EmployeeRepositoryImpl employeeRepository;


    private final EmployeeService employeeService;

    @Autowired
    public SecurityController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto signup(@RequestBody EmployeeDto employeeDto){
        return employeeService.createEmployee(employeeDto);
    }


    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> signin(@RequestBody EmployeeDto employeeDto){
        return employeeService.authorizeEmployee(employeeDto);

    }

}
