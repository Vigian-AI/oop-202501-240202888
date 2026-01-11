package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProductFormView extends VBox {

    public ProductFormView(ProductController controller) {

        TextField txtCode = new TextField();
        txtCode.setPromptText("Kode Produk");

        TextField txtName = new TextField();
        txtName.setPromptText("Nama Produk");

        TextField txtPrice = new TextField();
        txtPrice.setPromptText("Harga");

        TextField txtStock = new TextField();
        txtStock.setPromptText("Stok");

        Button btnAdd = new Button("Tambah Produk");

        ListView<Product> listView = new ListView<>();

        btnAdd.setOnAction(e -> {
            try {
                Product p = new Product(
                        txtCode.getText(),
                        txtName.getText(),
                        Double.parseDouble(txtPrice.getText()),
                        Integer.parseInt(txtStock.getText())
                );

                controller.addProduct(p);
                listView.getItems().setAll(controller.getProducts());

                txtCode.clear();
                txtName.clear();
                txtPrice.clear();
                txtStock.clear();

            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Input tidak valid").show();
            }
        });

        setSpacing(10);
        setPadding(new Insets(15));

        getChildren().addAll(
                new Label("Form Produk"),
                txtCode, txtName, txtPrice, txtStock,
                btnAdd,
                new Label("Daftar Produk"),
                listView
        );
    }
}
