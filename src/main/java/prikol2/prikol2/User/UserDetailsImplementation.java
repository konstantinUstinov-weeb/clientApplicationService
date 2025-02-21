package prikol2.prikol2.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor

public class UserDetailsImplementation implements UserDetails {

    private Long id;
    private String username;
    private String login;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImplementation() {
    }

    public static <List> UserDetailsImplementation build(User user){
        UserDetailsImplementation userDetailsImplementation = new UserDetailsImplementation();
        userDetailsImplementation.setId(user.getId());
        userDetailsImplementation.setLogin(user.getLogin());
        userDetailsImplementation.setUsername(user.getUserName());
        userDetailsImplementation.setPassword(user.getPassword());
        return userDetailsImplementation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
