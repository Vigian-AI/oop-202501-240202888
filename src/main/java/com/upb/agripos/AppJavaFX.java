package com.upb.agripos;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.controller.PosController;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.service.UserService;
import com.upb.agripos.view.LoginView;
import com.upb.agripos.view.PosView;

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
        var controller = new PosController(productService, cartService, userService);
        var view = new PosView();

        // Bind actions
        view.getBtnAddProduct().setOnAction(e -> controller.addProduct(
            view.getTxtCode(), view.getTxtName(), view.getTxtPrice(), view.getTxtStock(), view.getProductTable()
        ));
        view.getBtnDeleteProduct().setOnAction(e -> controller.deleteProduct(view.getProductTable()));
        view.getBtnIncreaseStock().setOnAction(e -> controller.increaseStock(view.getProductTable()));
        view.getBtnDecreaseStock().setOnAction(e -> controller.decreaseStock(view.getProductTable()));
        view.getBtnAddToCart().setOnAction(e -> controller.addToCart(
            view.getProductTable(), view.getCartList(), view.getTotalLabel()
        ));
        view.getBtnCheckout().setOnAction(e -> controller.checkout(view.getCartList(), view.getTotalLabel()));
        view.getBtnRefresh().setOnAction(e -> controller.loadProducts(view.getProductTable()));

        // Restrict product management to GUDANG only
        var current = userService.getCurrentUser();
        boolean isGudang = current != null && "gudang".equalsIgnoreCase(current.getRole());
        // show user info on top of view
        if (current != null) {
            view.getUserInfoLabel().setText("User: " + current.getFullName() + " (" + current.getRole() + ")");
        } else {
            view.getUserInfoLabel().setText("User: -");
        }

        if (isGudang) {
            // allow full access and load products
            view.getTxtCode().setDisable(false);
            view.getTxtName().setDisable(false);
            view.getTxtPrice().setDisable(false);
            view.getTxtStock().setDisable(false);
            view.getBtnAddProduct().setDisable(false);
            view.getBtnDeleteProduct().setDisable(false);
            view.getBtnIncreaseStock().setDisable(false);
            view.getBtnDecreaseStock().setDisable(false);
            view.getBtnRefresh().setDisable(false);
            // gudang should NOT interact with checkout/cart
            view.getBtnAddToCart().setDisable(true);
            view.getBtnCheckout().setDisable(true);
            controller.loadProducts(view.getProductTable());
        } else {
            // disable/hide management controls for other users
            view.getTxtCode().setDisable(true);
            view.getTxtName().setDisable(true);
            view.getTxtPrice().setDisable(true);
            view.getTxtStock().setDisable(true);
            view.getBtnAddProduct().setDisable(true);
            view.getBtnDeleteProduct().setDisable(true);
            view.getBtnIncreaseStock().setDisable(true);
            view.getBtnDecreaseStock().setDisable(true);
            view.getBtnRefresh().setDisable(true);
            // non-gudang should be able to use cart/checkout
            view.getBtnAddToCart().setDisable(false);
            view.getBtnCheckout().setDisable(false);
            // ensure product table is empty for non-gudang
            view.getProductTable().getItems().clear();
        }

        Scene scene = new Scene(view, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Agri-POS");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
