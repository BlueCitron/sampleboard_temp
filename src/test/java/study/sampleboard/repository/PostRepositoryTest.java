package study.sampleboard.repository;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import study.sampleboard.dto.PostDto;
import study.sampleboard.entity.Category;
import study.sampleboard.entity.Member;
import study.sampleboard.entity.Post;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void 초기화() {
        Member member = new Member("admin", "admin", "admin@admin.com", "BlueCitron");
        Category category = new Category("샘플_카테고리");
        Post post_1 = new Post("샘플_포스트_1", "내용_1", member, category);
        Post post_2 = new Post("샘플_포스트_2", "내용_2", member, category);
        Post post_3 = new Post("샘플_포스트_3", "내용_3", member, category);

        em.persist(member);
        em.persist(category);
        em.persist(post_1);
        em.persist(post_2);
        em.persist(post_3);
        em.flush();
    }

    @Test
    void 포스트_조회() {
        // given
        Category category = findAllCategory().get(0);
        PageRequest request = PageRequest.of(0, 10);

        // when & then
        Page<PostDto> findByCategory = postRepository.findAllAsDto(category, request);
        List<PostDto> posts = findByCategory.getContent();
        PostDto postDto = posts.get(0);
        Assertions.assertEquals(posts.size(), 3);
        Assertions.assertEquals(postDto.getWriter(), "BlueCitron");
        Assertions.assertEquals(postDto.getCategory(), "샘플_카테고리");
    }

    private List<Member> findAllMember() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    private List<Category> findAllCategory() {
        return em.createQuery("select c from Category c", Category.class).getResultList();
    }

    private List<Post> findAllPost() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

}