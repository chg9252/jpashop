package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;


    // @GeneratedValue
    // 기본 키 생성을 데이터베이스에 위임
    // 즉, id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT 해준다.
    // https://gmlwjd9405.github.io/2019/08/12/primary-key-mapping.html
}
