package study.sampleboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.sampleboard.dto.PostDto;
import study.sampleboard.entity.Category;
import study.sampleboard.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new study.sampleboard.dto.PostDto(p.id, p.title, p.content, p.viewCount, p.likeCount, p.dislikeCount, m.nickname, c.name) " +
            "from Post p join p.writer m join p.category c where p.category = :category")
    Page<PostDto> findAllAsDto(Category category, Pageable pageable);
}
