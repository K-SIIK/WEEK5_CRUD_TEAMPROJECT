package spring.week5_teamcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.week5_teamcrud.domain.Board;
import spring.week5_teamcrud.domain.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByBoard(Board board);
}

