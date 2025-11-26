package inf.unideb.backend.service;

import inf.unideb.backend.dto.*;
import inf.unideb.backend.mapper.UserMapper;
import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService service;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        service = new UserService(userRepository);
    }

    @Test
    void testGetAll() {
        User u1 = new User(UUID.randomUUID(), "a", "a@mail");
        User u2 = new User(UUID.randomUUID(), "b", "b@mail");

        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        var result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("a", result.get(0).username());
        verify(userRepository).findAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "john", "j@mail");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        var result = service.getOne(id);

        assertEquals("john", result.username());
        verify(userRepository).findById(id);
    }

    @Test
    void testCreate() {
        CreateUserDTO dto = new CreateUserDTO("john", "j@mail");
        User saved = new User(UUID.randomUUID(), "john", "j@mail");

        when(userRepository.save(any(User.class))).thenReturn(saved);

        var result = service.create(dto);

        assertEquals("john", result.username());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();
        User existing = new User(id, "old", "old@mail");

        UpdateUserDTO dto = new UpdateUserDTO("new", "new@mail");
        User saved = new User(id, "new", "new@mail");

        when(userRepository.findById(id)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenReturn(saved);

        var result = service.update(id, dto);

        assertEquals("new", result.username());
        verify(userRepository).save(existing);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(userRepository).deleteById(id);

        service.delete(id);

        verify(userRepository).deleteById(id);
    }
}
