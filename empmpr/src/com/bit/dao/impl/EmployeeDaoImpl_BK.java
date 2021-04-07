package com.bit.dao.impl;

import com.bit.dao.EmployeeDao;
import com.bit.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDaoImpl_BK implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
        // JDBC基本流程

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Employee> list = new ArrayList<>();
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
//            jdbc:mysql://localhost:3306/databasetest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
//            jdbc:mysql://localhost:3306/employee?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
            // mysql 8.0 以上
            String url = "jdbc:mysql://localhost:3306/employee?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String password = "baba20120506.";

            // 1.加载驱动
            Class.forName(driver);
            // 2.建立和数据库的连接
            conn = DriverManager.getConnection(url, user, password);
            // 3.创建一个SQL命令发送器
            stmt = conn.createStatement();
            // 4.准备好SQL语句，通过SQL命令发送器发送给数据库，并得到结果
            String sql = "select * from emp";
            rs = stmt.executeQuery(sql);
            // 5.处理结果（封装到List中）
            while(rs.next()){
                // 1.将当前行各列值取出来
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hireDate = rs.getDate("hiredate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptno = rs.getInt("deptno");
                // 2.封装到一个Employee对象中
                Employee emp = new Employee(empno, ename, job, mgr, hireDate, sal, comm, deptno);
                // 3.将对象添加到list中
                list.add(emp);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.关闭资源
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public Employee findById(int empno) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Employee emp = null; // 查询不到
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://114.55.218.160:3306/DbServer?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String password = "hyj！@#";

            // 1.加载驱动
            Class.forName(driver);
            // 2.建立和数据库的连接
            conn = DriverManager.getConnection(url, user, password);
            // 3.创建一个SQL命令发送器
            stmt = conn.createStatement();
            // 4.准备好SQL语句，通过SQL命令发送器发送给数据库，并得到结果
            String sql = "select * from emp where empno ="+ empno;
            stmt.executeQuery(sql);
            // 5.处理结果（封装到List中）
            if(rs.next()){
                // 1.将当前行各列值取出来
                int no = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hireDate = rs.getDate("hiredate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptno = rs.getInt("deptno");
                // 2.封装到一个Employee对象中
                emp = new Employee(no, ename, job, mgr, hireDate, sal, comm, deptno);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.关闭资源
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return emp;
    }

    @Override
    public int add(Employee emp) {
        return 0;
    }

    @Override
    public int update(Employee emp) {
        return 0;
    }

    @Override
    public int delete(int empno) {
        return 0;
    }

    @Override
    public void save() {

    }
}
