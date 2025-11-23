package inf.unideb.backend.controller;

import inf.unideb.backend.model.Item;
import inf.unideb.backend.model.User;
import inf.unideb.backend.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemControllerImplTest {

    private ItemService itemService;
    private ItemControllerImpl controller;

    @BeforeEach
    void setUp() {
        itemService = mock(ItemService.class);
        controller = new ItemControllerImpl(itemService);
    }

    @Test
    void testGetAll() {
        Item item1 = new Item(UUID.randomUUID(), "A", "desc", "img", "tag", new User(UUID.randomUUID(), "username1", "email1"));
        Item item2 = new Item(UUID.randomUUID(), "B", "desc2", "img2", "tag2", new User(UUID.randomUUID(), "username2", "email2"));

        when(itemService.getAll()).thenReturn(Arrays.asList(item1, item2));

        var result = controller.getAll();

        assertEquals(2, result.size());
        verify(itemService, times(1)).getAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Item item = new Item(id, "A", "desc", "img", "tag", new User(id2, "username", "email"));

        when(itemService.getOne(id)).thenReturn(item);

        var result = controller.getOne(id);

        assertEquals(id, result.getId());
        verify(itemService).getOne(id);
    }

    @Test
    void testCreate() {
        User user = new User(UUID.randomUUID(), "username", "email");
        Item item = new Item(null, "A", "desc", "img", "tag", user);

        Item saved = new Item(UUID.randomUUID(), "A", "desc", "img", "tag", user);

        when(itemService.create(ArgumentMatchers.any(Item.class))).thenReturn(saved);

        var result = controller.create(item);

        assertNotNull(result.getId());
        verify(itemService).create(item);
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();

        Item updatedItem = new Item(null, "New", "NewD", "NewImg", "NewTag",
                new User(UUID.randomUUID(), "NewName", "NewEmail"));
        Item saved = new Item(id, "New", "NewD", "NewImg", "NewTag",
                new User(UUID.randomUUID(), "NewName", "NewEmail"));

        when(itemService.update(eq(id), any(Item.class))).thenReturn(saved);

        var result = controller.update(id, updatedItem);

        assertEquals("New", result.getTitle());
        verify(itemService).update(eq(id), any(Item.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(itemService).delete(id);

        controller.delete(id);

        verify(itemService).delete(id);
    }
}
