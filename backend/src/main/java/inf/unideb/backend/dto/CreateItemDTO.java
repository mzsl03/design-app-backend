package inf.unideb.backend.dto;

public record CreateItemDTO(
        String title,
        String imageUrl,
        String description,
        String tags
) { }
