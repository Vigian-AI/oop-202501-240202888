package com.upb.agripos.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.upb.agripos.dao.TransactionDAO;
import com.upb.agripos.model.Transaction;

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

    public List<Transaction> getTransactionsByDateRange(LocalDateTime start, LocalDateTime end) {
        return transactionDAO.findByDateRange(start, end);
    }

    public String generateDailyReport(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);
        List<Transaction> transactions = getTransactionsByDateRange(start, end);

        double totalSales = transactions.stream().mapToDouble(Transaction::getTotalAmount).sum();
        int totalTransactions = transactions.size();
        Map<String, Double> salesByCashier = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getCashierName, Collectors.summingDouble(Transaction::getTotalAmount)));
        Map<String, Long> transactionsByPaymentMethod = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getPaymentMethod, Collectors.counting()));

        StringBuilder report = new StringBuilder();
        report.append("================================\n");
        report.append("    LAPORAN PENJUALAN HARIAN\n");
        report.append("================================\n");
        report.append("Tanggal: ").append(date).append("\n");
        report.append("Total Transaksi: ").append(totalTransactions).append("\n");
        report.append("Total Penjualan: Rp ").append(String.format("%,.0f", totalSales)).append("\n");
        report.append("--------------------------------\n");
        report.append("Penjualan per Kasir:\n");
        salesByCashier.forEach((cashier, sales) ->
            report.append("- ").append(cashier).append(": Rp ").append(String.format("%,.0f", sales)).append("\n"));
        report.append("--------------------------------\n");
        report.append("Metode Pembayaran:\n");
        transactionsByPaymentMethod.forEach((method, count) ->
            report.append("- ").append(method).append(": ").append(count).append(" transaksi\n"));
        report.append("================================\n");

        return report.toString();
    }

    public String generateCashierReport(int userId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);
        List<Transaction> transactions = transactionDAO.findByUserId(userId).stream()
            .filter(t -> !t.getTransactionDate().isBefore(start) && !t.getTransactionDate().isAfter(end))
            .collect(Collectors.toList());

        double totalSales = transactions.stream().mapToDouble(Transaction::getTotalAmount).sum();
        int totalTransactions = transactions.size();
        Map<String, Long> transactionsByPaymentMethod = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getPaymentMethod, Collectors.counting()));

        String cashierName = transactions.isEmpty() ? "Unknown" : transactions.get(0).getCashierName();

        StringBuilder report = new StringBuilder();
        report.append("================================\n");
        report.append("    LAPORAN PENJUALAN KASIR\n");
        report.append("================================\n");
        report.append("Kasir: ").append(cashierName).append("\n");
        report.append("Periode: ").append(startDate).append(" - ").append(endDate).append("\n");
        report.append("Total Transaksi: ").append(totalTransactions).append("\n");
        report.append("Total Penjualan: Rp ").append(String.format("%,.0f", totalSales)).append("\n");
        report.append("--------------------------------\n");
        report.append("Metode Pembayaran:\n");
        transactionsByPaymentMethod.forEach((method, count) ->
            report.append("- ").append(method).append(": ").append(count).append(" transaksi\n"));
        report.append("================================\n");

        return report.toString();
    }
}
