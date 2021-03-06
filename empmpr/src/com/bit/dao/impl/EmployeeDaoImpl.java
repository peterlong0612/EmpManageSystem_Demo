package com.bit.dao.impl;

import com.bit.dao.EmployeeDao;
import com.bit.entity.Employee;
import com.bit.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
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
        // 代码提取
        String sql = "insert into emp values(null,?,?,?,?,?,?,?)";
        Object [] params = {emp.getEname(), emp.getJob(), emp.getMgr(),
                new java.sql.Date(emp.getHireDate().getTime()), emp.getSal(), emp.getComm(), emp.getDeptno()};
        return DBUtil.executeUpdate(sql,params);
    }

    @Override
    public int update(Employee emp) {
        // 代码提取
        String sql = "update emp set job=?, sal=?, comm=?, deptno=? where empno=?";
        Object [] params = {emp.getJob(), emp.getSal(), emp.getComm(), emp.getDeptno(), emp.getEmpno()};
        return DBUtil.executeUpdate(sql,params);
    }

    @Override
    public int delete(int empno) {
        // 代码提取
        String sql = "delete from emp where empno=?";
        Object [] params = {empno};
        return DBUtil.executeUpdate(sql,params);
    }

    @Override
    public void save() {

    }
}
