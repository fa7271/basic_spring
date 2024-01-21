
package com.encore.basic.servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Controller 가 아닌 WebServlet을 통해 라우팅
@WebServlet("/hello-servlet-jsp-post")

public class HelloServletJspPost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");;
        String password = req.getParameter("password");;

//        console 출력
        System.out.println("name = " + name);
        System.out.println("password = " + password);
        System.out.println("email = " + email);

//        응답header
        resp.setContentType("text/plaing");
        resp.setCharacterEncoding("UTF-8");

//        응담 body
        PrintWriter out = resp.getWriter();
        out.print("ok");
        out.flush();

    }
}
