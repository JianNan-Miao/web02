package com.cheer.web.service;

import com.cheer.web.domain.Emp;
import com.cheer.web.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpService {
    public List<Emp> getList() {
        Emp emp = null;
        List<Emp> list = new ArrayList<Emp>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = DBUtils.getInstance().getConnection();
        String sql = "select * from emp";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                String hireDate = rs.getString("hireDate");
                double sal = rs.getDouble("sal");
                double com = rs.getDouble("com");
                int deptno = rs.getInt("deptno");
                emp = new Emp(empno, ename, job, mgr, hireDate, sal, com, deptno);
                list.add(emp);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.getInstance().close(rs, ps, conn);
        }

        return list;
    }


}
