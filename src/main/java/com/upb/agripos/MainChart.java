package main.java.com.upb.agripos;

import main.java.com.upb.agripos.util.CreditBy;

public class MainChart {
    public static void main() {
        System.out.println("Hello, I am Vigian Agus Isnaeni-240202888 (Week7)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);

        ShoppingChart cart = new ShoppingChart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();

        cart.removeProduct(p1);
        cart.printCart();
    CreditBy.print("240202888", "Vigian Agus Isnaeni");
    }
}
