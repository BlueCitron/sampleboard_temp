package study.sampleboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.sampleboard.entity.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
