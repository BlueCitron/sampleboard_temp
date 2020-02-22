package study.sampleboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
public class Member extends BaseEntity {
    private String account;
    private String password;
    private String email;
    private String nickname;

    public Member(String account, String password, String email, String nickname) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }
}
