package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.upb.agripos.config.DatabaseConfig;
import com.upb.agripos.model.User;

public class UserDAO {
    private static UserDAO instance;
    private Connection conn;

    private UserDAO() {
        try {
            DatabaseConfig config = DatabaseConfig.getInstance();
            conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
            );
            System.out.println("✓ UserDAO database connection successful");
        } catch (SQLException e) {
            System.err.println("✗ UserDAO database connection failed: " + e.getMessage());
            System.err.println("Pastikan PostgreSQL running di localhost:5432 dengan database 'agripos'");
            throw new RuntimeException("Koneksi database gagal: " + e.getMessage(), e);
        }
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public User authenticate(String username, String password) {
        String sql = "SELECT id, username, full_name, role FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
        }
        return null;
    }

    public boolean userExists(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking user: " + e.getMessage());
        }
        return false;
    }
}
