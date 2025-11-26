package inf.unideb.backend.dto;

public record ItemDTO(
        String title,
        String imageUrl,
        String description,
        String tags,
        UserDTO user
) { }
