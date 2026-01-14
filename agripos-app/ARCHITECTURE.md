# 🏗️ AGRIPOS Architecture & Design Patterns

**Complete technical documentation untuk system architecture dan design patterns**

---

## 📐 System Architecture

### High-Level Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                     PRESENTATION LAYER (JavaFX)            │
│  ┌────────────────────────────────────────────────────────┐│
│  │  LoginView.java              PosView.java             ││
│  │  ├─ Username TextField       ├─ Product Input Form    ││
│  │  ├─ Password PasswordField   ├─ Product TableView    ││
│  │  ├─ Login Button             ├─ Shopping Cart         ││
│  │  └─ Demo Credentials Label   ├─ Quantity Spinner      ││
│  │                              └─ Checkout Button       ││
│  └────────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                    CONTROLLER LAYER                         │
│  ┌────────────────────────────────────────────────────────┐│
│  │  PosController.java (Business Logic Orchestrator)      ││
│  │  ├─ addProduct(Product)                              ││
│  │  ├─ deleteProduct(String code)                       ││
│  │  ├─ getProducts() → List<Product>                    ││
│  │  ├─ addToCart(Product, int qty)                      ││
│  │  ├─ getCartTotal() → double                          ││
│  │  └─ clearCart()                                      ││
│  └────────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                     SERVICE LAYER                           │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────┐│
│  │ UserService.java │  │ProductService    │  │CartService   ││
│  │                  │  │                  │  │              ││
│  │ • login()        │  │ • addProduct()   │  │• addToCart() ││
│  │ • logout()       │  │ • deleteProduct()│  │• getTotal()  ││
│  │ • getCurrentUser │  │ • getAllProducts │  │• clearCart() ││
│  │ • isLoggedIn()   │  │ • loadFromDB()   │  │• getCart()   ││
│  └──────────────────┘  └──────────────────┘  └──────────────┘│
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                       DAO LAYER                             │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────┐│
│  │ UserDAO.java     │  │ ProductDAO       │  │Database      ││
│  │                  │  │ (interface)      │  │Config.java   ││
│  │ • authenticate() │  │                  │  │              ││
│  │ • getInstance()  │  │ • insert()       │  │ • getConfig()││
│  │ (Singleton)      │  │ • delete()       │  │ (Singleton)  ││
│  │                  │  │ • findAll()      │  │              ││
│  │                  │  │ (Jdbc impl)      │  │ Properties:  ││
│  │                  │  │ (Singleton)      │  │ • db.url     ││
│  │                  │  │                  │  │ • db.user    ││
│  │                  │  │                  │  │ • db.pass    ││
│  └──────────────────┘  └──────────────────┘  └──────────────┘│
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                   MODEL LAYER (POJOs)                       │
│  ┌──────────────┐  ┌─────────────┐  ┌──────────────┐        │
│  │ User.java    │  │Product.java │  │Cart.java     │        │
│  │              │  │             │  │              │        │
│  │ • id (F)     │  │ • code (F)  │  │ • items      │        │
│  │ • username   │  │ • name (F)  │  │ • addItem()  │        │
│  │ • password   │  │ • price (F) │  │ • getTotal()│        │
│  │ • fullName   │  │ • stock (M) │  │ • clear()   │        │
│  │ • role       │  │             │  │              │        │
│  └──────────────┘  └─────────────┘  └──────────────┘        │
│  ┌────────────────┐                                         │
│  │CartItem.java   │                                         │
│  │                │                                         │
│  │ • product      │                                         │
│  │ • quantity     │                                         │
│  │ • getSubtotal()│                                         │
│  └────────────────┘                                         │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                   DATABASE LAYER                            │
│               (PostgreSQL via JDBC)                         │
│  ┌──────────────┬──────────────┬──────────────┐             │
│  │   users      │   products   │    carts     │             │
│  │              │              │              │             │
│  │ • id (PK)    │ • code (PK)  │ • id (PK)    │             │
│  │ • username   │ • name       │ • user_id    │             │
│  │ • password   │ • price      │ • total_price│             │
│  │ • full_name  │ • stock      │ • status     │             │
│  │ • role       │ • created_at │ • created_at │             │
│  │ • created_at │              │              │             │
│  └──────────────┴──────────────┴──────────────┘             │
│  ┌──────────────┬──────────────┐                            │
│  │ cart_items   │transactions  │                            │
│  │              │              │                            │
│  │ • id (PK)    │ • id (PK)    │                            │
│  │ • cart_id    │ • cart_id    │                            │
│  │ • product_cd │ • user_id    │                            │
│  │ • quantity   │ • total_amt  │                            │
│  │ • subtotal   │ • payment_mt │                            │
│  │              │ • status     │                            │
│  │              │ • trans_date │                            │
│  └──────────────┴──────────────┘                            │
└─────────────────────────────────────────────────────────────┘
```

---

## 🎭 Design Patterns Implemented

### 1. MVC (Model-View-Controller)

```
┌─────────────────────────────────────────┐
│             USER INTERACTION            │
└─────────────────────┬───────────────────┘
                      ↓
     ┌────────────────────────────┐
     │    VIEW LAYER (JavaFX)    │
     │  • LoginView              │
     │  • PosView                │
     └────────────┬───────────────┘
                  ↓
     ┌────────────────────────────┐
     │  CONTROLLER LAYER          │
     │  • PosController           │
     │  (Routes events to model)  │
     └────────────┬───────────────┘
                  ↓
     ┌────────────────────────────┐
     │   MODEL LAYER (Services)   │
     │  • UserService             │
     │  • ProductService          │
     │  • CartService             │
     │  (Business logic)          │
     └────────────┬───────────────┘
                  ↓
         ┌─────────────────┐
         │   PERSISTENCE   │
         │  (Database)     │
         └─────────────────┘
```

**Benefit:** Loose coupling between UI and business logic

---

### 2. DAO (Data Access Object)

```
┌──────────────────────────────────────────┐
│         Service Layer Code               │
│      (ProductService.java)               │
└──────────────────────┬────────────────────┘
                       ↓
┌──────────────────────────────────────────┐
│       DAO Interface (ProductDAO)         │
│  + insert(Product): void                 │
│  + delete(String code): void             │
│  + findAll(): List<Product>              │
└──────────────────────┬────────────────────┘
                       ↓
┌──────────────────────────────────────────┐
│   DAO Implementation (JdbcProductDAO)    │
│  - Handles SQL queries                   │
│  - Maps ResultSet to objects             │
│  - Uses prepared statements              │
└──────────────────────┬────────────────────┘
                       ↓
         ┌──────────────────────┐
         │  PostgreSQL Database │
         └──────────────────────┘
```

**Benefit:** Abstraction of database access logic, easy to switch database

---

### 3. Singleton Pattern

```
┌─────────────────────────────────────────┐
│      DatabaseConfig.getInstance()       │
│  - private static instance = null       │
│  - public static getInstance()          │
│  {                                      │
│    if (instance == null) {              │
│      instance = new DatabaseConfig()    │
│    }                                    │
│    return instance                      │
│  }                                      │
└─────────────────────────────────────────┘

Also used in:
• JdbcProductDAO.getInstance()
• UserDAO.getInstance()
```

**Benefit:** Ensures single instance, shared resources, lazy initialization

---

### 4. Service Layer Pattern

```
┌────────────────────────────────┐
│     View/Controller            │
│  (requests business logic)     │
└────────────┬───────────────────┘
             ↓
┌────────────────────────────────┐
│     Service Layer              │
│  • UserService                 │
│  • ProductService              │
│  • CartService                 │
│                                │
│  Responsibilities:             │
│  • Validate input              │
│  • Coordinate DAOs             │
│  • Handle transactions         │
│  • Apply business rules        │
└────────────┬───────────────────┘
             ↓
┌────────────────────────────────┐
│     DAO Layer                  │
│  (direct DB access)            │
└────────────────────────────────┘
```

**Benefit:** Business logic separated from data access

---

### 5. Authentication Pattern

```
┌──────────────────┐
│  LoginView.java  │
│  User enters     │
│  credentials     │
└────────┬─────────┘
         ↓
┌──────────────────────────────┐
│  UserService.login()         │
│  • Validates input           │
│  • Calls UserDAO             │
└────────┬─────────────────────┘
         ↓
┌──────────────────────────────┐
│  UserDAO.authenticate()      │
│  • Query database            │
│  • Return User or null       │
└────────┬─────────────────────┘
         ↓
┌──────────────────────────────┐
│  UserService stores User     │
│  in currentUser field        │
└────────┬─────────────────────┘
         ↓
┌──────────────────────────────┐
│  PosView displays:           │
│  • User name in header       │
│  • Role badge                │
│  • Logout button enabled     │
└──────────────────────────────┘
```

**Benefit:** Secure authentication with session management

---

## 🔄 Application Flow

### Startup Flow

```
1. AppJavaFX.main()
   ↓
2. AppJavaFX.start(Stage)
   ↓
3. Create LoginView
   • UserService created
   • ProductService created  (loads data from DB)
   • CartService created
   ↓
4. Show LoginView
   • Display login form
   • Show demo credentials
   ↓
5. User enters credentials
   ↓
6. LoginView calls UserService.login(username, password)
   ↓
7. UserService calls UserDAO.authenticate()
   ↓
8. UserDAO queries database
   ↓
9. If valid → Show PosView
   If invalid → Show error dialog
```

### User Interaction Flow

```
┌──────────────┐
│  LoginView   │ (User logs in)
└──────┬───────┘
       ↓
   Valid auth?
       ├─ YES → ┌──────────────┐
       │        │  PosView     │ (Main application)
       │        └──────┬───────┘
       │               ↓
       │         Display products
       │               ↓
       │         User selects product
       │               ↓
       │         Add to cart
       │               ↓
       │         View cart total
       │               ↓
       │         Click checkout
       │               ↓
       │         Clear cart
       │               ↓
       │         User clicks logout
       │               ↓
       │         ┌──────────────┐
       │         │  LoginView   │ (Back to login)
       │         └──────────────┘
       │
       └─ NO → Show error
              Try again
```

---

## 📦 Package Structure

```
com/upb/agripos/
│
├── AppJavaFX.java
│   └─ Main entry point
│      - Start application
│      - Manage scenes (LoginView ↔ PosView)
│      - Handle logout callback
│
├── config/
│   └── DatabaseConfig.java
│       - Load database.properties
│       - Provide connection details
│       - Singleton pattern
│
├── controller/
│   └── PosController.java
│       - Business logic orchestrator
│       - Routes requests to services
│       - No direct DB access
│
├── dao/
│   ├── ProductDAO.java (interface)
│   ├── JdbcProductDAO.java (implementation)
│   │   - CRUD operations for products
│   │   - SQL queries
│   │   - Singleton pattern
│   └── UserDAO.java
│       - Authenticate users
│       - Query users table
│       - Singleton pattern
│
├── model/
│   ├── User.java
│   │   - id, username, password, fullName, role
│   ├── Product.java
│   │   - code, name, price, stock
│   ├── Cart.java
│   │   - List<CartItem>
│   │   - getTotal(), clear()
│   └── CartItem.java
│       - product, quantity
│       - getSubtotal()
│
├── service/
│   ├── UserService.java
│   │   - login(), logout()
│   │   - getCurrentUser(), isLoggedIn()
│   │   - Session management
│   ├── ProductService.java
│   │   - addProduct(), deleteProduct()
│   │   - getAllProducts()
│   │   - Load from database on init
│   └── CartService.java
│       - addToCart(), clearCart()
│       - getTotal(), getCartItems()
│
└── view/
    ├── LoginView.java
    │   - TextField for username
    │   - PasswordField for password
    │   - Demo credentials display
    │   - Validation & error handling
    └── PosView.java
        - Product input form
        - Product table (TableView)
        - Shopping cart
        - Checkout button
        - Logout button
        - User info header
```

---

## 🔐 Security Considerations

### Authentication
- Password stored in database (currently plain text - should hash in production)
- UserDAO validates against database
- Session maintained via UserService.currentUser

### SQL Injection Prevention
- Prepared statements used in all DAO operations
- User input never directly concatenated in SQL

### Data Validation
- LoginView validates non-empty credentials
- PosView validates product input
- Service layer validates business rules

---

## 📊 Data Flow Diagram

### Adding Product Flow

```
User enters:
- Code: PROD-001
- Name: Beras Premium
- Price: 150000
- Stock: 100
        ↓
┌─────────────────────────────────────┐
│ PosView (button listener)           │
│ Creates Product object              │
│ Calls PosController.addProduct()    │
└─────────────────────────────────────┘
        ↓
┌─────────────────────────────────────┐
│ PosController.addProduct()          │
│ • Validates product                 │
│ • Calls ProductService.addProduct() │
└─────────────────────────────────────┘
        ↓
┌─────────────────────────────────────┐
│ ProductService.addProduct()         │
│ • Add to in-memory list             │
│ • Calls JdbcProductDAO.insert()    │
└─────────────────────────────────────┘
        ↓
┌─────────────────────────────────────┐
│ JdbcProductDAO.insert()             │
│ • Create prepared statement         │
│ • Execute INSERT query              │
│ • Return success/failure            │
└─────────────────────────────────────┘
        ↓
┌─────────────────────────────────────┐
│ PostgreSQL Database                 │
│ INSERT INTO products (...)          │
│ VALUES (...)                        │
└─────────────────────────────────────┘
        ↓
┌─────────────────────────────────────┐
│ PosView updates TableView           │
│ New product appears in table        │
│ Success alert shown to user         │
└─────────────────────────────────────┘
```

---

## 🧩 Class Relationships

### Dependency Graph

```
AppJavaFX
├── depends on: LoginView
├── depends on: PosView
├── depends on: UserService
├── depends on: ProductService
└── depends on: CartService

LoginView
├── depends on: UserService
└── uses callback: AppJavaFX.showMainApp()

PosView
├── depends on: PosController
├── depends on: UserService
└── uses callback: AppJavaFX.handleLogout()

PosController
├── depends on: ProductService
└── depends on: CartService

ProductService
├── depends on: JdbcProductDAO
├── depends on: Product
└── depends on: DatabaseConfig

UserService
├── depends on: UserDAO
├── depends on: User
└── depends on: DatabaseConfig

CartService
├── depends on: Cart
├── depends on: CartItem
└── depends on: Product

JdbcProductDAO
├── depends on: Product
└── depends on: DatabaseConfig

UserDAO
├── depends on: User
└── depends on: DatabaseConfig

DatabaseConfig
└── depends on: database.properties
```

---

## 💾 Database Relationship Diagram

```
users (1)
  ├─ id (PK)
  ├─ username (UNIQUE)
  ├─ password
  ├─ full_name
  ├─ role
  └─ created_at
    ↓ 1:M
    │
    ├─── carts
    │      ├─ id (PK)
    │      ├─ user_id (FK → users.id)
    │      ├─ total_price
    │      ├─ status
    │      └─ created_at
    │        ↓ 1:M
    │        └─── cart_items
    │               ├─ id (PK)
    │               ├─ cart_id (FK → carts.id)
    │               ├─ product_code (FK → products.code)
    │               ├─ quantity
    │               └─ subtotal
    │
    └─── transactions
           ├─ id (PK)
           ├─ cart_id (FK → carts.id)
           ├─ user_id (FK → users.id)
           ├─ total_amount
           ├─ payment_method
           ├─ status
           └─ transaction_date

products (independent)
  ├─ code (PK)
  ├─ name
  ├─ price
  ├─ stock
  └─ created_at
```

---

## ⚙️ Technology Stack Layers

```
┌─────────────────────────────────────────────────────┐
│  PRESENTATION TIER                                  │
│  • JavaFX 17.0.2 - UI Framework                    │
│  • Stage & Scene - Window management                │
│  • TableView, TextField, Button - UI Components     │
└─────────────────────────────────────────────────────┘
                      ↓
┌─────────────────────────────────────────────────────┐
│  APPLICATION TIER                                   │
│  • Java 11 - Core Language                         │
│  • Controllers & Services - Business Logic          │
│  • Pattern Implementation - Design Patterns         │
└─────────────────────────────────────────────────────┘
                      ↓
┌─────────────────────────────────────────────────────┐
│  PERSISTENCE TIER                                   │
│  • JDBC API - Database connectivity                │
│  • PreparedStatement - SQL Execution                │
│  • DAO Layer - Data Access Abstraction             │
└─────────────────────────────────────────────────────┘
                      ↓
┌─────────────────────────────────────────────────────┐
│  DATA TIER                                          │
│  • PostgreSQL - Relational Database                │
│  • SQL Queries - Data Operations                    │
│  • Indexes - Performance Optimization               │
└─────────────────────────────────────────────────────┘
```

---

## 🎓 Design Principles Applied

### SOLID Principles

1. **S - Single Responsibility**
   - Each class has one reason to change
   - ProductDAO handles product data
   - UserService handles authentication

2. **O - Open/Closed**
   - ProductDAO interface allows multiple implementations
   - Easy to add new database types

3. **L - Liskov Substitution**
   - JdbcProductDAO implements ProductDAO interface correctly

4. **I - Interface Segregation**
   - ProductDAO interface only has product-related methods
   - No bloated interfaces

5. **D - Dependency Inversion**
   - Services depend on abstractions (ProductDAO interface)
   - Not on concrete implementations (JdbcProductDAO)

---

## 📈 Scalability Considerations

### Current Architecture Supports:
- ✅ Multiple database implementations (swap JdbcProductDAO)
- ✅ Additional services (InvoiceService, ReportService)
- ✅ Additional DAO methods
- ✅ Role-based access control (roles stored in User)

### Future Enhancements:
- 🔄 Transaction history with TransactionDAO
- 🔄 Inventory management with InventoryService
- 🔄 Report generation with ReportService
- 🔄 API layer with REST endpoints
- 🔄 Caching with Redis integration

---

**Document Created:** January 14, 2026  
**Architecture Version:** 1.0  
**Design Patterns:** 5 (MVC, DAO, Singleton, Service Layer, Authentication)
