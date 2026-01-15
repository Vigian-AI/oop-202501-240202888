package com.upb.agripos.service;

import com.upb.agripos.dao.TransactionDAO;
import com.upb.agripos.model.Transaction;

import java.util.List;

public class TransactionService {
    private final TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = TransactionDAO.getInstance();
    }

    public void saveTransaction(Transaction transaction) {
        transactionDAO.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDAO.findAll();
    }

    public List<Transaction> getTransactionsByUserId(int userId) {
        return transactionDAO.findByUserId(userId);
    }
}
