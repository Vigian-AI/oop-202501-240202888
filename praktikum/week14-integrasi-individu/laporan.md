# Laporan Praktikum Minggu 14
Topik: Integrasi Individu (OOP + Database + GUI)

## Identitas
- Nama  : Vg
- NIM   : 240202888
- Kelas : [Kelas]

---

## Ringkasan Aplikasi
Aplikasi Agri-POS adalah sistem point of sale sederhana yang mengintegrasikan konsep OOP, database PostgreSQL, dan GUI JavaFX. Fitur utama meliputi:
- Kelola produk (tambah, hapus, lihat) yang disimpan di database.
- Keranjang belanja menggunakan Collections untuk menyimpan item yang dipilih.
- Validasi input dan exception handling.
- Unit testing untuk logika keranjang.

---

## Keterkaitan dengan Bab 1–13
- Bab 1: Identitas dicetak di console saat aplikasi berjalan.
- Bab 2-5: Model Product menggunakan encapsulation, inheritance (tidak langsung), polymorphism (tidak langsung).
- Bab 6: UML Class Diagram mengikuti struktur MVC + Service + DAO.
- Bab 7: Keranjang menggunakan List<CartItem> dan Map-like behavior.
- Bab 9: Exception handling untuk validasi (IllegalArgumentException).
- Bab 10: Design pattern Singleton untuk DatabaseConnection.
- Bab 11: DAO pattern dengan JDBC untuk CRUD produk.
- Bab 12-13: GUI JavaFX dengan TableView, ListView, dll.

---

## Artefak UML
- Use Case: Kelola Produk (Tambah, Hapus, Lihat), Kelola Keranjang (Tambah ke Keranjang, Checkout).
- Activity Diagram: Alur Tambah Produk dan Tambah ke Keranjang.
- Sequence Diagram: View -> Controller -> Service -> DAO -> DB.
- Class Diagram: Pemisahan layer View, Controller, Service, DAO, Model.

---

## Tabel Traceability Bab 6 → Implementasi

| Artefak | Referensi | Handler/Trigger | Controller/Service | DAO | Dampak |
|---|---|---|---|---|---|
| Use Case | UC-Produk-01 Tambah | Tombol Tambah Produk | PosController.addProduct() → ProductService.insert() | ProductDAO.insert() | DB insert + TableView reload |
| Use Case | UC-Produk-02 Hapus | Tombol Hapus Produk | PosController.deleteProduct() → ProductService.delete() | ProductDAO.delete() | DB delete + TableView reload |
| Use Case | UC-Keranjang-01 Tambah ke Keranjang | Tombol Tambah ke Keranjang | PosController.addToCart() → CartService.addItem() | - | Keranjang update + total berubah |
| Sequence | SD-Produk-01 Tambah | Tombol Tambah | PosController → ProductService → ProductDAO → DB | insert | Produk baru di DB |
| Activity | AD-Produk-01 Tambah | Input form → Validasi → Insert | PosController.addProduct() | ProductDAO.insert() | Produk ditambah |

---

## Screenshot
- app_main.png: Screenshot GUI utama dengan tabel produk dan keranjang.
- junit_result.png: Hasil run test JUnit.

---

## Kendala & Solusi
1. Kendala: Integrasi database dengan GUI awalnya error koneksi. Solusi: Gunakan HikariCP untuk connection pooling.
2. Kendala: Exception handling di layer service. Solusi: Tambahkan validasi di ProductService dan CartService.

---
