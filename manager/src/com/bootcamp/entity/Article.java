package com.bootcamp.entity;

public class Article {
    private long Id;
    private String Title;
    private String Content;
    private String MenuId;

    public Article(long id, String title, String content, String menuId) {
        Id = id;
        Title = title;
        Content = content;
        MenuId = menuId;
    }

    public Article() {
    }

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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

}
