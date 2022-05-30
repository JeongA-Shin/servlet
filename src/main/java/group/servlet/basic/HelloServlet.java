package group.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet",urlPatterns="/hello")
public class HelloServlet extends HttpServlet {

    //서블릿이 호출되면 이 service 메서드가 호출됨
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("req: "+req);
        System.out.println("res: "+resp);

        //http 요청을 서블릿이 req 객체로 만들고 그걸 내가 알아서 처리함
        String username= req.getParameter("username");
        System.out.println("username: "+username);
        //이런 식으로 파싱된 데이터를 내가 알아서 비즈니스 로직을 짜는 거임

        //이제 응답을 보내보자
        resp.setContentType("text/plain"); //걍 단순 문자로 보낼거임 //content type은 헤더 정보로 들어감
        resp.setCharacterEncoding("utf-8"); //헤더의 정보로 들어감
        //이제 본격적으로 resonse로 보낼 http 객체의 body를 채우자
        resp.getWriter().write("hello"+username);

    }
}
