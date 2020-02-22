package study.sampleboard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "hello_id"))
public class Hello extends BaseEntity {


}
