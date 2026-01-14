package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;



public class ProductTableView extends VBox {

    private TableView<Product> table = new TableView<>();
    private ObservableList<Product> data = FXCollections.observableArrayList();

    // counter untuk tambah 1 per 1
    private int counter = 0;

    // daftar nama produk
    private String[] namaProduk = {
        "Pupuk Organik",
        "Pupuk Pestisida",
        "Benih Jagung",
        "Benih Padi"
    };

    public ProductTableView(ProductController controller) {

        // === KOLOM TABLE ===
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(c -> c.getValue().codeProperty());

        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(c -> c.getValue().nameProperty());

        TableColumn<Product, Number> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(c -> c.getValue().priceProperty());

        TableColumn<Product, Number> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(c -> c.getValue().stockProperty());

        table.getColumns().addAll(colCode, colName, colPrice, colStock);

        // === TOMBOL ===
        Button btnAdd = new Button("Tambah Produk");
        Button btnDelete = new Button("Hapus Produk");

        // === TAMBAH PRODUK (LAMBDA) ===
        btnAdd.setOnAction(e -> {

            if (counter >= namaProduk.length) {
                return; // semua produk sudah ditambahkan
            }

            String kode = "P" + String.format("%01d", counter + 1);

            Product p = new Product(
                kode,
                namaProduk[counter],
                10000 + (counter * 5000),
                10
            );

            controller.add(p);
            counter++;

            loadData(controller);
        });

        // === HAPUS PRODUK (LAMBDA) ===
        btnDelete.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.delete(selected.getCode());
                loadData(controller);
            }
        });

        getChildren().addAll(table, btnAdd, btnDelete);

        loadData(controller);
    }

    private void loadData(ProductController controller) {
        data.setAll(controller.load());
        table.setItems(data);
    }
}
