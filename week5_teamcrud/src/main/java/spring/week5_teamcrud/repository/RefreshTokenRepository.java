package spring.week5_teamcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.week5_teamcrud.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByuserName(String userName);
}
