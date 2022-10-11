package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        //[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);

        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // 길이 만큼의 데이터만 응답, 생략시 길이에 맞는 값으로 자동 생성
        response.setContentLength(2);
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        // Key-Value 데이터
        Cookie cookie = new Cookie("myCookie", "good");
        // MaxAge 600초
        cookie.setMaxAge(600);
        // 쿠키를 담는다.
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //// 302
        //response.setStatus(HttpServletResponse.SC_FOUND);
        //// 302 응답코드와 같이 사용시 이동 url 표현
        //response.setHeader("Location", "/basic/hello-form.html");

        // 해당 url로 리다이렉트를 시킨다.
        response.sendRedirect("/basic/hello-form.html");
    }
}
