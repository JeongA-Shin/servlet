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
import java.util.List;

@WebServlet(name="memberListServlet",urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {
    //싱글톤으로 되게 하였으므로 아래와 같이 new로는 생성할 수 없음
    // MemberRepository memberRepository=new MemberRepository();
    private MemberRepository memberRepository=MemberRepository.getInstance();
    // 서블릿이 mvc 패턴에서의 컨트롤러+서비스 단계를 모두 포괄한다고 생각하면 됨.
    //따라서 서비스는 리포지토리를 주입 받으므로 저렇게


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members=memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write(" <meta charset=\"UTF-8\">");
        w.write(" <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write(" <thead>");
        w.write(" <th>id</th>");
        w.write(" <th>username</th>");
        w.write(" <th>age</th>");
        w.write(" </thead>");
        w.write(" <tbody>");
/* 그냥 화면 확인용으로 정적으로
 w.write(" <tr>");
 w.write(" <td>1</td>");
 w.write(" <td>userA</td>");
 w.write(" <td>10</td>");
 w.write(" </tr>");
*/
        //동적으로 데이터 넣기
        for (Member member : members) {
            w.write(" <tr>");
            w.write(" <td>" + member.getId() + "</td>");
            w.write(" <td>" + member.getUsername() + "</td>");
            w.write(" <td>" + member.getAge() + "</td>");
            w.write(" </tr>");
        }
        w.write(" </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");

    }
}
