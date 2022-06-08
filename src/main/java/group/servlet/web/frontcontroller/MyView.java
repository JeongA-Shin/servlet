package group.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //jsp는 HttpServletRequest의 request를 씀
        //따라서 건네 받은 모델을 request 에 set 해줘야 함!!!!!
        modelToRequestAttribute(model,request);

        //그리고 나서는 그냥 원래 하던대로 forward를 통해 jsp로 넘어감
        RequestDispatcher dispatcher=request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    private void modelToRequestAttribute(Map<String, Object> model,
                                         HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
