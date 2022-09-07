package com.bootcamp.entity;

public class Menu {
    public long Id;
    public String Title;

    public Menu(long id, String title) {
        Id = id;
        Title = title;
    }

    public Menu(){}

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

}
