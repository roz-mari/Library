package org.library.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class DBManager {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = ("root");

    private static Connection connection;

    public static Connection initConnection() throws RuntimeException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to MySQL database");
            }
        } catch (SQLException exception) {
            System.out.println("SQL error: " + exception.getMessage());
            throw new RuntimeException("Failed to connect to DB");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void closeConnection() {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}

