# Laporan Praktikum Minggu 2

**Topik:** Class dan Object (Produk Pertanian)

## Identitas

- **Nama:** Fendy Agustian
- **NIM:** 240202898
- **Kelas:** 3IKRB

---

## Tujuan

- Mahasiswa mampu **menjelaskan konsep class, object, atribut, dan method** dalam OOP.
- Mahasiswa mampu **menerapkan access modifier dan enkapsulasi** dalam pembuatan class.
- Mahasiswa mampu **mengimplementasikan class Produk pertanian** dengan atribut dan method yang sesuai.
- Mahasiswa mampu **mendemonstrasikan instansiasi object** serta menampilkan data produk pertanian di console.
- Mahasiswa mampu **menyusun laporan praktikum** dengan bukti kode, hasil eksekusi, dan analisis sederhana.

---

## Dasar Teori

1. **Class** adalah cetak biru (blueprint), rancangan, atau prototipe yang mendefinisikan sifat (atribut) dan perilaku (method) yang umum untuk semua objek dari jenis tertentu. Class sendiri bukan data, melainkan definisi tentang bagaimana data akan dibuat dan dikelola.
2. **Object** adalah instansiasi (wujud nyata) dari sebuah Class. Setiap objek memiliki salinan dari atribut yang didefinisikan dalam Class dan dapat menggunakan method yang ada.
3. **Enkapsulasi** ekanisme penggabungan data (atribut) dengan method (perilaku) yang beroperasi pada data tersebut menjadi satu unit (class), sekaligus mengendalikan akses ke data tersebut. Konsep ini sering disebut sebagai "data hiding" (penyembunyian data).

---

## Langkah Praktikum
**Langkah 1**: Persiapan Proyek dan Struktur Direktori
Anggap Anda membuat folder proyek utama bernama AgriPOS. Anda perlu membuat struktur package yang sesuai dengan yang didefinisikan dalam kode (com.upb.agripos.model, com.upb.agripos.util, com.upb.agripos).

**Langkah 2**: Pembuatan File Kode
Buat dan simpan tiga file Java (.java) ke dalam direktori package yang sesuai. File Produk.java Lokasi: AgriPOS/src/com/upb/agripos/model/, File CreditBy.java
Lokasi: AgriPOS/src/com/upb/agripos/util/ , ile MainProduk.java Lokasi: AgriPOS/src/com/upb/agripos/

**Langkah 3**: Kompilasi dan Eksekusi (Manual via Terminal)
Asumsikan Anda berada di dalam folder AgriPOS/ di Terminal/Command Prompt.

  **3.1. Kompilasi**
  Anda harus mengkompilasi semua file .java agar Java Virtual Machine (JVM) dapat membacanya.
  
  **3.2. Eksekusi**
  Jalankan class utama yang berisi method main(), yaitu com.upb.agripos.MainProduk.
Bash


## Kode Program

### Produk.java

```java
package com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
}
```

### CreditBy.java

```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
```

### MainProduk.java

```java
package com.upb.agripos;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        // Tambah dan kurangi stok
        p1.tambahStok(50);
        p2.kurangiStok(20);
        p3.tambahStok(10);

        System.out.println("\nSetelah update stok:");
        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        CreditBy.print("240202898", "Fendy Agustian");
    }
}
```

---

## Hasil Eksekusi

![Screenshot hasil eksekusi](./screenshots/week-2-class-object.png)

---

## Analisis
Analisis

- Cara kerja kode:
    penerapan paradigma oop, penyimpanan informasi objek, metode mutasi data terkontrol, utilitas statis. 
- Perbedaan dengan minggu sebelumnya:
    - Minggu sebelumnya masih menggunakan pendekatan prosedural, di mana semua data dan logika berada di satu fungsi main().
    - Minggu ini mulai menerapkan pendekatan OOP, membuat kode lebih modular dan mudah diperluas.
- Kendala:
    - Tidak menemukan kendala yang begitu berarti

---

## Kesimpulan

- Program Menjadi Lebih Terstruktur (Modularitas)
- Mudah Dikembangkan (Maintainability & Extensibility)
- Mendukung Prinsip Reusability (Dapat Digunakan Kembali🏗️
---

## Quiz

1. **Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?**
   
   **Jawaban:** Mendeklarasikan atribut sebagai private adalah kunci untuk mencapai Penyembunyian Data (Data Hiding) dan Enkapsulasi.
   
2. **Apa fungsi getter dan setter dalam enkapsulasi?**
   
   **Jawaban:** - Getter, atau Accessor (pengakses), digunakan untuk mengambil atau membaca nilai dari atribut privat.
                - Setter, atau Mutator (pengubah), digunakan untuk mengubah atau menetapkan nilai baru pada atribut privat.
   
3. **Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?**
   
   **Jawaban:** Class Produk mendukung pengembangan aplikasi Point of Sale (POS) yang lebih kompleks dengan menyediakan fondasi modular dan terpusat untuk manajemen data                    dan perilaku barang.

