package inf.unideb.backend.mapper;

import inf.unideb.backend.dto.*;
import inf.unideb.backend.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    @Test
    void testToDTO() {
        User u = new User();
        u.setUsername("john");

        Item item = new Item();
        item.setTitle("Item 1");
        item.setImageUrl("img");
        item.setDescription("desc");
        item.setTags("tag");
        item.setUser(u);

        ItemDTO dto = ItemMapper.toDTO(item);

        assertEquals("Item 1", dto.title());
        assertEquals("img", dto.imageUrl());
        assertEquals("desc", dto.description());
        assertEquals("tag", dto.tags());
        assertEquals("john", dto.user().username());
    }

    @Test
    void testToEntity() {
        CreateItemDTO dto = new CreateItemDTO("Item 1", "img", "desc", "tag");

        Item entity = ItemMapper.toEntity(dto);

        assertEquals("Item 1", entity.getTitle());
        assertEquals("img", entity.getImageUrl());
        assertEquals("desc", entity.getDescription());
        assertEquals("tag", entity.getTags());
    }

    @Test
    void testUpdateEntity() {
        Item item = new Item();
        item.setTitle("Old");
        item.setImageUrl("OldImg");
        item.setDescription("OldDesc");
        item.setTags("OldTag");

        UpdateItemDTO dto = new UpdateItemDTO("New", "NewImg", "NewDesc", "NewTag");

        ItemMapper.updateEntity(item, dto);

        assertEquals("New", item.getTitle());
        assertEquals("NewImg", item.getImageUrl());
        assertEquals("NewDesc", item.getDescription());
        assertEquals("NewTag", item.getTags());
    }
}
