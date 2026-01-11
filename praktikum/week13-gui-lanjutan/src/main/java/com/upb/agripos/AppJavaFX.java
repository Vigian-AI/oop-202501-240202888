package com.upb.agripos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.dao.*;
import com.upb.agripos.service.*;
import com.upb.agripos.controller.*;
import com.upb.agripos.view.*;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos",
            "postgres",
            "1234"
        );

        ProductDAO dao = new ProductDAOImpl(conn);
        ProductService service = new ProductService(dao);
        ProductController controller = new ProductController(service);

        ProductTableView view = new ProductTableView(controller);

        stage.setScene(new Scene(view, 600, 400));
        stage.setTitle("Agri-POS - Produk");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
