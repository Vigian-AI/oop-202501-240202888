package com.upb.agripos;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.controller.PosController;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.service.UserService;
import com.upb.agripos.view.GudangView;
import com.upb.agripos.view.KasirView;
import com.upb.agripos.view.LoginView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Print identity
        System.out.println("Hello World, I am Vg-240202888");

        // Initialize database
        var dbConn = DatabaseConnection.getInstance();
        var dataSource = dbConn.getDataSource();

        // Initialize DAO
        var productDAO = new ProductDAOImpl(dataSource);

        // Initialize Services
        var productService = new ProductService(productDAO);
        var cartService = new CartService(productService);
        var userService = new UserService();
        // Show login screen first
        showLoginScreen(primaryStage, productService, cartService, userService);
    }

    private void showLoginScreen(Stage primaryStage, ProductService productService, CartService cartService, UserService userService) {
        LoginView loginView = new LoginView(primaryStage, userService);
        loginView.setOnLoginSuccess(() -> showMainApp(primaryStage, productService, cartService, userService));
        Scene loginScene = new Scene(loginView, 500, 600);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Agri-POS - Login");
        primaryStage.show();
    }

    private void showMainApp(Stage primaryStage, ProductService productService, CartService cartService, UserService userService) {
        var current = userService.getCurrentUser();
        
        if (current != null && "gudang".equalsIgnoreCase(current.getRole())) {
            // Show Gudang View
            showGudangApp(primaryStage, productService, cartService, userService, current);
        } else {
            // Show Kasir View
            showKasirApp(primaryStage, productService, cartService, userService, current);
        }
    }

    private void showGudangApp(Stage primaryStage, ProductService productService, CartService cartService, UserService userService, com.upb.agripos.model.User current) {
        var controller = new PosController(productService, cartService, userService);
        var view = new GudangView();

        // Display current user info
        view.getUserInfoLabel().setText("👤 " + current.getFullName() + " (GUDANG)");

        // Bind action handlers for product management
        view.getBtnAddProduct().setOnAction(e -> controller.addProduct(
            view.getTxtCode(), view.getTxtName(), view.getTxtPrice(), view.getTxtStock(), view.getProductTable()
        ));
        view.getBtnDeleteProduct().setOnAction(e -> controller.deleteProduct(view.getProductTable()));
        view.getBtnIncreaseStock().setOnAction(e -> controller.increaseStock(view.getProductTable()));
        view.getBtnDecreaseStock().setOnAction(e -> controller.decreaseStock(view.getProductTable()));
        view.getBtnRefresh().setOnAction(e -> controller.loadProducts(view.getProductTable()));

        // Load products
        controller.loadProducts(view.getProductTable());

        Scene scene = new Scene(view, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("📦 Agri-POS - Manajemen Gudang");
        primaryStage.show();
    }

    private void showKasirApp(Stage primaryStage, ProductService productService, CartService cartService, UserService userService, com.upb.agripos.model.User current) {
        var controller = new PosController(productService, cartService, userService);
        var view = new KasirView();

        // Display current user info
        String userName = current != null ? current.getFullName() : "-";
        String userRole = current != null ? current.getRole().toUpperCase() : "KASIR";
        view.getUserInfoLabel().setText("👤 " + userName + " (" + userRole + ")");

        // Bind action handlers for sales transaction
        view.getBtnAddToCart().setOnAction(e -> controller.addToCart(
            view.getProductTable(), view.getCartList(), view.getTotalLabel()
        ));
        view.getBtnCheckout().setOnAction(e -> controller.checkout(view.getCartList(), view.getTotalLabel()));

        // Load products for all users
        try {
            view.getProductTable().getItems().clear();
            view.getProductTable().getItems().addAll(productService.findAll());
        } catch (Exception e) {
            System.err.println("Error loading products: " + e.getMessage());
        }

        Scene scene = new Scene(view, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("🛒 Agri-POS - Sistem Kasir");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
