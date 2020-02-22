package study.sampleboard.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import study.sampleboard.dto.CategoryDto;
import study.sampleboard.repository.CategoryRepository;

import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
public class CommonController {

    private CategoryRepository categoryRepository;

    @ModelAttribute("categories")
    public List<CategoryDto> setCategory() {

        return null;
    }
}
