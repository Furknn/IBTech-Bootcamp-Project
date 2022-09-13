package com.bootcamp.entity;

public class Category {
    private long Id;
    private String Name;
    private String Detail;

    public Category(long id, String name, String detail) {
        Id = id;
        Name = name;
        Detail = detail;
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

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
