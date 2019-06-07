package com.cheer.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "DownloadServlet", urlPatterns = "/servlet/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        //获得要下载的文件的名称
        String filename = request.getParameter("filename");
        //告诉客户端该文件不是直接解析 而是以附件形式打开(下载)----filename="+filename 客户端默认对名字进行解码
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        //获取文件的绝对路径
        String realPath = this.getServletContext().getRealPath("download/"+filename);
        InputStream is=new FileInputStream(realPath);
        //获得输出流---通过response获得的输出流 用于向客户端写内容
        ServletOutputStream os = response.getOutputStream();
        int len=0;
        byte[] bytes=new byte[1024];
        while (-1!=(len=is.read(bytes))){
            os.write(bytes,0,bytes.length);
        }
        is.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
