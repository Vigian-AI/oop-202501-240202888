package main.java.com.upb.agripos;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    // Method tambah produk
    public void tambahProduk(Product product) {
        if (product == null) {
            System.out.println("Produk tidak valid!");
            return;
        }
        items.add(product);
        System.out.println("Produk " + product.getName() + " berhasil ditambahkan");
    }

    // Method tambah produk dengan kuantitas
    public void tambahProduk(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Input tidak valid!");
            return;
        }
        for (int i = 0; i < quantity; i++) {
            items.add(product);
        }
        System.out.println(" " + product.getName() + " x" + quantity + " berhasil ditambahkan");
    }

    // Method hapus produk
    public void hapusProduk(String productCode) {
        boolean removed = false;
        Iterator<Product> iterator = items.iterator();
        
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getCode().equals(productCode)) {
                iterator.remove();
                removed = true;
            }
        }
        
        if (removed) {
            System.out.println("Semua produk dengan kode " + productCode + " berhasil dihapus");
        } else {
            System.out.println("Produk dengan kode " + productCode + " tidak ditemukan");
        }
    }

    // Method hapus produk dengan kuantitas tertentu
    public void hapusProduk(String productCode, int quantity) {
        int count = 0;
        Iterator<Product> iterator = items.iterator();
        
        while (iterator.hasNext() && count < quantity) {
            Product product = iterator.next();
            if (product.getCode().equals(productCode)) {
                iterator.remove();
                count++;
            }
        }
        
        if (count > 0) {
            System.out.println(" " + count + " produk " + productCode + " berhasil dihapus");
        } else {
            System.out.println("Produk dengan kode " + productCode + " tidak ditemukan");
        }
    }

    // Method hitung total
    public double hitungTotal() {
        double total = 0;
        for (Product product : items) {
            total += product.getPrice();
        }
        return total;
    }

    // Method tampilkan keranjang
    public void tampilkanKeranjang() {
        System.out.println("\n ===== KERANJANG BELANJA =====");
        
        if (items.isEmpty()) {
            System.out.println("Keranjang belanja kosong.");
            return;
        }

        // Menggunakan Map untuk menghitung kuantitas
        java.util.Map<String, Integer> quantityMap = new java.util.HashMap<>();
        java.util.Map<String, Product> productMap = new java.util.HashMap<>();
        
        for (Product product : items) {
            String code = product.getCode();
            quantityMap.put(code, quantityMap.getOrDefault(code, 0) + 1);
            productMap.putIfAbsent(code, product);
        }

        // Menampilkan dengan format yang rapi
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│ No │ Kode  │ Produk           │ Qty │Harga  │");
        System.out.println("├─────────────────────────────────────────────┤");
        
        int no = 1;
        for (java.util.Map.Entry<String, Integer> entry : quantityMap.entrySet()) {
            String code = entry.getKey();
            Product product = productMap.get(code);
            int qty = entry.getValue();
            
            System.out.printf("│ %-2d │ %-5s │ %-15s │ %-3d │ %-7s │\n",
                no++,
                code,
                product.getName(),
                qty,
                String.format("Rp%,.0f", product.getPrice())
            );
        }
        
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.printf("Total: Rp%,.0f\n", hitungTotal());
    }

    // Method untuk mendapatkan jumlah item
    public int getJumlahItem() {
        return items.size();
    }

    // Method untuk membersihkan keranjang
    public void bersihkanKeranjang() {
        items.clear();
        System.out.println("🧹 Keranjang berhasil dikosongkan");
    }
}