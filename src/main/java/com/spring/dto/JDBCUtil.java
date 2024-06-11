package com.spring.dto;

import java.sql.*;

import static com.spring.dto.JDBCConst.*;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
