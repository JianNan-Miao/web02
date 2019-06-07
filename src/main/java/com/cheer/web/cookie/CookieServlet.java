package com.cheer.web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "CookieServlet", urlPatterns = "/servlet/CookieServlet")
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        Cookie cookie=new Cookie("lastTime",(new Date().toString()));
        cookie.setMaxAge(10*60);
        response.addCookie(cookie);
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie1:cookies){
                if("lastTime".equals(cookie1.getName())){
                    String time=cookie1.getValue();
                    writer.write("你上次登录的时间为"+time);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
