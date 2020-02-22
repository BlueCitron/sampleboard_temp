package study.sampleboard.service.exception;

import common.SystemException;

public class CategoryEntityNotFoundException extends SystemException {

    private String name;

    public CategoryEntityNotFoundException(String name) {
        super(String.format("카테고리를 찾을 수 없습니다. (name = %s)", name));
        this.name = name;
    }
}
