package inf.unideb.backend.controller;

import inf.unideb.backend.model.Board;
import inf.unideb.backend.model.Item;
import inf.unideb.backend.repository.BoardRepository;
import inf.unideb.backend.repository.ItemRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class BoardControllerImpl implements BoardController {

    private final BoardRepository boardRepository;
    private final ItemRepository itemRepository;

    public BoardControllerImpl(BoardRepository br,
                               ItemRepository ir) {
        this.boardRepository = br;
        this.itemRepository = ir;
    }

    @Override
    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    @Override
    public Board getOne(UUID id) {
        return boardRepository
                .findById(id)
                .orElseThrow();
    }

    @Override
    public Board create(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board update(UUID id, Board board) {
        Board existing = boardRepository
                .findById(id).orElseThrow();

        existing.setName(board.getName());
        existing.setUser(board.getUser());

        return boardRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Board addItem(UUID boardId, UUID itemId) {
        Board board = boardRepository
                .findById(boardId)
                .orElseThrow();
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow();

        board.getItems().add(item);
        return boardRepository
                .save(board);
    }
}
