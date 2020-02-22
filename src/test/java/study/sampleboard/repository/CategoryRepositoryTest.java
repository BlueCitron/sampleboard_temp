package study.sampleboard.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.sampleboard.dto.CategoryDto;
import study.sampleboard.entity.Category;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void 초기화() {
        Category category = new Category("샘플_카테고리");
        Category child = new Category("자식_카테고리");
        category.addChild(child);
        em.persist(category);
    }

    @Test
    void DTO_조회() {
         List<CategoryDto> allAsDto = categoryRepository.findAllAsDto();
         CategoryDto categoryDto = allAsDto.get(0);

         Assertions.assertEquals(allAsDto.size(), 2);
    }
}