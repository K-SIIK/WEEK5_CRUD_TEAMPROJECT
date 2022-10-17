package spring.week5_teamcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.week5_teamcrud.domain.Heart;

public interface HeartRepository extends JpaRepository<Heart,Long> {
}
