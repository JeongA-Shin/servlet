package group.servlet.web.frontcontroller.v1.controller;

import group.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 각 컨트롤러마다 구현하도록 만들어 놓은 인터페이스임. 각 컨트롤러에서 구현함

public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //servletmvc에서 했던 로직 그대로임
        String viewPath="WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher=request.getRequestDispatcher(viewPath);//컨트롤러에서 뷰로 이동할 때 사용하는 것
        dispatcher.forward(request,response);// 서블릿에서 jsp 호출하기
        //말 그대로 그냥 컨트롤러에서 서비스 호출하듯이 해당 jsp 를 호출하는 것

    }
}
