package group.servlet.repository;

import group.servlet.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest {
    //싱글톤으로 되게 하였으므로 아래와 같이 new로는 생성할 수 없음
   // MemberRepository memberRepository=new MemberRepository();

    MemberRepository memberRepository=MemberRepository.getInstance();
    //사실 STATIC을 쓰면 SINGLETON을 쓸 필요 없음. 그 자체가 하나만 만들어지는 것을 보장하므로

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void save(){
        //given
        Member member=new Member("hello",20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember=memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);

    }


    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

}
