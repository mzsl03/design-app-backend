package inf.unideb.backend.controller;

import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerImplTest {

    private UserRepository repository;
    private UserControllerImpl controller;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        controller = new UserControllerImpl(repository);
    }

    @Test
    void testGetAll() {
        User u1 = new User(UUID.randomUUID(), "user1", "email1");
        User u2 = new User(UUID.randomUUID(), "user2", "email2");

        when(repository.findAll()).thenReturn(Arrays.asList(u1, u2));

        var result = controller.getAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "user", "email");

        when(repository.findById(id)).thenReturn(Optional.of(user));

        var result = controller.getOne(id);

        assertEquals("user", result.getUsername());
        verify(repository).findById(id);
    }

    @Test
    void testCreate() {
        User newUser = new User(null, "user", "email");
        User savedUser = new User(UUID.randomUUID(), "user", "email");

        when(repository.save(any(User.class))).thenReturn(savedUser);

        var result = controller.create(newUser);

        assertNotNull(result.getId());
        verify(repository).save(newUser);
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();

        User oldUser = new User(id, "old", "old@mail.com");
        User updated = new User(null, "new", "new@mail.com");
        User saved = new User(id, "new", "new@mail.com");

        when(repository.findById(id)).thenReturn(Optional.of(oldUser));
        when(repository.save(any(User.class))).thenReturn(saved);

        var result = controller.update(id, updated);

        assertEquals("new", result.getUsername());
        verify(repository).save(any(User.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(repository).deleteById(id);

        controller.delete(id);

        verify(repository).deleteById(id);
    }
}
