package com.bootcamp.entity;

public class Product {
    private long Id;
    private String Name;
    private double Price;
    private long CategoryId;
    private String ImageUrl;

    public Product(long id, String name, double price, long categoryId, String imageUrl) {
        Id = id;
        Name = name;
        Price = price;
        CategoryId = categoryId;
        ImageUrl = imageUrl;
    }

    public Product() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(long categoryId) {
        CategoryId = categoryId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

}
