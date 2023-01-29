package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


// note Transactional 설정을 안해주면 테스트에서 넣은 데이터가 남게됨
//  AfterEach, BeforeEach 와 같은걸로 또 설정해줘야함..
//  하지만 Transactional을 이용하면 검증하고 롤백해줘서 테스트이후 원상복구함
//  이런식으로 테스트를 반복적으로 처리가능하게 해줌
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // 스프링부트에서 가져와야하는 객체들
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    // 여기서 Test전용 DB를 따로 만드므로, 테스트에서 생성한값은 걱정안해도 됨
    // 단 이미 생성되어있는 값이면 중복으로 에러가 나옴옴
   @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        // ctrl + alt + v
        Member findMember = memberService.findOne(saveId).get();
        // alt + enter
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when (예외가 터져야한다 , 이미 등록된 회원이기 때문 )
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}