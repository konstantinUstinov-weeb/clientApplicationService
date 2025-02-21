package prikol2.prikol2.Security.Token;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import prikol2.prikol2.User.UserDetailsImplementation;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtToken {

    @Value("${prikol2.app.secret}")
    private String secret;

    @Value("${prikol2.app.expirationMs}")
    private int lifetime;




    public String generateToken(Authentication authentication){
        UserDetailsImplementation userdetails = (UserDetailsImplementation)authentication.getPrincipal();
        return Jwts.builder().setSubject((userdetails.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + lifetime)))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();

    }

    public String getNameFromJwt(String token) {
        byte[] bytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(bytes);


        return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getBody()
                .getSubject();
    }
}
