package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    // 저장
    public void save(Order order){
        em.persist(order);
    }

    // 단건조회
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }
/*

    public List<Order> findAll(OrderSearch orderSearch){

        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        // 주문 상태 검색
        if(orderSearch.getOrderStatus() != null){
            if(isFirstCondition){
                jpql += "where";
                isFirstCondition = false;
            }else {
                jpql += "and";
            }
            jpql += "o.status = :status";
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if(isFirstCondition){
                jpql += "where";
                isFirstCondition = false;
            }else{
                jpql += "and";
            }
            jpql += "m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(1000);

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if(StringUtils.hasText(orderSearch.getMemberName())){
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();

        */
/* 정리
        jpql을 문자로 생성하는것은 이처럼 매우 번거롭고 오류를 찾기에도 힘들다.
        mybatis 는 검색하기 등 동적쿼리 생성에 편함.
        *//*


    }

*/

}
