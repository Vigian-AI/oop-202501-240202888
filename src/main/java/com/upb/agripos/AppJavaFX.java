package com.upb.agripos;// Import necessary JavaFX and other classes
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Import the Product class and ProductService (assuming they exist in the project)
import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;

public class AppJavaFX extends Application {
    private final ProductService productService = new ProductService(); // Initialize ProductService

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        TextField txtCode = new TextField();
        txtCode.setPromptText("Kode Produk");

        TextField txtName = new TextField();
        txtName.setPromptText("Nama Produk");

        TextField txtPrice = new TextField();
        txtPrice.setPromptText("Harga");

        TextField txtStock = new TextField();
        txtStock.setPromptText("Stok");

        Button btnAdd = new Button("Tambah Produk");
        ListView<String> listView = new ListView<>();

        // Event handling for the Add button
        btnAdd.setOnAction(event -> {
            try {
                Product p = new Product(
                        txtCode.getText(),
                        txtName.getText(),
                        Double.parseDouble(txtPrice.getText()),
                        Integer.parseInt(txtStock.getText())
                );
                productService.insert(p); // productService mengarah ke DAO
                listView.getItems().add(p.getCode() + " - " + p.getName());

                // Clear input fields after adding
                txtCode.clear();
                txtName.clear();
                txtPrice.clear();
                txtStock.clear();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Harga dan Stok harus berupa angka.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Layout setup
        VBox layout = new VBox(10, txtCode, txtName, txtPrice, txtStock, btnAdd, listView);
        Scene scene = new Scene(layout, 400, 400);

        primaryStage.setTitle("Daftar Produk Agri-POS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Update the run configuration to include JavaFX runtime arguments
// Add the following VM options:
// --module-path "C:\Users\Vg_\javafx-sdk-17.0.17\lib" --add-modules javafx.controls,javafx.fxml

// Ensure the JavaFX SDK is properly installed and the path is correct.
