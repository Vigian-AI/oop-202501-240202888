# 🌾 AGRIPOS - Sistem Informasi Penjualan Pertanian

**Full-featured Point of Sales application with JavaFX GUI, PostgreSQL Database, and Multi-user Authentication**

**Developed by:** Fendy Agustian (240202898)  
**Version:** 1.0-RELEASE  
**Date:** January 14, 2026

---

## 📋 Overview

AGRIPOS adalah aplikasi desktop modern untuk mengelola penjualan produk pertanian. Dilengkapi dengan:
- ✅ Multi-user authentication system (3 roles: Gudang, Admin, Kasir)
- ✅ Product management (CRUD)
- ✅ Shopping cart dengan automatic calculation
- ✅ Beautiful JavaFX GUI
- ✅ PostgreSQL database backend
- ✅ Session management & logout functionality

---

## 🎯 Prerequisites

Sebelum menjalankan aplikasi, pastikan sudah terinstall:

1. **Java Development Kit (JDK) 11+**
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **PostgreSQL 12+**
   ```bash
   psql --version
   ```

---

## 🚀 Installation & Setup

### Step 1: Start PostgreSQL Service

**Windows:**
```bash
net start postgresql-x64-14
```

**Linux/Mac:**
```bash
sudo service postgresql start
```

### Step 2: Create Database

From project root directory:
```bash
psql -U postgres -f sql/create_database.sql
```

### Step 3: Create Tables (Schema)

```bash
psql -U postgres -d agripos -f sql/schema.sql
```

### Step 4: Insert Sample Data

```bash
psql -U postgres -d agripos -f sql/seed.sql
```

### Step 5: Verify Data (Optional)

```bash
psql -U postgres -d agripos -c "SELECT COUNT(*) FROM users; SELECT COUNT(*) FROM products;"
```

Expected output:
```
 count
-------
     3
(1 row)

 count
-------
    10
(1 row)
```

---

## ▶️ Running the Application

### Option 1: Using Maven (Recommended)

```bash
cd agripos-app
mvn clean compile
mvn javafx:run
```

### Option 2: Build & Run JAR

```bash
mvn clean package
java -jar target/agripos-app.jar
```

---

## 🔐 Login Credentials

| Username | Password | Role | Name |
|----------|----------|------|------|
| **gudang** | gudang123 | GUDANG | Budi Santoso |
| **admin** | admin123 | ADMIN | Admin System |
| **kasir** | kasir123 | KASIR | Siti Nurhaliza |

---

## 🎨 Features & Usage

### 1. Login Screen
- Enter username and password
- Demo credentials displayed on login form
- Error handling untuk invalid credentials

### 2. Product Management
- **Input Form**: Add new products (Kode, Nama, Harga, Stok)
- **Product Table**: View all products from database
- **Refresh Button**: Reload data from database
- **Delete Button**: Remove selected product

### 3. Shopping Cart
- Select product from table
- Enter quantity using spinner
- Click "➕ Ke Keranjang" to add
- System validates stock availability

### 4. Checkout
- View total cart amount
- Click "✅ CHECKOUT" to process payment
- Cart automatically clears after checkout

### 5. User Management
- Header displays current logged-in user
- Role visible alongside name
- **Logout Button**: Click to return to login screen

---

## 📁 Project Structure

```
agripos-app/
├── src/
│   ├── main/
│   │   ├── java/com/upb/agripos/
│   │   │   ├── AppJavaFX.java              (Main application)
│   │   │   ├── config/
│   │   │   │   └── DatabaseConfig.java     (Config loader)
│   │   │   ├── controller/
│   │   │   │   └── PosController.java      (Business logic orchestrator)
│   │   │   ├── dao/
│   │   │   │   ├── ProductDAO.java         (Interface)
│   │   │   │   ├── JdbcProductDAO.java     (Implementation)
│   │   │   │   └── UserDAO.java            (User authentication)
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   ├── Product.java
│   │   │   │   ├── Cart.java
│   │   │   │   └── CartItem.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java        (Login/logout logic)
│   │   │   │   ├── ProductService.java     (Product management)
│   │   │   │   └── CartService.java        (Cart operations)
│   │   │   └── view/
│   │   │       ├── LoginView.java          (Login UI)
│   │   │       └── PosView.java            (Main POS UI)
│   │   └── resources/
│   │       └── database.properties          (DB config)
│   └── test/                                (Unit tests)
├── sql/
│   ├── create_database.sql                  (Database creation)
│   ├── schema.sql                           (Table structure)
│   └── seed.sql                             (Sample data)
├── pom.xml                                  (Maven configuration)
└── README.md                                (This file)
```

---

## 🏗️ Architecture

### Design Patterns Implemented

1. **MVC (Model-View-Controller)**
   - Clean separation of concerns
   - Model: User, Product, Cart, CartItem
   - View: LoginView, PosView
   - Controller: PosController

2. **DAO (Data Access Object)**
   - Abstraction layer untuk database access
   - Interface: ProductDAO
   - Implementation: JdbcProductDAO, UserDAO

3. **Singleton Pattern**
   - DatabaseConfig, UserDAO, JdbcProductDAO
   - Ensures single instance per application

4. **Service Layer**
   - Business logic isolation
   - UserService, ProductService, CartService

5. **Factory & Configuration Management**
   - DatabaseConfig centralized configuration
   - Flexible database connection string

---

## ⚙️ Configuration

### Database Connection (src/main/resources/database.properties)

```properties
db.url=jdbc:postgresql://localhost:5432/agripos
db.username=postgres
db.password=1234
```

**Change these values** if using different PostgreSQL credentials.

---

## 🧪 Testing

Run unit tests:
```bash
mvn test
```

---

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| **psql: command not found** | PostgreSQL not installed or not in PATH |
| **database "agripos" does not exist** | Run `sql/create_database.sql` first |
| **connection refused** | PostgreSQL service not running - use `net start postgresql-x64-14` |
| **authentication failed** | Wrong password - update `database.properties` |
| **ClassNotFoundException: org.postgresql.Driver** | PostgreSQL JDBC driver not loaded - check pom.xml dependencies |

---

## 📊 Database Schema

### Users Table
```sql
id (SERIAL PRIMARY KEY)
username (VARCHAR UNIQUE)
password (VARCHAR)
full_name (VARCHAR)
role (VARCHAR): GUDANG, ADMIN, KASIR
created_at (TIMESTAMP)
```

### Products Table
```sql
code (VARCHAR PRIMARY KEY)
name (VARCHAR)
price (DECIMAL)
stock (INTEGER)
created_at (TIMESTAMP)
```

### Carts & Transactions
- **carts**: Store user shopping carts
- **cart_items**: Items in each cart
- **transactions**: Purchase history

---

## 📈 Sample Data Included

**Products** (10 agricultural items pre-loaded):
- Beras Premium 10kg - Rp 150,000
- Pupuk Organik 5kg - Rp 45,000
- Benih Jagung 1kg - Rp 35,000
- Pestisida Alami 1L - Rp 55,000
- Bibit Tomat - Rp 5,000
- Polybag Hitam 1000pcs - Rp 85,000
- Pupuk NPK 15-15-15 25kg - Rp 250,000
- Obat Pembasmi Hama 500ml - Rp 65,000
- Benang Tali Pertanian 100m - Rp 28,000
- Kapur Pertanian 20kg - Rp 95,000

---

## 🤝 Contributing

This is an educational project. Feel free to fork, modify, and improve!

---

## 📄 License

This project is provided as-is for educational purposes.

---

## 📞 Support

For issues or questions:
1. Check DATABASE_VERIFICATION.md for detailed setup guide
2. Verify PostgreSQL is running
3. Check database.properties configuration
4. Review console output for error messages

---

## 🎓 Educational Value

This project demonstrates:
- ✅ Object-Oriented Programming (OOP) concepts
- ✅ Design Patterns (MVC, DAO, Singleton, Service Layer)
- ✅ JavaFX GUI development
- ✅ JDBC database connectivity
- ✅ SQL & Database design
- ✅ Authentication & Authorization
- ✅ Clean Code principles
- ✅ Professional project structure

---

**Created:** January 14, 2026  
**By:** Fendy Agustian (240202898)  
**Institution:** Universitas Pembangunan Pancasila (UPB)
