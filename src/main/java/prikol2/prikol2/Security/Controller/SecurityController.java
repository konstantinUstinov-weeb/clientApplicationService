package prikol2.prikol2.Security.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prikol2.prikol2.Security.Token.JwtToken;
import prikol2.prikol2.Security.AuthRequest.SignInRequest;
import prikol2.prikol2.Security.AuthRequest.SignUpRequest;
import prikol2.prikol2.User.User;
import prikol2.prikol2.User.Repository.UserRepositoryImpl;

@RestController
@RequestMapping("/auth")
public class SecurityController {


    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;





    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        if (userRepository.existsByUserName(signUpRequest.getUserName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("choose another name");
        }
        if (userRepository.existsByLogin(signUpRequest.getLogin())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("choose another login");
        }

        String hashed = passwordEncoder.encode(signUpRequest.getPassword());

        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(hashed);
        userRepository.save(user);
        return ResponseEntity.ok("user saved in database");
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest){
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getUserName(), signInRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtToken.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

}
