package com.example.springboot.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TokenUtils {
    private static String SIGNING_KEY;
    private static Long TOKEN_VALIDITY;
    private static String AUTHORITIES_KEY;

    @Value("${jwt.signing.key}")
    public void setSIGNING(String value) { this.SIGNING_KEY = value; }
    @Value("${jwt.token.validity}")
    public void setVALIDITY(Long value) { this.TOKEN_VALIDITY = value; }
    @Value("${jwt.authorities.key}")
    public void setAUTHORITIES(String value) { this.AUTHORITIES_KEY = value; }

    public TokenUtils() {}

    public static String createToken(String name, String email){
        long expirationTime = TOKEN_VALIDITY*1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);

        return Jwts.builder()
                .setSubject(name)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .claim("name", name)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication( String token ){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }
}
