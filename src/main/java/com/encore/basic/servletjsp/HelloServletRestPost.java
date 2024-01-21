
package com.encore.basic.servletjsp;

import com.encore.basic.domain.Hello;
import com.encore.basic.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

// Controller 가 아닌 WebServlet을 통해 라우팅
@WebServlet("/hello-servlet-rest-post")

public class HelloServletRestPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BufferedReader br = req.getReader();
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        if (req.getContentType().equals("application/json")) {
            Hello hello = mapper.readValue(req.getReader(), Hello.class);
            PrintWriter out = resp.getWriter();
            System.out.println(hello);
            out.println("ok");
        }else {
            System.out.println("no json");
        }
    }

}
