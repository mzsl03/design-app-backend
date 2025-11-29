package inf.unideb.backend.controller;

import inf.unideb.backend.dto.BoardDTO;
import inf.unideb.backend.dto.CreateBoardDTO;
import inf.unideb.backend.dto.UpdateBoardDTO;
import org.springframework.security.access.prepost.PreAuthorize;
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
    List<BoardDTO> getAll();

    @GetMapping("/api/boards/{id}")
    BoardDTO getOne(@PathVariable UUID id);

    @PostMapping("/api/boards")
    BoardDTO create(@RequestBody CreateBoardDTO board);

    @PutMapping("/api/boards/{id}")
    BoardDTO update(@PathVariable UUID id, @RequestBody UpdateBoardDTO board);

    @DeleteMapping("/api/boards/{id}")
    void delete(@PathVariable UUID id);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/boards/{boardId}/addItem/{itemId}")
    BoardDTO addItem(@PathVariable UUID boardId, @PathVariable UUID itemId);
}
