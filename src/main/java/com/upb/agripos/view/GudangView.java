package com.upb.agripos.view;

import com.upb.agripos.model.Product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GudangView extends VBox {
    private TextField txtCode, txtName, txtPrice, txtStock;
    private Button btnAddProduct, btnDeleteProduct, btnIncreaseStock, btnDecreaseStock, btnRefresh;
    private TableView<Product> productTable;
    private Label userInfoLabel;

    public GudangView() {
        // ========== MAIN STYLING ==========
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #f0f0f0;");

        // ========== HEADER ==========
        VBox headerBox = createHeaderBox();

        // ========== USER INFO ==========
        userInfoLabel = new Label("👤 User: -");
        userInfoLabel.setStyle("-fx-font-size: 13; -fx-font-weight: bold; -fx-text-fill: #d84315;");

        // ========== PRODUCT FORM SECTION ==========
        VBox formSection = createFormSection();

        // ========== PRODUCT TABLE SECTION ==========
        VBox tableSection = createTableSection();

        // ========== MAIN CONTENT BOX ==========
        HBox contentBox = new HBox(20);
        contentBox.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-border-radius: 5;");

        VBox leftPanel = new VBox(15, formSection, tableSection);
        VBox.setVgrow(tableSection, Priority.ALWAYS);
        HBox.setHgrow(leftPanel, Priority.ALWAYS);

        contentBox.getChildren().addAll(leftPanel);

        // ========== ASSEMBLE MAIN LAYOUT ==========
        this.getChildren().addAll(headerBox, userInfoLabel, contentBox);
    }

    private VBox createHeaderBox() {
        VBox headerBox = new VBox(5);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("📦 SISTEM MANAJEMEN GUDANG AGRIPOS");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #d84315;");

        Label subtitleLabel = new Label("Kelola stok produk dan inventaris dengan mudah");
        subtitleLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #666666;");

        headerBox.getChildren().addAll(titleLabel, subtitleLabel);
        return headerBox;
    }

    private VBox createFormSection() {
        VBox formSection = new VBox(10);
        formSection.setStyle("-fx-border-color: #ffb74d; -fx-border-radius: 5; " +
                "-fx-background-color: #fff8f0; -fx-padding: 12;");

        Label formLabel = new Label("📝 Tambah / Edit Produk");
        formLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #d84315;");

        // Input Fields
        txtCode = createStyledTextField("Kode Produk (cth: P001)");
        txtName = createStyledTextField("Nama Produk");
        txtPrice = createStyledTextField("Harga");
        txtStock = createStyledTextField("Stok");

        HBox inputRow1 = new HBox(10, txtCode, txtName);
        HBox.setHgrow(txtCode, Priority.ALWAYS);
        HBox.setHgrow(txtName, Priority.ALWAYS);

        HBox inputRow2 = new HBox(10, txtPrice, txtStock);
        HBox.setHgrow(txtPrice, Priority.ALWAYS);
        HBox.setHgrow(txtStock, Priority.ALWAYS);

        // Buttons
        btnAddProduct = createStyledButton("➕ Tambah Produk", "#d84315");
        btnDeleteProduct = createStyledButton("🗑️ Hapus Produk", "#c62828");
        btnIncreaseStock = createStyledButton("⬆️ Tambah Stok", "#1976d2");
        btnDecreaseStock = createStyledButton("⬇️ Kurangi Stok", "#f57c00");
        btnRefresh = createStyledButton("🔄 Refresh", "#555555");

        HBox buttonRow1 = new HBox(8, btnAddProduct, btnDeleteProduct);
        HBox.setHgrow(btnAddProduct, Priority.ALWAYS);
        HBox.setHgrow(btnDeleteProduct, Priority.ALWAYS);

        HBox buttonRow2 = new HBox(8, btnIncreaseStock, btnDecreaseStock, btnRefresh);
        HBox.setHgrow(btnIncreaseStock, Priority.ALWAYS);
        HBox.setHgrow(btnDecreaseStock, Priority.ALWAYS);
        HBox.setHgrow(btnRefresh, Priority.ALWAYS);

        formSection.getChildren().addAll(
            formLabel,
            inputRow1,
            inputRow2,
            buttonRow1,
            buttonRow2
        );

        return formSection;
    }

    private VBox createTableSection() {
        VBox tableSection = new VBox(10);
        tableSection.setStyle("-fx-border-color: #e0e0e0; -fx-border-radius: 5; " +
                "-fx-background-color: #fafafa; -fx-padding: 12;");

        Label tableLabel = new Label("📊 Daftar Produk di Gudang");
        tableLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #d84315;");

        // Product Table
        productTable = new TableView<>();
        productTable.setStyle("-fx-font-size: 11; -fx-padding: 5;");

        TableColumn<Product, String> codeCol = new TableColumn<>("Kode");
        codeCol.setCellValueFactory(cell -> cell.getValue().codeProperty());
        codeCol.setPrefWidth(80);

        TableColumn<Product, String> nameCol = new TableColumn<>("Nama Produk");
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        nameCol.setPrefWidth(150);

        TableColumn<Product, Double> priceCol = new TableColumn<>("Harga");
        priceCol.setCellValueFactory(cell -> cell.getValue().priceProperty().asObject());
        priceCol.setPrefWidth(100);

        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stok");
        stockCol.setCellValueFactory(cell -> cell.getValue().stockProperty().asObject());
        stockCol.setPrefWidth(80);

        productTable.getColumns().addAll(codeCol, nameCol, priceCol, stockCol);
        productTable.setPrefHeight(300);

        tableSection.getChildren().addAll(tableLabel, productTable);
        VBox.setVgrow(productTable, Priority.ALWAYS);

        return tableSection;
    }

    private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setPrefHeight(35);
        textField.setStyle("-fx-font-size: 11; -fx-padding: 8; -fx-border-radius: 3; " +
                "-fx-border-color: #ffb74d; -fx-background-color: white;");
        return textField;
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
            case "#d84315" -> "#bf360c";
            case "#c62828" -> "#ad1457";
            case "#1976d2" -> "#0d47a1";
            case "#f57c00" -> "#e65100";
            case "#555555" -> "#212121";
            default -> color;
        };
    }

    // ========== GETTERS ==========
    public TextField getTxtCode() {
        return txtCode;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public TextField getTxtPrice() {
        return txtPrice;
    }

    public TextField getTxtStock() {
        return txtStock;
    }

    public Button getBtnAddProduct() {
        return btnAddProduct;
    }

    public Button getBtnDeleteProduct() {
        return btnDeleteProduct;
    }

    public Button getBtnIncreaseStock() {
        return btnIncreaseStock;
    }

    public Button getBtnDecreaseStock() {
        return btnDecreaseStock;
    }

    public Button getBtnRefresh() {
        return btnRefresh;
    }

    public TableView<Product> getProductTable() {
        return productTable;
    }

    public Label getUserInfoLabel() {
        return userInfoLabel;
    }
}
