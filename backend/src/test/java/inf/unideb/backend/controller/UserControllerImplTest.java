package inf.unideb.backend.controller;

import inf.unideb.backend.dto.auth.RegisterRequestDTO;
import inf.unideb.backend.dto.UpdateUserDTO;
import inf.unideb.backend.dto.UserDTO;

import inf.unideb.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerImplTest {

    private UserService userService;
    private UserControllerImpl controller;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        controller = new UserControllerImpl(userService);
    }

    @Test
    void testGetAll() {
        UserDTO u1 = new UserDTO(UUID.randomUUID(), "user1");
        UserDTO u2 = new UserDTO(UUID.randomUUID(), "user2");

        when(userService.getAll()).thenReturn(Arrays.asList(u1, u2));

        var result = controller.getAll();

        assertEquals(2, result.size());
        verify(userService).getAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();

        UserDTO user = new UserDTO(id,"user");

        when(userService.getOne(id)).thenReturn(user);

        var result = controller.getOne(id);

        assertEquals("user", result.username());
        verify(userService).getOne(id);
    }

    @Test
    void testCreate() {
        RegisterRequestDTO dto = new RegisterRequestDTO("user", "email", "pw123");
        UserDTO saved = new UserDTO(UUID.randomUUID(), "user");

        when(userService.create(any(RegisterRequestDTO.class))).thenReturn(saved);

        var result = controller.create(dto);

        assertEquals("user", result.username());
        verify(userService).create(any(RegisterRequestDTO.class));
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();

        UpdateUserDTO dto = new UpdateUserDTO("new", "new@mail.com", "pw123");
        UserDTO saved = new UserDTO(id,"new");

        when(userService.update(eq(id), any(UpdateUserDTO.class))).thenReturn(saved);

        var result = controller.update(id, dto);

        assertEquals("new", result.username());
        verify(userService).update(eq(id), any(UpdateUserDTO.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(userService).delete(id);

        controller.delete(id);

        verify(userService).delete(id);
    }
}
