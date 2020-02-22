package study.sampleboard.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.sampleboard.entity.exception.DislikeDuplicatedException;
import study.sampleboard.entity.exception.LikeDuplicatedException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    void 초기화() {
        Member member = new Member("admin", "admin", "admin@admin.com", "BlueCitron");
        Category category = new Category("샘플_카테고리");
        em.persist(member);
        em.persist(category);
    }

    @Test
    void 포스트_생성() {
        Member member = findAllMember().get(0);
        Category category = findAllCategory().get(0);
        Post post = new Post("샘플_포스트", "내용", member, category);

        // when
        em.persist(post);
        em.flush();

        // then
        Post findPost = findAllPost().get(0);

        Assertions.assertEquals(post, findPost);
    }

    @Test
    void 포스트_좋아요() {
        Member member = findAllMember().get(0);
        Category category = findAllCategory().get(0);
        Post post = new Post("샘플_포스트", "내용", member, category);
        em.persist(post);

        // when
        PostLike postLike = new PostLike(member, post);
        em.flush();

        // then
        Integer likeCount = post.getLikeCount();
        PostLike findPostlike = post.getPostLikes().get(0);
        PostLike fromEm = em.createQuery("select pl from PostLike pl", PostLike.class).getSingleResult();

        Assertions.assertEquals(likeCount, 1);
        Assertions.assertEquals(postLike, findPostlike);
        Assertions.assertEquals(postLike, fromEm);
    }

    @Test
    void 포스트_좋아요_중복() {
        Member member = findAllMember().get(0);
        Category category = findAllCategory().get(0);
        Post post = new Post("샘플_포스트", "내용", member, category);
        em.persist(post);
        PostLike postLike = new PostLike(member, post);
        em.flush();

        // when & then
        Assertions.assertThrows(LikeDuplicatedException.class, () -> {
            PostLike duplicatedPostLike = new PostLike(member, post);
        });
    }

    @Test
    void 포스트_좋아요_취소() {
        Member member = findAllMember().get(0);
        Category category = findAllCategory().get(0);
        Post post = new Post("샘플_포스트", "내용", member, category);
        PostLike postLike = new PostLike(member, post);
        em.persist(post);
        em.flush();

        // when
        post.cancelLike(postLike);
        em.remove(postLike);
        em.flush();

        // then
        Assertions.assertEquals(post.getLikeCount(),  0);
        Assertions.assertEquals(post.getPostLikes().size(), 0);
        Assertions.assertThrows(NoResultException.class, () -> {
            PostLike findPostlike = em.createQuery("select pl from PostLike pl", PostLike.class).getSingleResult();
        });
    }

    @Test
    void 포스트_싫어요() {
        Member member = findAllMember().get(0);
        Category category = findAllCategory().get(0);
        Post post = new Post("샘플_포스트", "내용", member, category);
        em.persist(post);

        // when
        PostDislike postDislike = new PostDislike(member, post);
        em.flush();

        // then
        Integer likeCount = post.getLikeCount();
        PostDislike findPostlike = post.getPostDislikes().get(0);
        PostDislike fromEm = em.createQuery("select pd from PostDislike pd", PostDislike.class).getSingleResult();

        Assertions.assertEquals(post.getDislikeCount(), 1);
        Assertions.assertEquals(postDislike, findPostlike);
        Assertions.assertEquals(postDislike, fromEm);
    }

    @Test
    void 포스트_싫어요_중복() {
        Member member = findAllMember().get(0);
        Category category = findAllCategory().get(0);
        Post post = new Post("샘플_포스트", "내용", member, category);
        em.persist(post);
        PostDislike postDislike = new PostDislike(member, post);
        em.flush();

        // when & then
        Assertions.assertThrows(DislikeDuplicatedException.class, () -> {
            PostDislike duplicatedPostDislike = new PostDislike(member, post);
        });
    }

    @Test
    void 포스트_싫어요_취소() {
        Member member = findAllMember().get(0);
        Category category = findAllCategory().get(0);
        Post post = new Post("샘플_포스트", "내용", member, category);
        PostDislike postDislike = new PostDislike(member, post);
        em.persist(post);
        em.flush();

        // when
        post.cancelDislike(postDislike);
        em.remove(postDislike);
        em.flush();

        // then
        Assertions.assertEquals(post.getDislikeCount(),  0);
        Assertions.assertEquals(post.getPostDislikes().size(), 0);
        Assertions.assertThrows(NoResultException.class, () -> {
            PostDislike fromEm = em.createQuery("select pd from PostDislike pd", PostDislike.class).getSingleResult();
        });
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