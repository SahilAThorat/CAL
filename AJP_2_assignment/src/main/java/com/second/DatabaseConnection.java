package com.second;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/turf_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database Connected Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("🚨 MySQL Driver Not Found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("🚨 Database Connection Failed!");
            e.printStackTrace();
        }
        return conn;
    }
}


