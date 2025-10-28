package main.java.com.upb.agripos;

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