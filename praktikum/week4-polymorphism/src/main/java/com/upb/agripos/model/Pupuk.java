package main.java.com.upb.agripos.model;

public class Pupuk extends Produk {
    
    private String jenis; 

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }


    @Override
    public void tampilkanData() {
        System.out.println("\n--- Detail Produk Pupuk ---");
        super.tampilkanData(); // Memanggil tampilkanData() dari class Produk
        System.out.println("  Jenis Pupuk    : " + jenis); // Menampilkan atribut khusus
    }



    @Override
    public void getInfo() {
        System.out.println("--- Detail PUPUK ---");
        System.out.println(" Kode: " + getKode());
        System.out.println(" Nama: " + getNama());
        System.out.println(" Jenis Pupuk: " + jenis);
        System.out.println(" Harga (Rp): " + getHarga()); 
        System.out.println(" Stok: " + getStok() + " karung.");
    }

    public char[] deskripsi() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deskripsi'");
    }
}