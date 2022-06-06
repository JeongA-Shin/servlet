package group.servlet.web.servlet;

import group.servlet.repository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="memberFormServlet",urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    //싱글톤으로 되게 하였으므로 아래와 같이 new로는 생성할 수 없음
    // MemberRepository memberRepository=new MemberRepository();
    private MemberRepository memberRepository=MemberRepository.getInstance();
    // 서블릿이 mvc 패턴에서의 컨트롤러+서비스 단계를 모두 포괄한다고 생각하면 됨.
    //따라서 서비스는 리포지토리를 주입 받으므로 저렇게
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 서블릿의 응답으로 html 파일을 건네줘야 함
        // 이전까지 배운 내용 - 서블릿 응답 (노션의 httpServletResponse편 참고) 활용
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w=response.getWriter();
        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                " username: <input type=\"text\" name=\"username\" />\n" +
                " age: <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");

    }
}
