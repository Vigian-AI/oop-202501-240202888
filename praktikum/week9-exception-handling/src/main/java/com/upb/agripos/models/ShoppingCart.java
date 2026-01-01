package main.java.com.upb.agripos.models;

import java.util.HashMap;
import java.util.Map;
import main.java.com.upb.agripos.exceptions.CartEmptyException;
import main.java.com.upb.agripos.exceptions.InsufficientStockException;
import main.java.com.upb.agripos.exceptions.InvalidQuantityException;
import main.java.com.upb.agripos.exceptions.ProductNotFoundException;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();
    private double discount = 0.0;
    
    // Method untuk menambah produk dengan exception handling
    public void tambahProduk(Product product, int quantity) 
            throws InvalidQuantityException, InsufficientStockException {
        
        // Validasi input
        if (product == null) {
            throw new IllegalArgumentException("Produk tidak boleh null");
        }
        
        if (quantity <= 0) {
            throw new InvalidQuantityException(
                String.format("Kuantitas harus lebih dari 0. Kuantitas yang dimasukkan: %d", quantity)
            );
        }
        
        // Validasi stok
        if (product.getStock() < quantity) {
            throw new InsufficientStockException(product, quantity, product.getStock());
        }
        
        // Tambahkan ke keranjang
        int currentQty = items.getOrDefault(product, 0);
        items.put(product, currentQty + quantity);
        
        System.out.printf("✅ %s x%d berhasil ditambahkan ke keranjang\n", 
            product.getName(), quantity);
    }
    
    // Method untuk menghapus produk
    public void hapusProduk(Product product, int quantity) 
            throws ProductNotFoundException, InvalidQuantityException {
        
        if (product == null) {
            throw new IllegalArgumentException("Produk tidak boleh null");
        }
        
        if (quantity <= 0) {
            throw new InvalidQuantityException(
                String.format("Kuantitas harus lebih dari 0. Kuantitas yang dimasukkan: %d", quantity)
            );
        }
        
        if (!items.containsKey(product)) {
            throw new ProductNotFoundException(
                String.format("Produk '%s' tidak ditemukan dalam keranjang", product.getName())
            );
        }
        
        int currentQty = items.get(product);
        
        if (quantity > currentQty) {
            throw new InvalidQuantityException(
                String.format("Kuantitas yang diminta (%d) melebihi jumlah dalam keranjang (%d)", 
                    quantity, currentQty)
            );
        }
        
        if (quantity == currentQty) {
            items.remove(product);
            System.out.printf("🗑️ %s x%d dihapus dari keranjang\n", product.getName(), quantity);
        } else {
            items.put(product, currentQty - quantity);
            System.out.printf("🗑️ %s x%d dihapus, sisa dalam keranjang: %d\n", 
                product.getName(), quantity, currentQty - quantity);
        }
    }
    
    // Method untuk checkout dengan validasi
    public double checkout() throws CartEmptyException, InsufficientStockException {
        if (items.isEmpty()) {
            throw new CartEmptyException("Keranjang belanja kosong, tidak bisa checkout");
        }
        
        // Validasi stok sebelum checkout
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qtyInCart = entry.getValue();
            
            if (product.getStock() < qtyInCart) {
                throw new InsufficientStockException(product, qtyInCart, product.getStock());
            }
        }
        
        // Kurangi stok
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            product.reduceStock(qty);
        }
        
        double total = hitungTotal();
        double finalTotal = total - discount;
        
        // Kosongkan keranjang
        items.clear();
        discount = 0.0;
        
        return finalTotal;
    }
    
    // Method untuk menghitung total
    public double hitungTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }
    
    // Method untuk menampilkan keranjang
    public void tampilkanKeranjang() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🛒 KERANJANG BELANJA AGRIPOS");
        System.out.println("=".repeat(50));
        
        if (items.isEmpty()) {
            System.out.println("Keranjang belanja kosong.");
            return;
        }
        
        System.out.printf("%-3s %-15s %-10s %-8s %-12s\n", 
            "No", "Produk", "Harga", "Qty", "Subtotal");
        System.out.println("-".repeat(50));
        
        int index = 1;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getPrice() * quantity;
            
            System.out.printf("%-3d %-15s %-10s %-8d %-12s\n",
                index++,
                product.getName(),
                String.format("Rp%,.0f", product.getPrice()),
                quantity,
                String.format("Rp%,.0f", subtotal)
            );
        }
        
        System.out.println("-".repeat(50));
        double total = hitungTotal();
        System.out.printf("%-36s: Rp%,.0f\n", "Total", total);
        if (discount > 0) {
            System.out.printf("%-36s: -Rp%,.0f\n", "Diskon", discount);
            System.out.printf("%-36s: Rp%,.0f\n", "Total Setelah Diskon", total - discount);
        }
        System.out.printf("%-36s: %d item(s)\n", "Jumlah Item", getJumlahItem());
        System.out.println("=".repeat(50));
    }
    
    // Helper methods
    public int getJumlahItem() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    public void setDiscount(double discount) {
        if (discount < 0 || discount > hitungTotal()) {
            throw new IllegalArgumentException("Diskon tidak valid");
        }
        this.discount = discount;
    }
    
    public void kosongkanKeranjang() {
        items.clear();
        discount = 0.0;
        System.out.println("🧹 Keranjang berhasil dikosongkan");
    }

    public void addProduct(Product p1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
    }

    public void printCart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printCart'");
    }
}