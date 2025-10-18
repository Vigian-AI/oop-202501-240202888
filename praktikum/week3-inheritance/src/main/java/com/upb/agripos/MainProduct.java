package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Product;
import main.java.com.upb.agripos.util.CreditBy;

public class MainProduct {
    public static void main(String[] args) {
        Product prod1 = new Product("BNH-001", "Benih Padi IR64", 25000, 100);
        Product prod2 = new Product("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Product prod3 = new Product("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + prod1.getKode() + ", Nama: " + prod1.getNama() + ", Harga: " + prod1.getHarga() + ", Stok: " + prod1.getStok());
        System.out.println("Kode: " + prod2.getKode() + ", Nama: " + prod2.getNama() + ", Harga: " + prod2.getHarga() + ", Stok: " + prod2.getStok());
        System.out.println("Kode: " + prod3.getKode() + ", Nama: " + prod3.getNama() + ", Harga: " + prod3.getHarga() + ", Stok: " + prod3.getStok());

        p1.tambahStok(50);
        p2.kurangiStok(20);
        p3.tambahStok(10);

        System.out.println("\nSetelah update stok:");
        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        // Tampilkan identitas mahasiswa
        CreditBy.print("240202888", "Vigian Agus Isnaeni");
    }
}





