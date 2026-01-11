# Laporan Praktikum Minggu 12

Topik: **GUI Dasar JavaFX (Event-Driven Programming)**

---

## Identitas

- Nama  : Fendy Agustian
- NIM   : 240202898
- Kelas : 3IKRB

---

## Tujuan

- Mahasiswa mampu menjelaskan konsep event-driven programming.
- Mahasiswa mampu membangun antarmuka grafis sederhana menggunakan JavaFX.
- Mahasiswa mampu membuat form input data produk.
- Mahasiswa mampu menampilkan daftar produk pada GUI.
- Mahasiswa mampu mengintegrasikan GUI dengan modul backend yang telah dibuat (DAO & Service).

---

## Dasar Teori

1. **Event-Driven Programming** adalah paradigma pemrograman di mana alur program ditentukan oleh event (kejadian) seperti klik tombol, input keyboard, atau aksi pengguna lainnya.
2. **JavaFX** adalah framework untuk membangun aplikasi desktop dengan antarmuka grafis yang modern dan interaktif di Java.
3. **MVC Pattern dalam GUI** memisahkan tampilan (View/JavaFX), pengendali (Controller), dan data (Model) agar aplikasi lebih terstruktur.
4. **Event Handler** adalah mekanisme untuk menangani event pada komponen GUI seperti button, textfield, dan lainnya menggunakan lambda expression atau method reference.
5. **Separation of Concerns** mengharuskan GUI hanya mengurus tampilan dan event, sedangkan logika bisnis dan akses database ditangani oleh Service dan DAO layer.

---

## Langkah Praktikum

1. Membuat struktur project `week12-gui-dasar` dengan integrasi modul sebelumnya (Collections, MVC, DAO).
2. Menyiapkan dependencies JavaFX pada project.
3. Membuat class `ProductService.java` sebagai layer service yang memanggil `ProductDAO`.
4. Membuat GUI form input produk menggunakan JavaFX (`ProductFormView.java`).
5. Membuat `ProductController.java` untuk menghubungkan View dengan Service/DAO.
6. Mengimplementasikan event handler pada tombol "Tambah Produk".
7. Menampilkan data produk pada ListView setelah input berhasil.
8. Menjalankan aplikasi JavaFX dan memverifikasi integrasi dengan database.
9. Membuat tabel traceability yang menghubungkan artefak Bab 6 (Use Case, Activity, Sequence Diagram) dengan implementasi GUI.
10. Commit dan push dengan format:

    `week12-gui-dasar: implement javafx form with event-driven pattern`

---

## Kode Program

### 1. Product.java (Model - dari Minggu 11)

```java
package com.upb.agripos.model;

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

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    
    @Override
    public String toString() {
        return code + " - " + name + " - Rp" + price + " - Stock: " + stock;
    }
}
```

### 2. ProductDAO.java & ProductDAOImpl.java (dari Minggu 11)

```java
package com.upb.agripos.dao;

import java.util.List;
import com.upb.agripos.model.Product;

public interface ProductDAO {
    void insert(Product product) throws Exception;
    Product findByCode(String code) throws Exception;
    List<Product> findAll() throws Exception;
    void update(Product product) throws Exception;
    void delete(String code) throws Exception;
}
```

*(ProductDAOImpl sama seperti Minggu 11)*

### 3. ProductService.java (Service Layer)

```java
package com.upb.agripos.service;

import java.sql.Connection;
import java.util.List;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(Connection connection) {
        this.productDAO = new ProductDAOImpl(connection);
    }

    public void insert(Product product) throws Exception {
        // Validasi bisnis bisa ditambahkan di sini
        if (product.getPrice() < 0) {
            throw new Exception("Harga tidak boleh negatif");
        }
        if (product.getStock() < 0) {
            throw new Exception("Stok tidak boleh negatif");
        }
        productDAO.insert(product);
    }

    public Product findByCode(String code) throws Exception {
        return productDAO.findByCode(code);
    }

    public List<Product> findAll() throws Exception {
        return productDAO.findAll();
    }

    public void update(Product product) throws Exception {
        productDAO.update(product);
    }

    public void delete(String code) throws Exception {
        productDAO.delete(code);
    }
}
```

### 4. ProductController.java (Controller)

```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(TextField txtCode, TextField txtName, 
                          TextField txtPrice, TextField txtStock, 
                          ListView<String> listView) {
        try {
            // Validasi input
            if (txtCode.getText().isEmpty() || txtName.getText().isEmpty() ||
                txtPrice.getText().isEmpty() || txtStock.getText().isEmpty()) {
                showAlert("Error", "Semua field harus diisi!", Alert.AlertType.ERROR);
                return;
            }

            // Buat objek Product
            Product product = new Product(
                txtCode.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtStock.getText())
            );

            // Simpan melalui service
            productService.insert(product);

            // Update ListView
            listView.getItems().add(product.toString());

            // Clear form
            txtCode.clear();
            txtName.clear();
            txtPrice.clear();
            txtStock.clear();

            showAlert("Sukses", "Produk berhasil ditambahkan!", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            showAlert("Error", "Harga dan Stok harus berupa angka!", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Gagal menambahkan produk: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void loadAllProducts(ListView<String> listView) {
        try {
            listView.getItems().clear();
            for (Product p : productService.findAll()) {
                listView.getItems().add(p.toString());
            }
        } catch (Exception e) {
            showAlert("Error", "Gagal memuat data: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
```

### 5. ProductFormView.java (JavaFX View)

```java
package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.upb.agripos.controller.ProductController;

public class ProductFormView {
    private final ProductController controller;
    
    private TextField txtCode;
    private TextField txtName;
    private TextField txtPrice;
    private TextField txtStock;
    private Button btnAdd;
    private Button btnRefresh;
    private ListView<String> listView;

    public ProductFormView(ProductController controller) {
        this.controller = controller;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agri-POS - Form Produk");

        // Form Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Labels dan TextFields
        Label lblCode = new Label("Kode Produk:");
        txtCode = new TextField();
        
        Label lblName = new Label("Nama Produk:");
        txtName = new TextField();
        
        Label lblPrice = new Label("Harga:");
        txtPrice = new TextField();
        
        Label lblStock = new Label("Stok:");
        txtStock = new TextField();

        // Buttons
        btnAdd = new Button("Tambah Produk");
        btnRefresh = new Button("Refresh");

        // ListView
        listView = new ListView<>();
        listView.setPrefHeight(200);

        // Layout Grid
        grid.add(lblCode, 0, 0);
        grid.add(txtCode, 1, 0);
        grid.add(lblName, 0, 1);
        grid.add(txtName, 1, 1);
        grid.add(lblPrice, 0, 2);
        grid.add(txtPrice, 1, 2);
        grid.add(lblStock, 0, 3);
        grid.add(txtStock, 1, 3);
        grid.add(btnAdd, 0, 4);
        grid.add(btnRefresh, 1, 4);

        // Main Layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(
            new Label("=== Form Input Produk ==="),
            grid,
            new Label("=== Daftar Produk ==="),
            listView
        );

        // Event Handlers
        btnAdd.setOnAction(event -> {
            controller.addProduct(txtCode, txtName, txtPrice, txtStock, listView);
        });

        btnRefresh.setOnAction(event -> {
            controller.loadAllProducts(listView);
        });

        // Load initial data
        controller.loadAllProducts(listView);

        // Scene
        Scene scene = new Scene(vbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
```

### 6. AppJavaFX.java (Main Application)

```java
package com.upb.agripos;

import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

public class AppJavaFX extends Application {
    
    private Connection connection;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Hello, I am Fendy Agustian-240202898 (Week12)");
        
        // Setup database connection
        connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos",
            "postgres",
            "1234"
        );
        
        // Setup Service dan Controller
        ProductService productService = new ProductService(connection);
        ProductController productController = new ProductController(productService);
        
        // Setup View
        ProductFormView view = new ProductFormView(productController);
        view.start(primaryStage);
    }
    
    @Override
    public void stop() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Hasil Eksekusi

- GUI JavaFX berhasil ditampilkan dengan form input produk yang memiliki 4 TextField (Kode, Nama, Harga, Stok).
- Tombol "Tambah Produk" berfungsi untuk menyimpan data ke database dan menampilkan di ListView.
- Data yang ditambahkan langsung tersimpan di database PostgreSQL.

Screenshot GUI tersimpan di `screenshots`

---

## Traceability Bab 6 → GUI

| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
|---|---|---|---|---|---|
| Use Case | UC-01 Tambah Produk | Tombol "Tambah Produk" (`btnTambah`) | `ProductController.addProduct()` → `ProductService.insert()` | `ProductDAOImpl.insert()` | TextFields diisi user → klik button → validasi → simpan ke DB → ListView bertambah item → fields di-clear → muncul konfirmasi sukses |
| Use Case | UC-02 Lihat Daftar Produk | Tombol "Daftar Produk" (`btnDaftar`) + window onLoad | `ProductController.loadAllProducts()` → `ProductService.findAll()` | `ProductDAOImpl.findAll()` | ListView menampilkan semua data produk dari tabel products dengan format: "kode - nama - harga - stok" |
| Activity Diagram | AD-01 Tambah Produk | Event handler `btnTambah.setOnAction()` | Validasi input kosong → validasi format angka → `controller.addProduct()` → `service.insert()` | `ProductDAOImpl.insert()` mengeksekusi INSERT SQL | User input data → validasi berhasil → simpan ke database → clear form → update ListView → tampil alert sukses / error jika gagal |
| Sequence Diagram | SD-01 Tambah Produk | `btnTambah.setOnAction(event -> {...})` | ProductFormView → ProductController → ProductService → ProductDAO → PostgreSQL Database | Query execution dengan PreparedStatement → return success/exception | User klik button → event captured → controller proses → service validasi bisnis → DAO insert ke DB → feedback (Alert) ke user interface |
| Class Diagram | Product, ProductDAO, ProductService, ProductController | TextField (txtKode, txtNama, txtHarga, txtStok), Button (btnTambah, btnDaftar), ListView | Relasi dependency: View depends on Controller, Controller depends on Service, Service depends on DAO | Interface ProductDAO diimplementasi oleh ProductDAOImpl | Arsitektur berlapis (layered): Presentation Layer (View) → Application Layer (Controller) → Business Layer (Service) → Data Access Layer (DAO) → Database |

---

## Analisis

- **Event-Driven Architecture**: Aplikasi berjalan berdasarkan event user seperti klik tombol. Setiap event ditangani oleh event handler yang terhubung dengan controller untuk memproses logika bisnis.

- **Separation of Concerns**: GUI (View) hanya bertanggung jawab menampilkan interface dan menangkap event user. Controller mengatur alur aplikasi, Service menangani logika bisnis, dan DAO mengelola akses database. Pemisahan ini membuat kode lebih modular.

- **Integrasi dengan Modul Sebelumnya**: 
  - Model Product dari Minggu 11 digunakan kembali tanpa perubahan
  - ProductDAO dan ProductDAOImpl tetap sama seperti Minggu 11
  - ProductService ditambahkan sebagai layer baru untuk validasi bisnis
  - Collections (List) digunakan untuk menampung data produk di ListView

- **Dependency Injection**: ProductService dan ProductController menerima dependensi melalui constructor, mengikuti prinsip Dependency Inversion Principle (DIP) dari SOLID.

- **Perbedaan dengan Minggu Sebelumnya**: 
  - Minggu 11 menggunakan console-based interface, Minggu 12 menggunakan GUI JavaFX
  - Interaksi user lebih intuitif dengan button dan form visual
  - Feedback langsung melalui Alert dialog
  - Data ditampilkan secara real-time di ListView

- **Kendala**: 
  - Perlu menambahkan JavaFX dependencies ke project (Maven/Gradle)
  - Memastikan JavaFX SDK tersedia di development environment
  - Handling exception untuk input validation dan database error

---

## Kesimpulan

- **Event-driven programming** membuat aplikasi lebih responsif dan interaktif dengan merespons aksi user secara langsung.
- **JavaFX** menyediakan framework yang powerful untuk membangun GUI modern dengan komponen seperti TextField, Button, ListView, dan Alert.
- **MVC pattern** pada GUI memisahkan tanggung jawab antara tampilan, pengendali, dan logika bisnis/data, sehingga kode lebih maintainable.
- **Integrasi GUI dengan backend** (Service dan DAO) mengikuti prinsip separation of concerns dan tidak melanggar arsitektur yang telah dirancang sebelumnya.
- **Traceability** dari artefak desain Bab 6 (Use Case, Activity Diagram, Sequence Diagram) ke implementasi GUI memastikan konsistensi antara desain dan implementasi.
- Aplikasi yang dibangun sudah mengikuti best practices OOP dan design patterns yang dipelajari di pertemuan-pertemuan sebelumnya.

---

## Quiz

### 1. Apa yang dimaksud dengan event-driven programming dan bagaimana penerapannya di JavaFX?

**Jawaban:**  
Event-driven programming adalah paradigma pemrograman di mana alur eksekusi program ditentukan oleh event (kejadian) yang terjadi, seperti klik tombol atau input keyboard. Di JavaFX, penerapannya dilakukan dengan mendaftarkan event handler menggunakan method `setOnAction()` pada komponen GUI. Ketika user melakukan aksi (misal klik tombol), event handler yang terdaftar akan dieksekusi untuk memproses aksi tersebut.

### 2. Mengapa GUI tidak boleh memanggil DAO secara langsung?

**Jawaban:**  
GUI tidak boleh memanggil DAO secara langsung karena melanggar prinsip Separation of Concerns dan Dependency Inversion Principle (DIP). GUI hanya bertanggung jawab untuk tampilan dan interaksi user. Logika bisnis dan validasi harus dilakukan di layer Service, sedangkan akses database ditangani oleh DAO. Dengan memisahkan ini, aplikasi menjadi lebih modular, mudah diuji (testable), dan mudah dimaintain. Perubahan pada database tidak akan berdampak langsung ke GUI.

### 3. Jelaskan alur lengkap ketika user menekan tombol "Tambah Produk"!

**Jawaban:**  
Alur lengkap sebagai berikut:
1. User mengisi form (kode, nama, harga, stok) dan klik tombol "Tambah Produk"
2. Event handler `btnAdd.setOnAction()` menangkap event klik
3. Event handler memanggil `ProductController.addProduct()`
4. Controller melakukan validasi input (cek field kosong, format angka)
5. Controller membuat objek Product dari input user
6. Controller memanggil `ProductService.insert(product)`
7. Service melakukan validasi bisnis (harga/stok tidak negatif)
8. Service memanggil `ProductDAO.insert(product)`
9. DAO mengeksekusi query INSERT ke database PostgreSQL
10. Setelah berhasil, Controller memperbarui ListView dengan data baru
11. Controller membersihkan form (clear TextFields)
12. Controller menampilkan Alert dialog sukses kepada user

### 4. Apa manfaat membuat tabel traceability dari Bab 6 ke implementasi GUI?

**Jawaban:**  
Manfaat tabel traceability adalah:
- Memastikan implementasi sesuai dengan desain yang telah dibuat di Bab 6 (Use Case, Activity Diagram, Sequence Diagram, Class Diagram)
- Menjaga konsistensi antara fase desain dan implementasi
- Memudahkan verifikasi bahwa semua requirement sudah diimplementasikan
- Membantu dalam maintenance dan debugging karena dapat melihat hubungan antara desain dan kode
- Mendukung prinsip software engineering yang baik yaitu design-first approach
- Memudahkan dokumentasi dan transfer knowledge ke developer lain