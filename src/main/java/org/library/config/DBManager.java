package org.library.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = String.join ("PASSWORD");

    private static Connection connection;

    public static Connection initConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to MySQL database");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed successfully.");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

