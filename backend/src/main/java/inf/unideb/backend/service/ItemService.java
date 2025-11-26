package inf.unideb.backend.service;

import inf.unideb.backend.dto.CreateItemDTO;
import inf.unideb.backend.dto.ItemDTO;
import inf.unideb.backend.dto.UpdateItemDTO;
import inf.unideb.backend.mapper.ItemMapper;
import inf.unideb.backend.model.Item;
import inf.unideb.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository ir) {
        this.itemRepository = ir;
    }

    public List<ItemDTO> getAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemMapper::toDTO)
                .toList();
    }

    public ItemDTO getOne(UUID id) {
        Item item = itemRepository.findById(id).orElseThrow();
        return ItemMapper.toDTO(item);
    }

    public ItemDTO create(CreateItemDTO item) {
        Item entity = ItemMapper.toEntity(item);
        Item saved = itemRepository.save(entity);
        return ItemMapper.toDTO(saved);
    }

    public ItemDTO update(UUID id, UpdateItemDTO item) {
        Item existing = itemRepository.findById(id).orElseThrow();
        ItemMapper.updateEntity(existing, item);
        Item saved = itemRepository.save(existing);
        return ItemMapper.toDTO(saved);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

}
