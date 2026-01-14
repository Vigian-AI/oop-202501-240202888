# 🗺️ AGRIPOS Project Map & Navigation Guide

**Visual guide untuk navigasi project dan file organization**

---

## 📍 Project Location

```
D:\OOP240202898\
└── agripos-app/                    ← YOU ARE HERE
    ├── Documentation
    ├── Source Code
    ├── Database Scripts
    ├── Build Configuration
    └── Resources
```

---

## 🧭 Navigation Map

### 1. **Getting Started (First Time)**

```
START HERE
    ↓
📄 README.md
   └─ Full overview and features
      ↓
📄 QUICKSTART.md
   └─ 5-minute setup
      ↓
📄 DATABASE_SETUP.md
   └─ Database configuration
      ↓
▶️  mvn javafx:run
   └─ Run application
```

### 2. **Understanding the Architecture**

```
WANT TO UNDERSTAND SYSTEM?
    ↓
📄 ARCHITECTURE.md
   ├─ System architecture diagram
   ├─ Design patterns (5 types)
   ├─ Data flow diagrams
   ├─ Package structure
   └─ Class relationships
      ↓
📁 src/main/java/com/upb/agripos/
   └─ Explore actual code
```

### 3. **Setting Up Database**

```
NEED TO SETUP DATABASE?
    ↓
📄 DATABASE_SETUP.md
   ├─ Step-by-step setup
   ├─ Verification commands
   ├─ Troubleshooting
   └─ Success checklist
      ↓
📁 sql/
   ├─ create_database.sql
   ├─ schema.sql
   └─ seed.sql
```

### 4. **Checking Implementation Status**

```
WANT TO VERIFY COMPLETION?
    ↓
📄 IMPLEMENTATION_CHECKLIST.md
   ├─ Implementation status
   ├─ Feature checklist
   ├─ File manifest
   └─ Project statistics
```

---

## 📁 Directory Structure with Files

```
agripos-app/
│
├─ 📄 README.md                          ← Main documentation (START HERE)
│  ├─ Project overview
│  ├─ Prerequisites & installation
│  ├─ Features & usage
│  ├─ Architecture overview
│  └─ Troubleshooting
│
├─ 📄 QUICKSTART.md                      ← Fast 5-minute setup
│  ├─ Prerequisites check
│  ├─ Database setup (2 min)
│  ├─ Compilation (1 min)
│  ├─ Running application (1 min)
│  └─ Testing login
│
├─ 📄 DATABASE_SETUP.md                  ← Database detailed guide
│  ├─ Setup checklist
│  ├─ Verification commands
│  ├─ Configuration details
│  ├─ Troubleshooting
│  └─ Advanced queries
│
├─ 📄 ARCHITECTURE.md                    ← Technical design document
│  ├─ System architecture
│  ├─ Design patterns
│  ├─ Data flow
│  ├─ Package structure
│  └─ SOLID principles
│
├─ 📄 IMPLEMENTATION_CHECKLIST.md        ← Project status
│  ├─ Implementation status
│  ├─ Feature checklist
│  ├─ File manifest
│  └─ Project metrics
│
├─ 📄 pom.xml                            ← Maven configuration
│  ├─ Dependencies (JavaFX, PostgreSQL)
│  ├─ Plugins (Maven Compiler, Javafx)
│  └─ Project metadata
│
├─ 📁 sql/                               ← Database scripts
│  ├─ create_database.sql               (Create agripos database)
│  ├─ schema.sql                        (Create 5 tables)
│  └─ seed.sql                          (Insert sample data)
│
├─ 📁 src/main/resources/                ← Configuration & resources
│  └─ database.properties                (DB connection config)
│
└─ 📁 src/main/java/com/upb/agripos/    ← Java source code
   │
   ├─ 📄 AppJavaFX.java                  ← Application entry point
   │  ├─ main() method
   │ ├─ start(Stage) - Initialize UI
   │  ├─ showLoginScreen()
   │  ├─ showMainApp()
   │  └─ handleLogout()
   │
   ├─ 📁 config/
   │  └─ 📄 DatabaseConfig.java          ← Database configuration
   │     ├─ Load database.properties
   │     ├─ Provide connection details
   │     └─ Singleton pattern
   │
   ├─ 📁 controller/
   │  └─ 📄 PosController.java           ← Business logic orchestrator
   │     ├─ addProduct()
   │     ├─ deleteProduct()
   │     ├─ getProducts()
   │     ├─ addToCart()
   │     ├─ getCartTotal()
   │     └─ clearCart()
   │
   ├─ 📁 dao/                            ← Data Access Objects
   │  ├─ 📄 ProductDAO.java              (Interface)
   │  ├─ 📄 JdbcProductDAO.java          (JDBC implementation)
   │  │  ├─ insert(Product)
   │  │  ├─ delete(String code)
   │  │  └─ findAll()
   │  └─ 📄 UserDAO.java                 (User authentication)
   │     └─ authenticate(user, pass)
   │
   ├─ 📁 model/                          ← Data models (POJOs)
   │  ├─ 📄 User.java
   │  │  ├─ id, username, password
   │  │  ├─ fullName, role
   │  │  └─ Getters
   │  ├─ 📄 Product.java
   │  │  ├─ code, name, price, stock
   │  │  └─ Getters/Setters
   │  ├─ 📄 Cart.java
   │  │  ├─ List<CartItem> items
   │  │  ├─ addItem()
   │  │  ├─ getTotal()
   │  │  └─ clear()
   │  └─ 📄 CartItem.java
   │     ├─ product, quantity
   │     └─ getSubtotal()
   │
   ├─ 📁 service/                        ← Business logic layer
   │  ├─ 📄 UserService.java             (Authentication)
   │  │  ├─ login(user, pass)
   │  │  ├─ logout()
   │  │  ├─ getCurrentUser()
   │  │  └─ isLoggedIn()
   │  ├─ 📄 ProductService.java          (Product management)
   │  │  ├─ addProduct(Product)
   │  │  ├─ deleteProduct(String code)
   │  │  ├─ getAllProducts()
   │  │  └─ loadFromDatabase()
   │  └─ 📄 CartService.java             (Cart operations)
   │     ├─ addToCart()
   │     ├─ getTotal()
   │     ├─ getCartItems()
   │     └─ clearCart()
   │
   └─ 📁 view/                           ← UI (JavaFX)
      ├─ 📄 LoginView.java               (Login screen)
      │  ├─ Username TextField
      │  ├─ Password PasswordField
      │  ├─ Login Button
      │  ├─ Demo credentials
      │  └─ Validation & errors
      └─ 📄 PosView.java                 (Main application)
         ├─ Product input form
         ├─ Product TableView
         ├─ Shopping cart display
         ├─ Quantity spinner
         ├─ Checkout button
         ├─ User info header
         └─ Logout button
```

---

## 🚀 Quick Navigation by Task

### Task: I want to run the application
```
1. README.md → "Running the Application" section
2. or QUICKSTART.md → Follow 5-minute guide
```

### Task: I want to understand the code
```
1. ARCHITECTURE.md → "System Architecture" section
2. Then explore: src/main/java/com/upb/agripos/
3. Start with: AppJavaFX.java
4. Then explore: model/ → service/ → dao/ → view/
```

### Task: I want to setup the database
```
1. DATABASE_SETUP.md → Follow step-by-step
2. Or: QUICKSTART.md → "Setup Database" section
3. Run SQL scripts in: sql/ directory
```

### Task: I want to check what was implemented
```
1. IMPLEMENTATION_CHECKLIST.md
2. Look for ✅ marks to see completed items
```

### Task: I want to see project statistics
```
1. IMPLEMENTATION_CHECKLIST.md → "Project Statistics" section
2. Or: ARCHITECTURE.md → "Technology Stack Layers"
```

---

## 📖 Documentation Reading Order

### For Complete Understanding
```
1. README.md (10 min)
   └─ Get overall understanding
   
2. QUICKSTART.md (5 min)
   └─ Understand basic setup

3. DATABASE_SETUP.md (15 min)
   └─ Understand database

4. ARCHITECTURE.md (20 min)
   └─ Understand design

5. Source code (30+ min)
   └─ Study actual implementation
```

### For Quick Setup Only
```
1. QUICKSTART.md (5 min)
   └─ Follow instructions
   
2. Run application
   └─ Done!
```

### For Troubleshooting
```
1. README.md → "Troubleshooting" section
   └─ Common issues

2. DATABASE_SETUP.md → "Troubleshooting" section
   └─ Database issues

3. Check console output
   └─ Error messages
```

---

## 🎯 File Quick Reference

| File | Purpose | Read Time |
|------|---------|-----------|
| **README.md** | Main documentation | 15 min |
| **QUICKSTART.md** | Fast setup guide | 5 min |
| **DATABASE_SETUP.md** | Database guide | 15 min |
| **ARCHITECTURE.md** | Technical design | 20 min |
| **IMPLEMENTATION_CHECKLIST.md** | Project status | 10 min |
| **pom.xml** | Maven configuration | 5 min |

---

## 🗂️ Package Purpose Summary

```
com.upb.agripos
│
├─ config
│  └─ Database configuration management
│
├─ controller
│  └─ Business logic orchestration
│
├─ dao
│  └─ Direct database access (SQL queries)
│
├─ model
│  └─ Data objects (User, Product, Cart, CartItem)
│
├─ service
│  └─ Business rules and application logic
│
└─ view
   └─ User interface (JavaFX screens)
```

---

## 💾 Database Structure Reference

### Quick Column Lookup

**users table:**
```
id (PK) | username (UK) | password | full_name | role | created_at
```

**products table:**
```
code (PK) | name | price | stock | created_at
```

**carts table:**
```
id (PK) | user_id (FK) | total_price | status | created_at
```

**cart_items table:**
```
id (PK) | cart_id (FK) | product_code (FK) | quantity | subtotal
```

**transactions table:**
```
id (PK) | cart_id (FK) | user_id (FK) | total_amount | payment_method | status | transaction_date
```

---

## 🔗 External References

### SQL Scripts Location
```
agripos-app/sql/
├─ create_database.sql    (Run first)
├─ schema.sql              (Run second)
└─ seed.sql                (Run third)
```

### Configuration Files
```
agripos-app/src/main/resources/
└─ database.properties     (Edit if DB credentials differ)
```

### Source Code Location
```
agripos-app/src/main/java/com/upb/agripos/
└─ 13 Java files (~1,200 lines of code)
```

---

## 🎓 Learning Path

### Beginner (Basic Understanding)
```
1. Read README.md
2. Run QUICKSTART.md
3. Login and test application
```

### Intermediate (Understanding Code)
```
1. Read ARCHITECTURE.md
2. Explore model/ package
3. Study service/ package
4. Review view/ package
```

### Advanced (Deep Understanding)
```
1. Read ARCHITECTURE.md
2. Study all design patterns
3. Review DAO implementation
4. Analyze database schema
5. Study complete source code
```

---

## 📊 At a Glance

```
AGRIPOS Project Summary:
├─ Status: ✅ COMPLETE
├─ Java Files: 13
├─ Lines of Code: ~1,200
├─ Database Tables: 5
├─ Design Patterns: 5
├─ Features: 10+
├─ Documentation Files: 5
├─ SQL Scripts: 3
└─ Ready for: Deployment
```

---

## ✨ Where to Start

### **I have 5 minutes:**
→ Read **QUICKSTART.md** and run the app

### **I have 30 minutes:**
→ Read **README.md** + **DATABASE_SETUP.md**

### **I have 1 hour:**
→ Read all documentation + explore source code

### **I have 2+ hours:**
→ Study **ARCHITECTURE.md** + deep code review + experiment

---

## 📞 Need Help?

1. **Setup issue?** → DATABASE_SETUP.md
2. **How to use?** → README.md + QUICKSTART.md
3. **Technical question?** → ARCHITECTURE.md
4. **What's done?** → IMPLEMENTATION_CHECKLIST.md
5. **Can't find something?** → This file (PROJECT_MAP.md)

---

## 🎉 Ready to Get Started?

```
Next steps:
1. Read README.md (5 min)
2. Follow QUICKSTART.md (5 min)
3. Run: mvn javafx:run
4. Login and explore!
```

---

**Document Created:** January 14, 2026  
**Project:** AGRIPOS v1.0  
**Developer:** Fendy Agustian (240202898)  
**Status:** Complete & Ready for Use ✅
