package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingChart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty) throws InvalidQuantityException, InvalidPriceException{
        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity harus lebih dari 0.");
        }
        if (p.getPrice()<=0){
            throw new InvalidPriceException(p.getName()+ " memiliki harga yang tidak falid");
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
        System.out.println( "barang telah ditambahkan");
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Produk tidak ada dalam keranjang.");
        }
        items.remove(p);
        System.out.println("barang telah dihapus");
    }

    public void checkout() throws InsufficientStockException, EmptyCartException {
        if (items.isEmpty()) {
            throw new EmptyCartException("Keranjang kosong! Tidak dapat melakukan checkout.");
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                        "Stok tidak cukup untuk: " + product.getName()
                );
            }
        }

        System.out.println("\n Isi Keranjang:");
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getCode() + " " + e.getKey().getName() + " x" + e.getValue());
        }
        System.out.println("isi keranjang selesai dicheckout");
        // contoh pengurangan stok bila semua cukup
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
    }
}
