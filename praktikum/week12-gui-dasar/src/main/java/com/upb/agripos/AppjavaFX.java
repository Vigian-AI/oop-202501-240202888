package com.upb.agripos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppjavaFX extends Application {

    @Override
    public void start(Stage stage) {

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

        btnAdd.setOnAction(e -> {
            String item =
                    txtCode.getText() + " - " +
                    txtName.getText() + " - " +
                    txtPrice.getText() + " - " +
                    txtStock.getText();

            listView.getItems().add(item);

            txtCode.clear();
            txtName.clear();
            txtPrice.clear();
            txtStock.clear();
        });

        VBox root = new VBox(10,
                txtCode,
                txtName,
                txtPrice,
                txtStock,
                btnAdd,
                new Label("Daftar Produk"),
                listView
        );

        stage.setTitle("Agri-POS – Kelola Produk");
        stage.setScene(new Scene(root, 400, 450));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
