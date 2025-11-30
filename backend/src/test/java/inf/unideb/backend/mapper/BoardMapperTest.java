package inf.unideb.backend.mapper;

import inf.unideb.backend.dto.*;
import inf.unideb.backend.model.Board;
import inf.unideb.backend.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardMapperTest {

    @Test
    void testToDTO() {
        User user = new User();
        user.setUsername("john");

        Board board = new Board();
        board.setName("MyBoard");
        board.setUser(user);

        BoardDTO dto = BoardMapper.toDTO(board);

        assertEquals("MyBoard", dto.name());
        assertEquals("john", dto.user().username());
    }

    @Test
    void testToEntity() {
        CreateBoardDTO dto = new CreateBoardDTO("MyBoard");

        Board entity = BoardMapper.toEntity(dto);

        assertEquals("MyBoard", entity.getName());
    }

    @Test
    void testUpdateEntity() {
        Board board = new Board();
        board.setName("OldName");

        UpdateBoardDTO dto = new UpdateBoardDTO("NewName");

        BoardMapper.updateEntity(board, dto);

        assertEquals("NewName", board.getName());
    }
}
