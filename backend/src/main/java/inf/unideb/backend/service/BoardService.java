package inf.unideb.backend.service;

import inf.unideb.backend.model.Board;
import inf.unideb.backend.model.Item;
import inf.unideb.backend.repository.BoardRepository;
import inf.unideb.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoardService {


    private final BoardRepository boardRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public BoardService(BoardRepository br,
                        ItemRepository ir) {
        this.boardRepository = br;
        this.itemRepository = ir;
    }

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board getOne(UUID id) {
        return boardRepository
                .findById(id)
                .orElseThrow();
    }

    public Board create(Board board) {
        return boardRepository.save(board);
    }

    public Board update(UUID id, Board board) {
        Board existing = boardRepository
                .findById(id).orElseThrow();

        existing.setName(board.getName());
        existing.setUser(board.getUser());

        return boardRepository.save(existing);
    }

    public void delete(UUID id) {
        boardRepository.deleteById(id);
    }

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
