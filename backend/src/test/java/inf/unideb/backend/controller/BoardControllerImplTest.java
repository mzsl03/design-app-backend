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

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
        Board b1 = new Board();
        b1.setId(UUID.randomUUID());
        b1.setName("Designs");

        Board b2 = new Board();
        b2.setId(UUID.randomUUID());
        b2.setName("Clothes");

        when(boardRepository.findAll()).thenReturn(List.of(b1, b2));

        var result = boardService.getAll();

        assertEquals(2, result.size());
        assertEquals("Designs", result.get(0).name());
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        Board b = new Board();
        b.setId(id);
        b.setName("Board1");

        when(boardRepository.findById(id)).thenReturn(Optional.of(b));

        var result = boardService.getOne(id);
        assertEquals("Board1", result.name());
    }

    @Test
    void testCreate() {
        CreateBoardDTO dto = new CreateBoardDTO("MyBoard");

        Board saved = new Board();
        saved.setId(UUID.randomUUID());
        saved.setName("MyBoard");

        when(boardRepository.save(any(Board.class))).thenReturn(saved);

        var result = boardService.create(dto);
        assertEquals("MyBoard", result.name());
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();

        Board existing = new Board();
        existing.setId(id);
        existing.setName("Old");

        UpdateBoardDTO dto = new UpdateBoardDTO("New");

        Board saved = new Board();
        saved.setId(id);
        saved.setName("New");

        when(boardRepository.findById(id)).thenReturn(Optional.of(existing));
        when(boardRepository.save(existing)).thenReturn(saved);

        var result = boardService.update(id, dto);
        assertEquals("New", result.name());
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        Board existing = new Board();
        existing.setId(id);
        existing.setName("X");

        when(boardRepository.findById(id)).thenReturn(Optional.of(existing));
        doNothing().when(boardRepository).delete(existing);

        boardService.delete(id);

        verify(boardRepository).delete(existing);
    }

    @Test
    void testAddItem() {
        UUID boardId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();

        Board board = new Board();
        board.setId(boardId);
        board.setName("Test");

        Item item = new Item(itemId, "ItemA", null, null, null, null);

        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        when(boardRepository.save(any(Board.class))).thenReturn(board);

        var result = boardService.addItem(boardId, itemId);

        assertEquals("Test", result.name());
    }
}
