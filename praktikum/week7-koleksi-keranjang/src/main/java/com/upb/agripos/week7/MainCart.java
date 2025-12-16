package main.java.com.upb.agripos.week7;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Fendy Agustian-240202898 (Week7)");
        System.out.println("===========================================\n");

        // Membuat produk-produk
        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);
        Product p3 = new Product("P03", "Bibit Jagung", 25000);
        Product p4 = new Product("P04", "Pestisida", 45000);

        // ========== DEMO DENGAN ARRAYLIST ==========
        System.out.println("========== DEMO SHOPPING CART (ArrayList) ==========\n");
        
        ShoppingCart cart = new ShoppingCart();
        
        // Menambahkan produk
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);
        
        // Menampilkan keranjang
        cart.printCart();
        
        // Menambahkan produk lagi
        cart.addProduct(p4);
        cart.printCart();
        
        // Menghapus produk
        cart.removeProduct(p1);
        cart.printCart();

        // ========== DEMO DENGAN MAP (DENGAN QUANTITY) ==========
        System.out.println("\n========== DEMO SHOPPING CART MAP (HashMap) ==========\n");
        
        ShoppingCartMap cartMap = new ShoppingCartMap();
        
        // Menambahkan produk (bisa duplikat, quantity akan bertambah)
        cartMap.addProduct(p1);
        cartMap.addProduct(p2);
        cartMap.addProduct(p1); // Menambah quantity p1
        cartMap.addProduct(p1); // Menambah quantity p1 lagi
        cartMap.addProduct(p3);
        
        // Menampilkan keranjang
        cartMap.printCart();
        
        // Menghapus produk (quantity berkurang)
        cartMap.removeProduct(p1); // Quantity p1 berkurang
        cartMap.printCart();
        
        cartMap.removeProduct(p1); // Quantity p1 berkurang lagi
        cartMap.printCart();
        
        cartMap.removeProduct(p1); // p1 dihapus total
        cartMap.printCart();

        // ========== PERBANDINGAN ==========
        System.out.println("\n========== PERBANDINGAN ==========");
        System.out.println("ArrayList: Sederhana, cocok untuk keranjang tanpa quantity");
        System.out.println("HashMap: Lebih efisien untuk mengelola quantity produk");
        System.out.println("===================================\n");
    }
}
