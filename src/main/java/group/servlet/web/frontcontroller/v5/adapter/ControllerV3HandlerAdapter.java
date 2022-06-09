package group.servlet.web.frontcontroller.v5.adapter;

import group.servlet.web.frontcontroller.ModelView;
import group.servlet.web.frontcontroller.v3.ControllerV3;
import group.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//ControllerV3 (컨트롤러이면서 핸들러)에 쓰이는 어댑터!
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); //파라미터로 넘어온 handler가 ControllerV3 의 인스턴스인지 여부
    }


    //진짜 ControllerV3용 어댑터의 본격적인 역할
    //어댑터가 supports하는 해당 핸들러를 호출하고, 그 핸들러로부터 modelView를 리턴 받아옴
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV3 controller =(ControllerV3) handler; //파라미터로 건네받은 핸들러를 ControllerV3으로 캐스팅.(자료형 변환)
        //어차피 프론트 컨틀롤러에서 handle 이 함수를 호출하기 전에 supports 함수를 통해 v3가 아닌 애들은 걸러낼 거기 때문에
        //그냥 맘 편히 캐스팅해도 됨


        Map<String,String> paramMap=createParamMap(request);
        ModelView mv= controller.process(paramMap); //인터페이스 다형성. -> 그냥 인터페이스 함수로만 조작을 제한하겠다는 의도


        return mv;
    }

    //요청으로 들어온 쿼리 파라미터들을 Map형식으로 반환함. 쿼리의 파라미터명이 키고 해당 파라미터의 값이 value임
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }


}
