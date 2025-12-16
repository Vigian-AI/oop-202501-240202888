package main.java.com.upb.agripos.week9;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am Fendy Agustian-240202898 (Week9)");
        System.out.println("===========================================");
        System.out.println("Exception Handling - Agri-POS System");
        System.out.println("===========================================\n");

        // Menggunakan Singleton Pattern
        ProductService service = ProductService.getInstance();
        
        ShoppingCart cart = new ShoppingCart();
        Product p1 = service.findByCode("P01"); // Pupuk Organik, stok: 10
        Product p2 = service.findByCode("P02"); // Beras Premium, stok: 5
        Product p3 = service.findByCode("P03"); // Bibit Jagung, stok: 20

        // ========== TEST 1: InvalidQuantityException ==========
        System.out.println("========== TEST 1: Invalid Quantity ==========");
        try {
            System.out.println("Mencoba menambahkan produk dengan quantity = -1");
            cart.addProduct(p1, -1);
        } catch (InvalidQuantityException e) {
            System.out.println(" EXCEPTION TERTANGKAP: " + e.getMessage());
        } finally {
            System.out.println("Finally: Validasi quantity selesai.\n");
        }

        // ========== TEST 2: ProductNotFoundException ==========
        System.out.println("========== TEST 2: Product Not Found ==========");
        try {
            System.out.println("Mencoba menghapus produk yang tidak ada di keranjang");
            cart.removeProduct(p1);
        } catch (ProductNotFoundException e) {
            System.out.println(" EXCEPTION TERTANGKAP: " + e.getMessage());
        } finally {
            System.out.println("Finally: Penghapusan produk selesai diproses.\n");
        }

        // ========== TEST 3: Checkout Sukses ==========
        System.out.println("========== TEST 3: Checkout Berhasil ==========");
        try {
            System.out.println("Menambahkan produk dengan quantity valid:");
            cart.addProduct(p1, 3);
            cart.addProduct(p2, 2);
            cart.printCart();
            
            System.out.println("Info stok sebelum checkout:");
            System.out.println("  - " + p1);
            System.out.println("  - " + p2);
            
            cart.checkout();
            
            System.out.println("Info stok setelah checkout:");
            System.out.println("  - " + p1);
            System.out.println("  - " + p2);
            
        } catch (InvalidQuantityException | InsufficientStockException e) {
            System.out.println(" EXCEPTION: " + e.getMessage());
        }

        // ========== TEST 4: InsufficientStockException ==========
        System.out.println("\n========== TEST 4: Insufficient Stock ==========");
        try {
            System.out.println("Mencoba membeli melebihi stok yang tersedia:");
            System.out.println("Stok " + p2.getName() + " saat ini: " + p2.getStock());
            
            cart.addProduct(p2, 10); // Stok hanya 3 (sudah berkurang dari 5)
            cart.printCart();
            
            cart.checkout();
            
        } catch (InvalidQuantityException e) {
            System.out.println(" EXCEPTION: " + e.getMessage());
        } catch (InsufficientStockException e) {
            System.out.println(" EXCEPTION TERTANGKAP: " + e.getMessage());
            System.out.println("Transaksi dibatalkan. Silakan kurangi jumlah pembelian.");
        } finally {
            System.out.println("Finally: Proses checkout selesai.\n");
        }

        // ========== TEST 5: Multiple Exceptions ==========
        System.out.println("========== TEST 5: Mixed Operations ==========");
        ShoppingCart cart2 = new ShoppingCart();
        
        try {
            System.out.println("Operasi 1: Tambah produk normal");
            cart2.addProduct(p3, 5);
            
            System.out.println("Operasi 2: Tambah dengan quantity 0");
            cart2.addProduct(p1, 0);
            
        } catch (InvalidQuantityException e) {
            System.out.println(" EXCEPTION: " + e.getMessage());
        }
        
        cart2.printCart();

        // ========== SUMMARY ==========
        System.out.println("\n========== SUMMARY ==========");
        System.out.println(" InvalidQuantityException: Berhasil menangkap quantity <= 0");
        System.out.println(" ProductNotFoundException: Berhasil menangkap produk tidak ada");
        System.out.println(" InsufficientStockException: Berhasil menangkap stok tidak cukup");
        System.out.println(" Try-Catch-Finally: Berhasil diimplementasikan");
        System.out.println(" Singleton Pattern: ProductService berhasil digunakan");
        System.out.println("=============================\n");
    }
}
