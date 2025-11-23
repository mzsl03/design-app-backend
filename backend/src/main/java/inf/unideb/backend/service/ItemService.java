package inf.unideb.backend.service;

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

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getOne(UUID id) {
        return itemRepository.findById(id).orElseThrow();
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item update(UUID id, Item item) {
        Item existing = itemRepository.findById(id).orElseThrow();

        existing.setTitle(item.getTitle());
        existing.setImageUrl(item.getImageUrl());
        existing.setDescription(item.getDescription());
        existing.setTags(item.getTags());

        return itemRepository.save(existing);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

}
