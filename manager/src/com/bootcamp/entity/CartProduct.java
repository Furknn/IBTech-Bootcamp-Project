package com.bootcamp.entity;

public class CartProduct {
    private long Id;
    private long CartId;
    private long ProductId;
    private int Quantity;
    private double Price;
    private double TaxRate;
    private double LineAmount;


    public CartProduct() {
    }

    public CartProduct(long id, long cartId, long productId, int quantity, double price, double taxRate, double lineAmount) {
        Id = id;
        CartId = cartId;
        ProductId = productId;
        Quantity = quantity;
        Price = price;
        TaxRate = taxRate;
        LineAmount = lineAmount;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getCartId() {
        return CartId;
    }

    public void setCartId(long cartId) {
        CartId = cartId;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(double taxRate) {
        TaxRate = taxRate;
    }

    public double getLineAmount() {
        return LineAmount;
    }

    public void setLineAmount(double lineAmount) {
        LineAmount = lineAmount;
    }
}
