package com.bit.util;

import com.bit.entity.Employee;

import java.sql.*;

/**
 * 数据库访问工具类
 * 避免代码重复，方便修改
 */
public abstract class DBUtil {

    // 抽象类不能new实例化
    private DBUtil(){

    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){

        String driver = "com.mysql.cj.jdbc.Driver";
//            jdbc:mysql://localhost:3306/databasetest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
//            jdbc:mysql://localhost:3306/employee?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
        // mysql 8.0 以上
//        String url = "jdbc:mysql://localhost:3306/employee?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        // UTC会让日期减少一天
        String url = "jdbc:mysql://localhost:3306/employee?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "baba20120506.";
        Connection conn = null;

        try {
            // 1.加载驱动
            Class.forName(driver);
            // 2.建立和数据库的连接
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭各种资源
     */
    public static void closeAll(ResultSet rs, Statement stmt, Connection conn){
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

    /**
     * 完成insert、update、delete操作
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object [] params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int n = 0; // 添加失败
        try {
            // 1.加载驱动
            // 2.建立和数据库的连接
            conn = DBUtil.getConnection();
            // 3.创建一个SQL命令发送器
            // 主键id自增
            pstmt = conn.prepareStatement(sql);
            // 4.准备好SQL语句，通过SQL命令发送器发送给数据库，并得到结果
            // 占位符？的顺序，而不是数据库
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i+1,params[i]);
            }
            n = pstmt.executeUpdate();
            System.out.println(n);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.关闭资源
            DBUtil.closeAll(rs, pstmt, conn);
            // 父接口statement，子接口的实例也能引用
        }

        return n;
    }
}
