package com.upb.agripos.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.upb.agripos.model.Product;
import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.payment.PaymentMethod;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.service.TransactionService;
import com.upb.agripos.service.UserService;
import com.upb.agripos.view.PaymentDialog;
import com.upb.agripos.view.ReceiptView;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class PosController {
    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;
    private final TransactionService transactionService;

    public PosController(ProductService productService, CartService cartService, UserService userService, TransactionService transactionService) {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    public void addProduct(TextField txtCode, TextField txtName, TextField txtPrice, TextField txtStock, TableView<Product> tableView) {
        // only gudang can add products
        if (!isCurrentUserGudang()) {
            showAlert("Unauthorized", "Akses ditolak. Hanya user GUDANG yang dapat menambah produk.");
            return;
        }
        try {
            String code = txtCode.getText().trim();
            String name = txtName.getText().trim();
            double price = Double.parseDouble(txtPrice.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());

            Product product = new Product(code, name, price, stock);
            productService.insert(product);
            tableView.getItems().clear();
            tableView.getItems().addAll(productService.findAll());
            clearFields(txtCode, txtName, txtPrice, txtStock);
        } catch (NumberFormatException e) {
            showAlert("Error", "Input tidak valid: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        } catch (Exception e) {
            showAlert("Error", "Gagal menambah produk: " + e.getMessage());
        }
    }

    public void deleteProduct(TableView<Product> tableView) {
        // only gudang can delete products
        if (!isCurrentUserGudang()) {
            showAlert("Unauthorized", "Akses ditolak. Hanya user GUDANG yang dapat menghapus produk.");
            return;
        }
        Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                productService.delete(selectedProduct.getCode());
                tableView.getItems().clear();
                tableView.getItems().addAll(productService.findAll());
            } catch (Exception e) {
                showAlert("Error", "Gagal menghapus produk: " + e.getMessage());
            }
        } else {
            showAlert("Error", "Pilih produk untuk dihapus");
        }
    }

    public void loadProducts(TableView<Product> tableView) {
        // Only users with role GUDANG may view the product list
        if (!isCurrentUserGudang()) {
            tableView.getItems().clear();
            showAlert("Unauthorized", "Akses melihat produk dibatasi untuk user GUDANG.");
            return;
        }
        try {
            tableView.getItems().clear();
            tableView.getItems().addAll(productService.findAll());
        } catch (Exception e) {
            showAlert("Error", "Gagal memuat produk: " + e.getMessage());
        }
    }

    public void increaseStock(TableView<Product> tableView) {
        if (!isCurrentUserGudang()) {
            showAlert("Unauthorized", "Akses ditolak. Hanya user GUDANG yang dapat mengubah stok.");
            return;
        }
        Product selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Pilih produk untuk ditambah stok");
            return;
        }
        TextInputDialog dlg = new TextInputDialog("1");
        dlg.setTitle("Tambah Stok");
        dlg.setHeaderText("Masukkan jumlah stok yang ditambahkan untuk " + selected.getName());
        dlg.setContentText("Jumlah:");
        dlg.showAndWait().ifPresent(s -> {
            try {
                int qty = Integer.parseInt(s.trim());
                if (qty <= 0) throw new IllegalArgumentException("Jumlah harus positif");
                int newStock = selected.getStock() + qty;
                productService.updateStock(selected.getCode(), newStock);
                tableView.getItems().clear();
                tableView.getItems().addAll(productService.findAll());
            } catch (NumberFormatException ex) {
                showAlert("Error", "Jumlah harus angka");
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });
    }

    public void decreaseStock(TableView<Product> tableView) {
        if (!isCurrentUserGudang()) {
            showAlert("Unauthorized", "Akses ditolak. Hanya user GUDANG yang dapat mengubah stok.");
            return;
        }
        Product selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Pilih produk untuk dikurangi stok");
            return;
        }
        TextInputDialog dlg = new TextInputDialog("1");
        dlg.setTitle("Kurangi Stok");
        dlg.setHeaderText("Masukkan jumlah stok yang dikurangi untuk " + selected.getName());
        dlg.setContentText("Jumlah:");
        dlg.showAndWait().ifPresent(s -> {
            try {
                int qty = Integer.parseInt(s.trim());
                if (qty <= 0) throw new IllegalArgumentException("Jumlah harus positif");
                productService.decreaseStock(selected.getCode(), qty);
                tableView.getItems().clear();
                tableView.getItems().addAll(productService.findAll());
            } catch (NumberFormatException ex) {
                showAlert("Error", "Jumlah harus angka");
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });
    }

    private boolean isCurrentUserGudang() {
        try {
            var u = userService.getCurrentUser();
            return u != null && "gudang".equalsIgnoreCase(u.getRole());
        } catch (Exception e) {
            return false;
        }
    }

    public void addToCart(TableView<Product> productTable, ListView<String> cartList, Label totalLabel) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Tambah ke Keranjang");
            dialog.setHeaderText("Masukkan jumlah untuk " + selectedProduct.getName());
            dialog.setContentText("Jumlah:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(quantityStr -> {
                try {
                    int quantity = Integer.parseInt(quantityStr.trim());
                    cartService.addItem(selectedProduct.getCode(), quantity);
                    updateCartDisplay(cartList, totalLabel);
                } catch (NumberFormatException e) {
                    showAlert("Error", "Jumlah harus angka");
                } catch (IllegalArgumentException e) {
                    showAlert("Error", e.getMessage());
                } catch (Exception e) {
                    showAlert("Error", "Gagal menambah ke keranjang: " + e.getMessage());
                }
            });
        } else {
            showAlert("Error", "Pilih produk untuk ditambah ke keranjang");
        }
    }

    public void checkout(ListView<String> cartList, Label totalLabel) {
        if (cartService.getCart().getItems().isEmpty()) {
            showAlert("Error", "Keranjang kosong");
            return;
        }
        try {
            // Get current user
            var currentUser = userService.getCurrentUser();
            if (currentUser == null) {
                showAlert("Error", "User tidak ditemukan");
                return;
            }

            // Show payment dialog
            double totalAmount = cartService.getTotalPrice();
            PaymentDialog paymentDialog = new PaymentDialog(totalAmount);

            if (!paymentDialog.isConfirmed()) {
                showAlert("Info", "Pembayaran dibatalkan");
                return;
            }

            PaymentMethod paymentMethod = paymentDialog.getSelectedPaymentMethod();
            double paidAmount = paymentDialog.getPaidAmount();
            double change = paidAmount - totalAmount;

            // Decrease stock for each item in cart
            for (var item : cartService.getCart().getItems()) {
                productService.decreaseStock(item.getProduct().getCode(), item.getQuantity());
            }

            // Save cart to database
            int cartId = cartService.saveCart(currentUser.getId());
            if (cartId == -1) {
                throw new Exception("Gagal menyimpan keranjang");
            }

            // Save transaction with payment details
            String paymentMethodStr = paymentMethod.name();
            Transaction transaction = new Transaction(
                0, 
                cartId, 
                currentUser.getId(), 
                totalAmount, 
                paymentMethodStr, 
                "SUCCESS", 
                LocalDateTime.now(),
                paidAmount,
                change,
                currentUser.getFullName()
            );
            transactionService.saveTransaction(transaction);

            // Generate receipt text with payment details
            String receipt = generateReceipt(transaction);
            // Show receipt dialog (printable)
            ReceiptView.showReceipt(receipt);

            // Clear cart after checkout
            showAlert("Info", "Checkout berhasil. Total: " + String.format("Rp %,.0f", totalAmount));
            cartService.clearCart();
            updateCartDisplay(cartList, totalLabel);
        } catch (Exception e) {
            showAlert("Error", "Gagal checkout: " + e.getMessage());
        }
    }

    private String generateReceipt(Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append("================================\n");
        sb.append("\tAGRI-POS RECEIPT\n");
        sb.append("================================\n");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        sb.append("Tanggal: ").append(transaction.getTransactionDate().format(fmt)).append("\n");
        sb.append("Kasir: ").append(transaction.getCashierName()).append("\n");
        sb.append("--------------------------------\n");
        sb.append(String.format("%-20s %5s %10s %10s\n", "Item", "Qty", "Harga", "Subtotal"));
        sb.append("--------------------------------\n");
        for (var item : cartService.getCart().getItems()) {
            String name = item.getProduct().getName();
            int qty = item.getQuantity();
            double price = item.getProduct().getPrice();
            double subtotal = item.getTotalPrice();
            sb.append(String.format("%-20s %5d %10.0f %10.0f\n", name, qty, price, subtotal));
        }
        sb.append("--------------------------------\n");
        sb.append(String.format("%-20s %26.0f\n", "Total:", transaction.getTotalAmount()));
        sb.append("\n");
        sb.append(String.format("%-20s %26s\n", "Metode Pembayaran:", transaction.getPaymentMethod()));

        if (PaymentMethod.CASH.name().equals(transaction.getPaymentMethod())) {
            sb.append(String.format("%-20s %26.0f\n", "Jumlah Dibayar:", transaction.getPaidAmount()));
            sb.append(String.format("%-20s %26.0f\n", "Kembalian:", transaction.getChange()));
        }

        sb.append("\n\n");
        sb.append("\tTerima Kasih Atas Belanja Anda\n");
        sb.append("================================\n");
        return sb.toString();
    }

    private String generateReceipt() {
        // Kept for backward compatibility
        StringBuilder sb = new StringBuilder();
        sb.append("================================\n");
        sb.append("\tAGRI-POS RECEIPT\n");
        sb.append("================================\n");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        sb.append("Tanggal: ").append(LocalDateTime.now().format(fmt)).append("\n");
        sb.append("--------------------------------\n");
        sb.append(String.format("%-20s %5s %10s %10s\n", "Item", "Qty", "Harga", "Subtotal"));
        sb.append("--------------------------------\n");
        for (var item : cartService.getCart().getItems()) {
            String name = item.getProduct().getName();
            int qty = item.getQuantity();
            double price = item.getProduct().getPrice();
            double subtotal = item.getTotalPrice();
            sb.append(String.format("%-20s %5d %10.0f %10.0f\n", name, qty, price, subtotal));
        }
        sb.append("--------------------------------\n");
        sb.append(String.format("%-20s %26.0f\n", "Total:", cartService.getTotalPrice()));
        sb.append("\n");
        sb.append(String.format("%-20s %26s\n", "Metode Pembayaran:", "TUNAI"));
        sb.append(String.format("%-20s %26.0f\n", "Jumlah Dibayar:", cartService.getTotalPrice()));
        sb.append(String.format("%-20s %26.0f\n", "Kembalian:", 0.0));
        sb.append("\n\n");
        sb.append("\tTerima Kasih Atas Belanja Anda\n");
        sb.append("================================\n");
        return sb.toString();
    }

    private void updateCartDisplay(ListView<String> cartList, Label totalLabel) {
        cartList.getItems().clear();
        for (var item : cartService.getCart().getItems()) {
            cartList.getItems().add(item.getProduct().getName() + " x" + item.getQuantity() + " = " + item.getTotalPrice());
        }
        totalLabel.setText("Total: " + cartService.getTotalPrice());
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void loadKasirHistory(TableView<Transaction> historyTable, int userId) {
        try {
            historyTable.getItems().clear();
            historyTable.getItems().addAll(transactionService.getTransactionsByUserId(userId));
        } catch (Exception e) {
            showAlert("Error", "Gagal memuat history penjualan: " + e.getMessage());
        }
    }

    public void showDailySalesReport() {
        TextInputDialog dateDialog = new TextInputDialog(LocalDate.now().toString());
        dateDialog.setTitle("Laporan Penjualan Harian");
        dateDialog.setHeaderText("Masukkan tanggal (format: YYYY-MM-DD)");
        dateDialog.setContentText("Tanggal:");

        Optional<String> dateResult = dateDialog.showAndWait();
        if (dateResult.isPresent()) {
            try {
                LocalDate date = LocalDate.parse(dateResult.get().trim());
                String report = transactionService.generateDailyReport(date);

                Alert reportAlert = new Alert(Alert.AlertType.INFORMATION);
                reportAlert.setTitle("Laporan Penjualan Harian");
                reportAlert.setHeaderText("Laporan untuk tanggal " + date);
                reportAlert.setContentText(report);
                reportAlert.setResizable(true);
                reportAlert.showAndWait();
            } catch (Exception e) {
                showAlert("Error", "Format tanggal tidak valid: " + e.getMessage());
            }
        }
    }

    public void showCashierSalesReport() {
        try {
            var currentUser = userService.getCurrentUser();
            if (currentUser == null) {
                showAlert("Error", "User tidak ditemukan");
                return;
            }

            TextInputDialog startDateDialog = new TextInputDialog(LocalDate.now().minusDays(7).toString());
            startDateDialog.setTitle("Laporan Penjualan Kasir");
            startDateDialog.setHeaderText("Masukkan tanggal mulai (format: YYYY-MM-DD)");
            startDateDialog.setContentText("Tanggal Mulai:");
            Optional<String> startResult = startDateDialog.showAndWait();

            if (startResult.isPresent()) {
                LocalDate startDate = LocalDate.parse(startResult.get().trim());

                TextInputDialog endDateDialog = new TextInputDialog(LocalDate.now().toString());
                endDateDialog.setTitle("Laporan Penjualan Kasir");
                endDateDialog.setHeaderText("Masukkan tanggal akhir (format: YYYY-MM-DD)");
                endDateDialog.setContentText("Tanggal Akhir:");
                Optional<String> endResult = endDateDialog.showAndWait();

                if (endResult.isPresent()) {
                    LocalDate endDate = LocalDate.parse(endResult.get().trim());
                    String report = transactionService.generateCashierReport(currentUser.getId(), startDate, endDate);

                    Alert reportAlert = new Alert(Alert.AlertType.INFORMATION);
                    reportAlert.setTitle("Laporan Penjualan Kasir");
                    reportAlert.setHeaderText("Laporan untuk kasir " + currentUser.getFullName());
                    reportAlert.setContentText(report);
                    reportAlert.setResizable(true);
                    reportAlert.showAndWait();
                }
            }
        } catch (Exception e) {
            showAlert("Error", "Gagal membuat laporan: " + e.getMessage());
        }
    }

    public void showStockInReport() {
        try {
            var products = productService.findAll();
            int totalProducts = products.size();
            int totalStock = products.stream().mapToInt(Product::getStock).sum();
            double totalValue = products.stream().mapToDouble(p -> p.getPrice() * p.getStock()).sum();

            StringBuilder report = new StringBuilder();
            report.append("================================\n");
            report.append("    LAPORAN PRODUK MASUK\n");
            report.append("================================\n");
            report.append("Tanggal: ").append(LocalDate.now()).append("\n");
            report.append("Total Jenis Produk: ").append(totalProducts).append("\n");
            report.append("Total Stok: ").append(totalStock).append(" unit\n");
            report.append("Total Nilai: Rp ").append(String.format("%,.0f", totalValue)).append("\n");
            report.append("--------------------------------\n");
            report.append("Detail Produk:\n");
            for (Product product : products) {
                double productValue = product.getPrice() * product.getStock();
                report.append("- ").append(product.getName())
                      .append(" (").append(product.getCode()).append(")")
                      .append(": ").append(product.getStock()).append(" unit")
                      .append(" = Rp ").append(String.format("%,.0f", productValue)).append("\n");
            }
            report.append("================================\n");

            Alert reportAlert = new Alert(Alert.AlertType.INFORMATION);
            reportAlert.setTitle("Laporan Produk Masuk");
            reportAlert.setHeaderText("Laporan inventaris produk saat ini");
            reportAlert.setContentText(report.toString());
            reportAlert.setResizable(true);
            reportAlert.showAndWait();
        } catch (Exception e) {
            showAlert("Error", "Gagal membuat laporan produk masuk: " + e.getMessage());
        }
    }

    public void showStockOutReport() {
        try {
            // Ask for date range
            TextInputDialog startDateDialog = new TextInputDialog(LocalDate.now().minusDays(7).toString());
            startDateDialog.setTitle("Laporan Produk Keluar");
            startDateDialog.setHeaderText("Masukkan tanggal mulai (format: YYYY-MM-DD)");
            startDateDialog.setContentText("Tanggal Mulai:");
            Optional<String> startResult = startDateDialog.showAndWait();

            if (startResult.isPresent()) {
                java.time.LocalDate startDate = java.time.LocalDate.parse(startResult.get().trim());

                TextInputDialog endDateDialog = new TextInputDialog(LocalDate.now().toString());
                endDateDialog.setTitle("Laporan Produk Keluar");
                endDateDialog.setHeaderText("Masukkan tanggal akhir (format: YYYY-MM-DD)");
                endDateDialog.setContentText("Tanggal Akhir:");
                Optional<String> endResult = endDateDialog.showAndWait();

                if (endResult.isPresent()) {
                    java.time.LocalDate endDate = java.time.LocalDate.parse(endResult.get().trim());
                    java.time.LocalDateTime start = startDate.atStartOfDay();
                    java.time.LocalDateTime end = endDate.atTime(23,59,59);

                    var soldList = cartService.getSoldProductsByDateRange(start, end);

                    int totalProductsSold = soldList.stream().mapToInt(com.upb.agripos.model.SoldProduct::getQuantitySold).sum();
                    double totalSalesValue = soldList.stream().mapToDouble(com.upb.agripos.model.SoldProduct::getTotalValue).sum();

                    StringBuilder report = new StringBuilder();
                    report.append("================================\n");
                    report.append("    LAPORAN PRODUK KELUAR\n");
                    report.append("================================\n");
                    report.append("Periode: ").append(startDate).append(" - ").append(endDate).append("\n");
                    report.append("Total Jenis Produk Terjual: ").append(soldList.size()).append("\n");
                    report.append("Total Unit Terjual: ").append(totalProductsSold).append(" unit\n");
                    report.append("Total Nilai Penjualan: Rp ").append(String.format("%,.0f", totalSalesValue)).append("\n");
                    report.append("--------------------------------\n");
                    report.append("Detail Produk Terjual:\n");
                    for (com.upb.agripos.model.SoldProduct sp : soldList) {
                        report.append("- ").append(sp.getName())
                              .append(" (").append(sp.getProductCode()).append(")")
                              .append(": ").append(sp.getQuantitySold()).append(" unit")
                              .append(" = Rp ").append(String.format("%,.0f", sp.getTotalValue())).append("\n");
                    }
                    report.append("================================\n");

                    Alert reportAlert = new Alert(Alert.AlertType.INFORMATION);
                    reportAlert.setTitle("Laporan Produk Keluar");
                    reportAlert.setHeaderText("Laporan produk keluar");
                    reportAlert.setContentText(report.toString());
                    reportAlert.setResizable(true);
                    reportAlert.showAndWait();
                }
            }
        } catch (Exception e) {
            showAlert("Error", "Gagal membuat laporan produk keluar: " + e.getMessage());
        }
    }
}
