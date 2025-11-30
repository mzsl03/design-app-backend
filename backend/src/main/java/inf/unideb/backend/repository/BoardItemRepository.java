package inf.unideb.backend.repository;

import inf.unideb.backend.model.BoardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardItemRepository extends JpaRepository<BoardItem, UUID> {
}
