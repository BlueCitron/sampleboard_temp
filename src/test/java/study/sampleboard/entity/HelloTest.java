package study.sampleboard.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@SpringBootTest
class HelloTest {

    @Autowired
    EntityManager em;

    @Test
    void 엔티티_생성() {
        Hello hello = new Hello();
        em.persist(hello);

        Hello findHello = em.createQuery("select h from Hello h", Hello.class)
                .getSingleResult();

        Assertions.assertEquals(hello, findHello);
        Assertions.assertNotNull(hello.getId());
        Assertions.assertNotNull(hello.getCreatedAt());
        Assertions.assertNotNull(hello.getUpdatedAt());
    }
}