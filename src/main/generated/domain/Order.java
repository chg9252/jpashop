package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {


    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY) // member 가 1   order가 many인 관계이다. @ManyToOne은 기본패치전략이 .EAGER 이다. 그래서 따로 lazy로 바꿔줘야 한다.
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간.

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //== 연관관계 편의 메서드 ==//

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderitem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDeliverery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
