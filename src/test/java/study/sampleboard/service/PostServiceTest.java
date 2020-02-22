package study.sampleboard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import study.sampleboard.dto.PageResponse;
import study.sampleboard.dto.PostDto;
import study.sampleboard.entity.Category;
import study.sampleboard.entity.Member;
import study.sampleboard.entity.Post;
import study.sampleboard.repository.PostRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    EntityManager em;

    @BeforeEach
    void 초기화() {
        Member member = new Member("admin", "admin", "admin@admin.com", "BlueCitron");
        Category category = new Category("샘플_카테고리");
        em.persist(member);
        em.persist(category);

        for (int i = 1; i < 176; i++) {
            Post post = new Post("샘플_포스트_" + i, "내용_" + i, member, category);
            em.persist(post);
        }

        em.flush();
    }

    @Test
    void 조회() {
        PostDto postDto = new PostDto();
        postDto.setCategory("샘플_카테고리");
        PageRequest request = PageRequest.of(0, 20);

        PageResponse<PostDto> posts = postService.getPosts(postDto, request);
    }
}