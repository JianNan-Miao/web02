package com.cheer.web.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "SessionServlet", urlPatterns = "/servlet/SessionServlet")
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session,用于存放客户端的信息，如果有直接获取session，如果没有，先创建再获取
        HttpSession session = request.getSession();
        //向session中存放数据
        session.setAttribute("name","jerry");
        //获取JSESSIONID，用于存放到客户端的cookie中
        String id = session.getId();
        //将存放JSESSIONID的cookie持久化到内存中
        Cookie cookie=new Cookie("JSESSIONID",id);
        cookie.setMaxAge(60*10);
        cookie.setPath("web02/");
        response.addCookie(cookie);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
