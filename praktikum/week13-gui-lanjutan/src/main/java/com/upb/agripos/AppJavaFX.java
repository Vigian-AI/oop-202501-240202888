package com.upb.agripos;// Import necessary JavaFX and other classes
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;

public class AppJavaFX extends Application {

    private TableView<Product> tableView;
    private TextField txtCode, txtName, txtPrice, txtStock;
    private ProductController productController;

    // Apply @SuppressWarnings("unchecked") to the method declaration
    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        // Initialize input fields
        txtCode = new TextField();
        txtCode.setPromptText("Kode Produk");

        txtName = new TextField();
        txtName.setPromptText("Nama Produk");

        txtPrice = new TextField();
        txtPrice.setPromptText("Harga");

        txtStock = new TextField();
        txtStock.setPromptText("Stok");

        // Initialize buttons as local variables
        Button btnAdd = new Button("Tambah Produk");
        Button btnDelete = new Button("Hapus Produk");

        // Initialize ProductController with required arguments
        productController = new ProductController(txtCode, txtName, txtPrice, txtStock, btnAdd, new ListView<>(), new ProductService());

        // Initialize TableView
        tableView = new TableView<>();
        TableColumn<Product, String> codeColumn = new TableColumn<>("Kode");
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());

        TableColumn<Product, String> nameColumn = new TableColumn<>("Nama");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Harga");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<Product, Integer> stockColumn = new TableColumn<>("Stok");
        stockColumn.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());

        tableView.getColumns().addAll(codeColumn, nameColumn, priceColumn, stockColumn);

        btnAdd.setOnAction(event -> productController.addProduct(txtCode, txtName, txtPrice, txtStock, tableView));
        btnDelete.setOnAction(event -> productController.deleteProduct(tableView));

        // Layout
        VBox layout = new VBox(10, tableView, txtCode, txtName, txtPrice, txtStock, btnAdd, btnDelete);

        // Scene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Agri-POS - Kelola Produk");
        primaryStage.show();

        // Load initial data
        productController.loadData(tableView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Update the run configuration to include JavaFX runtime arguments
// Add the following VM options:
// --module-path "C:\Users\Vg_\javafx-sdk-17.0.17\lib" --add-modules javafx.controls,javafx.fxml

// Ensure the JavaFX SDK is properly installed and the path is correct.
