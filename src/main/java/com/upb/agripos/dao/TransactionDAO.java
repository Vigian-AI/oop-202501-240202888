package com.upb.agripos.dao;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.model.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private static TransactionDAO instance;
    private final DataSource dataSource;

    private TransactionDAO() {
        this.dataSource = DatabaseConnection.getInstance().getDataSource();
        System.out.println("✓ TransactionDAO initialized (using DataSource)");
    }

    public static synchronized TransactionDAO getInstance() {
        if (instance == null) {
            instance = new TransactionDAO();
        }
        return instance;
    }

    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions (cart_id, user_id, total_amount, payment_method, status, transaction_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, transaction.getCartId());
            ps.setInt(2, transaction.getUserId());
            ps.setDouble(3, transaction.getTotalAmount());
            ps.setString(4, transaction.getPaymentMethod());
            ps.setString(5, transaction.getStatus());
            ps.setTimestamp(6, Timestamp.valueOf(transaction.getTransactionDate()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    // transaction.setId(rs.getInt(1)); // if needed
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, cart_id, user_id, total_amount, payment_method, status, transaction_date FROM transactions ORDER BY transaction_date DESC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                transactions.add(new Transaction(
                    rs.getInt("id"),
                    rs.getInt("cart_id"),
                    rs.getInt("user_id"),
                    rs.getDouble("total_amount"),
                    rs.getString("payment_method"),
                    rs.getString("status"),
                    rs.getTimestamp("transaction_date").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching transactions: " + e.getMessage());
        }
        return transactions;
    }

    public List<Transaction> findByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, cart_id, user_id, total_amount, payment_method, status, transaction_date FROM transactions WHERE user_id = ? ORDER BY transaction_date DESC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("total_amount"),
                        rs.getString("payment_method"),
                        rs.getString("status"),
                        rs.getTimestamp("transaction_date").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching transactions by user: " + e.getMessage());
        }
        return transactions;
    }
}
