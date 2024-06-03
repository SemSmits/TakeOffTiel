package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY_FILE = "secret.key";
    private static String SECRET_KEY;

    @PostConstruct
    public void init() {
        try {
            File file = new File(SECRET_KEY_FILE);
            if (file.exists()) {
                SECRET_KEY = new String(Files.readAllBytes(Paths.get(SECRET_KEY_FILE)));
            } else {
                SECRET_KEY = generateSecretKey();
                try (FileWriter writer = new FileWriter(SECRET_KEY_FILE)) {
                    writer.write(SECRET_KEY);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize SECRET_KEY", e);
        }
    }

    private static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32]; // 256-bit key
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public static String createToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isTokenExpired(String token) {
        return validateToken(token).getExpiration().before(new Date());
    }
}
