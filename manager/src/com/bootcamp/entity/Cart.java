package com.bootcamp.entity;

public class Cart {
    private long Id;
    private double TotalAmount;
    private String CustomerName;

    public Cart(long id, double totalAmount, String customerName) {
        Id = id;
        TotalAmount = totalAmount;
        CustomerName = customerName;
    }

    public Cart() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
}
