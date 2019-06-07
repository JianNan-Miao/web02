import com.cheer.web.util.DBUtils;
import org.junit.Test;

import java.sql.*;

public class UserTest {
    PreparedStatement ps;
    ResultSet rs;
    Connection conn;

    public void userTest(){

        String username= "admin";
        String password="admin";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test3","root","123");
            String sql="select * from user where username=? and password=?";
            ps =conn.prepareStatement(sql);
            ps.setObject(1,username);
            ps.setObject(2,password);

            rs= ps.executeQuery();
            System.out.println(rs);
            while (rs.next()){
                System.out.println(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
