package inf.unideb.backend.dto;

public record CreateUserDTO(
        String username,
        String email,
        String password
) { }
