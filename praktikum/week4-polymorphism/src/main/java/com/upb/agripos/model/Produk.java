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