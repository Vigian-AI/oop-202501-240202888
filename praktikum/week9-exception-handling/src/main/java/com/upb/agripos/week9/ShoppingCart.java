package main.java.com.upb.agripos.week9;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty) throws InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity harus lebih dari 0. Anda memasukkan: " + qty);
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
        System.out.println(" Berhasil menambahkan: " + p.getName() + " x" + qty);
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Produk '" + p.getName() + "' tidak ada dalam keranjang.");
        }
        items.remove(p);
        System.out.println(" Berhasil menghapus: " + p.getName());
    }

    public void checkout() throws InsufficientStockException {
        System.out.println("\n========== PROSES CHECKOUT ==========");
        
        // Validasi stok untuk semua produk
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            
            System.out.println("Memeriksa stok: " + product.getName() + 
                             " (diminta: " + qty + ", tersedia: " + product.getStock() + ")");
            
            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak cukup untuk: " + product.getName() + 
                    " (diminta: " + qty + ", tersedia: " + product.getStock() + ")"
                );
            }
        }
        
        // Jika semua validasi berhasil, kurangi stok
        System.out.println("\n Semua stok mencukupi!");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
            System.out.println("  - " + entry.getKey().getName() + 
                             " stok berkurang " + entry.getValue() + 
                             " (sisa: " + entry.getKey().getStock() + ")");
        }
        
        System.out.println("\n CHECKOUT BERHASIL!");
        System.out.println("Total: Rp " + getTotal());
        System.out.println("=====================================\n");
        
        // Kosongkan keranjang setelah checkout
        items.clear();
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("\n====== ISI KERANJANG ========");
        if (items.isEmpty()) {
            System.out.println("Keranjang kosong");
        } else {
            int no = 1;
            for (Map.Entry<Product, Integer> e : items.entrySet()) {
                Product p = e.getKey();
                int qty = e.getValue();
                double subtotal = p.getPrice() * qty;
                System.out.printf("%d. %s x%d = Rp %.0f\n", 
                    no++, p.getName(), qty, subtotal);
            }
            System.out.println("=============================");
            System.out.printf("TOTAL: Rp %.0f\n", getTotal());
        }
        System.out.println("=============================\n");
    }
}
