package group.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="responseHeaderServlet",urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 코드 [status-line] 임의로 지정하기
        //response.setStatus(HttpServletResponse.SC_OK); //즉 응답의 상태를 200으로 강제 지정한 거임
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //즉 응답의 상태를 400으로 강제 지정한 거임

        //response에서의 "헤더의 데이터" ex) contenttype 등 임의 세팅
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("Cache-Control","no-cache,must-revalidate");
        response.setHeader("Pragma","no-cache");
        //내가 헤더에 넣고 싶은 임의의 데이터 .ex) xd에서의 프로젝트 아이디 도 넣을 수 있음
        response.setHeader("my-header","hello");

        //쿠키 편의 메서드
        cookie(response);
        redirect(response);

        //그리고 그냥 직접적으로 표시?될 응답
        //즉 클라이언트로 내보낼 응답
        response.getWriter().write("ok");
    }


    private void cookie(HttpServletResponse response){

        //웹서버가 웹 브라우저에게 보내어야 웹 브라우저가 저장하므로
       // 즉 서버가 응답으로 웹 브라우저에 이런 쿠키 정보를 보내면
        //그 다음부터 웹 브라우저가 리퀘스트에 받은 쿠키 정보를 헤더에 넣어 보내는 것

        //Set-Cookie: myCookie=good; Max-Age=600;
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //이 쿠키는 600초 동안 유효하다

        response.addCookie(cookie); //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302 리다이렉트
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }

    }
