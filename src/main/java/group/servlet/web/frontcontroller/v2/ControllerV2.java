package group.servlet.web.frontcontroller.v2;

import group.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//모든 컨트롤러들이 공통적으로 가지고 있는, 즉 꼭 구현하는 로직을 인터페이스로
//ㄹㅇ 인터페이스의 의의
public interface ControllerV2 {
    //각 컨트롤러들은 뷰를 처리하는 MyView를 호출(혹은 리턴)한다
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
