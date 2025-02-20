package org.example;

import java.sql.*;

public class Database {
    private static Database INSTANCE = new Database();
    private Connection connection;

    private Database() {
        try {
            String connURL = "jdbc:h2:./database/test;AUTO_SERVER=TRUE";
            connection = DriverManager.getConnection(connURL, "sa", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public int executeUpdates(String sql) {
        if (connection == null) {
            throw new IllegalStateException("Database connection is not initialized");
        }
        try (Statement st = connection.createStatement()) {
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
