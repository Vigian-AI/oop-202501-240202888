package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {
    void insert(Product p);
    void delete(String code);
    List<Product> findAll();
}
