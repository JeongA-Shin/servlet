package group.servlet.web.frontcontroller.v4;

import java.util.Map;

//모든 컨트롤러에 대해 공통인 기능을 인터페이스로 정의한 것
public interface ControllerV4{

    /**
     *
     * @param paramMap
     * @param model
     * @return
     */
    String process(Map<String,String> paramMap, Map<String,Object> model);
}
