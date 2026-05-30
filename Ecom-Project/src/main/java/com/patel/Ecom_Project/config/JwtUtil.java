package com.patel.Ecom_Project.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "mysecretkeymysecretkeymysecretkey"; // must be long
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+100*60*60))
                .signWith(key)
                .compact();
    }
    public String extractUsername(String token){
        return  Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

public boolean validateToken(String token,String username){
        final  String extractedUsername=extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
}
    private boolean isTokenExpired(String token) {

        Date expiration = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        return expiration.before(new Date());
    }


}
