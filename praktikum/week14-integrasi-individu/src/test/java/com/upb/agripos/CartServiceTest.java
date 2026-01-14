package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;

public class CartServiceTest {

    @Test
    void testTotalCart() {
        CartService cart = new CartService();
        cart.addToCart(new Product("P01", "Beras", 10000, 10), 2);
        assertEquals(20000, cart.getTotal());
    }
}
