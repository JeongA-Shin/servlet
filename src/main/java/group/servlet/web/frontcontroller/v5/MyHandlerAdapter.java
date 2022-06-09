package group.servlet.web.frontcontroller.v5;

import group.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//어댑터는 이렇게 구현해야 한다는 어댑터용 인터페이스이다!!!

public interface MyHandlerAdapter {

    boolean supports(Object handler);
    //handler는 컨트롤러를 말한다.
    //어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메서드다
    //즉, 파라미터로 넘어온 핸들러(==컨트롤러의 다른 명칭. 어댑터가 있는 컨트롤러)를 지원할 수 있는지 그 여부를 반환

    ModelView handle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException, IOException;
    //어댑터는 실제 컨트롤러를 호출하고, 그 결과로 ModelView를 반환해야 한다.
    //실제 컨트롤러가 ModelView를 반환하지 못하면, 어댑터가 ModelView를 직접 생성해서라도 반환해야 한다.



    //이전에는 프론트 컨트롤러가 실제 컨트롤러를 호출했지만 이제는 이 어댑터를 통해서!! 실제 컨트롤러가 호출된다
}
