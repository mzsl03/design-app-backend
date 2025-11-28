package inf.unideb.backend.auth;

import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.UserRepository;
import inf.unideb.backend.security.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    private UserRepository userRepository;
    private CustomUserDetailsService service;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        service = new CustomUserDetailsService(userRepository);
    }

    @Test
    void testLoadUserByUsername() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .username("john")
                .password("encoded")
                .email("mail@mail.com")
                .build();

        when(userRepository.findByUsername("john"))
                .thenReturn(Optional.of(user));

        var result = service.loadUserByUsername("john");

        assertEquals("john", result.getUsername());
        assertEquals("encoded", result.getPassword());
    }

    @Test
    void testLoadUserByUsernameThrows() {
        when(userRepository.findByUsername("missing"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.loadUserByUsername("missing"));
    }
}

