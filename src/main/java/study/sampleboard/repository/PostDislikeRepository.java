package study.sampleboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.sampleboard.entity.PostDislike;

public interface PostDislikeRepository extends JpaRepository<PostDislike, Long> {
}
