# Laporan Praktikum Minggu 1
Topik: "Pengenalan Paradigma dan Setup Proyek"

## Identitas
- Nama  : Fendy Agustian
- NIM   : 240202898
- Kelas : 3IKRB

---

## Tujuan
- Mahasiswa mampu mendefinisikan paradigma prosedural, OOP, dan fungsional.
- Mahasiswa mampu membandingkan kelebihan dan keterbatasan tiap paradigma.
- Mahasiswa mampu memberikan contoh program sederhana untuk masing-masing paradigma.
- Mahasiswa aktif dalam diskusi kelas (bertanya, menjawab, memberi opini).

---

## Dasar Teori
Paradigma pemrograman adalah cara pandang dalam menyusun program:  
- **Prosedural**: program dibangun sebagai rangkaian perintah (fungsi/prosedur).  
- **OOP (Object-Oriented Programming)**: program dibangun dari objek yang memiliki data (atribut) dan perilaku (method).  
- **Fungsional**: program dipandang sebagai pemetaan fungsi matematika, lebih menekankan ekspresi dan transformasi data. 

---

## Langkah Praktikum
1. Menginstall JDK versi terbaru dan cek instalasinya menggunakan perintah `java -version`  
2. Membuat file program `HelloProcedural.java`, `HelloOOP.java`, dan `HelloFunctional.java`.
3. Menulis kode sesuai paradigma yang diminta.
4. Melakukan eksekusi program
5. Mengecek hasil eksekusi di terminal.
6. Membuat commit ke repository GitHub dengan pesan commit `"week1-setup-hello-pos"`

---

## Kode Program
- Prosedural
```java
// HelloProcedural
public class HelloProcedural {
    public static void main(String[] args) {
        String nim = "240202898";
        String name = "Fendy Agustian";

        System.out.println("Hello World, I am " + name + " - " + nim);
    }
}
```

- OOP (Object-Oriented Programming)
```java
// HelloOOP
class Student {
    String nim;
    String name;
    Student(String nim, String name) {
        this.nim = nim;
        this.name = name;
    }

    void introduce() {
        System.out.println("Hello World, I am " + name + " - " + nim);
    }
}

public class HelloOOP {
    public static void main(String[] args) {
        Student s = new Student("240202898", "Fendy Agustian");
        s.introduce();
    }
}
```
- Fungsional
```java
// HelloFunctional
import java.util.function.BiConsumer;

public class HelloFunctional {
    public static void main(String[] args) {
        BiConsumer<String, String> introduce =
            (nim, name) -> System.out.println("Hello World, I am " + name + " - " + nim);

        introduce.accept("240202898","Fendy Agustian");
    }
}
```

---

## Hasil Eksekusi
- HelloProcedural
<img width="1002" height="90" alt="Screenshot 2025-10-10 201924" src="https://github.com/user-attachments/assets/29337483-a1e3-4f87-85a9-3bddd90c5533" />

- HelloOOP
<img width="1007" height="133" alt="Screenshot 2025-10-10 202451" src="https://github.com/user-attachments/assets/2aae21a2-79cc-42b2-9734-310bd77fbe78" />  

- HelloFunctional
<img width="808" height="157" alt="Screenshot 2025-10-10 203339" src="https://github.com/user-attachments/assets/414524e6-8ebf-413c-a609-520d2a4948f5" />


---

## Analisis
- Cara kerja kode:
   - Paradigma Prosedural: Implementasi kode didasarkan pada serangkaian instruksi yang dieksekusi secara berurutan, tanpa        menggunakan struktur pembungkus seperti class.
   - Paradigma Object-Oriented Programming (OOP): Kode diorganisir melalui Class Student, yang berhasil mengikat (meng-           encapsulate) data spesifik mahasiswa (nim, name) dan perilakunya (introduce()) menjadi satu unit logis (objek).
   - Paradigma Fungsional: Pendekatan ini memanfaatkan ekspresi lambda dan functional interface (khususnya BiConsumer) untuk      menjalankan aksi sederhana, yaitu pencetakan pesan, yang menghasilkan kode yang ringkas dan deklaratif. 
- Perbedaan dengan minggu sebelumnya:
   - Minggu ini mulai diperkenalkan perbedaan paradigma, tidak hanya menulis instruksi sederhana.
   - Pendekatan OOP membuat program lebih terstruktur, sedangkan functional membuat kode lebih ringkas.
- Kendala:
   Kesulitan awal yang dihadapi adalah menentukan secara jelas kapan sebaiknya menggunakan struktur class (OOP) dan kapan       lebih efisien menggunakan ekspresi lambda (Fungsional). Solusi untuk kendala ini adalah mendalami sumber referensi resmi     Java, terutama yang berkaitan dengan paket java.util.function, serta mempelajari kasus implementasi dasar OOP. 
  
---

## Kesimpulan
  - Pemrograman Prosedural ideal untuk proyek yang tidak kompleks dan diutamakan untuk kecepatan implementasi.
  - Pemrograman Berorientasi Objek (OOP) menyediakan fondasi struktural melalui penggunaan class dan objek, yang sangat          memfasilitasi pengembangan dan skalabilitas program di masa depan.
  - Pemrograman Fungsional unggul dalam menghasilkan kode yang lebih padat dan mengurangi kode berulang (boilerplate) dengan     memanfaatkan lambda expressio.
    
Dengan pemahaman yang kokoh terhadap ketiga model pemrograman ini, seorang pengembang dapat membuat keputusan yang terinformasi untuk memilih metodologi yang paling sesuai dengan kebutuhan spesifik dari aplikasi yang sedang dibangun.

---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural?  
   **Jawaban:**
   Tidak, Pemrograman Berorientasi Objek (OOP) tidak selalu lebih baik daripada pemrograman prosedural; keduanya memiliki       kekuatan dan kelemahan, dan pilihan terbaik tergantung pada jenis proyek yang sedang dikerjakan.
   
3. Kapan functional programming lebih cocok digunakan dibanding OOP  atau prosedural?    
   **Jawaban:**
   Pemrograman Fungsional (FP) lebih cocok digunakan daripada OOP atau prosedural ketika proyek berfokus pada transformasi      data yang kompleks, memerlukan komputasi paralel yang aman, dan membutuhkan kode yang sangat teruji (testable) dan dapat     diprediksi (predictable). 

4. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?  
   **Jawaban:**
   - **Prosedural:** Mudah dipahami untuk program kecil, tapi sulit di-*maintain* ketika aplikasi membesar karena logika dan data sering bercampur. Skalabilitas rendah.  
   - **OOP:** Lebih maintainable untuk sistem besar karena ada struktur class, encapsulation, inheritance, dan polymorphism. Skalabilitas tinggi karena mudah menambah fitur dengan objek baru.  
   - **Functional:** Maintainability tinggi untuk logika kompleks, karena fungsi murni lebih mudah diuji dan dirangkai ulang. Skalabilitas baik di sistem berbasis data atau yang membutuhkan *concurrency*. 

5. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?  
**Jawaban:**
   OOP (Pemrograman Berorientasi Objek) jauh lebih cocok untuk mengembangkan aplikasi Point of Sale (POS) dibandingkan pemrograman prosedural karena POS adalah sistem yang secara alami kaya akan entitas dunia nyata yang saling berinteraksi dan memerlukan pemeliharaan serta pengembangan fitur yang berkelanjutan.

6. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (boilerplate code)?  
**Jawaban:**
   Paradigma fungsional mengurangi boilerplate dengan:
   - **Higher-order function** → kita bisa membuat fungsi umum (misalnya `map`, `filter`, `reduce`) yang bisa dipakai ulang untuk berbagai kebutuhan tanpa menulis loop berulang.  
   - **Immutability & pure function** → memisahkan logika dari state, sehingga fungsi bisa dipakai ulang tanpa bergantung pada konteks.  
   - **Function composition** → membangun fungsi kompleks dari fungsi kecil tanpa duplikasi kode.  
