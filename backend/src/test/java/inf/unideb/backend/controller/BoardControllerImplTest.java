package inf.unideb.backend.controller;

import inf.unideb.backend.dto.BoardDTO;
import inf.unideb.backend.dto.CreateBoardDTO;
import inf.unideb.backend.dto.UpdateBoardDTO;
import inf.unideb.backend.dto.UserDTO;
import inf.unideb.backend.dto.ItemDTO;
import inf.unideb.backend.service.BoardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BoardControllerImplTest {

    private BoardService boardService;
    private BoardControllerImpl controller;

    @BeforeEach
    void setUp() {
        boardService = mock(BoardService.class);
        controller = new BoardControllerImpl(boardService);
    }

    @Test
    void testGetAll() {
        List<BoardDTO> expected = List.of(
                new BoardDTO(
                        UUID.randomUUID(),
                        "Board1",
                        new UserDTO(UUID.randomUUID(), "john"),
                        List.of()
                )
        );

        when(boardService.getAll()).thenReturn(expected);

        List<BoardDTO> result = controller.getAll();

        verify(boardService).getAll();
        assertEquals(expected, result);
    }

    @Test
    void testGetOne() {
        UUID id = UUID.randomUUID();

        BoardDTO expected = new BoardDTO(
                id,
                "Board1",
                new UserDTO(UUID.randomUUID(), "john"),
                List.of()
        );

        when(boardService.getOne(id)).thenReturn(expected);

        BoardDTO result = controller.getOne(id);

        verify(boardService).getOne(id);
        assertEquals(expected, result);
    }

    @Test
    void testCreate() {
        CreateBoardDTO dto = new CreateBoardDTO("NewBoard");

        BoardDTO expected = new BoardDTO(
                UUID.randomUUID(),
                "NewBoard",
                new UserDTO(UUID.randomUUID(), "john"),
                List.of()
        );

        when(boardService.create(dto)).thenReturn(expected);

        BoardDTO result = controller.create(dto);

        verify(boardService).create(dto);
        assertEquals(expected, result);
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();
        UpdateBoardDTO dto = new UpdateBoardDTO("UpdatedBoard");

        BoardDTO expected = new BoardDTO(
                id,
                "UpdatedBoard",
                new UserDTO(UUID.randomUUID(), "john"),
                List.of()
        );

        when(boardService.update(id, dto)).thenReturn(expected);

        BoardDTO result = controller.update(id, dto);

        verify(boardService).update(id, dto);
        assertEquals(expected, result);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        controller.delete(id);

        verify(boardService).delete(id);
    }

    @Test
    void testAddItem() {
        UUID boardId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();

        BoardDTO expected = new BoardDTO(
                boardId,
                "BoardX",
                new UserDTO(UUID.randomUUID(), "john"),
                List.of(new ItemDTO(itemId, "ItemA", "desc", null, null, new UserDTO(UUID.randomUUID(), "john")))
        );

        when(boardService.addItem(boardId, itemId)).thenReturn(expected);

        BoardDTO result = controller.addItem(boardId, itemId);

        verify(boardService).addItem(boardId, itemId);
        assertEquals(expected, result);
    }
}
