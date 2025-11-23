package inf.unideb.backend.controller;

import inf.unideb.backend.model.Board;
import inf.unideb.backend.service.BoardService;
import inf.unideb.backend.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class BoardControllerImpl implements BoardController {

    private final BoardService boardService;
    private final ItemService itemService;

    public BoardControllerImpl(BoardService br,
                               ItemService ir) {
        this.boardService = br;
        this.itemService = ir;
    }

    @Override
    public List<Board> getAll() {
        return boardService.getAll();
    }

    @Override
    public Board getOne(UUID id) {
        return boardService.getOne(id);
    }

    @Override
    public Board create(Board board) {
        return boardService.create(board);
    }

    @Override
    public Board update(UUID id, Board board) {
        return boardService.update(id, board);
    }

    @Override
    public void delete(UUID id) {
        boardService.delete(id);
    }

    @Override
    public Board addItem(UUID boardId, UUID itemId) {
        return boardService.addItem(boardId, itemId);

    }
}
