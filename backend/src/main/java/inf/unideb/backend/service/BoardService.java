package inf.unideb.backend.service;

import inf.unideb.backend.dto.BoardDTO;
import inf.unideb.backend.dto.CreateBoardDTO;
import inf.unideb.backend.dto.UpdateBoardDTO;
import inf.unideb.backend.mapper.BoardMapper;
import inf.unideb.backend.model.Board;
import inf.unideb.backend.model.BoardItem;
import inf.unideb.backend.model.Item;
import inf.unideb.backend.repository.BoardRepository;
import inf.unideb.backend.repository.ItemRepository;
import jakarta.transaction.Transactional;
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


    @Transactional
    public void delete(UUID id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        if (board.getBoardItems() != null) {
            board.getBoardItems().clear();
            boardRepository.save(board);
        }

        boardRepository.delete(board);
    }


    @Transactional
    public BoardDTO addItem(UUID boardId, UUID itemId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();

        BoardItem bi = BoardItem.builder()
                .board(board)
                .item(item)
                .build();

        board.getBoardItems().add(bi);

        boardRepository.save(board);
        return BoardMapper.toDTO(board);
    }

}
