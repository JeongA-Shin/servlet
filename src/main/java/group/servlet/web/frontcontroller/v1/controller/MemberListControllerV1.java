package group.servlet.web.frontcontroller.v1.controller;

import group.servlet.domain.member.Member;
import group.servlet.repository.MemberRepository;
import group.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {

    //싱글톤으로 되게 하였으므로 아래와 같이 new로는 생성할 수 없음
    // MemberRepository memberRepository=new MemberRepository();
    private MemberRepository memberRepository=MemberRepository.getInstance();
    // 서블릿이 mvc 패턴에서의 컨트롤러+서비스 단계를 모두 포괄한다고 생각하면 됨.
    //따라서 서비스는 리포지토리를 주입 받으므로 저렇게

    // serveltmvc에서의 로직과 똑같음
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("MvcMemberListServlet.service");
        List<Member> members = memberRepository.findAll();
        //Model(여기서는 임시로 그냥 request)에 데이터를 보관한다.
        //보관할 모델이ㅣ 있어야 뷰에 그 모델을 넘겨줘서 데이터를 뷰로 넘겨줄 수 있다
        request.setAttribute("members", members);
        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);//서블릿에서 jsp 호출하기

    }
}
