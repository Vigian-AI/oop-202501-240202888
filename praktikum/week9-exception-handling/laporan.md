# Laporan Praktikum Minggu 9

Topik: **Exception Handling**

## Identitas

* Nama  : Fendy Agustian
* NIM   : 240202898
* Kelas : 3IKRB

---

## Tujuan

* Mahasiswa mampu menjelaskan perbedaan antara error dan exception.
* Mahasiswa mampu mengimplementasikan `try-catch-finally`.
* Mahasiswa mampu membuat custom exception sesuai kebutuhan aplikasi.
* Mahasiswa mampu menerapkan exception handling pada sistem keranjang belanja.
* Mahasiswa mampu memahami dan menerapkan design pattern Singleton.

---

## Dasar Teori

1. **Exception** adalah kesalahan yang dapat ditangani oleh program menggunakan mekanisme `try-catch`.
2. **Error** adalah kesalahan fatal yang tidak dapat ditangani oleh program.
3. **Custom Exception** memungkinkan pembuatan kesalahan yang spesifik sesuai domain aplikasi.
4. Blok **finally** selalu dieksekusi baik terjadi exception maupun tidak.
5. **Singleton Pattern** memastikan hanya ada satu instance objek dalam aplikasi.

---

## Langkah Praktikum

1. Membuat struktur project `week9-exception-handling`.
2. Membuat beberapa custom exception:
   - `InvalidQuantityException`
   - `ProductNotFoundException`
   - `InsufficientStockException`
3. Memodifikasi class `Product` dengan atribut stok.
4. Mengimplementasikan exception handling pada class `ShoppingCart`.
5. Menerapkan Singleton Pattern pada `ProductService`.
6. Membuat class `MainExceptionDemo` untuk pengujian.
7. Melakukan compile dan run program.
8. Commit dan push dengan format:

   `week9-exception: implement exception handling dan singleton`

---

## Kode Program

### MainExceptionDemo.java

```java
package main.java.com.upb.agripos;

import main.java.com.upb.agripos.exceptions.CartEmptyException;
import main.java.com.upb.agripos.exceptions.InsufficientStockException;
import main.java.com.upb.agripos.exceptions.InvalidQuantityException;
import main.java.com.upb.agripos.exceptions.ProductNotFoundException;
import main.java.com.upb.agripos.models.Product;
import main.java.com.upb.agripos.models.ShoppingCart;
import main.java.com.upb.agripos.services.ProductService;

public class MainExceptionDemo {
    public static void main(String[] args) {
        // Ganti dengan nama dan NIM Anda
        System.out.println("Hello, I am [Fendy Agustian]-[240202898] (Week9)");
        System.out.println("=".repeat(50));
        System.out.println("DEMO EXCEPTION HANDLING DAN CUSTOM EXCEPTION\n");
        
        // Get Singleton instance
        ProductService productService = ProductService.getInstance();
        
        // Tampilkan katalog produk
        productService.tampilkanKatalog();
        
        // Buat keranjang belanja
        ShoppingCart keranjang = new ShoppingCart();
        
        try {
            // Demo 1: Menambah produk dengan kuantitas valid
            System.out.println("\n1. Menambah produk dengan kuantitas valid:");
            Product beras = productService.getProduk("P001");
            keranjang.tambahProduk(beras, 2);
            
            Product pupuk = productService.getProduk("P002");
            keranjang.tambahProduk(pupuk, 1);
            
            // Tampilkan keranjang
            keranjang.tampilkanKeranjang();
            
            // Demo 2: Exception - Kuantitas invalid
            System.out.println("\n2. Demo InvalidQuantityException:");
            try {
                keranjang.tambahProduk(beras, -5);
            } catch (InvalidQuantityException e) {
                System.out.println("❌ Error: " + e.getMessage());
                System.out.println("   Tipe Exception: " + e.getClass().getSimpleName());
            }
            
            // Demo 3: Exception - Stok tidak mencukupi
            System.out.println("\n3. Demo InsufficientStockException:");
            try {
                Product bibit = productService.getProduk("P003");
                keranjang.tambahProduk(bibit, 300); // Minta lebih dari stok
            } catch (InsufficientStockException e) {
                System.out.println("❌ Error: " + e.getMessage());
                System.out.println("   Produk: " + e.getProduct().getName());
                System.out.println("   Diminta: " + e.getRequestedQuantity());
                System.out.println("   Tersedia: " + e.getAvailableStock());
            }
            
            // Demo 4: Exception - Produk tidak ditemukan saat menghapus
            System.out.println("\n4. Demo ProductNotFoundException:");
            try {
                Product produkTidakAda = new Product("P999", "Produk Tidak Ada", 10000, 10);
                keranjang.hapusProduk(produkTidakAda, 1);
            } catch (ProductNotFoundException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
            
            // Demo 5: Exception - Checkout dengan keranjang kosong
            System.out.println("\n5. Demo CartEmptyException:");
            ShoppingCart keranjangKosong = new ShoppingCart();
            try {
                keranjangKosong.checkout();
            } catch (CartEmptyException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
            
            // Demo 6: Try-catch-finally
            System.out.println("\n6. Demo Try-Catch-Finally:");
            try {
                System.out.println("Mencoba menambah produk...");
                keranjang.tambahProduk(productService.getProduk("P004"), 5);
                System.out.println("Berhasil menambah produk!");
            } catch (InvalidQuantityException | InsufficientStockException e) {
                System.out.println("Terjadi error: " + e.getMessage());
            } finally {
                System.out.println("Blok finally selalu dieksekusi!");
            }
            
            // Tampilkan keranjang terakhir
            System.out.println("\n7. Status akhir keranjang:");
            keranjang.tampilkanKeranjang();
            
            // Demo 7: Checkout berhasil
            System.out.println("\n8. Demo Checkout Berhasil:");
            keranjang.setDiscount(10000); // Beri diskon Rp 10,000
            double totalBayar = keranjang.checkout();
            System.out.println("✅ Checkout berhasil!");
            System.out.println("Total yang harus dibayar: Rp" + String.format("%,.0f", totalBayar));
            
            // Tampilkan stok setelah checkout
            System.out.println("\n9. Stok produk setelah checkout:");
            productService.tampilkanKatalog();
            
        } catch (Exception e) {
            System.out.println("\n⚠️ Exception tidak terduga: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Program selesai dengan exception handling yang aman!");
    }
}
```


## Hasil Eksekusi

*(Hasil eksekusi program disertakan pada folder `screenshots/hasil.png`)*


## Analisis

- Program berjalan dengan membuat objek `Product` dan `ShoppingCart`.
- Ketika kuantitas bernilai negatif, program melempar `InvalidQuantityException`.
- Exception ditangkap oleh blok `catch` sehingga program tidak berhenti secara paksa.
- Blok `finally` tetap dieksekusi untuk memastikan proses akhir tetap berjalan.
- Dibandingkan minggu sebelumnya (**ArrayList dan HashMap**), praktikum minggu ini lebih fokus pada **penanganan kesalahan program**.
- Kendala yang dihadapi adalah memahami alur lempar (`throw`) dan tangkap (`catch`) exception, yang diatasi dengan mencoba berbagai skenario kesalahan.

---

## Kesimpulan

- Exception handling sangat penting untuk menjaga kestabilan aplikasi.
- Penggunaan **custom exception** membuat pesan kesalahan lebih jelas dan sesuai dengan konteks aplikasi.
- Blok `try-catch-finally` memastikan program tetap berjalan dengan baik meskipun terjadi kesalahan.

---

## Quiz

### 1. Jelaskan perbedaan error dan exception.
**Jawaban:**  
Error adalah kondisi fatal yang tidak dapat ditangani oleh program, sedangkan exception adalah kesalahan yang masih dapat ditangani menggunakan mekanisme `try-catch`.

---

### 2. Apa fungsi blok `finally` dalam `try-catch-finally`?
**Jawaban:**  
Blok `finally` selalu dieksekusi baik terjadi exception maupun tidak, dan biasanya digunakan untuk proses *cleanup resource*.

---

### 3. Mengapa custom exception diperlukan?
**Jawaban:**  
Custom exception diperlukan agar penanganan kesalahan lebih spesifik, pesan error lebih informatif, dan sesuai dengan kebutuhan aplikasi.

---

### 4. Berikan contoh kasus pada sistem POS yang membutuhkan custom exception.
**Jawaban:**  
Contohnya adalah jumlah pembelian bernilai negatif, stok produk tidak mencukupi, atau keranjang belanja kosong saat checkout.
