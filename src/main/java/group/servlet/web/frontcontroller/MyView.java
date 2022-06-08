package group.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyView {

    private String viewPath;

    public MyView(String viewPath){
        this.viewPath=viewPath;
    }

    //jsp로 이동(포워드)하는 것을 렌더링한다고 표현할 것
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행
        RequestDispatcher requestDispatcher=request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request,response);
    }

}
