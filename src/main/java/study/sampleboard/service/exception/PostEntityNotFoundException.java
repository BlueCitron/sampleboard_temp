package study.sampleboard.service.exception;

import common.SystemException;

public class PostEntityNotFoundException extends SystemException {

    private Long id;

    public PostEntityNotFoundException(Long id) {
        super(String.format("포스트 엔티티를 찾을 수 없습니다. (id = %d)", id));
        this.id = id;
    }
}
