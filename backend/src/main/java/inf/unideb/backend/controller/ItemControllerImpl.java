package inf.unideb.backend.controller;

import inf.unideb.backend.model.Item;
import inf.unideb.backend.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ItemControllerImpl implements ItemController {

    private final ItemRepository repo;

    public ItemControllerImpl(ItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Item> getAll() {
        return repo.findAll();
    }

    @Override
    public Item getOne(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Item create(Item item) {
        return repo.save(item);
    }

    @Override
    public Item update(UUID id, Item item) {
        Item existing = repo.findById(id).orElseThrow();

        existing.setTitle(item.getTitle());
        existing.setImageUrl(item.getImageUrl());
        existing.setDescription(item.getDescription());
        existing.setTags(item.getTags());

        return repo.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
