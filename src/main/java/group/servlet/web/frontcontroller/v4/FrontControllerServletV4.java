package group.servlet.web.frontcontroller.v4;

import group.servlet.web.frontcontroller.ModelView;
import group.servlet.web.frontcontroller.MyView;
import group.servlet.web.frontcontroller.v3.Controller.MemberFormControllerV3;
import group.servlet.web.frontcontroller.v3.Controller.MemberListControllerV3;
import group.servlet.web.frontcontroller.v3.Controller.MemberSaveControllerV3;
import group.servlet.web.frontcontroller.v3.ControllerV3;
import group.servlet.web.frontcontroller.v4.Controller.MemberFormControllerV4;
import group.servlet.web.frontcontroller.v4.Controller.MemberListControllerV4;
import group.servlet.web.frontcontroller.v4.Controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// !! 프론트 컨트롤러는 서블릿이어야 한다!!
@WebServlet(name="frontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    //매핑되는 URL에 따라 어떤 컨트롤러를 호출할지 알아야 하므로
    //그 URL과 컨트롤러 매핑 정보를 MAP에 저장해놓음
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        //생성될 때 controllerMap에 정보를 넣어놓자
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
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
        //왜냐면 다형성을 이용해 기능을 인터페이스로 한계를 지었기 때문
        ControllerV4 controller = controllerMap.get(requestURI); // 프론트 컨트롤러(여기)서 url에 맞게 컨트롤러를 담음

        //예외 처리: ex. 만약 해당 url에 대한 컨트롤러가 없을 때 - 예상하지 못한 url일 때
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //응답으로 404 반환해주기
            return;//바로 리턴
        }

        // 인스턴스 objI2의 데이터 타입을 I2로 한다는 것은 인스턴스를 외부에서 제어할 수 있는 조작 장치를 인스턴스 I2의 맴버로 제한한다는 의미가 된다.
        //controller 에서 호출할 수 있는 메서드는 인터페이스에 정의된 process밖에 없음


        //요청에서부터 데이터 뽑아옴
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        Map<String,Object> model=new HashMap<>();

        //각 컨트롤러들에게 입력받은 값을 저장한 paramMap, 그리고 그 값으로부터 생성된 객체일 모델(엔티티)을 넘겨줘야 함
        String viewName=controller.process(paramMap,model);

        //따라서 물리적 이름으로 변환해주는 viewResolver를 호출해서 통과함
        MyView view = viewResolver(viewName);
        view.render(model,request, response); // 코드 따라가보면 알겠지만 MyView의 render 함수는 forward 기능
        //이전과 다르게 뷰를 담당하는 MyView에게 모델을 넘겨줘야 함
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}