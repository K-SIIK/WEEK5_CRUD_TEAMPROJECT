package spring.week5_teamcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.week5_teamcrud.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByuserName(String userName);
}
