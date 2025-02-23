package prikol2.prikol2.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import prikol2.prikol2.employee.model.Employee;

import java.util.Collection;

@Data
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDetailsImpl implements UserDetails {

    private Long id;
    private String employeeName;
    private String login;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public EmployeeDetailsImpl() {
    }

    public static <List> EmployeeDetailsImpl build(Employee employee){
        EmployeeDetailsImpl employeeDetailsImpl = new EmployeeDetailsImpl();
        employeeDetailsImpl.setId(employee.getId());
        employeeDetailsImpl.setLogin(employee.getLogin());
        employeeDetailsImpl.setEmployeeName(employee.getEmployeeName());
        employeeDetailsImpl.setPassword(employee.getPassword());
        return employeeDetailsImpl;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return employeeName;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
