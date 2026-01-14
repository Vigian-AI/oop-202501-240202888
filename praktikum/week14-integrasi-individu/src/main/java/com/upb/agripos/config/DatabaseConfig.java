package com.upb.agripos.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static DatabaseConfig instance;
    private Properties properties;

    private DatabaseConfig() {
        properties = new Properties();
        try (InputStream input = getClass().getResourceAsStream("/database.properties")) {
            if (input == null) {
                System.err.println("⚠️  database.properties not found, using default configuration");
                setDefaults();
            } else {
                properties.load(input);
                System.out.println("✓ Database configuration loaded from database.properties");
            }
        } catch (IOException e) {
            System.err.println("Error loading database.properties: " + e.getMessage());
            setDefaults();
        }
    }

    private void setDefaults() {
        properties.setProperty("db.url", "jdbc:postgresql://localhost:5432/agripos");
        properties.setProperty("db.username", "postgres");
        properties.setProperty("db.password", "1234");
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public String getUrl() {
        return properties.getProperty("db.url");
    }

    public String getUsername() {
        return properties.getProperty("db.username");
    }

    public String getPassword() {
        return properties.getProperty("db.password");
    }

    @Override
    public String toString() {
        return String.format("Database Configuration: %s@%s", 
            getUsername(), 
            getUrl().substring(getUrl().lastIndexOf("/") + 1));
    }
}
