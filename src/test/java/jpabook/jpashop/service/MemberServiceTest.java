package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit 실행할 때 스프링이랑 같이 실행하기
@SpringBootTest // 스프링 컨테이너 안에서 돌아가게 하는 기능.
@Transactional // 테스트 이후 롤백
public class MemberServiceTest {

    // DI
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    // insert가 되는걸 확인하고 싶으면 EntityManager em 생성하고 em.flush(); 로


    @Test
    // @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given memberSetting
        Member member = new Member();
        member.setName("xion");

        // when service에 초기화한 맴버 할당.
        Long saveId = memberService.join(member);

        //then assertEquals()
        assertEquals(member, memberRepository.findOne(saveId)); //
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원예외() throws Exception {
        // 의도적으로 예외상황을 만들기
        //given
        Member member1 = new Member();
        member1.setName("Reonh");

        Member member2 = new Member();
        member2.setName("Reonh");

        // when

        memberService.join(member1);
        memberService.join(member2);
        /*   @Test에 (expected = IllegalStateException.class)을 추가해서 생략.
        try {
            memberService.join(member2); // 예외가 발생하는 부분~!!!
        } catch (IllegalStateException e) {
            return;
        }*/

        //then
        fail("예외 발생 !!!예외 발생 !!!예외 발생 !!!예외 발생 !!!예외 발생 !!!");

    }

}