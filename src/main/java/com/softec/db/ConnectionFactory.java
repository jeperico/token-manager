package com.softec.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5433/token_manager?ssl=false");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "postgres");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "postgres");

    private static class SingletonHelper {
        private static final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private ConnectionFactory() {}

    public static ConnectionFactory getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public static Connection getConnection() {
        int retries = 3;

        while (retries > 0) {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                retries--;
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("Failed to connect to DB");
    }
}
