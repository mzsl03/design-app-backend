package inf.unideb.backend.auth;

import inf.unideb.backend.security.CustomUserDetailsService;
import inf.unideb.backend.security.JwtAuthenticationFilter;
import inf.unideb.backend.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    private JwtService jwtService;
    private CustomUserDetailsService userDetailsService;
    private JwtAuthenticationFilter filter;

    @BeforeEach
    void setUp() {
        jwtService = mock(JwtService.class);
        userDetailsService = mock(CustomUserDetailsService.class);
        filter = new JwtAuthenticationFilter(jwtService, userDetailsService);

        SecurityContextHolder.clearContext();
    }

    @Test
    void testFilterDoesNothingWhenNoHeader() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn(null);

        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    void testValidTokenAuthenticatesUser() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("Authorization"))
                .thenReturn("Bearer TOKEN123");

        when(jwtService.extractUsername("TOKEN123"))
                .thenReturn("john");

        var userDetails =
                org.springframework.security.core.userdetails.User
                        .builder()
                        .username("john")
                        .password("pass")
                        .authorities("ROLE_USER")
                        .build();

        when(userDetailsService.loadUserByUsername("john"))
                .thenReturn(userDetails);

        when(jwtService.isTokenValid("TOKEN123", userDetails))
                .thenReturn(true);

        filter.doFilter(request, response, chain);

        var auth = SecurityContextHolder.getContext().getAuthentication();

        assert(auth instanceof UsernamePasswordAuthenticationToken);
        assert(auth.isAuthenticated());
        verify(chain).doFilter(request, response);
    }

    @Test
    void testInvalidTokenDoesNotAuthenticate() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("Authorization"))
                .thenReturn("Bearer BADTOKEN");

        when(jwtService.extractUsername("BADTOKEN"))
                .thenReturn("john");

        var userDetails =
                org.springframework.security.core.userdetails.User
                        .builder()
                        .username("john")
                        .password("pass")
                        .authorities("ROLE_USER")
                        .build();

        when(userDetailsService.loadUserByUsername("john"))
                .thenReturn(userDetails);

        when(jwtService.isTokenValid("BADTOKEN", userDetails))
                .thenReturn(false);

        filter.doFilter(request, response, chain);

        assert(SecurityContextHolder.getContext().getAuthentication() == null);
        verify(chain).doFilter(request, response);
    }
}
