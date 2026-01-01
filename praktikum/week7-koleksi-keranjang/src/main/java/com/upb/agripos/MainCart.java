package main.java.com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        // Ganti dengan nama dan NIM Anda
        System.out.println("Hello, I am [Fendy agustian]-[240202898]] (Week7)");
        System.out.println("=========================================\n");
        
        // Inisialisasi produk
        Product beras = new Product("P001", "Beras Premium",75000);
        Product pupuk = new Product("P002", "Pupuk Urea",45000);
        Product bibit = new Product("P003", "Bibit Padi",25000);
        Product pestisida = new Product("P004", "Pestisida",38000);
        Product cangkul = new Product("P005", "Cangkul",65000);
        
        // Membuat keranjang belanja
        ShoppingCart keranjang = new ShoppingCart();
        
        System.out.println("1. Menambahkan produk ke keranjang:");
        keranjang.tambahProduk(beras, 2);
        keranjang.tambahProduk(pupuk);
        keranjang.tambahProduk(bibit, 3);
        keranjang.tambahProduk(pestisida);
        
        // Tampilkan keranjang
        keranjang.tampilkanKeranjang();
        
        System.out.println("\n2. Menghapus produk:");
        keranjang.hapusProduk("P001"); // Hapus 1 beras
        keranjang.hapusProduk("P005");    // Coba hapus produk yang tidak ada
        
        // Tampilkan lagi
        keranjang.tampilkanKeranjang();
        
        System.out.println("\n3. Menambah produk lagi:");
        keranjang.tambahProduk(cangkul);
        keranjang.tambahProduk(beras, 2);
        
        // Tampilkan final
        keranjang.tampilkanKeranjang();
        
        System.out.println("\n4. Informasi Keranjang:");
        System.out.println("Jumlah item: " + keranjang.getJumlahItem());
        System.out.println("Total harga: Rp" + String.format("%,.0f", keranjang.hitungTotal()));
        
        // Demo menghapus semua produk tertentu
        System.out.println("\n5. Menghapus semua bibit:");
        keranjang.hapusProduk("P003");
        keranjang.tampilkanKeranjang();
        
        System.out.println("\n6. Membersihkan keranjang:");
        keranjang.bersihkanKeranjang();
        keranjang.tampilkanKeranjang();
    }
}