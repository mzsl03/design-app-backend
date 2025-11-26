package inf.unideb.backend.dto;

public record UpdateItemDTO(
        String title,
        String imageUrl,
        String description,
        String tags
) { }
