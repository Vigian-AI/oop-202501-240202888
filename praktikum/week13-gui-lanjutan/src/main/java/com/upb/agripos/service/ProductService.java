package com.upb.agripos.service;

import java.util.List;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public void add(Product product) {
        productDAO.insert(product);
    }

    public void delete(String code) {
        productDAO.delete(code);
    }
}
