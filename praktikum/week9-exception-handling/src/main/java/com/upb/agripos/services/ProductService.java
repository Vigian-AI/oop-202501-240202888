package main.java.com.upb.agripos.services;

import java.util.HashMap;
import java.util.Map;
import main.java.com.upb.agripos.exceptions.ProductNotFoundException;
import main.java.com.upb.agripos.models.Product;

public class ProductService {
    // Singleton instance
    private static ProductService instance;
    private final Map<String, Product> productCatalog;
    
    // Private constructor untuk Singleton
    private ProductService() {
        productCatalog = new HashMap<>();
        initializeDefaultProducts();
    }
    
    // Singleton getInstance method dengan double-checked locking
    public static ProductService getInstance() {
        if (instance == null) {
            synchronized (ProductService.class) {
                if (instance == null) {
                    instance = new ProductService();
                }
            }
        }
        return instance;
    }
    
    private void initializeDefaultProducts() {
        // Inisialisasi produk default
        tambahProduk(new Product("P001", "Beras Premium", 75000, 100));
        tambahProduk(new Product("P002", "Pupuk Urea", 45000, 50));
        tambahProduk(new Product("P003", "Bibit Padi", 25000, 200));
        tambahProduk(new Product("P004", "Pestisida", 38000, 30));
        tambahProduk(new Product("P005", "Cangkul", 65000, 20));
        tambahProduk(new Product("P006", "Benih Jagung", 32000, 150));
        tambahProduk(new Product("P007", "Herbisida", 28000, 40));
    }
    
    // CRUD Operations
    public void tambahProduk(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Produk tidak boleh null");
        }
        productCatalog.put(product.getCode(), product);
    }
    
    public Product getProduk(String code) throws ProductNotFoundException {
        Product product = productCatalog.get(code);
        if (product == null) {
            throw new ProductNotFoundException("Produk dengan kode " + code + " tidak ditemukan");
        }
        return product;
    }
    
    public void updateProduk(Product product) throws ProductNotFoundException {
        if (!productCatalog.containsKey(product.getCode())) {
            throw new ProductNotFoundException("Produk tidak ditemukan untuk diupdate");
        }
        productCatalog.put(product.getCode(), product);
    }
    
    public void hapusProduk(String code) throws ProductNotFoundException {
        if (!productCatalog.containsKey(code)) {
            throw new ProductNotFoundException("Produk tidak ditemukan untuk dihapus");
        }
        productCatalog.remove(code);
    }
    
    public void tampilkanKatalog() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📋 KATALOG PRODUK AGRIPOS");
        System.out.println("=".repeat(60));
        
        if (productCatalog.isEmpty()) {
            System.out.println("Katalog produk kosong.");
            return;
        }
        
        System.out.printf("%-8s %-20s %-12s %-8s\n", 
            "Kode", "Nama", "Harga", "Stok");
        System.out.println("-".repeat(60));
        
        for (Product product : productCatalog.values()) {
            System.out.printf("%-8s %-20s %-12s %-8d\n",
                product.getCode(),
                product.getName(),
                String.format("Rp%,.0f", product.getPrice()),
                product.getStock()
            );
        }
        System.out.println("=".repeat(60));
    }
    
    public boolean isProdukTersedia(String code, int quantity) {
        try {
            Product product = getProduk(code);
            return product.getStock() >= quantity;
        } catch (ProductNotFoundException e) {
            return false;
        }
    }
}