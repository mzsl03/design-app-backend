package inf.unideb.backend.controller;

import inf.unideb.backend.dto.CreateItemDTO;
import inf.unideb.backend.dto.ItemDTO;
import inf.unideb.backend.dto.UpdateItemDTO;
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
    public List<ItemDTO> getAll() {
        return itemService.getAll();
    }

    @Override
    public ItemDTO getOne(UUID id) {
        return itemService.getOne(id);
    }

    @Override
    public ItemDTO create(CreateItemDTO item) {
        return itemService.create(item);
    }

    @Override
    public ItemDTO update(UUID id, UpdateItemDTO item) {
        return itemService.update(id, item);
    }

    @Override
    public void delete(UUID id) {
        itemService.delete(id);
    }
}
