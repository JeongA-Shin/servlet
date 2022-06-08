package group.servlet.web.frontcontroller.v2.controller;

import group.servlet.domain.member.Member;
import group.servlet.repository.MemberRepository;
import group.servlet.web.frontcontroller.MyView;
import group.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    // 싱글톤이므로 이렇게 꺼내줌
    private MemberRepository memberRepository = MemberRepository.getInstance();

    
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);

        //뷰 담당 객체를 리턴
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
