package com.softec.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSetup {
    
    /**
     * Creates the database if it doesn't exist
     * Run this before running migrations
     */
    public static void createDatabase() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "password";
        String dbName = "token_manager";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            
            // Check if database exists
            String checkSql = "SELECT 1 FROM pg_database WHERE datname = '" + dbName + "'";
            var rs = stmt.executeQuery(checkSql);
            
            if (!rs.next()) {
                // Create database if not exists
                String sql = "CREATE DATABASE " + dbName + 
                            " WITH ENCODING 'UTF8' LC_COLLATE='en_US.UTF-8' LC_CTYPE='en_US.UTF-8'";
                stmt.executeUpdate(sql);
                System.out.println("Database '" + dbName + "' created successfully");
            } else {
                System.out.println("Database '" + dbName + "' already exists");
            }
            
        } catch (Exception e) {
            System.err.println("Failed to create database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Database Setup ===");
        createDatabase();
        System.out.println("\nNow you can run MigrationRunner to create tables and insert data.");
    }
}
