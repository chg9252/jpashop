package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // 스프링이 제공하는 기본 어노테이션. ComponentScan 의 대상이 돼서 자동으로 스프링빈에 등록된다. DTO와 비슷한 개념.
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
        // 커멘드와 쿼리를 분리해라.  라는 jpa원칙에 따라 member을 return하지 않는다.   CQS(Command-Query Separation) 
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }


}
