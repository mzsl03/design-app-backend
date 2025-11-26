package inf.unideb.backend.controller;

import inf.unideb.backend.dto.CreateItemDTO;
import inf.unideb.backend.dto.ItemDTO;
import inf.unideb.backend.dto.UpdateItemDTO;
import inf.unideb.backend.dto.UserDTO;
import inf.unideb.backend.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        ItemDTO item1 = new ItemDTO("A", "img", "desc", "tag", new UserDTO("username1"));
        ItemDTO item2 = new ItemDTO("B", "img2", "desc2", "tag2", new UserDTO("username2"));

        when(itemService.getAll()).thenReturn(Arrays.asList(item1, item2));

        var result = controller.getAll();

        assertEquals(2, result.size());
        verify(itemService, times(1)).getAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();

        ItemDTO item = new ItemDTO("A", "img", "desc", "tag", new UserDTO("username"));

        when(itemService.getOne(id)).thenReturn(item);

        var result = controller.getOne(id);

        assertEquals("A", result.title());
        verify(itemService).getOne(id);
    }

    @Test
    void testCreate() {
        CreateItemDTO dto = new CreateItemDTO("A", "img", "desc", "tag");
        ItemDTO saved = new ItemDTO("A", "img", "desc", "tag", new UserDTO("username"));

        when(itemService.create(any(CreateItemDTO.class))).thenReturn(saved);

        var result = controller.create(dto);

        assertEquals("A", result.title());
        verify(itemService).create(any(CreateItemDTO.class));
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();

        UpdateItemDTO dto = new UpdateItemDTO("New", "NewImg", "NewDesc", "NewTag");
        ItemDTO saved = new ItemDTO("New", "NewImg", "NewDesc", "NewTag", new UserDTO("user"));

        when(itemService.update(eq(id), any(UpdateItemDTO.class))).thenReturn(saved);

        var result = controller.update(id, dto);

        assertEquals("New", result.title());
        verify(itemService).update(eq(id), any(UpdateItemDTO.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(itemService).delete(id);

        controller.delete(id);

        verify(itemService).delete(id);
    }
}
