package inf.unideb.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
@Profile("prod")
public class JwtService {

    private static final long EXPIRATION = 1000 * 60 * 60 * 24;

    private static final String SECRET =
            "mySecretKeyForJwtSigning123456789012";

    private final Key key = Keys.hmacShaKeyFor(
            SECRET.getBytes(StandardCharsets.UTF_8)
    );

    public String generateToken(String username, String role, UUID userId) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        System.currentTimeMillis() + EXPIRATION
                ))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername())
                    && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
