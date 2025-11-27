package inf.unideb.backend.controller;


import inf.unideb.backend.dto.auth.AuthResponseDTO;
import inf.unideb.backend.dto.auth.LoginRequestDTO;
import inf.unideb.backend.dto.auth.RegisterRequestDTO;
import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.UserRepository;
import inf.unideb.backend.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthController(AuthenticationManager authManagerC,
                          UserRepository userRepositoryC,
                          PasswordEncoder passwordEncoderC,
                          JwtService jwtServiceC) {
        this.authManager = authManagerC;
        this.userRepository = userRepositoryC;
        this.passwordEncoder = passwordEncoderC;
        this.jwtService = jwtServiceC;
    }

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody RegisterRequestDTO dto) {

        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));

        userRepository.save(user);

        String token = jwtService.generateToken(dto.username());
        return new AuthResponseDTO(token);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO dto) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()
                )
        );

        String token = jwtService.generateToken(dto.username());
        return new AuthResponseDTO(token);
    }
}
