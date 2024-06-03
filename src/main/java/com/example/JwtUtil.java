package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    // Sleutel wordt eenmalig gegenereerd en gebruikt voor token signering
    private static final SecretKey SECRET_KEY = generateSecretKey();
    private static final long EXPIRATION_TIME = 86400000; // 24 uur

    // Genereer de sleutel eenmalig
    private static SecretKey generateSecretKey() {
        SecretKey key = MacProvider.generateKey();
        String base64EncodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated Secret Key: " + base64EncodedKey); // Voor debugging, sla op een veilige plaats op
        return key;
    }

    // Verkrijg de sleutel als SecretKey object
    private static SecretKey getSecretKey() {
        return new SecretKeySpec(SECRET_KEY.getEncoded(), 0, SECRET_KEY.getEncoded().length, "HmacSHA512");
    }

    // Genereer JWT token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, getSecretKey())
                .compact();
    }

    // Valideer JWT token
    public static Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
