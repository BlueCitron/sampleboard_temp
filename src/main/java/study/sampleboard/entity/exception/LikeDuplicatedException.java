package study.sampleboard.entity.exception;

import common.SystemException;

public class LikeDuplicatedException extends SystemException {

    private String memberName;
    private String postName;

    public LikeDuplicatedException(String memberName, String postName) {
        super(String.format("이미 좋아요 표시한 게시물입니다.(memberName=%s, postName=%s)", memberName, postName));
        this.memberName = memberName;
        this.postName = postName;
    }
}
