package com.upb.agripos.view;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.model.Product;
import com.upb.agripos.model.User;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PosView extends VBox {
    private final User currentUser;
    private final Runnable onLogout;

    public PosView(PosController controller, User currentUser, Runnable onLogout) {
        this.currentUser = currentUser;
        this.onLogout = onLogout;
        this.setPadding(new Insets(15));
        this.setSpacing(10);

        // ========== HEADER ==========
        HBox headerBox = new HBox();
        headerBox.setStyle("-fx-background-color: #2E7D32; -fx-padding: 10;");
        headerBox.setSpacing(10);

        Label headerTitle = new Label("🌾 AGRIPOS - Sistem POS");
        headerTitle.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: white;");

        Label userInfoLabel = new Label("👤 " + currentUser.getFullName() + " (" + currentUser.getRole() + ")");
        userInfoLabel.setStyle("-fx-font-size: 12; -fx-text-fill: white;");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button logoutBtn = new Button("🚪 Logout");
        logoutBtn.setStyle("-fx-font-size: 11; -fx-padding: 5; -fx-background-color: #d32f2f; -fx-text-fill: white;");
        logoutBtn.setOnAction(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Logout");
            confirm.setHeaderText(null);
            confirm.setContentText("Apakah Anda yakin ingin logout?");
            confirm.getButtonTypes().setAll(javafx.scene.control.ButtonType.YES, javafx.scene.control.ButtonType.NO);
            if (confirm.showAndWait().get() == javafx.scene.control.ButtonType.YES) {
                if (onLogout != null) {
                    onLogout.run();
                }
            }
        });

        headerBox.getChildren().addAll(headerTitle, spacer, userInfoLabel, logoutBtn);

        // ========== SECTION 1: INPUT PRODUK ==========
        Label titleProduct = new Label("📦 INPUT PRODUK");
        titleProduct.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        TextField code = new TextField();
        code.setPromptText("Kode Produk");
        TextField name = new TextField();
        name.setPromptText("Nama Produk");
        TextField price = new TextField();
        price.setPromptText("Harga");
        TextField stock = new TextField();
        stock.setPromptText("Stok");

        HBox inputBox = new HBox(5);
        inputBox.getChildren().addAll(code, name, price, stock);

        // ========== SECTION 2: TABEL PRODUK ==========
        Label titleTable = new Label("📋 DAFTAR PRODUK");
        titleTable.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        TableView<Product> tableProduct = new TableView<>();
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCode.setPrefWidth(80);

        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setPrefWidth(150);

        TableColumn<Product, Double> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setPrefWidth(100);

        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colStock.setPrefWidth(80);

        tableProduct.getColumns().addAll(colCode, colName, colPrice, colStock);
        tableProduct.setItems(javafx.collections.FXCollections.observableArrayList(controller.getProducts()));
        tableProduct.setPrefHeight(200);

        Button add = new Button("➕ Tambah Produk");
        add.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        add.setOnAction(e -> {
            try {
                if (code.getText().isEmpty() || name.getText().isEmpty() || 
                    price.getText().isEmpty() || stock.getText().isEmpty()) {
                    showAlert("Validasi", "Semua field harus diisi!");
                    return;
                }
                controller.addProduct(
                    new Product(
                        code.getText(),
                        name.getText(),
                        Double.parseDouble(price.getText()),
                        Integer.parseInt(stock.getText())
                    )
                );
                code.clear();
                name.clear();
                price.clear();
                stock.clear();
                tableProduct.getItems().setAll(controller.getProducts());
                showAlert("Sukses", "Produk berhasil ditambahkan!");
            } catch (NumberFormatException ex) {
                showAlert("Error", "Harga dan Stok harus berupa angka!");
            }
        });

        Button refreshData = new Button("🔄 Refresh");
        refreshData.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        refreshData.setOnAction(e -> {
            tableProduct.getItems().setAll(controller.getProducts());
        });

        HBox tableButtonBox = new HBox(5);
        tableButtonBox.getChildren().addAll(add, refreshData);

        Button delete = new Button("🗑️ Hapus Produk");
        delete.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        delete.setOnAction(e -> {
            Product selected = tableProduct.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Validasi", "Pilih produk yang ingin dihapus!");
                return;
            }
            controller.deleteProduct(selected.getCode());
            tableProduct.getItems().setAll(controller.getProducts());
            showAlert("Sukses", "Produk berhasil dihapus!");
        });

        // ========== SECTION 3: KERANJANG ==========
        Label titleCart = new Label("🛒 KERANJANG BELANJA");
        titleCart.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        Spinner<Integer> qty = new Spinner<>(1, 1000, 1);
        qty.setPrefWidth(80);

        Button addCart = new Button("➕ Ke Keranjang");
        addCart.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        addCart.setOnAction(e -> {
            Product selected = tableProduct.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Validasi", "Pilih produk dulu!");
                return;
            }
            if (qty.getValue() > selected.getStock()) {
                showAlert("Error", "Stok tidak cukup! Tersedia: " + selected.getStock());
                return;
            }
            controller.addToCart(selected, qty.getValue());
            showAlert("Sukses", selected.getName() + " (" + qty.getValue() + "x) ditambahkan");
        });

        HBox cartBox = new HBox(5);
        cartBox.getChildren().addAll(new Label("Qty:"), qty, addCart);

        Label totalLabel = new Label("Total: Rp 0");
        totalLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");

        Button checkout = new Button("✅ CHECKOUT");
        checkout.setStyle("-fx-font-size: 14; -fx-padding: 10; -fx-font-weight: bold;");
        checkout.setOnAction(e -> {
            double total = controller.getCartTotal();
            if (total == 0) {
                showAlert("Validasi", "Keranjang kosong!");
                return;
            }
            showAlert("Pembayaran", "Total: Rp " + String.format("%.0f", total));
            controller.clearCart();
        });

        // ========== ASSEMBLE ==========
        this.getChildren().addAll(
            headerBox,
            titleProduct,
            inputBox,
            titleTable,
            tableButtonBox,
            tableProduct,
            delete,
            titleCart,
            cartBox,
            totalLabel,
            checkout
        );
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
