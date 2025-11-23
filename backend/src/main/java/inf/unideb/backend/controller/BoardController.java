package inf.unideb.backend.controller;

import inf.unideb.backend.model.Board;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.UUID;

public interface BoardController {

    @GetMapping("/api/boards")
    List<Board> getAll();

    @GetMapping("/api/boards/{id}")
    Board getOne(@PathVariable UUID id);

    @PostMapping("/api/boards")
    Board create(@RequestBody Board board);

    @PutMapping("/api/boards/{id}")
    Board update(@PathVariable UUID id, @RequestBody Board board);

    @DeleteMapping("/api/boards/{id}")
    void delete(@PathVariable UUID id);

    @PostMapping("/api/boards/{boardId}/addItem/{itemId}")
    Board addItem(@PathVariable UUID boardId, @PathVariable UUID itemId);
}
