package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional  // @Transactional은 클래스나 메서드에 붙여줄 경우, 해당 범위 내 메서드가 트랜잭션이 되도록 보장해준다. 단 테스트케이스 안에 선언해준다면 끝나도 자동적으로 rollback을 수행한다.
    public void testMember() throws Exception{
        //given   // 여기서 설정한 맴버를 가지고
        Member member = new Member();
        member.setUsername("memberA");

        // when  // 설정을 해주면
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then  검증
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        Assertions.assertThat(findMember).isEqualTo(member); // JPA 엔티티 동일성 보장
    }

}