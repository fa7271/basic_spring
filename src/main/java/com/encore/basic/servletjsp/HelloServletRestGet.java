
package com.encore.basic.servletjsp;

import com.encore.basic.domain.Hello;
import com.encore.basic.domain.MemberRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Controller 가 아닌 WebServlet을 통해 라우팅
@WebServlet("/hello-servlet-rest-get")

public class HelloServletRestGet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Hello member = new Hello();

        member.setName("송보석");
        member.setEmail("fa7271@naver.com");
        member.setPassword("1234");
//        header
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
//        body
        String serialized_Data = mapper.writeValueAsString(member);
        PrintWriter out = resp.getWriter();
        out.print(serialized_Data);
        out.flush();
    }
}
