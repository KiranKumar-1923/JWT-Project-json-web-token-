package Jwt_Security.JWT_Project.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey";

    private static final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ROLE_USER");

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}