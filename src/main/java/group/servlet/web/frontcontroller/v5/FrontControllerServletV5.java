package group.servlet.web.frontcontroller.v5;

import group.servlet.web.frontcontroller.ModelView;
import group.servlet.web.frontcontroller.MyView;
import group.servlet.web.frontcontroller.v3.Controller.MemberFormControllerV3;
import group.servlet.web.frontcontroller.v3.Controller.MemberListControllerV3;
import group.servlet.web.frontcontroller.v3.Controller.MemberSaveControllerV3;
import group.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //기존의 url과 컨트롤러 매핑 정보를 보여줬던 map
    //private Map<String,ControllerV4> controllerMap=new HashMap<>();
    // 그리고 프론트 컨틀롤러 생성자에서 
    // controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
    // 등을 통해 각 url 패턴을 키로 두고, 대응되는 각 컨트롤러들을 값으로 해서 넣어줌

    //그리고 이제 어댑터 패턴에서의 url과 핸들러(컨트롤러) 매핑 정보 map
    private Map<String, Object> handlerMappingMap = new HashMap<>();
    //아무 컨틀롤러나 다 들어갈 수 있기 때문에 맵의 값의 자료형을 그냥 Object라고 둠

    private List<MyHandlerAdapter> handlerAdapters=new ArrayList<>();
    //왜냐면 여러 개의 어댑터 후보 중에 하나를 꺼내 써야 하므로, 그 후보를 리스트로 만들어 놓는 것


    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //이제 프론트 컨트롤러의 로직 시작

        //매핑 맵에서 해당되는 핸들러를 가져옴
        Object handler = getHandler(request);

        //예외 처리: ex. 만약 해당 url에 대한 핸들러가 없을 때 - 예상하지 못한 url일 때
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //응답으로 404 반환해주기
            return;//바로 리턴
        }

        //위에서 찾은 현재 url의 핸들러에 맞는!!! 핸들러 어댑터를 찾음
        MyHandlerAdapter adapter= getHandlerAdapter(handler);
        //어댑터가 supports하는 해당 핸들러를 호출하고, 그 핸들러로부터 modelView를 리턴 받아옴
        ModelView mv = adapter.handle(request,response,handler);

        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        //이제 getHandler를 통해 가져온 핸들러에 맞는!!! 핸들러 어댑터를 찾음
        for(MyHandlerAdapter adapter:handlerAdapters){
            //현재 리스트에 있는 어댑터 목록을 하나씩 돌면서 가져온 핸들러를 지원하는지(=맞는지) 찾음
            if(adapter.supports(handler)){
                return adapter;
            }
        }

        throw new IllegalArgumentException("해당 핸들러에 대한 핸들러 어댑터를 찾을 수 없습니다");
    }

    private Object getHandler(HttpServletRequest request) {

        //URL에서 서버 이름 뒷부분의 /부터 시작됨.
        //즉 http://localhost:8080/front-controller/v1/hello으로 클라이언트가 요청했다면
        // requestURI에는 /front-controller/v1/hello 만 담김
        String requestURI = request.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI); // 매핑 정보에 따라 url에 맞게 핸들러(컨트롤러)를 찾음

        return handler;
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
