package inf.unideb.backend.service;

import inf.unideb.backend.dto.*;
import inf.unideb.backend.mapper.BoardMapper;
import inf.unideb.backend.model.*;
import inf.unideb.backend.repository.BoardRepository;
import inf.unideb.backend.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    private BoardRepository boardRepository;
    private ItemRepository itemRepository;
    private BoardService service;

    @BeforeEach
    void setUp() {
        boardRepository = mock(BoardRepository.class);
        itemRepository = mock(ItemRepository.class);
        service = new BoardService(boardRepository, itemRepository);
    }

    @Test
    void testGetAll() {
        Board b1 = new Board(UUID.randomUUID(), "A", null, new ArrayList<>());
        Board b2 = new Board(UUID.randomUUID(), "B", null, new ArrayList<>());

        when(boardRepository.findAll()).thenReturn(List.of(b1, b2));

        var result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).name());
        verify(boardRepository).findAll();
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();
        Board board = new Board(id, "Board1", null, new ArrayList<>());

        when(boardRepository.findById(id)).thenReturn(Optional.of(board));

        var result = service.getOne(id);

        assertEquals("Board1", result.name());
        verify(boardRepository).findById(id);
    }

    @Test
    void testCreate() {
        CreateBoardDTO dto = new CreateBoardDTO("BoardX");
        Board saved = new Board(UUID.randomUUID(), "BoardX", null, new ArrayList<>());

        when(boardRepository.save(any(Board.class))).thenReturn(saved);

        var result = service.create(dto);

        assertEquals("BoardX", result.name());
        verify(boardRepository).save(any(Board.class));
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();
        Board existing = new Board(id, "Old", null, new ArrayList<>());

        UpdateBoardDTO dto = new UpdateBoardDTO("New");
        Board saved = new Board(id, "New", null, new ArrayList<>());

        when(boardRepository.findById(id)).thenReturn(Optional.of(existing));
        when(boardRepository.save(existing)).thenReturn(saved);

        var result = service.update(id, dto);

        assertEquals("New", result.name());
        verify(boardRepository).save(existing);
    }

    @Test
    void testAddItem() {
        UUID boardId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();

        Board board = new Board(boardId, "MyBoard", null, new ArrayList<>());
        Item item = new Item(itemId, "ItemA", "i", "d", "t", null);

        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        when(boardRepository.save(board)).thenReturn(board);

        var result = service.addItem(boardId, itemId);

        assertEquals(1, result.items().size());
        assertEquals("ItemA", result.items().get(0).title());
        verify(boardRepository).save(board);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(boardRepository).deleteById(id);

        service.delete(id);

        verify(boardRepository).deleteById(id);
    }
}
