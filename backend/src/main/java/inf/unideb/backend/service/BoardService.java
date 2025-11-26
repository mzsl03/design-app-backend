package inf.unideb.backend.service;

import inf.unideb.backend.dto.BoardDTO;
import inf.unideb.backend.dto.CreateBoardDTO;
import inf.unideb.backend.dto.UpdateBoardDTO;
import inf.unideb.backend.mapper.BoardMapper;
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

    public List<BoardDTO> getAll() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardMapper::toDTO)
                .toList();
    }

    public BoardDTO getOne(UUID id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return BoardMapper.toDTO(board);
    }

    public BoardDTO create(CreateBoardDTO board) {
        Board entity = BoardMapper.toEntity(board);
        Board saved = boardRepository.save(entity);
        return BoardMapper.toDTO(saved);
    }

    public BoardDTO update(UUID id, UpdateBoardDTO board) {
        Board existing = boardRepository.findById(id).orElseThrow();
        BoardMapper.updateEntity(existing, board);
        Board saved = boardRepository.save(existing);
        return BoardMapper.toDTO(saved);
    }

    public void delete(UUID id) {
        boardRepository.deleteById(id);
    }

    public BoardDTO addItem(UUID boardId, UUID itemId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();

        board.getItems().add(item);
        Board saved = boardRepository.save(board);

        return BoardMapper.toDTO(saved);
    }

}
