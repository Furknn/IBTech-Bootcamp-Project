package com.bootcamp.entity;

public class User {
    public long Id;
    public String Username;
    public String Password;

    public User(long id, String username, String password) {
        Id = id;
        Username = username;
        Password = password;
    }

    public User() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
