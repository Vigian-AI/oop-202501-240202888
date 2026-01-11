# Laporan Praktikum Minggu 10

Topik: **Design Pattern (Singleton, MVC) dan Unit Testing menggunakan JUnit**

---

## Identitas

- Nama  : Fendy Agustian  
- NIM   : 240202898  
- Kelas : 3IKRB  

---

## Tujuan

- Mahasiswa mampu menjelaskan konsep dasar design pattern.
- Mahasiswa mampu mengimplementasikan **Singleton Pattern** dengan benar.
- Mahasiswa mampu menerapkan **Model–View–Controller (MVC)** pada aplikasi sederhana.
- Mahasiswa mampu membuat dan menjalankan **unit testing menggunakan JUnit**.
- Mahasiswa mampu menganalisis manfaat design pattern dan unit testing terhadap kualitas perangkat lunak.

---

## Dasar Teori

1. **Design Pattern** adalah solusi desain yang telah terbukti efektif untuk menyelesaikan masalah umum dalam pengembangan perangkat lunak.
2. **Singleton Pattern** memastikan suatu class hanya memiliki satu instance selama aplikasi berjalan.
3. **MVC (Model–View–Controller)** memisahkan logika data, tampilan, dan pengendali aplikasi.
4. **Unit Testing** digunakan untuk memastikan setiap unit kode berjalan sesuai harapan.

---

## Langkah Praktikum

1. Membuat struktur project `week10-pattern-testing`.
2. Mengimplementasikan **Singleton Pattern** pada class `DatabaseConnection`.
3. Membuat struktur **MVC** sederhana untuk fitur Product.
4. Membuat Model, View, dan Controller.
5. Mengintegrasikan MVC pada class `AppMVC`.
6. Membuat unit test menggunakan **JUnit**.
7. Menjalankan aplikasi dan unit test.
8. Commit dan push dengan format:

   `week10-pattern-testing: implement singleton, mvc, dan unit testing`

---

## Kode Program

### 1. DatabaseConnection.java (Singleton)

```java
package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        // constructor private
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

### 2. Product.java (Model)

```java
package com.upb.agripos.model;

public class Product {
    private final String code;
    private final String name;

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
```

### 3. ConsoleView.java (View)

```java
package com.upb.agripos.view;

public class ConsoleView {
    public void showMessage(String message) {
        System.out.println(message);
    }
}
```

### 4. ProductController.java (Controller)

```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private final Product model;
    private final ConsoleView view;

    public ProductController(Product model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void showProduct() {
        view.showMessage("Produk: " + model.getCode() + " - " + model.getName());
    }
}
```

### 5. AppMVC.java (Main Program)

```java
package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;
import com.upb.agripos.controller.ProductController;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am Fendy Agustian-240202898 (Week10)");

        Product product = new Product("P01", "Pupuk Organik");
        ConsoleView view = new ConsoleView();
        ProductController controller = new ProductController(product, view);

        controller.showProduct();
    }
}
```

### 6. ProductTest.java (Unit Test JUnit)

```java
package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    public void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        assertEquals("Benih Jagung", p.getName());
    }
}
```

---

## Hasil Eksekusi

- Program berhasil dijalankan dan menampilkan data produk menggunakan pola MVC.
- Unit test JUnit berjalan tanpa error.
- Hasil pengujian ditampilkan pada IDE dan disimpan pada folder `screenshots/junit_result.png`.

---

## Analisis

- Penerapan Singleton Pattern memastikan hanya ada satu instance objek DatabaseConnection.
- Pola MVC membuat kode lebih rapi dan memisahkan tanggung jawab antar class.
- Unit testing membantu memastikan method berjalan sesuai harapan.
- Dibandingkan minggu sebelumnya, praktikum ini lebih fokus pada struktur desain aplikasi dan kualitas kode.

---

## Kesimpulan

- Design pattern meningkatkan kualitas dan maintainability kode.
- Singleton Pattern cocok digunakan untuk resource global seperti koneksi database.
- MVC mempermudah pengembangan dan pengujian aplikasi.
- Unit testing penting untuk mencegah bug sejak tahap awal pengembangan.

---

## Quiz

### 1. Mengapa constructor pada Singleton harus bersifat private?

**Jawaban:**  
Agar objek tidak dapat dibuat langsung dari luar class dan hanya bisa diakses melalui method `getInstance()`.

### 2. Jelaskan manfaat pemisahan Model, View, dan Controller.

**Jawaban:**  
Agar kode lebih terstruktur, mudah dipelihara, dan setiap bagian memiliki tanggung jawab yang jelas.

### 3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?

**Jawaban:**  
Untuk memastikan setiap unit kode berjalan sesuai harapan dan mendeteksi bug lebih awal.

### 4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?

**Jawaban:**  
Dapat menyebabkan lebih dari satu instance terbentuk sehingga menimbulkan inkonsistensi data.