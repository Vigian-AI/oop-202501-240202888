package com.upb.agripos.controller;

import java.util.List;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;

public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void addProduct(Product product) {
        service.insert(product);
    }

    public List<Product> getProducts() {
        return service.getAll();
    }
}
