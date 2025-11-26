package inf.unideb.backend.mapper;

import inf.unideb.backend.dto.CreateItemDTO;
import inf.unideb.backend.dto.ItemDTO;
import inf.unideb.backend.dto.UpdateItemDTO;
import inf.unideb.backend.model.Item;

public class ItemMapper {

    public static ItemDTO toDTO(Item item) {
        if (item == null) {
            return null;
        }

        return new ItemDTO(
                item.getTitle(),
                item.getImageUrl(),
                item.getDescription(),
                item.getTags(),
                UserMapper.toDTO(item.getUser())
        );
    }

    public static Item toEntity(CreateItemDTO dto) {
        Item item = new Item();
        item.setTitle(dto.title());
        item.setImageUrl(dto.imageUrl());
        item.setDescription(dto.description());
        item.setTags(dto.tags());
        return item;
    }

    public static void updateEntity(Item item, UpdateItemDTO dto) {
        if (dto.title() != null) {
            item.setTitle(dto.title());
        }
        if (dto.imageUrl() != null) {
            item.setImageUrl(dto.imageUrl());
        }
        if (dto.description() != null) {
            item.setDescription(dto.description());
        }
        if (dto.tags() != null) {
            item.setTags(dto.tags());
        }
    }

}
