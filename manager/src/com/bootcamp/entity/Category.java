package com.bootcamp.entity;

public class Category {
    public long Id;
    public String Name;

    public Category(long id, String name) {
        Id = id;
        Name = name;
    }

    public Category(){}

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
}
