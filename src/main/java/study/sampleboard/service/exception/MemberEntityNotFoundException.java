package study.sampleboard.service.exception;

import common.SystemException;

public class MemberEntityNotFoundException extends SystemException {

    private String account;

    public MemberEntityNotFoundException(String account) {
        super(String.format("멤버를 찾을 수 없습니다. (account = %s)", account));
        this.account = account;
    }
}
