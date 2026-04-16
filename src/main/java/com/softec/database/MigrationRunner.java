package com.softec.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

public class MigrationRunner {
    
    public static void runMigrations() {
        System.out.println("Starting database migrations...");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Run V1 - Create tables
            System.out.println("Running V1__create_base_tables.sql...");
            executeSqlFile(conn, "/migrations/V1__create_base_tables.sql");
            System.out.println("✓ Tables created successfully");
            
            // Run V2 - Insert base data
            System.out.println("Running V2__insert_base_data.sql...");
            executeSqlFile(conn, "/migrations/V2__insert_base_data.sql");
            System.out.println("✓ Base data inserted successfully");
            
            System.out.println("\nMigrations completed successfully!");
            
        } catch (Exception e) {
            System.err.println("Migration failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void executeSqlFile(Connection conn, String filePath) throws Exception {
        InputStream is = MigrationRunner.class.getResourceAsStream(filePath);
        if (is == null) {
            throw new RuntimeException("Migration file not found: " + filePath);
        }
        
        String sql = new BufferedReader(new InputStreamReader(is))
                .lines()
                .collect(Collectors.joining("\n"));
        
        // Split by semicolon and execute each statement
        String[] statements = sql.split(";");
        
        try (Statement stmt = conn.createStatement()) {
            for (String statement : statements) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty() && !trimmed.startsWith("--")) {
                    stmt.execute(trimmed);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        runMigrations();
    }
}
