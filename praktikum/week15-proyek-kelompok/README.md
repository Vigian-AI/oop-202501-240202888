# AgriPOS Desktop — Sistem Kasir untuk Produk Pertanian

<div style="text-align:center">

[![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-blue?style=flat)](https://gluonhq.com/products/javafx/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-336791?style=flat&logo=postgresql)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-Build-yellow?style=flat&logo=apache-maven)](https://maven.apache.org/)

</div>

## Ringkasan Singkat

AgriPOS Desktop adalah aplikasi Point-Of-Sale berbasis Java yang dirancang untuk mencatat penjualan produk pertanian, mengelola stok, dan menghasilkan laporan operasional. Aplikasi ini ditujukan untuk usaha ritel pertanian kecil hingga menengah yang memerlukan antarmuka sederhana dan fitur laporan dasar.

## Fitur Utama

- Multi-role (Admin & Kasir) dengan hak akses berbeda
- CRUD produk dan kategori dengan stok yang diperbarui otomatis
- Pengelolaan pengguna untuk admin
- Keranjang belanja yang mendukung penambahan, pengurangan, dan pembaruan jumlah
- Opsi pembayaran Tunai dan Dompet Elektronik (mock)
- Menampilkan struk transaksi setelah checkout
- Histori transaksi dengan kemampuan filter
- Laporan penjualan berbasis rentang tanggal
- Ekspor data transaksi ke file Excel

## Teknologi yang Digunakan

- Java 17 (LTS)
- JavaFX 21 untuk UI
- PostgreSQL (13+)
- Maven sebagai build system
- JDBC Driver PostgreSQL
- Apache POI untuk fitur eksport Excel

## Kebutuhan Sistem (Minimal)

- Java 17 atau lebih
- PostgreSQL 13+ terpasang
- 2 GB RAM (4 GB direkomendasikan)
- Ruang disk sekitar 500 MB

## Cara Cepat Menjalankan (Quick Start)

1) Siapkan database di PostgreSQL:

```powershell
# masuk ke psql
psql -U postgres
# buat database baru
CREATE DATABASE agripos;
\c agripos
```

2) Jalankan skrip skema jika tersedia:

```powershell
psql -U postgres -d agripos -f docs/schema.sql
```

3) Bangun proyek dengan Maven:

```powershell
mvn clean package
```

4) Atur konfigurasi koneksi database pada file konfigurasi (contoh di `src/main/java/com/upb/agripos/config/` atau `config/DatabaseConnection.java`) dengan URL, user, dan password PostgreSQL ditaruh di dalam file .env.

5) Menjalankan aplikasi:

```powershell
# menjalankan lewat plugin JavaFX
mvn javafx:run

```

## Akun Penguji

Contoh akun bawaan yang dapat dipakai untuk pengujian lokal:

Kasir
```
username: kasir
password: 12345
role: Kasir
```

Admin
```
username: admin
password: 12345
role: Admin
```

(Ubah kredensial di database jika perlu)

## Struktur Proyek (ringkasan)

- `src/main/java/com/upb/agripos/` - Kode sumber utama (controller, view, model, service, dao, config)
- `src/test/java/` - Unit test
- `docs/` - Dokumentasi dan berkas pendukung
- `screenshots/` - Bukti tampilan aplikasi
- `pom.xml` - Konfigurasi Maven

File entry point: `AppJavaFX.java`
Beberapa paket penting: `service`, `dao`, `view`, `model`, `controller`.

## Arsitektur Singkat

Aplikasi disusun secara berlapis (presentation → controller → service → dao → database). UI dibangun dengan JavaFX, logika bisnis pada layer service, akses data lewat DAO menggunakan JDBC ke PostgreSQL.

## Pengujian

Untuk menjalankan unit test:

```powershell
mvn test
```

Terdapat test untuk logika keranjang, layanan produk, dan beberapa integrasi ringan.

## Keamanan & Validasi

- Password tidak ditampilkan secara polos di UI
- Validasi input pada form untuk mencegah data tidak valid
- Penggunaan prepared statements untuk mengurangi risiko SQL injection
- Kontrol akses berdasar role (admin vs kasir)

## Build & Distribusi

- Development: `mvn clean compile` dan `mvn javafx:run`
- Release: `mvn clean package` → jalankan JAR dari folder `target`

## Tim Pengembang

- Vigian Agus Isnaeni — Koordinator; membuat unit test; menambahkan fitur tambahan
- Fendi Agustian — Implementasi sistem login; mengatur fungsi laporan gudang dan kasir
- Leila Aristawati — Mengatur fitur gudang dan tampilannya
- Mayang Nur Annisa K — Membuat UI dasar untuk kasir
- Tsaqif Nur Fathan A — Mengelola fitur laporan

## Dokumentasi Tambahan

Lihat file-file di folder `docs/` untuk spesifikasi, arsitektur, dan panduan pengguna lebih rinci.

## Lisensi

Proyek ini dibuat untuk keperluan mata kuliah OOP di Universitas Putra Bangsa. Silakan lihat `LICENSE` untuk detail.

## Bantuan & Troubleshooting

- Koneksi database gagal: pastikan PostgreSQL aktif dan kredensial sesuai.
- Masalah tampilan JavaFX: periksa bahwa Java dan JavaFX cocok versinya dan tersedia di classpath.
- Port PostgreSQL sibuk: ubah port atau hentikan layanan lain yang memakai port tersebut.

--

Updated: 30 Januari 2026
Version: 1.0-SNAPSHOT

