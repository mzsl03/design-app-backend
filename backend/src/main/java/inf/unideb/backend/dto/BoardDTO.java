package inf.unideb.backend.dto;

import java.util.List;
import java.util.UUID;

public record BoardDTO(
        UUID id,
        String name,
        UserDTO user,
        List<ItemDTO> items
) { }
