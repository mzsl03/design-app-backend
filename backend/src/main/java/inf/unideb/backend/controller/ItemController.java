package inf.unideb.backend.controller;

import inf.unideb.backend.model.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ItemController {

    @GetMapping("/api/items")
    List<Item> getAll();

    @GetMapping("/api/items/{id}")
    Item getOne(@PathVariable UUID id);

    @PostMapping("/api/items")
    Item create(@RequestBody Item item);

    @PutMapping("/api/items/{id}")
    Item update(@PathVariable UUID id, @RequestBody Item item);

    @DeleteMapping("/api/items/{id}")
    void delete(@PathVariable UUID id);
}