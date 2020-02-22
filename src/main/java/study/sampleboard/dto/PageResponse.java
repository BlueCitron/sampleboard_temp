package study.sampleboard.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class PageResponse <T> {

    List<T> content;

    private Integer page;
    private Integer size;
    private String sort;

    private Integer totalPage;
    private Integer blockStartNum;
    private Integer blockFinishNum;
    private Boolean isFirst;
    private Boolean isLast;

    public PageResponse(Page<T> page) {
        this.content = page.getContent();

        this.page = page.getNumber() + 1;
        this.size = page.getSize();
        this.sort = page.getSort().toString();
        this.totalPage = page.getTotalPages();
//        this.blockStartNum = 0;
//        this.blockFinishNum = 0;
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
        setBlockNum();
    }

    private void setBlockNum() {
        if (this.page % 10 == 0 && this.page > 1) {
            this.blockStartNum = (this.page / 10 - 1) * 10 + 1;
        } else if (this.page % 10 != 0) {
            this.blockStartNum = (this.page / 10) * 10 + 1;
        }

        // 12 / 30, 24 / 30
        // 14 / 27, 24 / 27
        // 1 / 7, 1 / 10
        // 1 / 0
        if (this.totalPage == 0) {
            this.blockFinishNum = 1;
        } else if (this.blockStartNum / 10 == this.totalPage / 10) {
            this.blockFinishNum = this.totalPage;
        } else {
            this.blockFinishNum = (this.blockStartNum / 10 + 1) * 10;
        }
    }

}
