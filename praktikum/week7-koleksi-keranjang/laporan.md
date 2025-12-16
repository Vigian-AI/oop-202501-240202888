# Laporan Praktikum Minggu 7

Topik: **Collections dan Implementasi Keranjang Belanja**

## Identitas

* Nama  : Fendy Agustian
* NIM   : 240202898
* Kelas : 3IKRB

---

## Tujuan

* Mahasiswa mampu memahami konsep dasar Java Collections (List, Map, Set).
* Mahasiswa mampu mengimplementasikan ArrayList untuk keranjang belanja.
* Mahasiswa mampu menggunakan Map untuk mengelola quantity produk.
* Mahasiswa mampu menampilkan isi keranjang dan menghitung total transaksi.

---

## Dasar Teori

1. **List** menyimpan data terurut dan dapat duplikat (contoh: ArrayList).
2. **Map** menyimpan data dalam bentuk pasangan *key–value*.
3. **Set** menyimpan elemen unik (tanpa duplikat).
4. ArrayList cocok untuk daftar item yang membutuhkan urutan.
5. HashMap cocok untuk data produk dengan quantity.

---

## Langkah Praktikum

1. Membuat class `Product` dengan atribut code, name, dan price.
2. Membuat class `ShoppingCart` menggunakan ArrayList.
3. Membuat class `ShoppingCartMap` menggunakan HashMap untuk quantity.
4. Membuat class `MainCart` untuk menjalankan program.
5. Melakukan compile dan run.
6. Melakukan commit dan push dengan format:

   `week7-collections: implementasi shopping cart`

---

## Kode Program

```java
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


```

---

## Hasil Eksekusi

*(hasil ada di folder screenshots)*



---

## Analisis

* Program berjalan dengan memanfaatkan struktur data ArrayList dan Map.
* ArrayList digunakan untuk keranjang sederhana tanpa quantity.
* Map digunakan untuk keranjang yang membutuhkan quantity per produk.
* Perbedaan: Minggu ini fokus pada struktur data dinamis, dan tidak menggambar berbagi macam diagram
* Kendala: Perlu memahami perbedaan key-value dan iterasi Map.

---

## Kesimpulan

Praktikum minggu ini memperkenalkan Collections Framework yang sangat membantu dalam pengelolaan data. Dengan ArrayList dan Map, pengelolaan keranjang belanja menjadi lebih efektif, fleksibel, dan terstruktur.

---

## Quiz

1. **Jelaskan perbedaan mendasar antara List, Map, dan Set.**

   **Jawaban:** List menyimpan data terurut dan dapat duplikat; Map menyimpan pasangan key–value; Set menyimpan data unik tanpa duplikat.

2. **Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?**

   **Jawaban:** Karena urutan data dipertahankan dan proses tambah/hapus item dilakukan secara sederhana dan cepat.

3. **Bagaimana struktur Set mencegah duplikasi data?**

   **Jawaban:** Set menggunakan mekanisme hashing sehingga elemen yang sama tidak bisa ditambahkan dua kali.
