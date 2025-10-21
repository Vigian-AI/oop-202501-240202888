package main.java.com.upb.agripos.model;

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
