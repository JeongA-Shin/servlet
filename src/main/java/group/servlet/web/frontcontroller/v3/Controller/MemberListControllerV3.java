package group.servlet.web.frontcontroller.v3.Controller;

import group.servlet.domain.member.Member;
import group.servlet.repository.MemberRepository;
import group.servlet.web.frontcontroller.ModelView;
import group.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    // 싱글톤이므로 생성이 아니라 이렇게 꺼내주는 식으로 해야 함
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
       List<Member> members=memberRepository.findAll();
       ModelView mv= new ModelView("members");
       mv.getModel().put("members",members);

       return mv;
    }
}
