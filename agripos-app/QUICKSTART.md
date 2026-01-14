# 🚀 AGRIPOS Quick Start Guide

**5-minute setup untuk jalankan aplikasi AGRIPOS**

---

## 📋 Prerequisites Check

```bash
java -version          # Should show Java 11 or higher
mvn -version           # Should show Maven 3.6 or higher
psql --version         # Should show PostgreSQL 12 or higher
```

---

## 1️⃣ Setup Database (2 minutes)

### Open Command Prompt / Terminal in `agripos-app` folder

```bash
cd agripos-app
```

### Create Database

```bash
psql -U postgres -f sql/create_database.sql
```

### Create Tables

```bash
psql -U postgres -d agripos -f sql/schema.sql
```

### Insert Sample Data

```bash
psql -U postgres -d agripos -f sql/seed.sql
```

✅ Database ready!

---

## 2️⃣ Compile Application (1 minute)

```bash
mvn clean compile
```

Expected output: `BUILD SUCCESS`

---

## 3️⃣ Run Application (1 minute)

```bash
mvn javafx:run
```

Expected: **Login window appears** ✅

---

## 4️⃣ Login & Test

### Default Credentials (choose one):

| Username | Password | 
|----------|----------|
| **gudang** | gudang123 |
| **admin** | admin123 |
| **kasir** | kasir123 |

### After Login:

1. Click **"Refresh Data"** button to load products
2. Select a product from table
3. Enter quantity (1-10)
4. Click **"➕ Ke Keranjang"** to add to cart
5. Click **"✅ CHECKOUT"** to complete
6. Click **"🚪 LOGOUT"** to return to login

✅ Application working!

---

## 🔧 If Something Goes Wrong

### Database Error?
```bash
psql -U postgres -c "DROP DATABASE IF EXISTS agripos;"
psql -U postgres -f sql/create_database.sql
psql -U postgres -d agripos -f sql/schema.sql
psql -U postgres -d agripos -f sql/seed.sql
```

### Can't connect to database?
Edit `src/main/resources/database.properties`:
```properties
db.url=jdbc:postgresql://localhost:5432/agripos
db.username=postgres
db.password=1234
```

Change password if different.

### PostgreSQL not running?
```bash
# Windows
net start postgresql-x64-14

# Linux
sudo service postgresql start

# Mac
brew services start postgresql
```

---

## 📁 Project Structure

```
agripos-app/
├── src/main/java/com/upb/agripos/
│   ├── AppJavaFX.java (Main entry point)
│   ├── controller/, dao/, model/, service/, view/
├── src/main/resources/
│   └── database.properties
├── sql/
│   ├── create_database.sql
│   ├── schema.sql
│   └── seed.sql
├── pom.xml
├── README.md (Full documentation)
└── DATABASE_SETUP.md (Detailed setup guide)
```

---

## 🎯 Features to Try

✅ **Login** with 3 different users  
✅ **Add products** to cart  
✅ **Remove products** from table  
✅ **Checkout** and view total  
✅ **Logout** to switch users  

---

## 📞 Still Having Issues?

1. Read `README.md` for detailed documentation
2. Read `DATABASE_SETUP.md` for database troubleshooting
3. Check console output for error messages
4. Verify PostgreSQL is running: `psql -U postgres`

---

## 🎉 Success!

If you can:
- Login with any credentials ✅
- See products in table ✅
- Add to cart and checkout ✅
- Logout successfully ✅

**Then AGRIPOS is fully working!** 🌾

---

**Created:** January 14, 2026  
**Version:** 1.0
