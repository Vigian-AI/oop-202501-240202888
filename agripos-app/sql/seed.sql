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
