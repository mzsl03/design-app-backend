package inf.unideb.backend.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Profile("prod")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtServiceC,
                                   CustomUserDetailsService userDSC) {
        this.jwtService = jwtServiceC;
        this.userDetailsService = userDSC;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getServletPath();
        if (path == null || path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        final String username = jwtService.extractUsername(token);

        if (username != null
                && SecurityContextHolder
                .getContext()
                .getAuthentication() == null) {

            UserDetails userDetails = userDetailsService
                    .loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails)) {

                String role = jwtService.extractRole(token);

                var authorities = List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + role
                        ));

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                authorities
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}
