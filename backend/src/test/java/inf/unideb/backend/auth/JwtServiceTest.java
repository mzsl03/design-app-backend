package inf.unideb.backend.auth;

import inf.unideb.backend.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    void testGenerateTokenAndExtractUsername() {
        UserDetails user = org.springframework.security.core.userdetails.User
                .builder()
                .username("john")
                .password("pass")
                .roles("USER")
                .build();

        UUID userId = UUID.randomUUID();
        String token = jwtService.generateToken(user.getUsername(), "USER", userId);

        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertEquals("john", jwtService.extractUsername(token));
    }

    @Test
    void testTokenIsValid() {
        UserDetails user = org.springframework.security.core.userdetails.User
                .builder()
                .username("john")
                .password("pass")
                .roles("USER")
                .build();

        UUID userId = UUID.randomUUID();
        String token = jwtService.generateToken(user.getUsername(), "USER", userId);

        assertTrue(jwtService.isTokenValid(token, user));
    }

    @Test
    void testTokenIsInvalidForDifferentUser() {
        UserDetails user1 = org.springframework.security.core.userdetails.User
                .builder()
                .username("john")
                .password("pass")
                .roles("USER")
                .build();

        UserDetails user2 = org.springframework.security.core.userdetails.User
                .builder()
                .username("mary")
                .password("pass")
                .roles("USER")
                .build();

        UUID userId = UUID.randomUUID();
        String token = jwtService.generateToken(user1.getUsername(), "USER", userId);

        assertFalse(jwtService.isTokenValid(token, user2));
    }
}
