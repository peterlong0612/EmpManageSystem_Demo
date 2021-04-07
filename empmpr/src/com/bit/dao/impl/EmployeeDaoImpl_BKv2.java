package com.bit.dao.impl;

import com.bit.dao.EmployeeDao;
import com.bit.entity.Employee;
import com.bit.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDaoImpl_BKv2 implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
        // JDBC基本流程
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Employee> list = new ArrayList<>();
        try {
            // 1.加载驱动
            // 2.建立和数据库的连接
            conn = DBUtil.getConnection();
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.关闭资源
            DBUtil.closeAll(rs, stmt, conn);
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
            // 1.加载驱动
            // 2.建立和数据库的连接
            conn = DBUtil.getConnection();
            // 3.创建一个SQL命令发送器
            stmt = conn.createStatement();
            // 4.准备好SQL语句，通过SQL命令发送器发送给数据库，并得到结果
            String sql = "select * from emp where empno ="+ empno;
            rs = stmt.executeQuery(sql);
            // 5.处理结果
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.关闭资源
            DBUtil.closeAll(rs, stmt, conn);
        }

        return emp;
    }

    @Override
    public int add(Employee emp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int n = 0; // 添加失败
        try {
            // 1.加载驱动
            // 2.建立和数据库的连接
            conn = DBUtil.getConnection();
            // 3.创建一个SQL命令发送器
            // 主键id自增,所以只有7个
            String sql = "insert into emp values(null,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            // 4.准备好SQL语句，通过SQL命令发送器发送给数据库，并得到结果

            // sql面向关系，java面向类和对象
            // 占位符？的顺序，而不是数据库
            pstmt.setString(1,emp.getEname());
            pstmt.setString(2,emp.getJob());
            pstmt.setInt(3,emp.getMgr());
            // provide: java.util.Date,  required: java.sql.Date
            /*
                java.sql.Date/Time/Timestamp extends java.util.Date

                java.sql.Date yyyyMMdd
                java.sql.Tiem hhmmss
                java.sql.Timestamp yyyyMMddhhmmss 更精确

                这里其实是需要一个子类对象，但给的是父类对象
                util.date变成sql.date的方法
                java.sql.Date sdate =  new java.sql.Date(emp.getHireDate().getTime())
                getTime()得到底层毫秒数，根据毫秒数计算当前年月日
             */
            pstmt.setDate(4,new java.sql.Date(emp.getHireDate().getTime())); // 提供util.date,但是需要sql.data
            pstmt.setDouble(5,emp.getSal());
            pstmt.setDouble(6,emp.getComm());
            pstmt.setInt(7,emp.getDeptno());
            n = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.关闭资源
            DBUtil.closeAll(rs, pstmt, conn);
            // 父接口statement，子接口的实例也能引用
        }

        return n;
    }

    @Override
    public int update(Employee emp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int n = 0; // 添加失败
        try {
            // 1.加载驱动
            // 2.建立和数据库的连接
            conn = DBUtil.getConnection();
            // 3.创建一个SQL命令发送器
            // 主键id自增,所以只有7个
            String sql = "update emp set job=?, sal=?, comm=?, deptno=? where empno=?";
            pstmt = conn.prepareStatement(sql);
            // 4.准备好SQL语句，通过SQL命令发送器发送给数据库，并得到结果
            // 占位符？的顺序，而不是数据库
            pstmt.setString(1,emp.getJob());
            pstmt.setDouble(2,emp.getSal());
            pstmt.setDouble(3,emp.getComm());
            pstmt.setInt(4,emp.getDeptno());
            pstmt.setInt(5,emp.getEmpno());
            n = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.关闭资源
            DBUtil.closeAll(rs, pstmt, conn);
            // 父接口statement，子接口的实例也能引用
        }

        return n;
    }

    @Override
    public int delete(int empno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int n = 0; // 添加失败
        try {
            // 1.加载驱动
            // 2.建立和数据库的连接
            conn = DBUtil.getConnection();
            // 3.创建一个SQL命令发送器
            // 主键id自增,所以只有7个
            String sql = "delete from emp where empno=?";
            pstmt = conn.prepareStatement(sql);
            // 4.准备好SQL语句，通过SQL命令发送器发送给数据库，并得到结果
            // 占位符？的顺序，而不是数据库
            pstmt.setInt(1,empno);
            n = pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.关闭资源
            DBUtil.closeAll(rs, pstmt, conn);
            // 父接口statement，子接口的实例也能引用
        }

        return n;

    }

    @Override
    public void save() {

    }
}
