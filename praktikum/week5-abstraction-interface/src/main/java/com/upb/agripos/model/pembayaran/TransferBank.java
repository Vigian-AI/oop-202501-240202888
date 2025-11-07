package main.java.com.upb.agripos.model.pembayaran;

import main.java.com.upb.agripos.model.kontrak.Receiptable;
import main.java.com.upb.agripos.model.kontrak.Validatable;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private String kodeBank;

    public TransferBank(String invoiceNo, double total, String kodeBank) {
        super(invoiceNo, total);
        this.kodeBank = kodeBank;
    }

    @Override
    public double biaya() {
        return 3500.0;
    }

    @Override
    public boolean validasi() {
        // ✅ Hanya lolos kalau 3 digit angka (misal "999")
        return kodeBank.matches("\\d{3}");
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE %s | TOTAL+FEE: %.2f | BANK: %s | STATUS: %s",
            invoiceNo, totalBayar(), kodeBank, prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
