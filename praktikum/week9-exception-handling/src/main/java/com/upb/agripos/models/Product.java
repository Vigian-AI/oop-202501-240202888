package main.java.com.upb.agripos.models;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private int stock;
    
    public Product(String code, String name, double price, int stock) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }
        
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    
    // Business methods
    public synchronized boolean reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Kuantitas harus lebih dari 0");
        }
        if (stock >= quantity) {
            stock -= quantity;
            return true;
        }
        return false;
    }
    
    public synchronized void addStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Kuantitas harus lebih dari 0");
        }
        stock += quantity;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return code.equals(product.code);
    }
    
    @Override
    public int hashCode() {
        return code.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s (Rp%,.0f) | Stok: %d", 
            code, name, price, stock);
    }
}