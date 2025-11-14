package inf.unideb.backend.controller;

import inf.unideb.backend.model.Board;
import inf.unideb.backend.repository.BoardRepository;
import inf.unideb.backend.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardControllerImplTest {

    private BoardRepository boardRepository;
    private ItemRepository itemRepository;
    private BoardControllerImpl controller;

    @BeforeEach
    void setUp() {
        boardRepository = mock(BoardRepository.class);
        itemRepository = mock(ItemRepository.class);
        controller = new BoardControllerImpl(boardRepository, itemRepository);
    }

    @Test
    void testGetAll() {
        Board b1 = new Board(UUID.randomUUID(), "Designs", null, new ArrayList<>());
        Board b2 = new Board(UUID.randomUUID(), "Clothes", null, new ArrayList<>());

        when(boardRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        var result = controller.getAll();

        assertEquals(2, result.size());
        verify(boardRepository).findAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();

        Board board = new Board(id, "Designs", null, new ArrayList<>());

        when(boardRepository.findById(id)).thenReturn(Optional.of(board));

        var result = controller.getOne(id);

        assertEquals("Designs", result.getName());
        verify(boardRepository).findById(id);
    }

    @Test
    void testCreate() {
        Board board = new Board(null, "Test", null, new ArrayList<>());
        Board saved = new Board(UUID.randomUUID(), "Test", null, new ArrayList<>());

        when(boardRepository.save(any(Board.class))).thenReturn(saved);

        var result = controller.create(board);

        assertNotNull(result.getId());
        verify(boardRepository).save(board);
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();

        Board oldBoard = new Board(id, "Old", null, new ArrayList<>());
        Board update = new Board(null, "New", null, new ArrayList<>());
        Board saved = new Board(id, "New", null, new ArrayList<>());

        when(boardRepository.findById(id)).thenReturn(Optional.of(oldBoard));
        when(boardRepository.save(any(Board.class))).thenReturn(saved);

        var result = controller.update(id, update);

        assertEquals("New", result.getName());
        verify(boardRepository).save(any(Board.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(boardRepository).deleteById(id);

        controller.delete(id);

        verify(boardRepository).deleteById(id);
    }
}
