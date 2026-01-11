package com.upb.agripos.controller;

import java.util.List;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // dipanggil TableView saat load
    public List<Product> load() {
        return productService.findAll();
    }

    // dipanggil tombol hapus
    public void delete(String code) {
        productService.delete(code);
    }

    // ✅ TAMBAHAN INI (UNTUK TOMBOL TAMBAH)
    public void add(Product product) {
        productService.add(product);
    }
}
