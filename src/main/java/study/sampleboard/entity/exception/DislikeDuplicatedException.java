package study.sampleboard.entity.exception;

import common.SystemException;

public class DislikeDuplicatedException extends SystemException {

    private String memberName;
    private String postName;

    public DislikeDuplicatedException(String memberName, String postName) {
        super(String.format("이미 싫어요 표시한 게시물입니다.(memberName=%s, postName=%s)", memberName, postName));
        this.memberName = memberName;
        this.postName = postName;
    }
}
