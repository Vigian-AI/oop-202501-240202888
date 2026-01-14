CREATE TABLE IF NOT EXISTS products (
    code VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

-- =====================================================
-- SCHEMA DATABASE AGRIPOS (PostgreSQL)
-- Created: 2026-01-14
-- =====================================================
-- PERHATIAN: Database agripos harus sudah ada sebelumnya!
-- Jalankan command ini terlebih dahulu:
-- psql -U postgres -c "CREATE DATABASE agripos;"
-- 
-- Atau jalankan create_database.sql terlebih dahulu:
-- psql -U postgres -f sql/create_database.sql
--
-- Kemudian jalankan script ini pada database agripos:
-- psql -U postgres -d agripos -f sql/schema.sql
-- =====================================================

-- =====================================================
-- TABLE: USERS (Untuk Login)
-- =====================================================
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- TABLE: PRODUCTS (Produk)
-- =====================================================

-- =====================================================
-- TABLE: CARTS (Keranjang Belanja)
-- =====================================================
CREATE TABLE carts (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =====================================================
-- TABLE: CART_ITEMS (Detail Barang di Keranjang)
-- =====================================================
CREATE TABLE cart_items (
    id SERIAL PRIMARY KEY,
    cart_id INT NOT NULL,
    product_code VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (product_code) REFERENCES products(code)
);

-- =====================================================
-- TABLE: TRANSACTIONS (Transaksi Penjualan)
-- =====================================================
CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    cart_id INT NOT NULL,
    user_id INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50),
    status VARCHAR(50) DEFAULT 'SUCCESS',
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =====================================================
-- INDEX untuk performa
-- =====================================================
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_products_code ON products(code);
CREATE INDEX idx_transactions_user ON transactions(user_id);
CREATE INDEX idx_transactions_date ON transactions(transaction_date);
