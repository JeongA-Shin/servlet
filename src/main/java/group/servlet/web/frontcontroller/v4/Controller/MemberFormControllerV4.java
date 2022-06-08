package group.servlet.web.frontcontroller.v4.Controller;


import group.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form"; // 뷰 파일의 논리적 이름만 반환하도록 함
    }
}
