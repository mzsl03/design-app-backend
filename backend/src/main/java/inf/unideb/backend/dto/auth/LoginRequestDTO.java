package inf.unideb.backend.dto.auth;

public record LoginRequestDTO(
        String username,
        String password
) { }
