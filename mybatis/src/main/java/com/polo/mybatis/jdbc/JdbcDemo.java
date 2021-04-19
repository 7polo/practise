package com.polo.mybatis.jdbc;

import java.sql.*;

/**
 * @author baoqianyong
 * @date 2021/4/19
 */
public class JdbcDemo {
//    url:
//    username: apollo
//    password: root

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://[ip]:[port]/[dbname]?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "****";
        String password = "****";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        statement.execute("SELECT 1");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
