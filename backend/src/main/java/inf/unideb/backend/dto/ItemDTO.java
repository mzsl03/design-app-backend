package inf.unideb.backend.dto;

import java.util.UUID;

public record ItemDTO(
        UUID id,
        String title,
        String imageUrl,
        String description,
        String tags,
        UserDTO user
) { }
