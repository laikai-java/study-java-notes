package com.lk.spring.jdbc;


import com.lk.spring.generator.entity.User;

import java.sql.*;

/**
 * jdbc连接的步骤
 * <p>
 *     第1步：注冊驱动 (仅仅做一次)
 * <p>
 * 　　第2步：建立连接(Connection)
 * <p>
 * 　　第3步：创建运行SQL的语句(Statement)
 * <p>
 * 　　第4步：运行语句
 * <p>
 * 　　第5步：处理运行结果(ResultSet)
 * <p>
 * 　　第6步：释放资源
 */
public class JdbcClient {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
        String userName = "root";
        String password = "root";
        String sql = "select * from user";

        String prepareStatementSql = "select * from user where id = ?";

        Connection conn = null;

        Statement statement = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //创建连接
            conn = DriverManager.getConnection(url, userName, password);
            //创建statement/prepareStatement
            //statement = conn.createStatement();
            //执行SQL语句,得到返回结果
            //resultSet = statement.executeQuery(sql);

            ps = conn.prepareStatement(prepareStatementSql);
            ps.setInt(1,1);
            rs = ps.executeQuery();

            //处理结果
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                User user = new User(id, name, sex, age);
                System.out.println(user);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭对象从下往上关闭
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
