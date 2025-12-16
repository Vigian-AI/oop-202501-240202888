package main.java.com.upb.agripos.week7;

public class Product {

    private final String code;
    private final String name;
    private final int price;

    // Constructor yang BENAR
    public Product(String code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
