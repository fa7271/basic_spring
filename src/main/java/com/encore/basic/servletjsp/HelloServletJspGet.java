package com.encore.basic.servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Controller 가 아닌 WebServlet을 통해 라우팅
@WebServlet("/hello-servlet-jsp-get")

public class HelloServletJspGet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        기본패턴 : req를 받아 res를 return해주는 방식
        req.setAttribute("myData", "jsp test data");
        req.getRequestDispatcher("hello-jsp").forward(req, resp);
    }


    // service 메소드는 서블릿에 들어오는 모든요청(get,post,put,delete) 등 을 처리
//    다만, 구체적으로 doGet, doPost등 메소드를 쓰는게 더 좋은 문법
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("myData", "jsp test data");
//        req.getRequestDispatcher("hello-jsp").forward(req, resp);
//
//    }
}