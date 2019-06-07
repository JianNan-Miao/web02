package com.cheer.web.servlet;

import com.cheer.web.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        int count=0;
        this.getServletContext().setAttribute("count",count);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection conn=null;
        String username= request.getParameter("username");
        String password=request.getParameter("password");


        try {

           conn=DBUtils.getInstance().getConnection();
            String sql="select * from user where username=? and password=?";
            ps =conn.prepareStatement(sql);
            ps.setObject(1,username);
            ps.setObject(2,password);
            rs= ps.executeQuery();
            if(rs.next()){
                Integer count = (Integer) this.getServletContext().getAttribute("count");
                count++;
                response.getWriter().write(rs.getString("username")+"欢迎您"+",您是第"+count+"次登录成功");
                this.getServletContext().setAttribute("count",count);
                response.sendRedirect("../empList.html");
            }else{
                response.getWriter().write("sorry!登录失败！");
                response.sendRedirect("../login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs,ps,conn);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
