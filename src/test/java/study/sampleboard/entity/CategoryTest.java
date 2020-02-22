package study.sampleboard.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CategoryTest {

    @Autowired
    EntityManager em;

    @Test
    void 카테고리_생성() {
        // given
        Category category = new Category("테스트_카테고리");

        // when
        em.persist(category);
        em.flush();

        // then
        Category findCategory = em.createQuery("select c from Category c", Category.class)
                .getSingleResult();

        Assertions.assertEquals(category, findCategory);
    }

    @Test
    void 카테고리_부모_자식_관계설정_생성() {
        // given
        Category category1 = new Category("테스트_카테고리_1");
        Category category2 = new Category("테스트_카테고리_2");
        Category category3 = new Category("테스트_카테고리_3");

        // when
        category1.addChild(category2);
        category1.addChild(category3);
        em.persist(category1);
        em.flush();

        // then
        List<Category> categories = em.createQuery("select c from Category c", Category.class)
                .getResultList();

        Assertions.assertEquals(categories.size(), 3);
    }

    @Test
    void 카테고리_부모_자식_관계설정_삭제() {
        // given
        Category category1 = new Category("테스트_카테고리_1");
        Category category2 = new Category("테스트_카테고리_2");
        Category category3 = new Category("테스트_카테고리_3");

        category1.addChild(category2);
        category1.addChild(category3);
        em.persist(category1);
        em.flush();

        // when
        em.remove(category1);

        // then
        List<Category> categories = em.createQuery("select c from Category c", Category.class)
                .getResultList();

        Assertions.assertEquals(categories.size(), 0);
    }

}