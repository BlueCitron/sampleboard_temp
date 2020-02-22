package study.sampleboard.entity;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class BaseEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

}
