package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsolView;
import com.upb.agripos.dao.ProductController;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am Vigian Agus Isnaeni-240202888 (Week10)");
        Product product = new Product("P01", "Pupuk Organik");
        ConsolView view = new ConsolView();
        ProductController controller = new ProductController(product, view);
        controller.showProduct();
    }
}
