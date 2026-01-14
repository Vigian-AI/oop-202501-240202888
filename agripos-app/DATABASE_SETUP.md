# 🗄️ Database Verification & Setup Guide

**Complete step-by-step guide untuk database setup, verification, dan troubleshooting**

---

## ✅ Database Setup Checklist

### Step 1: Verify PostgreSQL Installation

**Windows:**
```bash
psql --version
```

Expected output: `psql (PostgreSQL) 12.x, 13.x, 14.x, or higher`

**If not installed:**
- Download from https://www.postgresql.org/download/
- During installation, remember the postgres user password

---

### Step 2: Create Database

```bash
cd agripos-app
psql -U postgres -f sql/create_database.sql
```

**Expected output:**
```
CREATE DATABASE
```

**If error "database agripos already exists":**
```bash
psql -U postgres -c "DROP DATABASE IF EXISTS agripos;"
psql -U postgres -f sql/create_database.sql
```

---

### Step 3: Create Schema (Tables & Indexes)

```bash
psql -U postgres -d agripos -f sql/schema.sql
```

**Expected output:**
```
CREATE TABLE
CREATE TABLE
CREATE TABLE
CREATE TABLE
CREATE TABLE
CREATE INDEX
CREATE INDEX
```

---

### Step 4: Insert Sample Data

```bash
psql -U postgres -d agripos -f sql/seed.sql
```

**Expected output:**
```
INSERT 0 3
INSERT 0 10
```

---

## 🔍 Verification Commands

### Verify Database Created

```bash
psql -U postgres -c "\l"
```

Look for `agripos` in the list.

### Verify All Tables Created

```bash
psql -U postgres -d agripos -c "\dt"
```

Expected tables: `cart_items`, `carts`, `products`, `transactions`, `users`

### Verify Users Table Data

```bash
psql -U postgres -d agripos -c "SELECT id, username, full_name, role FROM users;"
```

Expected output:
```
 id | username |    full_name    |  role
----+----------+-----------------+--------
  1 | gudang   | Budi Santoso    | GUDANG
  2 | admin    | Admin System    | ADMIN
  3 | kasir    | Siti Nurhaliza  | KASIR
(3 rows)
```

### Verify Products Table Data

```bash
psql -U postgres -d agripos -c "SELECT code, name, price, stock FROM products LIMIT 5;"
```

Expected output:
```
      code      |          name           | price | stock
----------------+-------------------------+-------+-------
 PROD-001       | Beras Premium 10kg      | 150000 |   100
 PROD-002       | Pupuk Organik 5kg       |  45000 |   200
 PROD-003       | Benih Jagung 1kg        |  35000 |   150
 PROD-004       | Pestisida Alami 1L      |  55000 |   120
 PROD-005       | Bibit Tomat             |   5000 |   500
(5 rows)
```

### Count Total Products

```bash
psql -U postgres -d agripos -c "SELECT COUNT(*) as total_products FROM products;"
```

Expected: `10`

---

## 🔐 Verify Login Credentials

### Test User Authentication Manually

```bash
psql -U postgres -d agripos -c "SELECT username, password, role FROM users;"
```

Expected passwords (hashed or plain):
```
 username |  password  |  role
----------+------------+--------
 gudang   | gudang123  | GUDANG
 admin    | admin123   | ADMIN
 kasir    | kasir123   | KASIR
(3 rows)
```

---

## ⚙️ Database Configuration

### Check Current Configuration (database.properties)

File location: `src/main/resources/database.properties`

```properties
db.url=jdbc:postgresql://localhost:5432/agripos
db.username=postgres
db.password=1234
```

**Edit if:**
- PostgreSQL running on different port (not 5432)
- PostgreSQL username not "postgres"
- PostgreSQL password not "1234"

---

## 🚨 Troubleshooting

### Error 1: "psql: command not found"

**Solution:**
PostgreSQL not in PATH. Add to system PATH or use full path:

**Windows:**
```bash
"C:\Program Files\PostgreSQL\14\bin\psql" -U postgres -f sql/create_database.sql
```

### Error 2: "FATAL: password authentication failed for user 'postgres'"

**Solution:**
Password incorrect. Specify correct password:

```bash
psql -U postgres -W -f sql/create_database.sql
```
*(Will prompt for password)*

Or use environment variable:
```bash
set PGPASSWORD=your_password
psql -U postgres -f sql/create_database.sql
```

### Error 3: "database agripos already exists"

**Solution:**
Drop existing database and recreate:

```bash
psql -U postgres -c "DROP DATABASE IF EXISTS agripos;"
psql -U postgres -f sql/create_database.sql
```

### Error 4: "could not open file 'sql/create_database.sql' for reading"

**Solution:**
Not in correct directory. Run from `agripos-app` folder:

```bash
cd agripos-app
psql -U postgres -f sql/create_database.sql
```

### Error 5: "relation 'products' does not exist" (when running application)

**Solution:**
Schema not created. Run:

```bash
psql -U postgres -d agripos -f sql/schema.sql
```

### Error 6: "org.postgresql.util.PSQLException: Connection refused"

**Solution:**
PostgreSQL service not running.

**Windows:**
```bash
net start postgresql-x64-14
```

**Linux:**
```bash
sudo service postgresql start
```

**Mac:**
```bash
brew services start postgresql
```

---

## 🔄 Complete Fresh Setup (Clean Slate)

If something goes wrong, do complete reset:

```bash
# 1. Stop any running application
# 2. Drop database
psql -U postgres -c "DROP DATABASE IF EXISTS agripos;"

# 3. Create database
psql -U postgres -f sql/create_database.sql

# 4. Create schema
psql -U postgres -d agripos -f sql/schema.sql

# 5. Insert data
psql -U postgres -d agripos -f sql/seed.sql

# 6. Verify
psql -U postgres -d agripos -c "SELECT COUNT(*) FROM users; SELECT COUNT(*) FROM products;"
```

---

## 📊 Advanced Verification Queries

### Show All Indexes

```bash
psql -U postgres -d agripos -c "\di"
```

### Show Table Schemas in Detail

```bash
psql -U postgres -d agripos -c "\d products"
psql -U postgres -d agripos -c "\d users"
psql -U postgres -d agripos -c "\d carts"
```

### Check Constraints

```bash
psql -U postgres -d agripos -c "\d+ products"
```

### View All Data in Products Table

```bash
psql -U postgres -d agripos -c "SELECT * FROM products ORDER BY code;"
```

### View Transaction History (if exists)

```bash
psql -U postgres -d agripos -c "SELECT * FROM transactions LIMIT 10;"
```

---

## 💡 Tips & Best Practices

1. **Always verify** database exists before running application
2. **Run schema.sql** AFTER create_database.sql
3. **Run seed.sql** to populate sample data
4. **Test login** with provided credentials (gudang/gudang123)
5. **Keep database.properties** updated with correct credentials
6. **Use -W flag** if prompted for password:
   ```bash
   psql -U postgres -W -f sql/create_database.sql
   ```

---

## ✨ Success Checklist

After completing all steps, verify:

- [ ] `psql --version` shows PostgreSQL installed
- [ ] `psql -U postgres -l` shows `agripos` database
- [ ] `psql -U postgres -d agripos -c "\dt"` shows 5 tables
- [ ] `psql -U postgres -d agripos -c "SELECT COUNT(*) FROM users;"` returns 3
- [ ] `psql -U postgres -d agripos -c "SELECT COUNT(*) FROM products;"` returns 10
- [ ] `database.properties` has correct db credentials
- [ ] Application starts without connection errors
- [ ] Login screen appears
- [ ] Can login with gudang/gudang123

---

## 🎯 Next Steps

Once database is verified:

1. **Configure database.properties** if needed
2. **Run application**: `mvn javafx:run`
3. **Test login** with sample credentials
4. **Test CRUD operations** (add product, view, delete)
5. **Test shopping cart** functionality
6. **Test logout** returns to login screen

---

**Last Updated:** January 14, 2026  
**Database:** PostgreSQL 12+  
**JDBC Driver:** postgresql-42.5.4
