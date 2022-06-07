package group.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/* *
 각 컨트롤러마다 공통적인 부분(이면서 동시에 필수적인 부분들)을 인터페이스로 빼두고, 각 컨트롤러에서 상황에 맞게 로직을 구현함
 */

public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
