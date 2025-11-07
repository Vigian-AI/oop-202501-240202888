package main.java.com.upb.agripos;

import main.java.com.upb.agripos.util.CreditBy;

import main.java.com.upb.agripos.model.kontrak.Receiptable;
import main.java.com.upb.agripos.model.pembayaran.Cash;
import main.java.com.upb.agripos.model.pembayaran.EWallet;
import main.java.com.upb.agripos.model.pembayaran.Pembayaran;
import main.java.com.upb.agripos.model.pembayaran.TransferBank;

public class MainAbstraction {
    public static void main(String[] args) {



        System.out.println("=== WEEK 5: ABSTRACTION INTERFACE ===");
        System.out.println("\n----------------------------------------------------------------------------------------------\n");
        
        // --- 1. CASH (Tunai Cukup) ---
        Pembayaran cash = new Cash("INV-C01", 100000, 120000);
        System.out.println("=== PEMBAYARAN CASH ===");
        cash.prosesPembayaran(); // Memproses
        System.out.println(((Receiptable) cash).cetakStruk()); // Mencetak Struk
        System.out.println("-----------------------------------------------------------------------------------------------");
        // --- 2. E-WALLET (Validasi Berhasil) ---
        Pembayaran ew = new EWallet("INV-E01", 150000, "fendy@ewallet", "100801");
        System.out.println("=== PEMBAYARAN E-WALLET ===");
        ew.prosesPembayaran(); // Memanggil Validasi
        System.out.println(((Receiptable) ew).cetakStruk()); // Mencetak Struk
        System.out.println("-----------------------------------------------------------------------------------------------");
        // --- 3. TRANSFER BANK (Validasi Berhasil) ---
        // Biaya: Rp3.500,00
        Pembayaran transfer = new TransferBank("INV-T01", 75000, "999"); 
        System.out.println("=== PEMBAYARAN TRANSFER BANK ===");
        transfer.prosesPembayaran(); // Memanggil Validasi
        System.out.println(((Receiptable) transfer).cetakStruk());
        System.out.println("-----------------------------------------------------------------------------------------------");
        // --- 4. TRANSFER BANK (Validasi GAGAL) ---
        Pembayaran transferGagal = new TransferBank("INV-T02", 5000, "BRI-9999"); 
        System.out.println("=== TRANSFER BANK (GAGAL) ===");
        transferGagal.prosesPembayaran(); 
        System.out.println(((Receiptable) transferGagal).cetakStruk());

        
        // --- 3 - Panggilan CreditBy
        CreditBy.print("240202898", "Fendy Agustian");
    }
}