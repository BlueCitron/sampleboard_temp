package study.sampleboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.sampleboard.entity.exception.DislikeDuplicatedException;
import study.sampleboard.entity.exception.LikeDuplicatedException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "post_id"))
public class Post extends BaseEntity {

    private String title;
    private String content;
    private Integer viewCount;
    private Integer likeCount;
    private Integer dislikeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PostDislike> postDislikes = new ArrayList<>();


    public Post(String title, String content, Member writer, Category category) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;

        this.likeCount = 0;
        this.dislikeCount = 0;
    }

    public void like(PostLike postLike) throws LikeDuplicatedException {
        boolean contains = this.postLikes.stream().anyMatch(el -> el.getMember() == postLike.getMember() && el.getPost() == postLike.getPost());
        if (contains) {
            throw new LikeDuplicatedException(postLike.getMember().getNickname(), this.title);
        }
        this.postLikes.add(postLike);
        this.likeCount += 1;
    }

    public void cancelLike(PostLike postLike) {
        this.postLikes.remove(postLike);
        this.likeCount -= 1;
    }

    public void dislike(PostDislike postDislike) {
        boolean contains = this.postDislikes.stream().anyMatch(el -> el.getMember() == postDislike.getMember() && el.getPost() == postDislike.getPost());
        if (contains) {
            throw new DislikeDuplicatedException(postDislike.getMember().getNickname(), this.title);
        }
        this.postDislikes.add(postDislike);
        this.dislikeCount += 1;
    }

    public void cancelDislike(PostDislike postDislike) {
        this.postDislikes.remove(postDislike);
        this.dislikeCount -= 1;
    }
}
