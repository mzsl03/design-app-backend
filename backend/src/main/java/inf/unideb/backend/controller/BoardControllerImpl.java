package inf.unideb.backend.controller;

import inf.unideb.backend.dto.BoardDTO;
import inf.unideb.backend.dto.CreateBoardDTO;
import inf.unideb.backend.dto.UpdateBoardDTO;
import inf.unideb.backend.service.BoardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class BoardControllerImpl implements BoardController {

    private final BoardService boardService;


    public BoardControllerImpl(BoardService br) {
        this.boardService = br;
    }

    @Override
    public List<BoardDTO> getAll() {
        return boardService.getAll();
    }

    @Override
    public BoardDTO getOne(UUID id) {
        return boardService.getOne(id);
    }

    @Override
    public BoardDTO create(CreateBoardDTO board) {
        return boardService.create(board);
    }

    @Override
    public BoardDTO update(UUID id, UpdateBoardDTO board) {
        return boardService.update(id, board);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(UUID id) {
        boardService.delete(id);
    }

    @Override
    public BoardDTO addItem(UUID boardId, UUID itemId) {
        return boardService.addItem(boardId, itemId);

    }
}
