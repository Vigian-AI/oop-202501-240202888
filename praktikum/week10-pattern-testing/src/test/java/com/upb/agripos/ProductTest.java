package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

import com.upb.agripos.config.DatabaseConnection;
import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    public void testProductAttributes() {
        // Menampilkan pesan identitas saat pengujian dijalankan
        System.out.println("Hello, I am [Fendy Agustian] - [240202898] (Week10)");
        
        Product p = new Product("P01", "Benih Jagung");
        assertEquals("P01", p.getCode());
        assertEquals("Benih Jagung", p.getName());
        
        System.out.println("Pengujian Atribut Produk Berhasil!");
    }

    @Test
    public void testSingletonInstance() {
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();
        
        // Memastikan instance yang sama
        assertSame(instance1, instance2, "Singleton harus menghasilkan instance yang sama");
        
        System.out.println("Pengujian Singleton Database Berhasil!");
    }
}