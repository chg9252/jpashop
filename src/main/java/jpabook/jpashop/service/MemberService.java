package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
// Transactional 안에서 데이터의 변경이나 등록이 일어나야 한다. 그래야 fetch = lazy 도 적용가능함
// 조회하기 같은 경우 (readOnly = true) 를 사용해 리소스를 덜 사용하게 만들어주는 것 이 좋다.


    private final MemberRepository memberRepository;


    /*
    private MemberRepository memberRepository;

    // setter injection
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;S
    } */

    /*

    private final MemberRepository memberRepository; // 생성자를 만들어 놓고 컴파일시점에 체크를 해줄수 있기 때문에 final을 넣어준다.

    // Constructor Injection
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public static void main(String[] args) {
        MemberService memberRepository = new MemberService();
    }
    */



    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 전체 회원찾기
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 한명찾기
    public Member findOne(Long memberID){
        return memberRepository.findOne(memberID);
    }

}
