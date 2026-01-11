package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ProductController {
    private TextField txtCode;
    private TextField txtName;
    private TextField txtPrice;
    private TextField txtStock;
    private Button btnAdd;
    private ListView<String> listView;
    private ProductService productService;

    public ProductController(TextField txtCode, TextField txtName, TextField txtPrice, TextField txtStock, Button btnAdd, ListView<String> listView, ProductService productService) {
        this.txtCode = txtCode;
        this.txtName = txtName;
        this.txtPrice = txtPrice;
        this.txtStock = txtStock;
        this.btnAdd = btnAdd;
        this.listView = listView;
        this.productService = productService;
        initialize();
    }

    private void initialize() {
        btnAdd.setOnAction(event -> {
            Product product = new Product(
                txtCode.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtStock.getText())
            );
            productService.insert(product);
            listView.getItems().add(product.getCode() + " - " + product.getName());
        });
    }
}