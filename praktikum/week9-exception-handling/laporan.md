# Laporan Praktikum Minggu 9  
**Topik: Exception Handling**

---

## Identitas
- **Nama**  : Fendy Agustian  
- **NIM**   : 240202898  
- **Kelas** : 3IKRB  

---

## Tujuan
Tujuan dari praktikum minggu ke-9 ini adalah:
- Memahami perbedaan antara **error** dan **exception** dalam pemrograman Java.
- Mampu mengimplementasikan **try–catch–finally** untuk menangani kesalahan program.
- Mampu membuat **custom exception** sesuai kebutuhan aplikasi.
- Mengintegrasikan exception handling ke dalam studi kasus **keranjang belanja (Agri-POS)**.
- Memahami penerapan **Singleton Pattern** untuk pengelolaan data terpusat.

---

## Dasar Teori
1. **Exception** adalah kondisi tidak normal yang masih dapat ditangani oleh program.
2. **Error** merupakan kondisi fatal yang umumnya tidak dapat dipulihkan.
3. **Try–catch–finally** digunakan untuk mencegah program berhenti secara tiba-tiba.
4. **Custom exception** dibuat untuk menghasilkan pesan kesalahan yang spesifik.
5. **Singleton Pattern** memastikan hanya terdapat satu instance dari suatu class.

---

## Langkah Praktikum
1. Membuat project Java untuk praktikum minggu 9.
2. Membuat beberapa **custom exception**, yaitu:
   - `InvalidQuantityException`
   - `ProductNotFoundException`
   - `InsufficientStockException`
   - `CartEmptyException`
3. Memodifikasi class `Product` dengan atribut stok.
4. Mengimplementasikan **exception handling** pada class `ShoppingCart`.
5. Menerapkan **Singleton Pattern** pada class `ProductService`.
6. Menguji program dengan berbagai skenario kesalahan.
7. Melakukan commit dan push ke repository.

**Commit message:**
```text
week9-exception: implement custom exception dan singleton pattern
Kode Program
InvalidQuantityException.java
java
package com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}
Product.java
java

package com.upb.agripos;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void reduceStock(int qty) {
        this.stock -= qty;
    }
}
ShoppingCart.java
java

package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product, int quantity)
            throws InvalidQuantityException {

        if (quantity <= 0) {
            throw new InvalidQuantityException("Kuantitas harus lebih dari 0.");
        }

        items.put(product, items.getOrDefault(product, 0) + quantity);
    }
}
ProductService.java (Singleton Pattern)
java

package com.upb.agripos;

public class ProductService {
    private static ProductService instance;

    private ProductService() {}

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }
}
MainExceptionDemo.java
java

package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am Fendy Agustian-240202898 (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P001", "Beras Premium", 75000, 10);

        try {
            cart.addProduct(p1, -3);
        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Blok finally dieksekusi.");
        }
    }
}
Hasil Eksekusi
Output program yang dihasilkan:
(Screenshot hasil eksekusi disertakan pada folder screenshots/hasil.png)

Analisis
Program berjalan dengan membuat objek ShoppingCart dan Product.

Ketika kuantitas bernilai negatif, program melempar InvalidQuantityException.

Exception ditangkap oleh blok catch sehingga program tidak berhenti secara paksa.

Blok finally tetap dieksekusi untuk memastikan proses akhir tetap berjalan.

Dibandingkan dengan minggu sebelumnya (ArrayList dan HashMap), praktikum minggu ini lebih fokus pada penanganan kesalahan program.

Kendala yang dihadapi adalah memahami alur lempar dan tangkap exception, yang dapat diatasi dengan mencoba berbagai skenario kesalahan.

Kesimpulan
Exception handling sangat penting untuk menjaga kestabilan aplikasi. Penggunaan custom exception membuat pesan kesalahan lebih jelas dan sesuai dengan konteks aplikasi. Singleton Pattern membantu memastikan pengelolaan data terpusat dalam sistem.

Quiz
Jelaskan perbedaan error dan exception.
Jawaban:
Error adalah kondisi fatal yang tidak dapat ditangani oleh program, sedangkan exception adalah kesalahan yang masih dapat ditangani menggunakan mekanisme try–catch.

Apa fungsi blok finally dalam try–catch–finally?
Jawaban:
Blok finally selalu dieksekusi baik terjadi exception maupun tidak, biasanya digunakan untuk proses cleanup resource.

Mengapa custom exception diperlukan?
Jawaban:
Custom exception diperlukan agar penanganan kesalahan lebih spesifik, pesan error lebih informatif, dan sesuai dengan kebutuhan domain aplikasi.

Berikan contoh kasus bisnis POS yang membutuhkan custom exception.
Jawaban:
Contohnya adalah stok produk tidak mencukupi saat checkout, jumlah pembelian bernilai negatif, atau keranjang belanja kosong.

