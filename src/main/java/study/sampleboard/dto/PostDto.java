package study.sampleboard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Integer viewCount;
    private Integer likeCount;
    private Integer dislikeCount;
    private String writer;
    private String category;

    public PostDto(Long id, String title, String content, Integer viewCount, Integer likeCount, Integer dislikeCount, String writer, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.writer = writer;
        this.category = category;
    }
}
