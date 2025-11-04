# Laporan Praktikum Minggu 4
Topik: Polymorphism (Info Produk)

## Identitas
- Nama  : Fendy Agustian
- NIM   : 240202898
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep polymorphism** dalam OOP.  
- Mahasiswa mampu **membedakan method overloading dan overriding**.  
- Mahasiswa mampu **mengimplementasikan polymorphism (overriding, overloading, dynamic binding)** dalam program.  
- Mahasiswa mampu **menganalisis contoh kasus polymorphism** pada sistem nyata (Agri-POS).
---

## Dasar Teori
Polymorphism berarti “banyak bentuk” dan memungkinkan objek yang berbeda merespons panggilan method yang sama dengan cara yang berbeda.

   1. Overloading → mendefinisikan method dengan nama sama tetapi parameter berbeda.
   2. Overriding → subclass mengganti implementasi method dari superclass.
   3. Dynamic Binding → pemanggilan method ditentukan saat runtime, bukan compile time.
Dalam konteks Agri-POS, misalnya:

   -Method getInfo() pada Produk dioverride oleh Benih, Pupuk, AlatPertanian untuk menampilkan detail spesifik.
   
   -Method tambahStok() bisa dibuat overload dengan parameter berbeda (int, double).

---

## Langkah Praktikum
1. **Overloading**  
   - Tambahkan method `tambahStok(int jumlah)` dan `tambahStok(double jumlah)` pada class `Produk`.  

2. **Overriding**  
   - Tambahkan method `getInfo()` pada superclass `Produk`.  
   - Override method `getInfo()` pada subclass `Benih`, `Pupuk`, dan `AlatPertanian`.  

3. **Dynamic Binding**  
   - Buat array `Produk[] daftarProduk` yang berisi objek `Benih`, `Pupuk`, dan `AlatPertanian`.  
   - Loop array tersebut dan panggil `getInfo()`. Perhatikan bagaimana Java memanggil method sesuai jenis objek aktual.  

4. **Main Class**  
   - Buat `MainPolymorphism.java` untuk mendemonstrasikan overloading, overriding, dan dynamic binding.  

5. **CreditBy**  
   - Tetap panggil `CreditBy.print("<NIM>", "<Nama>")`.  

6. **Commit dan Push**  
   - Commit dengan pesan: `week4-polymorphism`.

---

## Kode Program

### Produk.java (Overloading & getInfo default)  

```java
package main.java.com.upb.agripos.model;
// Produk.java

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    protected int stok;

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
        if (jumlah > 0) {
            this.stok += jumlah;
            System.out.println("Stok " + nama + " bertambah " + jumlah + " (int). Stok baru: " + this.stok);
        } else {
            // Mengubah pesan agar lebih sesuai konteks
            System.out.println("Gagal: Jumlah stok yang ditambahkan harus lebih dari nol!");
        }
    }

    public void tambahStok(double jumlah) {
        int jumlahInt = (int) Math.round(jumlah);
        if (jumlahInt > 0) {
            this.stok += jumlahInt;
            System.out.println("Stok " + nama + " bertambah " + jumlah + " (double/dibulatkan jadi " + jumlahInt + "). Stok baru: " + this.stok);
        } else {
            System.out.println("Gagal: Jumlah stok yang ditambahkan (setelah dibulatkan) harus lebih dari nol!");
        }
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            System.out.println("Stok " + nama + " berkurang " + jumlah + ". Stok baru: " + this.stok);
        } else {
            System.out.println("Stok tidak mencukupi untuk " + nama + "! Tersedia: " + this.stok);
        }
    }

    public void tampilkanData() {
        System.out.println("  Kode Produk: " + kode);
        System.out.println("  Nama Produk: " + nama);
        System.out.println("  Harga (Rp): " + harga);
        System.out.println("  Stok Tersedia: " + stok);
    }

    public void getInfo() {
        System.out.println("--- Detail Produk Umum ---");
        System.out.println(" Kode: " + kode);
        System.out.println(" Nama: " + nama);
        System.out.println(" Harga (Rp): " + harga);
        System.out.println(" Stok Tersedia: " + stok);
    }

}
```
### MainPolymorphism.java

```package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.ObatTanaman;
import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.model.Pupuk;
import main.java.com.upb.agripos.util.CreditBy; 

public class MainPolymorphism {
    public static void main(String[] args) {
        
        System.out.println("=== WEEK4 POLYMORPHISM ===");
        
        System.out.println("\n--- Overloading (tambahStok) ---");
        Produk produkUmum = new Produk("PRD001", "Jagung", 300000, 150);

        System.out.print("Panggilan INT: ");
        produkUmum.tambahStok(50); 
        
        System.out.print("Panggilan DOUBLE: ");
        produkUmum.tambahStok(14.8); 

        
        System.out.println("\n--- Overriding (getInfo) & Dynamic Binding ---");
        
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 150, "IR64");
        

        Pupuk p_urea = new Pupuk("PPK-101", "Pupuk Urea", 350000,20, "Anorganik");
        Pupuk p_granul = new Pupuk("PPK-102", "Pupuk Granul", 550000, 50, "Organik"); 

    
        AlatPertanian a_baja = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 25, "Baja");
        AlatPertanian a_kayu = new AlatPertanian("ALT-502", "Garu", 20000, 25, "Kayu");
        AlatPertanian a_plastik = new AlatPertanian("ALT-503", "Mulsa", 750000, 20, "Plastik");

        ObatTanaman o_tanaman = new ObatTanaman("OBT1", "Gramaxon", 45000, 25, "Cair"); 
        
        Produk[] daftarProduk = {
            b, 
            p_urea, 
            p_granul,
            a_baja,
            a_kayu,
            a_plastik, 
            o_tanaman, 
            produkUmum 
        };
        
        System.out.println("\nHasil getInfo() melalui array Produk[]:");
        for (Produk p : daftarProduk) {
            
            p.getInfo(); 
        }

        
        CreditBy.print("240202898", "Fendy Agustian"); 
    }
}
```
---

## Hasil Eksekusi  
![alt text](<Screenshot 2025-11-04 194718.png>)
![alt text](<Screenshot 2025-11-04 194803.png>)
![alt text](<Screenshot 2025-11-04 194816.png>)

---
## Analisis
- **Jelaskan bagaimana kode berjalan.**

   Program membuat beberapa objek produk pertanian (Benih, Pupuk, AlatPertanian, dan ObatHama) yang semuanya turunan dari class Produk. Setiap subclass meng-override method getInfo() agar menampilkan informasi berbeda sesuai jenis produk. Array Produk[] digunakan untuk menyimpan semua objek tersebut, lalu saat p.getInfo() dipanggil di dalam perulangan, Java otomatis memanggil versi method yang sesuai dengan objek aslinya (dynamic binding).

- **Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.** 

   Minggu sebelumnya (Inheritance) fokus pada pewarisan atribut dan method dari superclass ke subclass. Sedangkan minggu ini (Polymorphism) fokus pada perbedaan perilaku antar subclass melalui method overriding dan dynamic binding, meskipun dipanggil dengan tipe referensi yang sama (Produk).

- **Kendala yang dihadapi dan cara mengatasinya.**

  Kebingungan antara Overloading dan Overriding: Terdapat dua jenis utama polimorfisme (statis/waktu kompilasi dan dinamis/waktu proses), yang masing-masing dicapai melalui method overloading dan method overriding. Pemula sering kali bingung membedakan kedua mekanisme ini dan kapan harus menggunakannya. 
---

## Kesimpulan
Pada praktikum ini, konsep polymorphism berhasil diterapkan dengan menggunakan method overloading dan overriding. Setiap subclass (Benih, Pupuk, AlatPertanian, dan ObatHama) dapat menampilkan informasi berbeda melalui method getInfo(), meskipun semuanya dipanggil menggunakan tipe referensi Produk.Dengan polymorphism, program menjadi lebih fleksibel, efisien, dan mudah dikembangkan karena satu method dapat berperilaku berbeda sesuai objek yang digunakan.

---

## Quiz
1. Apa perbedaan overloading dan overriding?  
   **Jawaban:** overloading memungkinkan beberapa metode dengan nama sama dalam satu kelas, namun dengan parameter berbeda (polimorfisme waktu kompilasi), sedangkan overriding memungkinkan kelas anak mengubah implementasi metode yang diwarisi dari kelas induk dengan nama dan parameter yang sama persis (polimorfisme waktu proses).
2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?  
   **Jawaban:** Java menentukan metode mana yang dipanggil dalam pengikatan dinamis saat runtime, berdasarkan objek aktual yang direferensikan oleh variabel, bukan berdasarkan tipe variabel itu sendiri. Mekanisme ini bergantung pada polimorfisme runtime melalui penggantian metode (method overriding), di mana Java Virtual Machine (JVM) secara dinamis memilih implementasi metode yang benar pada saat eksekusi. 
3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.  
   **Jawaban:** Dalam sistem POS ritel (misalnya, toko pakaian atau elektronik), terdapat berbagai metode pembayaran seperti tunai, kartu kredit, kartu debit, dompet digital (e-wallet), atau voucher hadiah. Meskipun setiap metode ini memerlukan alur pemrosesan yang sedikit berbeda, sistem POS dapat menggunakan polimorfisme untuk menanganinya secara seragam.
