package main.java.com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String material;


    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }


    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    
    @Override
    public void tampilkanData() {
        System.out.println("\n--- Detail Produk Alat Pertanian ---");
        super.tampilkanData(); // Call the tampilkanData() method from the Produk class
        System.out.println("  Material       : " + material); // Display the specific attribute
    }

    
    
    @Override
    public void getInfo() {
        System.out.println("--- Detail ALAT PERTANIAN ---");
        System.out.println(" Kode: " + getKode());
        System.out.println(" Nama: " + getNama());
        System.out.println(" Material Utama: " + material);
        System.out.println(" Harga (Rp): " + getHarga());
        System.out.println(" Stok: " + getStok() + " unit.");
    }


    public char[] deskripsi() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deskripsi'");
    }
}
