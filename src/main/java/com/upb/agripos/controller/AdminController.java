package com.upb.agripos.controller;

import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.User;
import com.upb.agripos.service.TransactionService;
import com.upb.agripos.service.UserService;
import javafx.scene.control.*;

public class AdminController {
    private final UserService userService;
    private final TransactionService transactionService;

    public AdminController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    public void addUser(TextField txtUsername, TextField txtPassword, TextField txtFullName, ComboBox<String> cbRole, TableView<User> userTable) {
        try {
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            String fullName = txtFullName.getText().trim();
            String role = cbRole.getValue();

            if (userService.addUser(username, password, fullName, role)) {
                showAlert("Sukses", "User berhasil ditambahkan");
                clearFields(txtUsername, txtPassword, txtFullName);
                loadUsers(userTable);
            } else {
                showAlert("Error", "Gagal menambahkan user. Username mungkin sudah ada.");
            }
        } catch (Exception e) {
            showAlert("Error", "Gagal menambahkan user: " + e.getMessage());
        }
    }

    public void loadUsers(TableView<User> userTable) {
        try {
            userTable.getItems().clear();
            userTable.getItems().addAll(userService.getAllUsers());
        } catch (Exception e) {
            showAlert("Error", "Gagal memuat users: " + e.getMessage());
        }
    }

    public void loadHistory(TableView<Transaction> historyTable) {
        try {
            historyTable.getItems().clear();
            historyTable.getItems().addAll(transactionService.getAllTransactions());
        } catch (Exception e) {
            showAlert("Error", "Gagal memuat history: " + e.getMessage());
        }
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
}
