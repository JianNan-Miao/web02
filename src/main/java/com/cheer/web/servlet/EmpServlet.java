package com.cheer.web.servlet;

import com.cheer.web.domain.Emp;
import com.cheer.web.service.EmpService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmpServlet", urlPatterns = "/servlet/EmpServlet")
public class EmpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        EmpService empService=new EmpService();
        List<Emp> list=empService.getList();
        Gson gson=new Gson();
        String data=gson.toJson(list);
        response.getWriter().write(data);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
