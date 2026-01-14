# 🌾 AGRIPOS - Sistem Informasi Penjualan Pertanian

## Setup Database

Sebelum menjalankan aplikasi, pastikan database PostgreSQL sudah terinstal dan berjalan.

### 1. Login ke PostgreSQL

```bash
psql -U postgres
```

### 2. Jalankan Script Schema

```sql
-- Copy dan paste seluruh konten file: sql/schema.sql
```

Atau jalankan dari command line:

```bash
psql -U postgres -f sql/schema.sql
```

### 3. Jalankan Script Seed Data

```bash
psql -U postgres -d agripos -f sql/seed.sql
```

### 4. Verifikasi Data

```bash
psql -U postgres -d agripos
SELECT * FROM users;
SELECT * FROM products;
```

## Login Credentials

Setelah setup database, gunakan kredensial berikut untuk login:

| Username | Password | Role | Nama Lengkap |
|----------|----------|------|--------------|
| gudang | gudang123 | GUDANG | Budi Santoso |
| admin | admin123 | ADMIN | Admin System |
| kasir | kasir123 | KASIR | Siti Nurhaliza |

## Database Structure

### Users Table
- id: Unique identifier
- username: Username untuk login (unique)
- password: Password (plaintext - gunakan hashing untuk production)
- full_name: Nama lengkap user
- role: Role user (GUDANG, ADMIN, KASIR)
- created_at: Timestamp created

### Products Table
- code: Kode produk (primary key)
- name: Nama produk
- price: Harga produk
- stock: Stok produk
- created_at: Timestamp created

### Carts Table
- id: Unique identifier
- user_id: Reference ke users table
- total_price: Total harga di keranjang
- status: Status keranjang (ACTIVE, COMPLETED, etc)
- created_at: Timestamp created

### Cart Items Table
- id: Unique identifier
- cart_id: Reference ke carts table
- product_code: Reference ke products table
- quantity: Jumlah item
- subtotal: Subtotal item (quantity × price)

### Transactions Table
- id: Unique identifier
- cart_id: Reference ke carts table
- user_id: Reference ke users table
- total_amount: Total jumlah transaksi
- payment_method: Metode pembayaran
- status: Status transaksi (SUCCESS, FAILED, etc)
- transaction_date: Tanggal transaksi

## Sample Data

Database sudah dilengkapi dengan 10 produk pertanian sample:
1. Beras Premium 10kg
2. Pupuk Organik 5kg
3. Benih Jagung (1kg)
4. Pestisida Alami 1L
5. Bibit Tomat
6. Polybag Hitam (1000pcs)
7. Pupuk NPK 15-15-15 (25kg)
8. Obat Pembasmi Hama 500ml
9. Benang Tali Pertanian (100m)
10. Kapur Pertanian 20kg

## Menjalankan Aplikasi

```bash
mvn clean compile
mvn javafx:run
```

Atau:

```bash
mvn exec:java -Dexec.mainClass="com.upb.agripos.AppJavaFX"
```

## Fitur Aplikasi

✅ **Login System** - Multi-user dengan role-based access
✅ **Product Management** - Tambah, lihat, dan hapus produk
✅ **Shopping Cart** - Tambah barang ke keranjang
✅ **Checkout** - Proses pembayaran
✅ **User Management** - Logout dan session management

---

Dikembangkan oleh: Fendy Agustian (240202898)
