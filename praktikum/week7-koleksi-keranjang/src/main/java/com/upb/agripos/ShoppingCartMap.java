package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(main.java.com.upb.agripos.Product p1) { items.put(p1, items.getOrDefault(p1, 0) + 1); }

    public void removeProduct(main.java.com.upb.agripos.Product p1) {
        if (!items.containsKey(p1)) return;
        int qty = items.get(p1);
        if (qty > 1) items.put(p1, qty - 1);
        else items.remove(p1);
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (Map):");
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getCode() + " " + e.getKey().getName() + " x" + e.getValue());
        }
        System.out.println("Total: " + getTotal());
    }
}