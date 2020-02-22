package study.sampleboard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import study.sampleboard.entity.Category;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class CategoryDto {
    private String name;
    private Category parent;
    private List<CategoryDto> children = new ArrayList<>();

    public CategoryDto(String name, Category parent, List<CategoryDto> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }
}
