package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductController {
    private final TextField txtCode;
    private final TextField txtName;
    private final TextField txtPrice;
    private final TextField txtStock;
    private final Button btnAdd;
    private final ListView<String> listView;
    private final ProductService productService;

    public ProductController(TextField txtCode, TextField txtName, TextField txtPrice, TextField txtStock, Button btnAdd, ListView<String> listView, ProductService productService) {
        this.txtCode = txtCode;
        this.txtName = txtName;
        this.txtPrice = txtPrice;
        this.txtStock = txtStock;
        this.btnAdd = btnAdd;
        this.listView = listView;
        this.productService = productService;
    }

    public void addProduct(TextField txtCode, TextField txtName, TextField txtPrice, TextField txtStock, TableView<Product> tableView) {
        try {
            Product product = new Product(
                txtCode.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtStock.getText())
            );
            productService.insert(product);
            tableView.getItems().add(product);
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    public void deleteProduct(TableView<Product> tableView) {
        Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productService.delete(selectedProduct); // Ensure ProductService has a delete method
            tableView.getItems().remove(selectedProduct);
        }
    }

    public void loadData(TableView<Product> tableView) {
        tableView.getItems().clear();
        tableView.getItems().addAll(productService.getAllProducts());
    }

    private void clearFields() {
        txtCode.clear();
        txtName.clear();
        txtPrice.clear();
        txtStock.clear();
    }
}