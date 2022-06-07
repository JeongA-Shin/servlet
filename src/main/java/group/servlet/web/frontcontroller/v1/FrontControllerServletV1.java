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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //System.out.println("front Controller success");
        //이제 프론트 컨트롤러의 로직 시작
        //V1에서는 MAP을 통해서 매핑된 URL이 어떤 컨트롤러에 해당되는지를 찾고 호출함
        String requestURI = request.getRequestURI();  //URL에서 서버 이름 뒷부분의 /부터 시작됨.
        //즉 http://localhost:8080/front-controller/v1/hello으로 클라이언트가 요청했다면
        // requestURI에는 /front-controller/v1/hello 만 담김

        //다형성!!
        //인터페이스가 자료형인 변수에는 해당 인터페이스를 구현한 클래스의 객체가 들어갈 수 있음!!
        //다형성의 의의 - 하나의 자료형 안에 여러 종류의 객체가 들어갈 수 있다
        //이렇게 인터페이스로 객체들을 꺼내게 되면 굉장히 일관성 있게 사용할 수 있음
        //인스턴스 objI2의 데이터 타입을 I2로 한다는 것은 인스턴스를 외부에서 제어할 수 있는 조작 장치를 인스턴스 I2의 맴버로 제한한다는 의미가 되기 때문
        //즉 아래에서도 이제 나오지만 controller.메서드 이렇게 하고 싶을 때 호출할 수 있는 내장 메서드는 인터페이스에서 정의된 proceess밖에 없음
        //왜냐면 다형성으로 인터페이스로 한계를 지었기 때문
        ControllerV1 controller=controllerMap.get(requestURI);

        //예외 처리: ex. 만약 해당 url에 대한 컨트롤러가 없을 때 - 예상하지 못한 url일 때
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //응답으로 404 반환해주기
            return;//바로 리턴
        }

        // 인스턴스 objI2의 데이터 타입을 I2로 한다는 것은 인스턴스를 외부에서 제어할 수 있는 조작 장치를 인스턴스 I2의 맴버로 제한한다는 의미가 된다.
        //controller 에서 호출할 수 있는 메서드는 인터페이스에 정의된 process밖에 없음
        controller.process(request,response);
    }
}
