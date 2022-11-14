package jpabook.jpashop.domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name= "member_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;

    // mappedBy  기본적으로 외래키가 있는곳에 설정해줘야 성능 및 이슈가 발생할 확률이 줄어든다.
    @OneToMany(mappedBy = "member")  // order테이블에 있는 member에 의해 변경되는 것뿐이다. 읽기전용 같은 느낌.
    private List<Order> orders = new ArrayList<>();




}
