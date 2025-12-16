package main.java.com.upb.agripos.week7;

import java.util.ArrayList;

public class ShoppingCart {

    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(p);
        System.out.println("Produk ditambahkan: " + p.getName());
    }

    public void removeProduct(Product p) {
        if (items.remove(p)) {
            System.out.println("Produk dihapus: " + p.getName());
        } else {
            System.out.println("Produk tidak ditemukan: " + p.getName());
        }
    }

    public int getTotal() {
        int sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("\n========== ISI KERANJANG ==========");
        if (items.isEmpty()) {
            System.out.println("Keranjang kosong");
        } else {
            for (int i = 0; i < items.size(); i++) {
                Product p = items.get(i);
                System.out.printf(
                    "%d. %s - %s = Rp %d%n",
                    (i + 1),
                    p.getCode(),
                    p.getName(),
                    p.getPrice()
                );
            }
            System.out.println("===================================");
            System.out.printf("TOTAL: Rp %d%n", getTotal());
        }
        System.out.println("===================================\n");
    }

    public int getItemCount() {
        return items.size();
    }
}
