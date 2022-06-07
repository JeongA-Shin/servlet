package group.servlet.web.frontcontroller.v1;

import group.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import group.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import group.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// !! 프론트 컨트롤러는 서블릿이어야 한다!!
@WebServlet(name="frontControllerServletV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    //매핑되는 URL에 따라 어떤 컨트롤러를 호출할지 알아야 하므로
    //그 URL과 컨트롤러 매핑 정보를 MAP에 저장해놓음
   private Map<String,ControllerV1> controllerMap=new HashMap<>();

    public FrontControllerServletV1() {
        //생성될 때 controllerMap에 정보를 넣어놓자
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println("front Controller success");
    }
}
