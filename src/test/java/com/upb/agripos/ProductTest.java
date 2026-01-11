package com.upb.agripos;

import org.junit.jupiter.api.Test;
import com.upb.agripos.model.Product;
import com.upb.agripos.config.DatabaseConnection;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        assertEquals("Benih Jagung", p.getName());
    }

    @Test
    void testDatabaseConnection() {
        HikariDataSource dataSource = DatabaseConnection.getDataSource();
        assertNotNull(dataSource, "DataSource should not be null");
    }

    @Test
    void testGetConnection() {
        HikariDataSource dataSource = DatabaseConnection.getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");
        } catch (Exception e) {
            fail("Failed to get connection: " + e.getMessage());
        }
    }
}
