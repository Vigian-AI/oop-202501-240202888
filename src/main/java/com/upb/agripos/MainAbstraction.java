package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.*;
import main.java.com.upb.agripos.model.kontrak.Reciptable;
import main.java.com.upb.agripos.model.pembayaran.Cash;
import main.java.com.upb.agripos.model.pembayaran.EWalet;
import main.java.com.upb.agripos.model.pembayaran.Pembayaran;
import main.java.com.upb.agripos.model.pembayaran.TransferBank;
import main.java.com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
    Pembayaran cash = new Cash("INV-001", 100000, 120000);
    Pembayaran ew = new EWalet("INV-002", 150000, "user@ewallet", "123456");
    Pembayaran tb = new TransferBank("INV-003", 500000,"2910483989383828",true);

    System.out.println(((Reciptable) cash).cetakStruk());
    System.out.println(((Reciptable) ew).cetakStruk());
        System.out.println(((Reciptable) tb).cetakStruk());

    CreditBy.print("240202888", "Vigian Agus Isnaeni");
    }
}
