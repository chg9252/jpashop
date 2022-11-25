package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
    * 주문
    */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //  엔티티 조회
        // member와 item의 ID로 각각 Repository 에서 조회.

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        // 배송정보는 Delivery 생성자 생성    member의 주소를 delivery Address에 세팅.
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());


        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문저장
        orderRepository.save(order);

        return order.getId();

    }

    // 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔터티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }
    //검색
    public List<Order> findOrders(OrderSearch ordersearch) {
        return orderRepository.findAllByString(ordersearch);
    }

}
