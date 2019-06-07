package com.cheer.web.servlet;

import com.cheer.web.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "RegistServlet", urlPatterns = "/servlet/RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String username= request.getParameter("username");
        String password=request.getParameter("password");
        String sql="insert into user values (?,?)";
        PreparedStatement ps=null;
        Connection conn=null;
        conn= DBUtils.getInstance().getConnection();
        try {
            ps=conn.prepareStatement(sql);
            ps.setObject(1,username);
            ps.setObject(2,password);
            ps.execute();
            response.getWriter().write("注册成功");
            response.sendRedirect("../success.html");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(null,ps,conn);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
