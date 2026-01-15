package com.upb.agripos.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int cartId;
    private int userId;
    private double totalAmount;
    private String paymentMethod;
    private String status;
    private LocalDateTime transactionDate;

    public Transaction(int id, int cartId, int userId, double totalAmount, String paymentMethod, String status, LocalDateTime transactionDate) {
        this.id = id;
        this.cartId = cartId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionDate = transactionDate;
    }

    // Getters
    public int getId() { return id; }
    public int getCartId() { return cartId; }
    public int getUserId() { return userId; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }
    public LocalDateTime getTransactionDate() { return transactionDate; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cartId=" + cartId +
                ", userId=" + userId +
                ", totalAmount=" + totalAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
