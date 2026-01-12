package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.service.CartService;
import javafx.scene.control.*;
import java.util.Optional;

public class PosController {
    private final ProductService productService;
    private final CartService cartService;

    public PosController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public void addProduct(TextField txtCode, TextField txtName, TextField txtPrice, TextField txtStock, TableView<Product> tableView) {
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
        try {
            tableView.getItems().clear();
            tableView.getItems().addAll(productService.findAll());
        } catch (Exception e) {
            showAlert("Error", "Gagal memuat produk: " + e.getMessage());
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
        // For simplicity, just clear cart after "checkout"
        showAlert("Info", "Checkout berhasil. Total: " + cartService.getTotalPrice());
        cartService.clearCart();
        updateCartDisplay(cartList, totalLabel);
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
}