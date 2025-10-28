package main.java.com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    
    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }


    @Override
    public void tampilkanData() {
        System.out.println("\n--- Detail Produk Benih ---");
        super.tampilkanData(); // Memanggil method tampilkanData() dari class Produk
        System.out.println("  Varietas Benih : " + varietas);
    }
    
    

    @Override
    public void getInfo() {
        System.out.println("--- Detail BENIH ---");
        System.out.println(" Kode: " + getKode());
        System.out.println(" Nama: " + getNama());
        System.out.println(" Varietas: " + varietas);
        // Menggunakan getHarga() dan getStok() dari Produk untuk keamanan
        System.out.println(" Harga (Rp): " + getHarga()); 
        System.out.println(" Stok: " + getStok() + " bungkus.");
    }

    public char[] deskripsi() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deskripsi'");
    }

}
