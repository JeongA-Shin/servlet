package group.servlet.web.frontcontroller;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ModelView {

    private String viewName;//뷰의 논리적 이름
    private Map<String,Object> model=new HashMap<>(); // 객체


    public ModelView(String viewName){
        this.viewName=viewName;
    }
}
