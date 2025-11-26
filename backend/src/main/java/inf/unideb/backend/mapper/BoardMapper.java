package inf.unideb.backend.mapper;

import inf.unideb.backend.dto.BoardDTO;
import inf.unideb.backend.dto.CreateBoardDTO;
import inf.unideb.backend.dto.UpdateBoardDTO;
import inf.unideb.backend.model.Board;

public class BoardMapper {

    public static BoardDTO toDTO(Board board) {
        if (board == null) {
            return null;
        }

        return new BoardDTO(
                board.getName(),
                UserMapper.toDTO(board.getUser()),
                board.getItems()
                        .stream()
                        .map(ItemMapper::toDTO)
                        .toList()
        );
    }

    public static Board toEntity(CreateBoardDTO dto) {
        Board board = new Board();
        board.setName(dto.name());
        return board;
    }

    public static void updateEntity(Board board, UpdateBoardDTO dto) {
        if (dto.name() != null) {
            board.setName(dto.name());
        }
    }

}
