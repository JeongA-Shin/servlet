package group.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="responseJsonServlet",urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //content type은 applicaton json으로 들어가야 함
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");


        //응답으로 보내질 객체 - 서버에서는 객체만 다룸(객체 단위로 모든 것이 이루어짐)
        HelloData helloData=new HelloData();
        helloData.setUsername("jeong");
        helloData.setAge(20);


        //객체를 json으로 바꾸어야 함
        String result= objectMapper.writeValueAsString(helloData);

        //그리고 응답으로 보냄 - json 뿌리기
        response.getWriter().write(result);
    }
}
