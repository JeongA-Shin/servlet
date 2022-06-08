package group.servlet.web.frontcontroller.v4.Controller;

import group.servlet.domain.member.Member;
import group.servlet.repository.MemberRepository;
import group.servlet.web.frontcontroller.ModelView;
import group.servlet.web.frontcontroller.v3.ControllerV3;
import group.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    // 싱글톤이므로 생성이 아니라 이렇게 꺼내주는 식으로 해야 함
    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members=memberRepository.findAll();

        model.put("members",members);

        return "members";
    }
}
