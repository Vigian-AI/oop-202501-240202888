# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Bab 3 – Inheritance (Kategori Produk)

## Identitas
- Nama  : Vigian Agus Isnaeni
- NIM   : 240202888
- Kelas : 3IKRB

---

## Tujuan

  - Mahasiswa mampu menjelaskan konsep inheritance (pewarisan class) dalam OOP. 
  - Mahasiswa mampu membuat superclass dan subclass untuk produk pertanian. 
  - Mahasiswa mampu mendemonstrasikan hierarki class melalui contoh kode. 
  - Mahasiswa mampu menggunakan super untuk memanggil konstruktor dan method parent class. 
  - Mahasiswa mampu membuat laporan praktikum yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.

---

## Dasar Teori
Inheritance adalah mekanisme dalam OOP yang memungkinkan suatu class mewarisi atribut dan method dari class lain.
   - Superclass: class induk yang mendefinisikan atribut umum. 
   - Subclass: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru. 
   - super digunakan untuk memanggil konstruktor atau method superclass. 

Dalam konteks Agri-POS, kita dapat membuat class Produk sebagai superclass, kemudian Benih, Pupuk, dan AlatPertanian sebagai subclass. Hal ini membuat kode lebih reusable dan terstruktur. ###
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
benih.java 
```java
public class Benih extends  Product{

    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }

    public  void deskripsi() {
        System.out.println("Nama Barang : " + getNama() + "\n    Varietas benih : " + getVarietas() + "\n      harga Benih/bungkus :" + getHarga() +"\n");
    }
}
```
Pupuk.java

```java
public class Pupuk extends  Product{

    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public  void deskripsi() {
        System.out.println("Nama Barang : " + getNama() + "\n       Jenis Pupuk : " + getJenis() + "\n      harga pupuk/20kg :" + getHarga() +"\n");
    }
}

```
Alat Pertanian. java 

```java
public class AlatPertanian extends Product{

    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public  void deskripsi(){
        System.out.println("Nama Barang : "+ getNama() + "\n    Mterial yang dipakai : " + getMaterial() + "\n      harga barang :" +  getHarga() +"\n");
    }
}

```
MainInheitance.java
```java
public class MainInheritance {
    public static void main(String[] args) {
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

        b.deskripsi();
        p.deskripsi();
        a.deskripsi();


        CreditBy.print("240202888", "Vigian Agus Isaneni");
    }
}
```
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/mm.png)
)
---

## Analisis
(
#### Penjelasan Cara Kerja Kode ####

Kode ini bekerja dengan menerapkan inheritance, di mana tiga subclass (Benih, Pupuk, dan AlatPertanian) mewarisi semua properti dasar dari superclass (Produk). Setiap subclass memanggil konstruktor superclass menggunakan super() untuk menginisialisasi atribut umum, kemudian menambahkan dan menampilkan atribut uniknya masing-masing seperti varietas, jenis, atau material.

#### Perbedaan Pendekatan Minggu Ini ####

Pendekatan minggu ini menggunakan pewarisan untuk menciptakan hierarki yang terstruktur, yang memungkinkan reuse kode dan spesialisasi produk. Berbeda dengan minggu sebelumnya yang menggunakan class tunggal atau class-class terpisah, pendekatan ini menghindari duplikasi atribut umum seperti nama dan harga di setiap jenis produk.

#### kendala ####
-
)
---

## Kesimpulan ##
implementasi Inheritance (Pewarisan) dalam OOP dengan membuat hierarki class untuk produk pertanian. Superclass Produk mendefinisikan atribut dasar yang umum (kode, nama, harga, stok), sementara Subclass (seperti Benih, Pupuk, dan AlatPertanian) mewarisi atribut tersebut dan menambahkan properti yang spesifik (varietas, jenis, material). Penggunaan keyword super() dalam konstruktor subclass memastikan inisialisasi yang efisien terhadap data superclass, yang pada akhirnya menghasilkan kode yang lebih reusable, terstruktur, dan mudah dipelihara dibandingkan dengan pendekatan class tunggal. Pendekatan ini adalah pondasi penting untuk sistem Agri-POS agar dapat mengelola berbagai jenis produk secara modular.

---

## Quiz
1. Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?  
   **Jawaban:** Keuntungan utamanya adalah Reusabilitas Kode (Code Reusability), karena atribut dan method umum hanya perlu didefinisikan sekali di superclass dan secara otomatis tersedia di semua subclass. Selain itu, inheritance menciptakan struktur hierarkis yang jelas (Is-A relationship), yang membuat kode lebih mudah dipelihara (pemeliharaan) dan lebih mudah untuk dikembangkan atau dispesialisasikan (misalnya, menambahkan atribut khusus).

2. Bagaimana cara subclass memanggil konstruktor superclass?  
   **Jawaban:** Subclass memanggil konstruktor superclass menggunakan keyword super() di dalam konstruktornya sendiri. Panggilan super(...) ini menjadi baris perintah pertama dalam konstruktor subclass dan argumen yang diteruskan harus sesuai dengan parameter konstruktor yang didefinisikan di superclass.

3. Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.  
   **Jawaban:**  Contoh kasus lain yang bisa dijadikan subclass dari Produk adalah Pestisida dan Obat Hewan. Pestisida dapat memiliki atribut tambahan seperti TargetHama atau BahanAktif, sedangkan Obat Hewan dapat memiliki atribut seperti JenisTernak atau Dosis.

