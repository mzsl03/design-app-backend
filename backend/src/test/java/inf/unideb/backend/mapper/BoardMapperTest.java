package inf.unideb.backend.mapper;

import inf.unideb.backend.dto.*;
import inf.unideb.backend.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardMapperTest {

    @Test
    void testToDTO() {
        User user = new User();
        user.setUsername("john");

        Item item = new Item();
        item.setTitle("Item A");
        item.setUser(user);

        Board board = new Board();
        board.setName("MyBoard");
        board.setUser(user);
        board.setItems(List.of(item));

        BoardDTO dto = BoardMapper.toDTO(board);

        assertEquals("MyBoard", dto.name());
        assertEquals("john", dto.user().username());
        assertEquals("Item A", dto.items().get(0).title());
    }

    @Test
    void testToEntity() {
        CreateBoardDTO dto = new CreateBoardDTO("MyBoard");

        Board entity = BoardMapper.toEntity(dto);

        assertEquals("MyBoard", entity.getName());
        assertTrue(entity.getItems().isEmpty());
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
