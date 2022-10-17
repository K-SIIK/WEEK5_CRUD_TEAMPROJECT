package spring.week5_teamcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.week5_teamcrud.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByModifiedAtDesc();

}
