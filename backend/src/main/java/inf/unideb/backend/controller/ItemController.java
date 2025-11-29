package inf.unideb.backend.controller;

import inf.unideb.backend.dto.CreateItemDTO;
import inf.unideb.backend.dto.ItemDTO;
import inf.unideb.backend.dto.UpdateItemDTO;
import org.springframework.security.access.prepost.PreAuthorize;
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
    List<ItemDTO> getAll();

    @GetMapping("/api/items/{id}")
    ItemDTO getOne(@PathVariable UUID id);

    @PostMapping("/api/items")
    ItemDTO create(@RequestBody CreateItemDTO item);

    @PutMapping("/api/items/{id}")
    ItemDTO update(@PathVariable UUID id, @RequestBody UpdateItemDTO item);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/items/{id}")
    void delete(@PathVariable UUID id);
}
