package main.java.com.upb.agripos.week9;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService instance;
    private final List<Product> products;

    private ProductService() {
        products = new ArrayList<>();
        initializeProducts();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    private void initializeProducts() {
        products.add(new Product("P01", "Pupuk Organik", 25000, 10));
        products.add(new Product("P02", "Beras Premium", 50000, 5));
        products.add(new Product("P03", "Bibit Jagung", 15000, 20));
        products.add(new Product("P04", "Pestisida", 35000, 3));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product findByCode(String code) {
        for (Product p : products) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }
}