package group.servlet.web.servlet;

import group.servlet.domain.member.Member;
import group.servlet.repository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="memberSaveServlet",urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    //싱글톤으로 되게 하였으므로 아래와 같이 new로는 생성할 수 없음
    // MemberRepository memberRepository=new MemberRepository();
    private MemberRepository memberRepository=MemberRepository.getInstance();
    // 서블릿이 mvc 패턴에서의 컨트롤러+서비스 단계를 모두 포괄한다고 생각하면 됨.
    //따라서 서비스는 리포지토리를 주입 받으므로 저렇게

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //request로 온 데이터 파싱
        //현재 html form 을 통해 요청이 들어온 것이므로 노션에서 httpServletRequest에서 메소드별 요청에서 post:html 부분 참고
        String username = request.getParameter("username");
        int age=Integer.parseInt(request.getParameter("age"));


        //그리고 basic (이전 부분)에서도 다루었지만, 서버입장에서는 늘 객체 단위로, 객체로 매핑하여 다루어야함
        Member member=new Member(username,age);
        memberRepository.save(member); //저장

        //요청을 처리했으니, 이제 클라이언트에게 돌려줄 응답 생성
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w= response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");

    }
}
