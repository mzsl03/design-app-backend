package inf.unideb.backend.controller;

import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.UserRepository;
import inf.unideb.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        User u1 = new User(UUID.randomUUID(), "user1", "email1");
        User u2 = new User(UUID.randomUUID(), "user2", "email2");

        when(userService.getAll()).thenReturn(Arrays.asList(u1, u2));

        var result = controller.getAll();

        assertEquals(2, result.size());
        verify(userService).getAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "user", "email");

        when(userService.getOne(id)).thenReturn(user);

        var result = controller.getOne(id);

        assertEquals("user", result.getUsername());
        verify(userService).getOne(id);
    }

    @Test
    void testCreate() {
        User newUser = new User(null, "user", "email");
        User savedUser = new User(UUID.randomUUID(), "user", "email");

        when(userService.create(any(User.class))).thenReturn(savedUser);

        var result = controller.create(newUser);

        assertNotNull(result.getId());
        verify(userService).create(newUser);
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();
        User updated = new User(null, "new", "new@mail.com");
        User saved = new User(id, "new", "new@mail.com");

        when(userService.update(eq(id), any(User.class))).thenReturn(saved);

        var result = controller.update(id, updated);

        assertEquals("new", result.getUsername());
        verify(userService).update(eq(id), any(User.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(userService).delete(id);

        controller.delete(id);

        verify(userService).delete(id);
    }
}
