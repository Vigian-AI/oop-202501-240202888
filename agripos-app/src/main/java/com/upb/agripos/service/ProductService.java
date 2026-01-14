package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {
    private final ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
        this.loadFromDatabase();
    }

    private void loadFromDatabase() {
        try {
            List<Product> products = dao.findAll();
            System.out.println("✓ Loaded " + products.size() + " products from database");
        } catch (Exception e) {
            System.err.println("Error loading products from database: " + e.getMessage());
        }
    }

    public void addProduct(Product p) {
        if (p.getCode().isEmpty()) {
            throw new IllegalArgumentException("Kode produk kosong");
        }
        dao.insert(p);
    }

    public void deleteProduct(String code) {
        dao.delete(code);
    }

    public List<Product> getAllProducts() {
        return dao.findAll();
    }
}
