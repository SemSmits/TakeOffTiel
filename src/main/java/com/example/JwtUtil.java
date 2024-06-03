package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

public class JwtUtil {

    private static KeyGenerator MacProvider;
    private static final Key key = MacProvider.generateKey();

    public static String createToken(String username, String role) throws JwtException {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder().setSubject(username).setExpiration(expiration.getTime()).claim("role", role).signWith(SignatureAlgorithm.HS512, key).compact();
    }

    public static Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
