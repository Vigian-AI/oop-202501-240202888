package com.upb.agripos.view;

import java.util.function.Consumer;

import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import com.upb.agripos.model.Transaction;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class KasirView extends VBox {
    private TableView<Product> productTable;
    private Button btnAddToCart, btnCheckout;
    private TableView<CartItem> cartTable;
    private Consumer<String> onIncreaseQuantity;
    private Consumer<String> onDecreaseQuantity;
    private Label totalLabel;
    private Label userInfoLabel;
    private Button btnLogout;
    private TabPane tabPane;
    private TableView<Transaction> historyTable;

    public KasirView() {
        // ========== MAIN STYLING ==========
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #f0f0f0;");

        // ========== HEADER ==========
        VBox headerBox = createHeaderBox();

        // ========== USER INFO ==========
        HBox userInfoBox = new HBox(10);
        userInfoBox.setAlignment(Pos.CENTER_LEFT);
        userInfoLabel = new Label("👤 User: -");
        userInfoLabel.setStyle("-fx-font-size: 13; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");
        btnLogout = createStyledButton("🚪 Logout", "#c62828");
        userInfoBox.getChildren().addAll(userInfoLabel, btnLogout);

        // ========== TAB PANE ==========
        tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-border-radius: 5;");

        // Tab Penjualan
        Tab tabPenjualan = new Tab("Penjualan");
        tabPenjualan.setClosable(false);
        tabPenjualan.setContent(createPenjualanTab());

        // Tab History
        Tab tabHistory = new Tab("History Penjualan");
        tabHistory.setClosable(false);
        tabHistory.setContent(createHistoryTab());

        tabPane.getTabs().addAll(tabPenjualan, tabHistory);

        // ========== ASSEMBLE MAIN LAYOUT ==========
        this.getChildren().addAll(headerBox, userInfoBox, tabPane);
    }

    private VBox createHeaderBox() {
        VBox headerBox = new VBox(5);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("🛒 SISTEM KASIR AGRIPOS");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");

        Label subtitleLabel = new Label("Kelola transaksi penjualan dengan mudah");
        subtitleLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #666666;");

        headerBox.getChildren().addAll(titleLabel, subtitleLabel);
        return headerBox;
    }

    private VBox createPenjualanTab() {
        VBox penjualanTab = new VBox(10);
        penjualanTab.setStyle("-fx-background-color: #fafafa; -fx-padding: 12;");

        // ========== PRODUCT TABLE SECTION ==========
        VBox tableSection = new VBox(10);
        tableSection.setStyle("-fx-border-color: #e0e0e0; -fx-border-radius: 5; " +
                "-fx-background-color: #fafafa; -fx-padding: 12;");

        Label tableLabel = new Label("📊 Daftar Produk Tersedia");
        tableLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");

        // Product Table
        productTable = new TableView<>();
        productTable.setStyle("-fx-font-size: 10; -fx-padding: 5;"); // Lebih kecil font

        TableColumn<Product, String> nameCol = new TableColumn<>("Nama Produk");
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        nameCol.setPrefWidth(120); // Lebih kecil

        TableColumn<Product, Double> priceCol = new TableColumn<>("Harga");
        priceCol.setCellValueFactory(cell -> cell.getValue().priceProperty().asObject());
        priceCol.setPrefWidth(70); // Lebih kecil

        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stok");
        stockCol.setCellValueFactory(cell -> cell.getValue().stockProperty().asObject());
        stockCol.setPrefWidth(50); // Lebih kecil

        productTable.getColumns().addAll(nameCol, priceCol, stockCol);
        productTable.setPrefHeight(200); // Lebih kecil

        btnAddToCart = createStyledButton("🛒 Tambah ke Keranjang", "#2E7D32");

        tableSection.getChildren().addAll(tableLabel, productTable, btnAddToCart);
        VBox.setVgrow(productTable, Priority.ALWAYS);

        // ========== CART PANEL ==========
        VBox cartPanel = createCartPanel();

        // ========== MAIN CONTENT BOX ==========
        HBox contentBox = new HBox(20);
        contentBox.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-border-radius: 5;");

        VBox leftPanel = new VBox(15, tableSection);
        VBox.setVgrow(tableSection, Priority.ALWAYS);
        HBox.setHgrow(leftPanel, Priority.ALWAYS);

        contentBox.getChildren().addAll(leftPanel, cartPanel);

        penjualanTab.getChildren().add(contentBox);
        VBox.setVgrow(contentBox, Priority.ALWAYS);

        return penjualanTab;
    }

    private VBox createHistoryTab() {
        VBox historyTab = new VBox(10);
        historyTab.setStyle("-fx-background-color: #fafafa; -fx-padding: 12;");

        Label historyLabel = new Label("📜 Riwayat Penjualan");
        historyLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");

        // History Table
        historyTable = new TableView<>();
        historyTable.setStyle("-fx-font-size: 11; -fx-padding: 5;");

        TableColumn<Transaction, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);

        TableColumn<Transaction, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        totalCol.setPrefWidth(100);

        TableColumn<Transaction, String> paymentCol = new TableColumn<>("Pembayaran");
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        paymentCol.setPrefWidth(100);

        TableColumn<Transaction, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(80);

        TableColumn<Transaction, String> dateCol = new TableColumn<>("Tanggal");
        dateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(
            cell.getValue().getTransactionDate().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
        ));
        dateCol.setPrefWidth(150);

        historyTable.getColumns().addAll(idCol, totalCol, paymentCol, statusCol, dateCol);
        historyTable.setPrefHeight(300);

        historyTab.getChildren().addAll(historyLabel, historyTable);
        VBox.setVgrow(historyTable, Priority.ALWAYS);

        return historyTab;
    }

    private VBox createCartPanel() {
        VBox cartPanel = new VBox(12);
        cartPanel.setStyle("-fx-border-color: #2E7D32; -fx-border-radius: 5; " +
                "-fx-background-color: white; -fx-padding: 15; -fx-border-width: 2;");
        cartPanel.setPrefWidth(840);

        Label cartTitleLabel = new Label("🛍️ KERANJANG BELANJA");
        cartTitleLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");

        Separator separator1 = new Separator();
        separator1.setStyle("-fx-padding: 0;");

        // Cart Table
        cartTable = new TableView<>();
        cartTable.setStyle("-fx-font-size: 11; -fx-padding: 5;");
        cartTable.setPrefHeight(900); // Perbesar tabel keranjang (3x)

        TableColumn<CartItem, String> nameCol = new TableColumn<>("Produk");
        nameCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getProduct().getName()));
        nameCol.setPrefWidth(120);

        TableColumn<CartItem, Integer> qtyCol = new TableColumn<>("Qty");
        qtyCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());
        qtyCol.setPrefWidth(50);

        TableColumn<CartItem, Double> priceCol = new TableColumn<>("Harga");
        priceCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleDoubleProperty(cell.getValue().getProduct().getPrice()).asObject());
        priceCol.setPrefWidth(80);

        TableColumn<CartItem, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleDoubleProperty(cell.getValue().getTotalPrice()).asObject());
        totalCol.setPrefWidth(80);

        TableColumn<CartItem, Void> actionCol = new TableColumn<>("Aksi");
        actionCol.setPrefWidth(80);
        actionCol.setCellFactory(param -> new javafx.scene.control.TableCell<>() {
            private final Button btnPlus = new Button("+");
            private final Button btnMinus = new Button("-");
            private final HBox hbox = new HBox(5, btnMinus, btnPlus);

            {
                btnPlus.setStyle("-fx-font-size: 10; -fx-padding: 2 5; -fx-background-color: #4CAF50; -fx-text-fill: white;");
                btnMinus.setStyle("-fx-font-size: 10; -fx-padding: 2 5; -fx-background-color: #f44336; -fx-text-fill: white;");
                hbox.setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    CartItem cartItem = getTableView().getItems().get(getIndex());
                    btnPlus.setOnAction(e -> {
                        if (onIncreaseQuantity != null) {
                            onIncreaseQuantity.accept(cartItem.getProduct().getCode());
                        }
                    });
                    btnMinus.setOnAction(e -> {
                        if (onDecreaseQuantity != null) {
                            onDecreaseQuantity.accept(cartItem.getProduct().getCode());
                        }
                    });
                    setGraphic(hbox);
                }
            }
        });

        cartTable.getColumns().addAll(nameCol, qtyCol, priceCol, totalCol, actionCol);

        Separator separator2 = new Separator();

        // Total
        totalLabel = new Label("💰 Total: Rp 0");
        totalLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #2E7D32; -fx-padding: 10;");
        totalLabel.setAlignment(Pos.CENTER_RIGHT);

        // Checkout Button
        btnCheckout = createStyledButton("✅ CHECKOUT", "#1976d2");
        btnCheckout.setStyle("-fx-font-size: 12; -fx-font-weight: bold; -fx-padding: 12; " +
                "-fx-background-color: #1976d2; -fx-text-fill: white; -fx-cursor: hand; " +
                "-fx-border-radius: 5;");
        btnCheckout.setPrefHeight(45);

        cartPanel.getChildren().addAll(
            cartTitleLabel,
            separator1,
            cartTable,
            separator2,
            totalLabel,
            btnCheckout
        );

        VBox.setVgrow(cartTable, Priority.ALWAYS);
        return cartPanel;
    }

    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setPrefHeight(35);
        button.setStyle("-fx-font-size: 11; -fx-font-weight: bold; -fx-padding: 8; " +
                "-fx-background-color: " + color + "; -fx-text-fill: white; -fx-cursor: hand; " +
                "-fx-border-radius: 3;");

        // Hover effect
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-font-size: 11; -fx-font-weight: bold; -fx-padding: 8; " +
                    "-fx-background-color: " + darkenColor(color) + "; -fx-text-fill: white; " +
                    "-fx-cursor: hand; -fx-border-radius: 3;");
        });

        button.setOnMouseExited(e -> {
            button.setStyle("-fx-font-size: 11; -fx-font-weight: bold; -fx-padding: 8; " +
                    "-fx-background-color: " + color + "; -fx-text-fill: white; -fx-cursor: hand; " +
                    "-fx-border-radius: 3;");
        });

        return button;
    }

    private String darkenColor(String color) {
        return switch (color) {
            case "#2E7D32" -> "#1B5E20";
            case "#d32f2f" -> "#b71c1c";
            case "#1976d2" -> "#0d47a1";
            case "#f57c00" -> "#e65100";
            case "#555555" -> "#212121";
            default -> color;
        };
    }

    // ========== GETTERS ==========
    public TableView<Product> getProductTable() {
        return productTable;
    }

    public Button getBtnAddToCart() {
        return btnAddToCart;
    }

    public Button getBtnCheckout() {
        return btnCheckout;
    }

    public TableView<CartItem> getCartTable() {
        return cartTable;
    }

    public void setOnIncreaseQuantity(Consumer<String> callback) {
        this.onIncreaseQuantity = callback;
    }

    public void setOnDecreaseQuantity(Consumer<String> callback) {
        this.onDecreaseQuantity = callback;
    }

    public Label getTotalLabel() {
        return totalLabel;
    }

    public Label getUserInfoLabel() {
        return userInfoLabel;
    }

    public Button getBtnLogout() {
        return btnLogout;
    }

    public TableView<Transaction> getHistoryTable() {
        return historyTable;
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
