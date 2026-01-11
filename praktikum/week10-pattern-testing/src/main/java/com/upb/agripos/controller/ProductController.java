package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsolView;

public class ProductController {
    private final Product model;
    private final ConsolView view;

    public ProductController(Product model, ConsolView view) {
        this.model = model;
        this.view = view;
    }

    public void showProduct() {
        view.showMessage("Produk: " + model.getCode() + " - " + model.getName());
    }
}