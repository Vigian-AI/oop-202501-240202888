package main.java.com.upb.agripos.model.pembayaran;

import main.java.com.upb.agripos.model.kontrak.Reciptable;
import main.java.com.upb.agripos.model.kontrak.Validatable;

public class TransferBank extends Pembayaran implements Reciptable, Validatable {
    String noRek;
    boolean pin;
    
    public TransferBank(String invoiceNo, double total, String noRek, boolean pin) {
        super(invoiceNo, total);
        this.noRek = noRek;
        this.pin = pin;
    }

    @Override
    public String cetakStruk() {
        return String.format("INVOICE %s | TOTAL: %s  | Transfer Bank: %s | STATUS: %s",
                invoiceNo, totalBayar(), noRek, prosesPembayaran() ? "BERHASIL" : "GAGAL");
    }

    @Override
    public boolean validasi() {
        return total >= 3500 && pin == true && noRek.length()==16 ;
    }

    @Override
    public double biaya() {
        return 0;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }
}
