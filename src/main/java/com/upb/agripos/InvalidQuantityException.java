package main.java.com.upb.agripos;

import java.lang.Exception;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}
