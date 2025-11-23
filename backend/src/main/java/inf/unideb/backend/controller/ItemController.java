package inf.unideb.backend.controller;

import inf.unideb.backend.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

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
