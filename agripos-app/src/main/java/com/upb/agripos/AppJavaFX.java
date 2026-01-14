package com.upb.agripos;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.service.UserService;
import com.upb.agripos.view.LoginView;
import com.upb.agripos.view.PosView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    private Stage primaryStage;
    private UserService userService;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.userService = new UserService();

        System.out.println("\n🌾 ============================================");
        System.out.println("  AGRIPOS - Sistem Penjualan Pertanian");
        System.out.println("  Fendy Agustian (240202898)");
        System.out.println("============================================\n");

        // Tampilkan Login Screen
        showLoginScreen();

        primaryStage.setTitle("AGRIPOS - Login");
        primaryStage.setWidth(500);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

    private void showLoginScreen() {
        LoginView loginView = new LoginView(primaryStage, userService);
        loginView.setOnLoginSuccess(this::showMainApp);

        Scene loginScene = new Scene(loginView);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("AGRIPOS - Login");
    }

    private void showMainApp() {
        // Initialize services
        ProductService ps = new ProductService(JdbcProductDAO.getInstance());
        CartService cs = new CartService();
        PosController controller = new PosController(ps, cs);

        // Create main POS View with logout callback
        PosView posView = new PosView(controller, userService.getCurrentUser(), this::handleLogout);

        Scene mainScene = new Scene(posView);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("AGRIPOS - " + userService.getCurrentUser().getFullName());
        primaryStage.setWidth(900);
        primaryStage.setHeight(700);
    }

    public void handleLogout() {
        System.out.println("\n✓ Logout...\n");
        userService.logout();
        showLoginScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}
