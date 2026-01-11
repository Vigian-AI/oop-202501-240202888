package com.upb.agripos.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
    private static HikariDataSource hikariDataSource;

    public static HikariDataSource getDataSource() {
        if (hikariDataSource == null) {
            synchronized (DatabaseConnection.class) {
                if (hikariDataSource == null) {
                    try {
                        Dotenv dotenv = Dotenv.load();
                        String pass = dotenv.get("DB_PASSWORD");

                        HikariConfig config = new HikariConfig();
                        config.setDriverClassName("org.postgresql.Driver");
                        config.setUsername("postgres");  // Adjust username if needed
                        config.setPassword(pass);
                        config.setJdbcUrl("jdbc:postgresql://localhost:5432/OOP");

                        config.setMaximumPoolSize(10);
                        config.setMinimumIdle(5);
                        config.setIdleTimeout(60000);
                        config.setMaxLifetime(60 * 60000);

                        hikariDataSource = new HikariDataSource(config);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to initialize database connection: " + e.getMessage(), e);
                    }
                }
            }
        }
        return hikariDataSource;
    }
}
