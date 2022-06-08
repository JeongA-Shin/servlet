package group.servlet.web.frontcontroller.v1.controller;

import group.servlet.domain.member.Member;
import group.servlet.repository.MemberRepository;
import group.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    //싱글톤으로 되게 하였으므로 아래와 같이 new로는 생성할 수 없음
    // MemberRepository memberRepository=new MemberRepository();
    private MemberRepository memberRepository=MemberRepository.getInstance();
    // 서블릿이 mvc 패턴에서의 컨트롤러+서비스 단계를 모두 포괄한다고 생각하면 됨.
    //따라서 서비스는 리포지토리를 주입 받으므로 저렇게

    // servletmvc에서 했을 때와 로직이 동일함
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request로 온 데이터 파싱
        //현재 html form 을 통해 요청이 들어온 것

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        //System.out.println("member = " + member);
        memberRepository.save(member);
        //Model(여기서는 임시로 그냥 request)에 데이터를 보관한다.
        //보관할 모델이ㅣ 있어야 뷰에 그 모델을 넘겨줘서 데이터를 뷰로 넘겨줄 수 있다
        request.setAttribute("member", member);
        String viewPath = "/WEB-INF/views/save-result.jsp"; //jsp는 서블릿에서의 req, resp을 그대로 쓸 수 있음
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); //서블릿에서 jsp 호출하기
    }
}
