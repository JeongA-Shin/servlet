package group.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="RequestBodyJsonServlet",urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    //전송받은 json 데이터를 우리가 만든 객체로 변환하기
    //Jackson 라이브러리 사용 - 스프링부트에서는 spring mvc를 선택했을 시 기본으로 제공
    private ObjectMapper objectMapper=new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //바이트 코드를 스트링으로 변환하는 것까지는 단순 텍스트로 받는 것과 과정이 같음
        //왜냐면 json 도 결국 문자이므로
        ServletInputStream inputStream=request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println(messageBody);

        //전송받은 json 데이터를 우리가 만든 객체로 변환하기
        HelloData helloData= objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.username = "+helloData.getUsername());
        System.out.println("helloData.age = "+helloData.getAge());

        //그리고 돌려줄 응답
        response.getWriter().write("ok");
    }
}
