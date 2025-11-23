package inf.unideb.backend.controller;

import inf.unideb.backend.model.Item;
import inf.unideb.backend.service.ItemService;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
public class ItemControllerImpl implements ItemController {

    private final ItemService itemService;

    public ItemControllerImpl(ItemService service) {
        this.itemService = service;
    }

    @Override
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @Override
    public Item getOne(UUID id) {
        return itemService.getOne(id);
    }

    @Override
    public Item create(Item item) {
        return itemService.create(item);
    }

    @Override
    public Item update(UUID id, Item item) {
        return itemService.update(id, item);
    }

    @Override
    public void delete(UUID id) {
        itemService.delete(id);
    }
}
