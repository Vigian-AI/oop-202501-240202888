package main.java.com.upb.agripos;

import java.lang.Exception;

public class EmptyCartException extends Exception {
    public EmptyCartException(String msg) { super(msg); }
}
