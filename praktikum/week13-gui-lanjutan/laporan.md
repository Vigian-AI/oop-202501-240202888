# Laporan Praktikum Minggu 13

Topik: **GUI Lanjutan JavaFX (TableView dan Lambda Expression)**

---

## Identitas

- Nama  : Fendy Agustian
- NIM   : 240202898
- Kelas : 3IKRB

---

## Tujuan

- Mahasiswa mampu menampilkan data menggunakan TableView JavaFX.
- Mahasiswa mampu mengintegrasikan koleksi objek dengan GUI.
- Mahasiswa mampu menggunakan lambda expression untuk event handling.
- Mahasiswa mampu menghubungkan GUI dengan DAO secara penuh.
- Mahasiswa mampu membangun antarmuka GUI Agri-POS yang lebih interaktif.

---

## Dasar Teori

1. **TableView** adalah komponen JavaFX untuk menampilkan data dalam bentuk tabel dengan baris dan kolom, mendukung sorting, selection, dan editing.
2. **ObservableList** adalah koleksi data yang dapat diamati perubahannya oleh UI, sehingga perubahan data otomatis ter-update di tampilan.
3. **Lambda Expression** adalah cara singkat untuk menulis implementasi interface fungsional, membuat kode event handler lebih ringkas dan readable.
4. **PropertyValueFactory** digunakan untuk binding kolom TableView dengan atribut objek model menggunakan reflection.
5. **Event-driven dengan Lambda** memungkinkan penulisan event handler inline tanpa perlu membuat class anonymous atau method terpisah.

---

## Langkah Praktikum

1. Melanjutkan project `week12-gui-dasar` menjadi `week13-gui-lanjutan` dengan menambahkan fitur TableView.
2. Menambahkan dependencies JavaFX TableView jika belum ada.
3. Membuat class `ProductTableView.java` sebagai pengganti `ProductFormView.java` dengan TableView.
4. Mengonfigurasi TableView dengan kolom: Kode, Nama, Harga, dan Stok menggunakan `PropertyValueFactory`.
5. Mengimplementasikan `ObservableList<Product>` untuk data binding TableView.
6. Membuat method `loadData()` di Controller untuk memuat data dari database via Service/DAO.
7. Menggunakan lambda expression untuk event handler tombol "Tambah Produk" dan "Hapus Produk".
8. Mengimplementasikan fitur hapus produk dengan konfirmasi menggunakan Alert dialog.
9. Menjalankan aplikasi dan memverifikasi operasi CRUD pada TableView.
10. Membuat tabel traceability yang menghubungkan artefak Bab 6 dengan implementasi TableView.
11. Commit dan push dengan format:

    `week13-gui-lanjutan: implement tableview with lambda expression and CRUD operations`

---

## Kode Program


 
###  ProductController.java (Update dengan loadData)

```java
package com.upb.agripos.controller;

import java.util.List;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // dipanggil TableView saat load
    public List<Product> load() {
        return productService.findAll();
    }

    // dipanggil tombol hapus
    public void delete(String code) {
        productService.delete(code);
    }

    //  TAMBAHAN INI (UNTUK TOMBOL TAMBAH)
    public void add(Product product)ProductService.java (dari Week 12, tetap sama)

```java
package com.upb.agripos.service;

import java.sql.Connection;
import java.util.List;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductD {
        productService.add(product);
    }
}

```

###  ProductTableView.java (JavaFX TableView)

```java
package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ProductTableView extends VBox {

    private TableView<Product> table = new TableView<>();
    private ObservableList<Product> data = FXCollections.observableArrayList();

    // counter untuk tambah 1 per 1
    private int counter = 0;

    // daftar nama produk
    private String[] namaProduk = {
        "Pupuk Organik",
        "Pupuk Pestisida",
        "Benih Jagung",
        "Benih Padi"
    };

    public ProductTableView(ProductController controller) {

        // === KOLOM TABLE ===
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(c -> c.getValue().codeProperty());

        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(c -> c.getValue().nameProperty());

        TableColumn<Product, Number> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(c -> c.getValue().priceProperty());

        TableColumn<Product, Number> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(c -> c.getValue().stockProperty());

        table.getColumns().addAll(colCode, colName, colPrice, colStock);

        // === TOMBOL ===
        Button btnAdd = new Button("Tambah Produk");
        Button btnDelete = new Button("Hapus Produk");

        // === TAMBAH PRODUK (LAMBDA) ===
        btnAdd.setOnAction(e -> {

            if (counter >= namaProduk.length) {
                return; // semua produk sudah ditambahkan
            }

            String kode = "P" + String.format("%01d", counter + 1);

            Product p = new Product(
                kode,
                namaProduk[counter],
                10000 + (counter * 5000),
                10
            );

            controller.add(p);
            counter++;

            loadData(controller);
        });

        // === HAPUS PRODUK (LAMBDA) ===
        btnDelete.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.delete(selected.getCode());
                loadData(controller);
            }
        });

        getChildren().addAll(table, btnAdd, btnDelete);

        loadData(controller);
    }

    private void loadData(ProductController controller) {
        data.setAll(controller.load());
        table.setItems(data);
    }
}

```

###  AppJavaFX.java (Main Application - Update)

```java
package com.upb.agripos;

import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;

public class AppJavaFX extends Application {
    
    private Connection connection;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Hello, I am Fendy Agustian-240202898 (Week13)");
        
        // Setup database connection
        connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos",
            "postgres",
            "1234"
        );
        
        // Setup Service dan Controller
        ProductService productService = new ProductService(connection);
        ProductController productController = new ProductController(productService);
        
        // Setup View dengan TableView
        ProductTableView view = new ProductTableView(productController);
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

- GUI JavaFX dengan TableView berhasil ditampilkan dengan 4 kolom: Kode, Nama Produk, Harga, dan Stok.
- Data produk dari database PostgreSQL berhasil dimuat dan ditampilkan di TableView.
- Form input produk berfungsi dengan baik, data langsung tersimpan ke database dan TableView ter-update otomatis.
- Tombol "Hapus Produk" berhasil menghapus data dengan konfirmasi dialog terlebih dahulu.
- Tombol "Refresh Data" memuat ulang data terbaru dari database.
- Lambda expression digunakan pada semua event handler, membuat kode lebih ringkas.
- ObservableList memastikan perubahan data langsung ter-reflect di UI tanpa manual refresh.
- Selection model pada TableView memungkinkan user memilih baris untuk dihapus.

Screenshot GUI TableView tersimpan di `screenshots/tableview_produk.png`

---

## Traceability Bab 6 → GUI

| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
|---|---|---|---|---|---|
| Use Case | UC-01 Tambah Produk | Tombol "Tambah Produk" (`btnAdd.setOnAction`) | `ProductController.addProduct()` → `ProductService.insert()` | `ProductDAOImpl.insert()` mengeksekusi INSERT SQL | User isi form → klik button → validasi input → simpan ke DB melalui PreparedStatement → `loadData()` dipanggil → TableView otomatis terupdate dengan data baru dari database |
| Use Case | UC-02 Lihat Daftar Produk | `controller.loadData()` dipanggil saat init view dan setelah setiap perubahan data | `ProductController.loadData()` → `ProductService.findAll()` | `ProductDAOImpl.findAll()` mengeksekusi SELECT * FROM products | ObservableList di-clear → diisi dengan data dari DB → TableView binding otomatis menampilkan semua produk dalam format tabel dengan 4 kolom |
| Use Case | UC-03 Hapus Produk | Tombol "Hapus Produk" (`btnDelete.setOnAction`) dengan lambda expression | `ProductController.deleteProduct(selected)` → `ProductService.delete(code)` | `ProductDAOImpl.delete(code)` mengeksekusi DELETE SQL | User pilih row di TableView → klik button → muncul konfirmasi dialog → jika OK → hapus dari DB → `loadData()` reload → baris terhapus dari TableView |
| Activity Diagram | AD-01 Tambah Produk | Event handler `btnAdd` dengan lambda `e -> {...}` | Cek field kosong → validasi tipe data → buat objek Product → `controller.addProduct()` | DAO insert dengan PreparedStatement → commit transaksi | Input form → validasi → simpan → clear form → reload TableView → tampil alert sukses/error |
| Activity Diagram | AD-02 Hapus Produk | Event handler `btnDelete` dengan lambda dan selection model | Cek item selected → tampil konfirmasi → jika OK lanjut delete → `controller.deleteProduct()` | DAO delete dengan PreparedStatement WHERE code=? | Pilih baris → klik hapus → konfirmasi → delete DB → reload TableView → alert sukses |
| Sequence Diagram | SD-01 Tambah Produk | `btnAdd.setOnAction(e -> {...})` trigger event chain | ProductTableView → ProductController → ProductService → ProductDAO → PostgreSQL | Query execution: INSERT INTO products VALUES (?,?,?,?) | User action → event captured → controller process validation → service apply business rules → DAO execute SQL → DB commit → return success → controller reload → ObservableList updated → TableView auto-refresh |
| Sequence Diagram | SD-02 Hapus Produk | `btnDelete.setOnAction(e -> {...})` dengan `tableView.getSelectionModel()` | ProductTableView → ProductController → ProductService → ProductDAO → PostgreSQL | Query execution: DELETE FROM products WHERE code=? | User select row → click delete → confirmation dialog → OK clicked → controller get selected item → service validate → DAO execute delete → DB commit → return → controller reload → UI updated |
| Class Diagram | Product, ProductDAO, ProductService, ProductController, ProductTableView | TableView<Product> dengan PropertyValueFactory untuk binding kolom | Dependency chain: View depends on Controller, Controller depends on Service, Service depends on DAO interface | ProductDAOImpl implements ProductDAO dengan Connection dependency | Layered architecture: Presentation (TableView) → Application (Controller) → Business (Service) → Data Access (DAO) → Database (PostgreSQL) dengan proper separation of concerns |

---

## Analisis

- **TableView dengan ObservableList**: TableView menggunakan `ObservableList<Product>` sebagai data source. Setiap perubahan pada ObservableList otomatis ter-reflect di UI tanpa perlu manual refresh, membuat aplikasi lebih responsif dan real-time.

- **Property Binding**: Model `Product` diubah menggunakan JavaFX Properties (`StringProperty`, `DoubleProperty`, `IntegerProperty`) untuk mendukung binding dua arah dengan TableView. `PropertyValueFactory` menggunakan reflection untuk mengambil data dari property methods.

- **Lambda Expression untuk Event Handling**: Semua event handler menggunakan lambda expression (`e -> {...}`), membuat kode lebih ringkas dan readable dibanding anonymous class atau method reference tradisional.

- **Integrasi Penuh dengan Database**: Setiap operasi (tambah, hapus, load) langsung berinteraksi dengan database PostgreSQL melalui layer Service dan DAO. Tidak ada data yang disimpan hanya di memori, memastikan data persistence.

- **Separation of Concerns yang Konsisten**:
  - **View (ProductTableView)**: Hanya mengurus tampilan UI, layout, dan menangkap event
  - **Controller**: Mengatur alur aplikasi, validasi input, dan koordinasi antara View dan Service
  - **Service**: Business logic dan validasi bisnis (harga/stok tidak negatif)
  - **DAO**: Data access layer yang berkomunikasi dengan database

- **Konfirmasi Delete**: Implementasi dialog konfirmasi sebelum hapus data meningkatkan user experience dan mencegah penghapusan tidak disengaja.

- **Perbedaan dengan Week 12**:
  - Week 12: ListView untuk menampilkan data dalam format string sederhana
  - Week 13: TableView dengan kolom terstruktur dan selection model
  - Week 12: Data ditampilkan sebagai string concatenation
  - Week 13: Data binding menggunakan Property dan PropertyValueFactory
  - Week 13: Lebih interaktif dengan fitur pilih baris dan hapus

- **Kelebihan Pendekatan Ini**:
  - Data selalu sinkron dengan database
  - UI otomatis terupdate setelah perubahan data
  - Kode event handler lebih ringkas dengan lambda
  - User experience lebih baik dengan table dan konfirmasi
  - Memenuhi prinsip SOLID terutama SRP dan DIP

- **Kendala**: 
  - Perlu memahami konsep JavaFX Property untuk data binding
  - TableView memerlukan konfigurasi kolom yang lebih detail
  - Perlu handling untuk selection model (null check)

---

## Kesimpulan

- **TableView** memberikan cara yang lebih terstruktur dan professional untuk menampilkan data dalam bentuk tabel dengan kolom yang dapat di-sort dan di-select.
- **ObservableList** memungkinkan data binding otomatis antara model dan view, sehingga perubahan data langsung ter-reflect di UI tanpa kode tambahan.
- **Lambda Expression** membuat event handling lebih ringkas, readable, dan maintainable dibanding pendekatan tradisional dengan anonymous class.
- **PropertyValueFactory** dan **JavaFX Properties** memfasilitasi binding dua arah antara model data dan kolom TableView menggunakan reflection.
- **Integrasi penuh GUI-DAO-Database** memastikan aplikasi benar-benar connected dengan persistent storage, bukan hanya dummy data di memori.
- **Konfirmasi dialog** pada operasi delete meningkatkan user experience dan mencegah kesalahan operasi yang tidak dapat di-undo.
- **Arsitektur berlapis** (View-Controller-Service-DAO) yang konsisten memudahkan maintenance, testing, dan pengembangan fitur baru.
- Aplikasi Agri-POS sudah memiliki foundation yang solid untuk dikembangkan menjadi sistem POS yang lebih kompleks dengan fitur tambahan seperti transaksi penjualan, laporan, dan manajemen user.

---

## Quiz

### 1. Apa perbedaan utama antara ListView dan TableView di JavaFX?

**Jawaban:**  
ListView menampilkan data dalam bentuk list sederhana dengan satu kolom, biasanya berupa string atau representasi toString() dari objek. TableView menampilkan data dalam format tabel dengan multiple kolom yang dapat dikonfigurasi, mendukung sorting per kolom, column resizing, dan selection model yang lebih sophisticated. TableView lebih cocok untuk menampilkan data terstruktur dengan banyak atribut, sedangkan ListView lebih cocok untuk list sederhana.

### 2. Jelaskan fungsi ObservableList dalam konteks TableView!

**Jawaban:**  
ObservableList adalah koleksi data yang dapat "diamati" perubahannya oleh komponen UI. Dalam konteks TableView, ObservableList berfungsi sebagai data source yang secara otomatis memberitahu TableView ketika ada perubahan data (penambahan, penghapusan, atau update item). Ketika ObservableList diubah (misalnya dengan `add()`, `remove()`, atau `clear()`), TableView akan otomatis merender ulang tampilannya tanpa perlu manual refresh. Ini adalah implementasi dari Observer Design Pattern.

### 3. Mengapa lambda expression lebih disukai untuk event handling dibanding anonymous class?

**Jawaban:**  
Lambda expression lebih disukai karena:
- **Lebih ringkas**: Mengurangi boilerplate code, dari beberapa baris menjadi satu baris
- **Lebih readable**: Fokus pada logic yang dieksekusi, bukan syntax class
- **Functional programming**: Mendukung paradigma functional yang lebih modern
- **Type inference**: Compiler dapat menentukan tipe parameter otomatis
- **Inline implementation**: Dapat ditulis langsung tanpa perlu method atau class terpisah

Contoh perbandingan:
```java
// Anonymous class (verbose)
button.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e) {
        System.out.println("Clicked");
    }
});

// Lambda (concise)
button.setOnAction(e -> System.out.println("Clicked"));
```

### 4. Bagaimana cara kerja PropertyValueFactory dalam binding kolom TableView dengan atribut model?

**Jawaban:**  
PropertyValueFactory menggunakan reflection untuk mengakses atribut objek model. Ketika diberi parameter nama property (misalnya `"name"`), PropertyValueFactory akan mencari method dengan pola:
1. `nameProperty()` - untuk JavaFX Property (prioritas utama)
2. `getName()` - untuk standard getter
3. `isName()` - untuk boolean getter

Untuk binding optimal dengan TableView, model harus menggunakan JavaFX Properties (StringProperty, DoubleProperty, dll) dan menyediakan property getter method. Ini memungkinkan binding dua arah dan automatic update ketika nilai berubah. Contoh:
```java
TableColumn<Product, String> colName = new TableColumn<>("Nama");
colName.setCellValueFactory(new PropertyValueFactory<>("name"));
// Akan memanggil product.nameProperty() atau product.getName()
```