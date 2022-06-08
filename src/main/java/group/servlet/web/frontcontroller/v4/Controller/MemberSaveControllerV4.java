package group.servlet.web.frontcontroller.v4.Controller;

import group.servlet.domain.member.Member;
import group.servlet.repository.MemberRepository;
import group.servlet.web.frontcontroller.ModelView;
import group.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    // 싱글톤이므로 생성이 아니라 이렇게 꺼내주는 식으로 해야 함
    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        //사용자가 입력한 폼의 값들이 파라미터의 paramMap으로 들어옴
        String username=paramMap.get("username");
        int age= Integer.parseInt(paramMap.get("age"));

        Member member=new Member(username,age);//엔티티 생성
        memberRepository.save(member);

       model.put("member",member);

        return "save-result";
    }
}
