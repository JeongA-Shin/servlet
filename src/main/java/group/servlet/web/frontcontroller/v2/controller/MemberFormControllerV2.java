package group.servlet.web.frontcontroller.v2.controller;

import group.servlet.web.frontcontroller.MyView;
import group.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //등록 폼에서는 별도의 로직이 필요 없음. 걍 리퀘스트만 받아서 save에 넘겨주면 되므로
        return new MyView("/WEB-INF/views/new-form.jsp"); //뷰 담당 객체를 리턴
    }
}
