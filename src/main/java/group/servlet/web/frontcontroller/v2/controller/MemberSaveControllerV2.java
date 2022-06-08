package group.servlet.web.frontcontroller.v2.controller;

import group.servlet.domain.member.Member;
import group.servlet.repository.MemberRepository;
import group.servlet.web.frontcontroller.MyView;
import group.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    //싱글톤으로 되게 하였으므로 아래와 같이 new로는 생성할 수 없음
    // MemberRepository memberRepository=new MemberRepository();
    private MemberRepository memberRepository=MemberRepository.getInstance();
    // 서블릿이 mvc 패턴에서의 컨트롤러+서비스 단계를 모두 포괄한다고 생각하면 됨.
    //따라서 서비스는 리포지토리를 주입 받으므로 저렇게


    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request로 온 데이터 파싱
        //현재 html form 을 통해 요청이 들어온 것

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        request.setAttribute("member", member);

        //뷰 담당 객체를 리턴
        return new MyView("/WEB-INF/views/save-result.jsp"); //jsp에 request,response는 자동으로 전달됨
    }
}
