package group.servlet.web.frontcontroller.v3;


import group.servlet.web.frontcontroller.ModelView;

import java.util.Map;

//각 컨트롤러들마다 꼭 구현해야 하는 것을 정의한 인터페이스
//인터페이스의 의의 그 자체
public interface ControllerV3 {
    ModelView process(Map<String,String> paramMap); //이전까지와 다르게 서블릿에 대한 것이 하나도 없음!
    //version3의 최종 목표: 각 컨트롤러들이 서블릿의 개념에서 완전히 독립할 수 있도록 하자
}
