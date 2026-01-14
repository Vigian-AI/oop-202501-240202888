# 🔍 AGRIPOS - Database Setup Verification Checklist

## ✅ STEP 1: Verifikasi PostgreSQL Terinstall & Running

```bash
# Windows - Buka Command Prompt/PowerShell
psql --version

# Harus ada output seperti: psql (PostgreSQL) 14.x
```

**Jika belum terinstall, download dari:** https://www.postgresql.org/download/

---

## ✅ STEP 2: Buat Database

```bash
# Dari folder root project
cd d:\oop240202898

# Jalankan script create database
psql -U postgres -f sql/create_database.sql

# Output yang diharapkan:
# CREATE DATABASE
```

---

## ✅ STEP 3: Buat Tables (Schema)

```bash
# Jalankan schema script
psql -U postgres -d agripos -f sql/schema.sql

# Output yang diharapkan:
# CREATE TABLE
# CREATE TABLE
# ...
# CREATE INDEX
```

---

## ✅ STEP 4: Insert Sample Data

```bash
# Jalankan seed data
psql -U postgres -d agripos -f sql/seed.sql

# Output yang diharapkan:
# INSERT 0 3
# INSERT 0 10
# SELECT 3
# SELECT 10
```

---

## ✅ STEP 5: Verifikasi Data di Database

```bash
# Check users
psql -U postgres -d agripos -c "SELECT * FROM users;"

# Output yang diharapkan:
#  id | username |    password    |      full_name      | role  |         created_at
# ----+----------+----------------+---------------------+-------+----------------------------
#   1 | gudang   | gudang123      | Budi Santoso        | GUDANG | 2026-01-14...
#   2 | admin    | admin123       | Admin System        | ADMIN  | 2026-01-14...
#   3 | kasir    | kasir123       | Siti Nurhaliza      | KASIR  | 2026-01-14...

# Check products
psql -U postgres -d agripos -c "SELECT * FROM products;"

# Output yang diharapkan:
#  code |        name        | price | stock | created_at
# ------+--------------------+-------+-------+-------------
#  P001 | Beras Premium 10kg | 150000| 50   | 2026-01-14...
#  ...
```

---

## ✅ STEP 6: Update Database Configuration (Optional)

Edit file: `src/main/resources/database.properties`

```properties
# Default configuration (sesuaikan jika berbeda)
db.url=jdbc:postgresql://localhost:5432/agripos
db.username=postgres
db.password=1234
```

---

## ✅ STEP 7: Compile & Run Project

```bash
# Compile
mvn clean compile

# Run aplikasi
mvn javafx:run
```

---

## 🔐 Login Credentials

| Username | Password | Role |
|----------|----------|------|
| **gudang** | gudang123 | GUDANG |
| **admin** | admin123 | ADMIN |
| **kasir** | kasir123 | KASIR |

---

## ❌ Troubleshooting

### Error: "psql: command not found"
- PostgreSQL belum diinstall atau tidak di PATH
- **Solusi:** Reinstall PostgreSQL atau add ke system PATH

### Error: "database "agripos" does not exist"
- Database belum dibuat
- **Solusi:** Jalankan `sql/create_database.sql` terlebih dahulu

### Error: "role "postgres" does not exist"
- Username PostgreSQL berbeda (bukan "postgres")
- **Solusi:** Check username yang benar, update script SQL

### Error: "password authentication failed"
- Password salah
- **Solusi:** Reset password PostgreSQL atau update di database.properties

### Error: "Connection refused"
- PostgreSQL tidak running
- **Solusi:** Start PostgreSQL service
  ```bash
  # Windows
  net start postgresql-x64-14
  ```

### Error di Terminal: "Could not initialize class ..."
- Database belum disetup
- **Solusi:** Ikuti steps 1-6 di atas

---

## 📞 Support

Jika ada error yang belum terpecahkan:
1. Check PostgreSQL status: `psql -U postgres -l`
2. Cek log output aplikasi
3. Verifikasi database.properties settings
4. Pastikan PostgreSQL listening di port 5432

---

Created: 2026-01-14
Updated by: Fendy Agustian (240202898)
