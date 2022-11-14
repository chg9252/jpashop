package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name= "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL = 1,2,3,4,5... 숫자로 들어간다. 그래서 추후에 READY, COMP 말고 추가로 중간에 어떤 값을 넣어주게 되면 밀리기 때문에 오류 발생. 그렇기 때문에 꼭 .String으로 넣어줘야 한다.
    private DeliveryStatus status; // READY 배송 준비, COMP 배송


}
