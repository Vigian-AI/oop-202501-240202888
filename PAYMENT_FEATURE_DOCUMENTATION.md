# Fitur Pembayaran dan Kembalian - AGRIPOS Kasir System

## 📋 Ringkasan Implementasi

Telah ditambahkan fitur pembayaran yang komprehensif di sistem AGRIPOS Kasir dengan dukungan:
- **Metode Pembayaran**: Tunai (CASH) dan E-Wallet
- **Perhitungan Kembalian**: Otomatis untuk pembayaran tunai
- **Informasi Kasir**: Nama kasir tercetak di struk
- **Dialog Pembayaran**: Interface yang user-friendly untuk input metode dan jumlah pembayaran

---

## 🏗️ Arsitektur & Komponen

### 1. **Payment Model** (`com.upb.agripos.model.payment.PaymentMethod`)
Enum yang mendefinisikan metode pembayaran yang didukung:
```java
PaymentMethod.CASH       // Pembayaran tunai dengan kembalian
PaymentMethod.E_WALLET   // Pembayaran e-wallet tanpa kembalian
```

### 2. **Transaction Model** (Update)
Model Transaction diperluas dengan field baru:
- `paidAmount` (double) - Jumlah uang yang dibayarkan pelanggan
- `change` (double) - Kembalian (untuk tunai)
- `cashierName` (String) - Nama kasir yang melayani

Konstruktor yang kompatibel:
```java
// Dengan detail pembayaran lengkap
new Transaction(id, cartId, userId, totalAmount, paymentMethod, status, 
                transactionDate, paidAmount, change, cashierName)

// Backward compatible (untuk kode lama)
new Transaction(id, cartId, userId, totalAmount, paymentMethod, status, 
                transactionDate)
```

### 3. **PaymentDialog View** (`com.upb.agripos.view.PaymentDialog`)
Dialog interaktif untuk:
- Menampilkan total belanja
- Memilih metode pembayaran (Tunai / E-Wallet)
- Input jumlah pembayaran
- Tampilan real-time kembalian (hanya untuk tunai)
- Validasi pembayaran

**Usage:**
```java
PaymentDialog dialog = new PaymentDialog(totalAmount);
if (dialog.isConfirmed()) {
    PaymentMethod method = dialog.getSelectedPaymentMethod();
    double paidAmount = dialog.getPaidAmount();
    double change = paidAmount - totalAmount;
}
```

### 4. **PosController Update**
Metode `checkout()` diperbarui untuk:
1. Tampilkan PaymentDialog
2. Ambil metode pembayaran dan jumlah pembayaran
3. Hitung kembalian
4. Simpan transaction dengan detail pembayaran
5. Tampilkan struk lengkap

Metode `generateReceipt(Transaction)` diperbarui untuk:
- Menampilkan nama kasir
- Menampilkan metode pembayaran
- Menampilkan jumlah dibayar
- Menampilkan kembalian (hanya untuk tunai)

---

## 🗄️ Database Schema Update

### Kolom Baru di Tabel `transactions`:
```sql
ALTER TABLE transactions ADD COLUMN paid_amount DECIMAL(10, 2) DEFAULT 0;
ALTER TABLE transactions ADD COLUMN change DECIMAL(10, 2) DEFAULT 0;
ALTER TABLE transactions ADD COLUMN cashier_name VARCHAR(100);
```

Atau jika membuat database baru, gunakan schema.sql yang sudah diperbarui.

### TransactionDAO Update
Semua query di TransactionDAO sudah diupdate untuk:
- **save()** - INSERT dengan kolom baru
- **findAll()** - SELECT kolom baru
- **findByUserId()** - SELECT kolom baru untuk user tertentu

---

## 📝 Contoh Alur Checkout

```
1. Kasir memilih produk dan menambah ke keranjang
   ↓
2. Kasir klik tombol "CHECKOUT"
   ↓
3. PaymentDialog muncul menampilkan:
   - Total belanja: Rp 100.000
   - Pilihan: Tunai / E-Wallet
   - Input jumlah pembayaran
   - Real-time perhitungan kembalian
   ↓
4. Kasir memilih metode dan input jumlah pembayaran
   ↓
5. Sistem memproses transaksi dan generate struk:
   - Item detail dengan harga
   - Nama kasir yang melayani
   - Metode pembayaran
   - Jumlah dibayar
   - Kembalian (jika tunai)
   ↓
6. Struk ditampilkan untuk dicetak
```

---

## 🎨 UI/UX Fitur PaymentDialog

**PaymentDialog Features:**
- Title: "💳 FORM PEMBAYARAN"
- Shows: Total belanja dengan format currency (Rp)
- Payment method selection: Radio buttons untuk Tunai/E-Wallet
- Amount input: TextField untuk jumlah yang dibayarkan
- Real-time change display: Hanya muncul untuk pembayaran tunai
- Validation: 
  - Jumlah pembayaran > 0
  - Untuk tunai: paid amount >= total
  - Format currency otomatis
- Buttons: "✅ Proses" (confirm) / "❌ Batal" (cancel)

---

## 📊 Struk Contoh Output

```
================================
	AGRI-POS RECEIPT
================================
Tanggal: 16-01-2026 14:30:45
Kasir: Budi Santoso
--------------------------------
Item                 Qty     Harga    Subtotal
--------------------------------
Benih Padi          2       50000       100000
Pupuk Urea          1       30000        30000
--------------------------------
Total:                            130000

Metode Pembayaran:               TUNAI
Jumlah Dibayar:                  200000
Kembalian:                        70000

	Terima Kasih Atas Belanja Anda
================================
```

---

## 🔧 Integrasi & Testing

### Untuk Mengintegrasikan:
1. ✅ Rebuild project dengan Maven
2. ✅ Jalankan database migration (schema.sql)
3. ✅ UI otomatis terintegrasi dengan PosController

### Testing Checklist:
- [ ] Pembayaran tunai dengan kembalian
- [ ] Pembayaran tunai dengan uang pas
- [ ] Pembayaran e-wallet
- [ ] Validasi pembayaran tidak cukup
- [ ] Cetak struk dengan informasi lengkap
- [ ] History transaksi menampilkan detail pembayaran

---

## 📂 File-file yang Dimodifikasi/Dibuat

**Dibuat:**
- `src/main/java/com/upb/agripos/model/payment/PaymentMethod.java` - Enum metode pembayaran
- `src/main/java/com/upb/agripos/view/PaymentDialog.java` - Dialog pembayaran

**Dimodifikasi:**
- `src/main/java/com/upb/agripos/model/Transaction.java` - Tambah field pembayaran
- `src/main/java/com/upb/agripos/controller/PosController.java` - Update checkout & receipt
- `src/main/java/com/upb/agripos/dao/TransactionDAO.java` - Handle kolom baru
- `sql/schema.sql` - Tambah kolom database

---

## 🚀 Future Enhancement

Possible enhancements untuk versi mendatang:
- [ ] Integrasi dengan payment gateway (e-wallet, transfer bank)
- [ ] Receipt printing dengan thermal printer
- [ ] Digital receipt (email/SMS)
- [ ] Discount/promo system
- [ ] Payment verification system
- [ ] Reporting dengan detail pembayaran

---

## 📞 Support & Debugging

**Jika ada error saat compile:**
1. Pastikan PaymentMethod.java ada di package `com.upb.agripos.model.payment`
2. Pastikan PaymentDialog.java ada di package `com.upb.agripos.view`
3. Rebuild project: `mvn clean compile`

**Jika database error:**
1. Pastikan kolom baru sudah ditambahkan ke tabel transactions
2. Jalankan ALTER TABLE jika table sudah ada
3. Atau drop dan recreate dengan schema.sql baru

---

Generated: January 16, 2026
Version: AGRIPOS 1.0 - Payment System Update
