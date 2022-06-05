package group.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

//단순한 텍스트 전송시 - json형식이 아니라 단순 텍스트로만 보낼 때
@WebServlet(name="RequestBodyStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream=request.getInputStream(); // 이렇게 하면 텍스트 내용을 byte code로 얻게 됨
        //스트링으로 바꾸기;
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//문자와 바이트 간에 변환을 할 때는(양 방향 모두) 어떤 인코딩을 사용할지 꼭 알려줘야 함

        System.out.println("messageBody: "+messageBody);

        //그리고 보낼 응답
        response.getWriter().write("ok");
    }
}
