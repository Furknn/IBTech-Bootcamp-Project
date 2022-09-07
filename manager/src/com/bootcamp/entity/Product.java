package com.bootcamp.entity;

public class Product {
	public long Id;
	public String Name;
    public double Price;
    private long CategoryId;

    public Product(long id, String name, double price,long categoryId) {
        Id = id;
        Name = name;
        Price = price;
        CategoryId=categoryId;
    }

    public Product(){}

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
    
    
}
