# Laporan Praktikum Minggu 3
Topik: Inheritance (Kategori Produk)

## Identitas
- Nama  : Fendy Agustian
- NIM   : 240202898
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep inheritance (pewarisan class)** dalam OOP.  
- Mahasiswa mampu **membuat superclass dan subclass** untuk produk pertanian.  
- Mahasiswa mampu **mendemonstrasikan hierarki class** melalui contoh kode.  
- Mahasiswa mampu **menggunakan `super` untuk memanggil konstruktor dan method parent class**.  
- Mahasiswa mampu **membuat laporan praktikum** yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.  

---

## Dasar Teori
Inheritance adalah mekanisme dalam OOP yang memungkinkan suatu class mewarisi atribut dan method dari class lain.  
- **Superclass**: class induk yang mendefinisikan atribut umum.  
- **Subclass**: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru.  
- `super` digunakan untuk memanggil konstruktor atau method superclass.  

---

## Langkah Praktikum
1. **Membuat Superclass Produk**  
   - Gunakan class `Produk` dari Bab 2 sebagai superclass.  

2. **Membuat Subclass**  
   - `Benih.java` → atribut tambahan: varietas.  
   - `Pupuk.java` → atribut tambahan: jenis pupuk (Urea, NPK, dll).  
   - `AlatPertanian.java` → atribut tambahan: material (baja, kayu, plastik).  

3. **Membuat Main Class**  
   - Instansiasi minimal satu objek dari tiap subclass.  
   - Tampilkan data produk dengan memanfaatkan inheritance.  

4. **Menambahkan CreditBy**  
   - Panggil class `CreditBy` untuk menampilkan identitas mahasiswa.  

5. **Commit dan Push**  
   - Commit dengan pesan: `week3-inheritance`.  

---

## Kode Program
```java
       package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.*;
import main.java.com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 150, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 20, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 25, "Baja");

        System.out.println("------------------------------------------");
        System.out.println(b.deskripsi());

        System.out.println("------------------------------------------");
        System.out.println(p.deskripsi());
        
        System.out.println("------------------------------------------");
        System.out.println(a.deskripsi());

        CreditBy.print("240202898", "Fendy Agustian");
    }
}
```
---

## Hasil Eksekusi

<img width="788" height="529" alt="Screenshot 2025-10-24 212119" src="https://github.com/user-attachments/assets/9af4ed9f-e44f-46c9-a950-e66ccd813917" />




---

## Analisis
Ketiga subclass (Benih, Pupuk, AlatPertanian) mewarisi atribut dan method dari superclass Produk, seperti nama, harga, dan stok.
Masing-masing menambahkan atribut unik:

   - Benih → jenis_tanaman

   - Pupuk → komposisi

   - AlatPertanian → bahan

Method deskripsi() di setiap subclass menggunakan super() untuk menampilkan informasi dasar dari Produk, lalu menambah detail spesifik masing-masing.

Dengan inheritance, kode menjadi:

   -Lebih efisien: tidak perlu menulis ulang atribut umum.

   -Lebih terstruktur: jelas hubungan induk–turunan.

   -Mudah dikembangkan: mudah menambah jenis produk baru.

Dibanding versi tanpa pewarisan, kode sekarang lebih ringkas, rapi, dan mudah dipelihara.

## Kesimpulan
- Inheritance membuat program lebih terorganisir dan menghindari duplikasi kode.  
- Subclass dapat memperluas fungsionalitas superclass tanpa perlu menulis ulang atribut dan method dasar.  
- Praktikum ini memperkuat pemahaman konsep **reusability dan extensibility** dalam OOP.  
---

## Quiz
1. **Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?**  
   **Jawaban:** Kode menjadi lebih efisien dan mudah dipelihara karena atribut dan method umum hanya didefinisikan satu kali di superclass.  

2. **Bagaimana cara subclass memanggil konstruktor superclass?**  
   **Jawaban:** Dengan menggunakan `super(parameter)` di baris pertama konstruktor subclass.  

3. **Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.**  

   **Jawaban:** Subclass `Pestisida` dengan atribut tambahan seperti `bahanAktif` dan `dosisPenggunaan`.  
