package inf.unideb.backend.mapper;

import inf.unideb.backend.dto.*;
import inf.unideb.backend.dto.auth.RegisterRequestDTO;
import inf.unideb.backend.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void testToDTO() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@mail.com");

        UserDTO dto = UserMapper.toDTO(user);

        assertEquals("john", dto.username());
    }

    @Test
    void testToEntity() {
        RegisterRequestDTO dto = new RegisterRequestDTO("john", "john@mail.com", "pw123");

        User entity = UserMapper.toEntity(dto);

        assertEquals("john", entity.getUsername());
        assertEquals("john@mail.com", entity.getEmail());
    }

    @Test
    void testUpdateEntity() {
        User user = new User();
        user.setUsername("old");
        user.setEmail("old@mail.com");

        UpdateUserDTO dto = new UpdateUserDTO("new", "new@mail.com", "pw123");

        UserMapper.updateEntity(user, dto);

        assertEquals("new", user.getUsername());
        assertEquals("new@mail.com", user.getEmail());
    }
}
