package com.upb.agripos.service;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.dao.ProductRepository;
import com.upb.agripos.model.Product;

public class ProductService {

    private ProductRepository repository;
    private List<Product> products = new ArrayList<>();

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void insert(Product product) {
        products.add(product);       // simulasi DB
        repository.save(product);    // ke DAO
    }

    public List<Product> getAll() {
        return products;
    }

    public Product[] findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
