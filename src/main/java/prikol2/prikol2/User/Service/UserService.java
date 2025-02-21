package prikol2.prikol2.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prikol2.prikol2.User.Repository.UserRepositoryImpl;
import prikol2.prikol2.User.User;
import prikol2.prikol2.User.UserDetailsImplementation;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    public void setUserRepository(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username).orElseThrow(
                ()-> new UsernameNotFoundException(
                        String.format("User '%s' not found", username)
                ));
        return UserDetailsImplementation.build(user);
    }
}
