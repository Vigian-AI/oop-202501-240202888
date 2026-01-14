# Laporan Praktikum Week 14 - Integrasi Individu
Topik: **Sistem Informasi Penjualan Pertanian (AGRIPOS) - Full Integration dengan JavaFX, Database, dan Authentication**

## Identitas
- Nama  : **Fendy Agustian**
- NIM   : **240202898**
- Kelas : **SI-3 A**

---

## Tujuan
Mahasiswa mampu mengintegrasikan seluruh konsep OOP, JavaFX GUI, Database (PostgreSQL), dan design patterns untuk membuat aplikasi **Point of Sales (POS) lengkap** dengan fitur:
1. Sistem login multi-user dengan role-based access (Gudang, Admin, Kasir)
2. Management produk pertanian (CRUD operations)
3. Shopping cart dengan perhitungan otomatis
4. Authentication dan session management
5. Integration dengan database PostgreSQL

---

## Dasar Teori

### 1. **MVC (Model-View-Controller) Pattern**
   - **Model**: Mewakili data (User, Product, Cart, CartItem)
   - **View**: Interface JavaFX (LoginView, PosView)
   - **Controller**: Business logic (PosController, UserService)

### 2. **DAO (Data Access Object) Pattern**
   - Abstraksi akses data dari database
   - Implementasi: UserDAO, JdbcProductDAO, ProductDAO interface
   - Memudahkan maintenance dan perubahan database

### 3. **Singleton Pattern**
   - Digunakan untuk JdbcProductDAO dan UserDAO
   - Memastikan hanya satu instance koneksi database yang digunakan

### 4. **Service Layer Pattern**
   - UserService, ProductService, CartService
   - Memisahkan business logic dari presentation layer

### 5. **Authentication & Authorization**
   - Login verification dengan username dan password
   - Role-based access control (GUDANG, ADMIN, KASIR)
   - Session management dengan UserService

---

## Langkah Praktikum

### Phase 1: Setup Database & Schema
1. Buat PostgreSQL database `agripos`
2. Jalankan `sql/create_database.sql` untuk membuat database
3. Jalankan `sql/schema.sql` untuk membuat tables:
   - `users` - Menyimpan data login
   - `products` - Menyimpan data produk
   - `carts`, `cart_items` - Untuk keranjang belanja
   - `transactions` - Untuk riwayat transaksi
4. Jalankan `sql/seed.sql` untuk insert sample data (3 users + 10 products)

### Phase 2: Backend Development
1. Buat Model classes:
   - `User.java` - Representasi user
   - `Product.java` - Representasi produk
   - `Cart.java`, `CartItem.java` - Untuk shopping cart

2. Buat DAO layer:
   - `UserDAO.java` - Handle user authentication
   - `ProductDAO.java` (interface)
   - `JdbcProductDAO.java` - Implementasi JDBC untuk produk
   - `DatabaseConfig.java` - Centralized database configuration

3. Buat Service layer:
   - `UserService.java` - Business logic untuk login/logout
   - `ProductService.java` - Business logic untuk product management
   - `CartService.java` - Business logic untuk cart operations

4. Buat Controller:
   - `PosController.java` - Orchestrate antara Service dan View

### Phase 3: Frontend Development (JavaFX)
1. `LoginView.java` - Beautiful login UI dengan demo credentials
2. `PosView.java` - Main application UI dengan:
   - Product input form
   - Product list table
   - Shopping cart functionality
   - User info dan logout button

### Phase 4: Integration & Testing
1. Config file: `database.properties`
2. Update `AppJavaFX.java` untuk:
   - Show login screen on startup
   - Transition to main app on successful login
   - Handle logout dan return ke login screen

---

## Kode Program

### 1. Model - User.java
```java
public class User {
    private int id;
    private final String username;
    private final String fullName;
    private final String role;

    public User(int id, String username, String fullName, String role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }
    
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
}
```

### 2. DAO - UserDAO.java
```java
public User authenticate(String username, String password) {
    String sql = "SELECT id, username, full_name, role FROM users WHERE username = ? AND password = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, username);
        ps.setString(2, password);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("full_name"),
                    rs.getString("role")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Error during authentication: " + e.getMessage());
    }
    return null;
}
```

### 3. Service - UserService.java
```java
public User login(String username, String password) {
    if (username == null || username.trim().isEmpty() ||
        password == null || password.trim().isEmpty()) {
        return null;
    }
    
    User user = userDAO.authenticate(username, password);
    if (user != null) {
        this.currentUser = user;
        System.out.println("✓ Login sukses: " + user.getFullName());
    }
    return user;
}

public void logout() {
    if (currentUser != null) {
        System.out.println("✓ Logout: " + currentUser.getFullName());
        currentUser = null;
    }
}
```

### 4. View - LoginView UI (Partial)
```java
TextField usernameField = new TextField();
usernameField.setPromptText("Masukkan username");

PasswordField passwordField = new PasswordField();
passwordField.setPromptText("Masukkan password");

Button loginButton = new Button("🔓 LOGIN");
loginButton.setOnAction(e -> {
    User user = userService.login(usernameField.getText().trim(), passwordField.getText());
    if (user != null) {
        if (onLoginSuccess != null) {
            onLoginSuccess.run();
        }
    }
});
```

### 5. Controller - PosController.java
```java
public class PosController {
    private final ProductService productService;
    private final CartService cartService;
    
    public void addProduct(Product p) {
        productService.addProduct(p);
    }
    
    public void deleteProduct(String code) {
        productService.deleteProduct(code);
    }
    
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    
    public void addToCart(Product p, int qty) {
        cartService.addToCart(p, qty);
    }
    
    public double getCartTotal() {
        return cartService.getTotal();
    }
}
```

---

## Hasil Eksekusi

berada di folder screenshoots
---

## Analisis

### 1. Arsitektur Aplikasi
- **Separation of Concerns**: Kode terbagi menjadi Model, View, Controller, Service, dan DAO layers
- **Dependency Injection**: Services dan Controllers menerima dependencies melalui constructor
- **Configuration Management**: Database config di-centralize di `database.properties`

### 2. Database Integration
- **JDBC Connection Pooling**: Singleton pattern untuk manage database connections
- **Prepared Statements**: Prevent SQL injection attacks
- **Foreign Keys**: Maintain referential integrity antar tables

### 3. User Authentication
- Multi-user system dengan 3 roles (Gudang, Admin, Kasir)
- Session management dengan currentUser di UserService
- Logout functionality dengan confirmation dialog

### 4. GUI Implementation (JavaFX)
- **TableView** untuk menampilkan daftar produk
- **TextField & PasswordField** untuk input
- **Spinner** untuk quantity selection
- **Alert Dialogs** untuk user feedback
- **Header dengan user info** dan logout button

### 5. Kendala & Solusi

| Kendala | Solusi |
|---------|--------|
| Database connection error (MySQL vs PostgreSQL) | Gunakan PostgreSQL JDBC driver, update connection string |
| Variable scope issue di PosView | Deklarasikan `tableProduct` sebelum digunakan di event handlers |
| CREATE DATABASE di transaction block | Pisahkan create database ke script terpisah |
| Table data tidak muncul saat startup | Tambah method `loadFromDatabase()` di ProductService |
| Logout langsung exit aplikasi | Implement logout callback ke showLoginScreen() |

---

## Kesimpulan

Praktikum week 14 berhasil mengintegrasikan:
1. ✅ **OOP Concepts**: Class hierarchy, encapsulation, abstraction
2. ✅ **Design Patterns**: MVC, DAO, Singleton, Service Layer
3. ✅ **JavaFX GUI**: Multi-screen application dengan login flow
4. ✅ **Database**: PostgreSQL dengan JDBC connection management
5. ✅ **Authentication**: Multi-user login system dengan role support
6. ✅ **Business Logic**: Product management dan shopping cart

Aplikasi AGRIPOS siap untuk digunakan sebagai sistem POS untuk toko pertanian dengan fitur-fitur lengkap dan professional. Arsitektur yang clean membuat aplikasi mudah di-maintain dan di-extend untuk fitur-fitur tambahan di masa depan.

---

## File Structure

```
praktikum/week14-integrasi-individu/
├── src/
│   ├── main/java/com/upb/agripos/
│   │   ├── AppJavaFX.java
│   │   ├── config/
│   │   │   └── DatabaseConfig.java
│   │   ├── controller/
│   │   │   └── PosController.java
│   │   ├── dao/
│   │   │   ├── ProductDAO.java (interface)
│   │   │   ├── JdbcProductDAO.java
│   │   │   └── UserDAO.java
│   │   ├── model/
│   │   │   ├── User.java
│   │   │   ├── Product.java
│   │   │   ├── Cart.java
│   │   │   └── CartItem.java
│   │   ├── service/
│   │   │   ├── UserService.java
│   │   │   ├── ProductService.java
│   │   │   └── CartService.java
│   │   └── view/
│   │       ├── LoginView.java
│   │       └── PosView.java
│   ├── main/resources/
│   │   └── database.properties
│   └── test/
│       └── CartServiceTest.java
├── pom.xml
└── laporan.md
```

---

Dibuat: **14 Januari 2026**  
Oleh: **Fendy Agustian (240202898)**  
Version: **1.0**
