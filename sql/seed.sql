-- =====================================================
-- SEED DATA UNTUK AGRIPOS
-- =====================================================

-- =====================================================
-- INSERT USERS (3 User: Gudang, Admin, Kasir)
-- =====================================================
INSERT INTO users (username, password, full_name, role) VALUES
('gudang', 'gudang123', 'Budi Santoso', 'GUDANG'),
('admin', 'admin123', 'Admin System', 'ADMIN'),
('kasir', 'kasir123', 'Siti Nurhaliza', 'KASIR');

-- =====================================================
-- INSERT SAMPLE PRODUCTS (Produk Pertanian)
-- =====================================================
INSERT INTO products (code, name, price, stock) VALUES
('P001', 'Beras Premium 10kg', 150000, 50),
('P002', 'Pupuk Organik 5kg', 45000, 100),
('P003', 'Benih Jagung (1kg)', 35000, 75),
('P004', 'Pestisida Alami 1L', 55000, 40),
('P005', 'Bibit Tomat', 5000, 200),
('P006', 'Polybag Hitam (1000pcs)', 85000, 30),
('P007', 'Pupuk NPK 15-15-15 (25kg)', 250000, 20),
('P008', 'Obat Pembasmi Hama 500ml', 65000, 35),
('P009', 'Benang Tali Pertanian (100m)', 28000, 60),
('P010', 'Kapur Pertanian 20kg', 95000, 25);

-- =====================================================
-- INSERT SAMPLE CARTS (Keranjang Belanja)
-- =====================================================
INSERT INTO carts (user_id, total_price, status) VALUES
(3, 180000, 'COMPLETED'),  -- Cart 1: Kasir - Transaksi 1
(3, 95000, 'COMPLETED'),   -- Cart 2: Kasir - Transaksi 2
(3, 335000, 'COMPLETED'),  -- Cart 3: Kasir - Transaksi 3
(3, 155000, 'COMPLETED'),  -- Cart 4: Kasir - Transaksi 4
(3, 100000, 'COMPLETED'),  -- Cart 5: Kasir - Transaksi 5
(3, 250000, 'COMPLETED'),  -- Cart 6: Kasir - Transaksi 6
(3, 290000, 'COMPLETED'),  -- Cart 7: Kasir - Transaksi 7
(3, 175000, 'COMPLETED'),  -- Cart 8: Kasir - Transaksi 8
(3, 220000, 'COMPLETED'),  -- Cart 9: Kasir - Transaksi 9
(3, 410000, 'COMPLETED'),  -- Cart 10: Kasir - Transaksi 10
(3, 145000, 'COMPLETED'),  -- Cart 11: Kasir - Transaksi 11
(3, 205000, 'COMPLETED'),  -- Cart 12: Kasir - Transaksi 12
(3, 300000, 'COMPLETED'),  -- Cart 13: Kasir - Transaksi 13
(3, 120000, 'COMPLETED'),  -- Cart 14: Kasir - Transaksi 14
(3, 185000, 'COMPLETED');  -- Cart 15: Kasir - Transaksi 15

-- =====================================================
-- INSERT SAMPLE CART ITEMS (Detail Produk dalam Keranjang)
-- =====================================================
-- Cart 1: Beras + Pupuk
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(1, 'P001', 1, 150000),
(1, 'P002', 1, 45000);

-- Cart 2: Bibit Tomat + Benang
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(2, 'P005', 19, 95000),
(2, 'P009', 1, 28000);

-- Cart 3: Pupuk NPK + Benih Jagung
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(3, 'P007', 1, 250000),
(3, 'P003', 2, 70000);

-- Cart 4: Beras + Bibit Tomat
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(4, 'P001', 1, 150000),
(4, 'P005', 1, 5000);

-- Cart 5: Polybag + Kapur
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(5, 'P006', 1, 85000),
(5, 'P010', 0, 15000);

-- Cart 6: Pestisida + Pupuk Organik
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(6, 'P004', 4, 220000),
(6, 'P002', 1, 45000);

-- Cart 7: Benih Jagung + Obat Hama
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(7, 'P003', 8, 280000),
(7, 'P008', 1, 65000);

-- Cart 8: Beras + Benang
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(8, 'P001', 1, 150000),
(8, 'P009', 1, 28000);

-- Cart 9: Pupuk Organik + Kapur
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(9, 'P002', 4, 180000),
(9, 'P010', 1, 95000);

-- Cart 10: Pupuk NPK + Polybag
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(10, 'P007', 1, 250000),
(10, 'P006', 1, 85000);

-- Cart 11: Bibit Tomat + Benang
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(11, 'P005', 29, 145000),
(11, 'P009', 1, 28000);

-- Cart 12: Benih Jagung + Pestisida
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(12, 'P003', 2, 70000),
(12, 'P004', 4, 220000);

-- Cart 13: Beras + Pupuk NPK
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(13, 'P001', 2, 300000),
(13, 'P007', 1, 250000);

-- Cart 14: Polybag + Obat Hama
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(14, 'P006', 1, 85000),
(14, 'P008', 1, 65000);

-- Cart 15: Pupuk Organik + Kapur
INSERT INTO cart_items (cart_id, product_code, quantity, subtotal) VALUES
(15, 'P002', 2, 90000),
(15, 'P010', 1, 95000);

-- =====================================================
-- INSERT SAMPLE TRANSACTIONS (Transaksi Penjualan)
-- Tanggal: Berbeda-beda untuk simulasi laporan harian
-- =====================================================
INSERT INTO transactions (cart_id, user_id, total_amount, payment_method, status, transaction_date, paid_amount, change, cashier_name) VALUES
-- 15 Januari 2026 (3 transaksi)
(1, 3, 195000, 'CASH', 'SUCCESS', '2026-01-15 08:30:00', 200000, 5000, 'Siti Nurhaliza'),
(2, 3, 123000, 'CASH', 'SUCCESS', '2026-01-15 10:45:00', 125000, 2000, 'Siti Nurhaliza'),
(3, 3, 335000, 'DEBIT_CARD', 'SUCCESS', '2026-01-15 14:20:00', 335000, 0, 'Siti Nurhaliza'),

-- 16 Januari 2026 (3 transaksi)
(4, 3, 155000, 'CASH', 'SUCCESS', '2026-01-16 09:15:00', 160000, 5000, 'Siti Nurhaliza'),
(5, 3, 100000, 'DEBIT_CARD', 'SUCCESS', '2026-01-16 11:30:00', 100000, 0, 'Siti Nurhaliza'),
(6, 3, 265000, 'CASH', 'SUCCESS', '2026-01-16 15:45:00', 270000, 5000, 'Siti Nurhaliza'),

-- 17 Januari 2026 (3 transaksi)
(7, 3, 345000, 'CASH', 'SUCCESS', '2026-01-17 08:00:00', 350000, 5000, 'Siti Nurhaliza'),
(8, 3, 178000, 'DEBIT_CARD', 'SUCCESS', '2026-01-17 12:30:00', 178000, 0, 'Siti Nurhaliza'),
(9, 3, 275000, 'CASH', 'SUCCESS', '2026-01-17 16:15:00', 280000, 5000, 'Siti Nurhaliza'),

-- 18 Januari 2026 (4 transaksi)
(10, 3, 335000, 'DEBIT_CARD', 'SUCCESS', '2026-01-18 07:45:00', 335000, 0, 'Siti Nurhaliza'),
(11, 3, 173000, 'CASH', 'SUCCESS', '2026-01-18 10:20:00', 175000, 2000, 'Siti Nurhaliza'),
(12, 3, 290000, 'CASH', 'SUCCESS', '2026-01-18 13:50:00', 295000, 5000, 'Siti Nurhaliza'),
(13, 3, 550000, 'DEBIT_CARD', 'SUCCESS', '2026-01-18 16:30:00', 550000, 0, 'Siti Nurhaliza');

-- =====================================================
-- VERIFY DATA
-- =====================================================
SELECT 'Users:' AS Type;
SELECT * FROM users;

SELECT 'Products:' AS Type;
SELECT * FROM products ORDER BY code;

SELECT 'Carts:' AS Type;
SELECT id, user_id, total_price, status, created_at FROM carts;

SELECT 'Transactions:' AS Type;
SELECT id, cart_id, user_id, total_amount, payment_method, status, transaction_date, cashier_name FROM transactions ORDER BY transaction_date;

SELECT 'Transaction Summary by Date:' AS Type;
SELECT DATE(transaction_date) as transaction_day, COUNT(*) as total_transactions, SUM(total_amount) as daily_sales
FROM transactions GROUP BY DATE(transaction_date) ORDER BY transaction_day;
