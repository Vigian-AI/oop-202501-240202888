package main.java.com.upb.agripos.exceptions;

public class CartEmptyException extends Exception {
    public CartEmptyException(String message) {
        super(message);
    }
}