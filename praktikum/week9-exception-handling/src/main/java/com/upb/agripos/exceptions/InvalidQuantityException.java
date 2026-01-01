package main.java.com.upb.agripos.exceptions;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
    
    public InvalidQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}