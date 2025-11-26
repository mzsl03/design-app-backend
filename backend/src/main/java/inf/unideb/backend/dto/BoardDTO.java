package inf.unideb.backend.dto;

import java.util.List;

public record BoardDTO(
        String name,
        UserDTO user,
        List<ItemDTO> items
) { }
