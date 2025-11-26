package inf.unideb.backend.service;

import inf.unideb.backend.dto.*;
import inf.unideb.backend.mapper.ItemMapper;
import inf.unideb.backend.model.Item;
import inf.unideb.backend.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private ItemRepository itemRepository;
    private ItemService service;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        service = new ItemService(itemRepository);
    }

    @Test
    void testGetAll() {
        Item i1 = new Item(UUID.randomUUID(), "A", "imgA", "descA", "tagA", null);
        Item i2 = new Item(UUID.randomUUID(), "B", "imgB", "descB", "tagB", null);

        when(itemRepository.findAll()).thenReturn(List.of(i1, i2));

        var result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).title());
        verify(itemRepository).findAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        Item item = new Item(id, "A", "img", "desc", "tag", null);

        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        var result = service.getOne(id);

        assertEquals("A", result.title());
        verify(itemRepository).findById(id);
    }

    @Test
    void testCreate() {
        CreateItemDTO dto = new CreateItemDTO("A", "img", "desc", "tag");
        Item saved = new Item(UUID.randomUUID(), "A", "img", "desc", "tag", null);

        when(itemRepository.save(any(Item.class))).thenReturn(saved);

        var result = service.create(dto);

        assertEquals("A", result.title());
        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();
        Item existing = new Item(id, "Old", "OldImg", "OldDesc", "OldTag", null);

        UpdateItemDTO dto = new UpdateItemDTO("New", "NewImg", "NewDesc", "NewTag");
        Item saved = new Item(id, "New", "NewImg", "NewDesc", "NewTag", null);

        when(itemRepository.findById(id)).thenReturn(Optional.of(existing));
        when(itemRepository.save(existing)).thenReturn(saved);

        var result = service.update(id, dto);

        assertEquals("New", result.title());
        verify(itemRepository).save(existing);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(itemRepository).deleteById(id);

        service.delete(id);

        verify(itemRepository).deleteById(id);
    }
}
