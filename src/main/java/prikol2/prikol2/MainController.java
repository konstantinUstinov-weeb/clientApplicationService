package prikol2.prikol2;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prikol2.prikol2.Security.AuthRequest.SignInRequest;

@RestController
@RequestMapping("/user")
public class MainController {

    @GetMapping("/main")
        public String currentUserName(Authentication authentication) {
            return authentication.name();
    }
    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(signInRequest.getUserName());
    }

}
