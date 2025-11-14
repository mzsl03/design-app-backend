package inf.unideb.backend.controller;

import inf.unideb.backend.model.Item;
import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemControllerImplTest {

    private ItemRepository repository;
    private ItemControllerImpl controller;

    @BeforeEach
    void setUp() {
        repository = mock(ItemRepository.class);
        controller = new ItemControllerImpl(repository);
    }

    @Test
    void testGetAll() {
        Item item1 = new Item(UUID.randomUUID(), "A", "desc", "img", "tag", new User(UUID.randomUUID(), "username1", "email1"));
        Item item2 = new Item(UUID.randomUUID(), "B", "desc2", "img2", "tag2", new User(UUID.randomUUID(), "username2", "email2"));

        when(repository.findAll()).thenReturn(Arrays.asList(item1, item2));

        var result = controller.getAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Item item = new Item(id, "A", "desc", "img", "tag", new User(id2, "username", "email"));

        when(repository.findById(id)).thenReturn(Optional.of(item));

        var result = controller.getOne(id);

        assertEquals(id, result.getId());
        verify(repository).findById(id);
    }

    @Test
    void testCreate() {
        User user = new User(UUID.randomUUID(), "username", "email");
        Item item = new Item(null, "A", "desc", "img", "tag", user);

        Item saved = new Item(UUID.randomUUID(), "A", "desc", "img", "tag", user);

        when(repository.save(ArgumentMatchers.any(Item.class))).thenReturn(saved);

        var result = controller.create(item);

        assertNotNull(result.getId());
        verify(repository).save(item);
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Item oldItem = new Item(id, "Old", "OldD", "OldImg", "OldTag", new User(id2, "OldName", "OldEmail"));
        Item updatedItem = new Item(null, "New", "NewD", "NewImg", "NewTag", new User(id2, "NewName", "NewEmail"));
        Item saved = new Item(id, "New", "NewD", "NewImg", "NewTag",  new User(id2, "NewName", "NewEmail"));

        when(repository.findById(id)).thenReturn(Optional.of(oldItem));
        when(repository.save(any(Item.class))).thenReturn(saved);

        var result = controller.update(id, updatedItem);

        assertEquals("New", result.getTitle());
        verify(repository).save(any(Item.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(repository).deleteById(id);

        controller.delete(id);

        verify(repository).deleteById(id);
    }
}
