package com.spring.dto;

import java.sql.*;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // JDBC 1단계 : 드라이버 객체 로딩
            Class.forName("oracle.jdbc.OracleDriver");

            // JDBC 2단계 : 커넥션 연결
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "spring";
            String password = "spring";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public static void rollback() {
        try (Connection conn = getConnection()) {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
