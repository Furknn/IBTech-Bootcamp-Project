package com.bootcamp.entity;

public class Province {
    private long Id;
    private String Name;

    public Province(long id, String name) {
        Id = id;
        Name = name;
    }

    public Province(){}

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
