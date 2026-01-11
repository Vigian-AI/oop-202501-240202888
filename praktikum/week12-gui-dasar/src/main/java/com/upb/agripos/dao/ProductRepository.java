package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.util.List;

public interface ProductRepository {
    void save(Product product);
    List<Product> findAll();
}
