package com.cheer.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    private  static  final  DBUtils INSTANCE =new DBUtils();
    private DBUtils(){

    }

    public static  DBUtils getInstance(){
        return INSTANCE;
    }

    public Connection getConnection(){
        Connection conn=null;
        Properties pro=new Properties();
       InputStream is= DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            pro.load(is);
            Class.forName(pro.getProperty("driver"));
           conn= DriverManager.getConnection(pro.getProperty("url"),pro.getProperty("user"),
                    pro.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close(ResultSet rs, PreparedStatement ps,Connection conn ){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       if(conn!=null){
           try {
               conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }


}
