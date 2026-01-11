package com.upb.agripos;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class AppMVC {

    public static void main(String[] args) {

        System.out.println("Hello, I am [Fendy agustian]-[240202898] (Week10)");

        // Uji Singleton
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        System.out.println("Apakah instance sama? " + (db1 == db2));

        // MVC
        Product product = new Product("P01", "Pupuk Organik");
        ConsoleView view = new ConsoleView();
        ProductController controller =
                new ProductController(product, view);

        controller.showProduct();
    }
}