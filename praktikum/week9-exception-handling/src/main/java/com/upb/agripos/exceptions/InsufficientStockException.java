package main.java.com.upb.agripos.exceptions;

import main.java.com.upb.agripos.models.Product;

public class InsufficientStockException extends Exception {
    private final Product product;
    private final int requestedQuantity;
    private final int availableStock;
    
    public InsufficientStockException(Product product, int requestedQuantity, int availableStock) {
        super(String.format("Stok tidak mencukupi untuk produk '%s'. Diminta: %d, Tersedia: %d", 
              product.getName(), requestedQuantity, availableStock));
        this.product = product;
        this.requestedQuantity = requestedQuantity;
        this.availableStock = availableStock;
    }
    
    public Product getProduct() { return product; }
    public int getRequestedQuantity() { return requestedQuantity; }
    public int getAvailableStock() { return availableStock; }
}