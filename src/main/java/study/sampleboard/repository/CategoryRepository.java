package study.sampleboard.repository;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.sampleboard.dto.CategoryDto;
import study.sampleboard.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    default List<CategoryDto> findAllAsDto() {
        ModelMapper modelMapper = new ModelMapper();
        List<CategoryDto> collect = findAll().stream()
                .map(el -> modelMapper.map(el, CategoryDto.class))
                .collect(Collectors.toList());
        return collect;
    };

}
