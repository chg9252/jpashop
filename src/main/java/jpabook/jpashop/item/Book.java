package jpabook.jpashop.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("B") // 초기화 해주지 않고 그냥 두면 클래스 이름이 들어간다.
public class Book extends Item{

    private String author;
    private String isbn;
}
