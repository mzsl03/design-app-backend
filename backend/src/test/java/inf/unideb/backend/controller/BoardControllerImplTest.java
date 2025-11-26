package inf.unideb.backend.controller;

import inf.unideb.backend.dto.CreateBoardDTO;
import inf.unideb.backend.dto.UpdateBoardDTO;
import inf.unideb.backend.model.Board;
import inf.unideb.backend.model.Item;
import inf.unideb.backend.repository.BoardRepository;
import inf.unideb.backend.repository.ItemRepository;
import inf.unideb.backend.service.BoardService;
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
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        boardRepository = mock(BoardRepository.class);
        itemRepository = mock(ItemRepository.class);
        boardService = new BoardService(boardRepository, itemRepository);
    }

    @Test
    void testGetAll() {
        Board b1 = new Board(UUID.randomUUID(), "Designs", null, new ArrayList<>());
        Board b2 = new Board(UUID.randomUUID(), "Clothes", null, new ArrayList<>());

        when(boardRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        var result = boardService.getAll();

        assertEquals(2, result.size());

        assertEquals("Designs", result.get(0).name());
        assertEquals("Clothes", result.get(1).name());

        verify(boardRepository).findAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        Board board = new Board(id, "Designs", null, new ArrayList<>());

        when(boardRepository.findById(id)).thenReturn(Optional.of(board));

        var result = boardService.getOne(id);


        assertEquals("Designs", result.name());
        verify(boardRepository).findById(id);
    }

    @Test
    void testCreate() {
        CreateBoardDTO dto = new CreateBoardDTO("Test");

        Board saved = new Board(UUID.randomUUID(), "Test", null, new ArrayList<>());

        when(boardRepository.save(any(Board.class))).thenReturn(saved);

        var result = boardService.create(dto);


        assertEquals("Test", result.name());
        verify(boardRepository).save(any(Board.class));
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();

        Board oldBoard = new Board(id, "Old", null, new ArrayList<>());
        UpdateBoardDTO dto = new UpdateBoardDTO("New");
        Board saved = new Board(id, "New", null, new ArrayList<>());

        when(boardRepository.findById(id)).thenReturn(Optional.of(oldBoard));
        when(boardRepository.save(any(Board.class))).thenReturn(saved);

        var result = boardService.update(id, dto);


        assertEquals("New", result.name());
        verify(boardRepository).save(any(Board.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(boardRepository).deleteById(id);

        boardService.delete(id);

        verify(boardRepository).deleteById(id);
    }

    @Test
    void testAddItem() {
        UUID boardId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();

        Board board = new Board(boardId, "TestBoard", null, new ArrayList<>());
        Item item = new Item(itemId, "Item", null, null, null, null);

        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        when(boardRepository.save(board)).thenReturn(board);

        var result = boardService.addItem(boardId, itemId);


        assertEquals(1, result.items().size());
        assertEquals("Item", result.items().get(0).title());

        verify(boardRepository).findById(boardId);
        verify(itemRepository).findById(itemId);
        verify(boardRepository).save(board);
    }
}
