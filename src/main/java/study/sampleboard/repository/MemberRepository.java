package study.sampleboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.sampleboard.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
