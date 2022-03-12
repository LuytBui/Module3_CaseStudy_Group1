package com.codegym.model;

public class Blog {
    public static final int MAX_CHAR_PREVIEW = 50;
    private int id;

    private int category_id;

    private int user_id;

    private String tittle;

    private String content;

    private String dateModified;

    public Blog() {
    }

    public Blog(int category_id, int user_id, String tittle, String content, String dateModified) {
        this.category_id = category_id;
        this.user_id = user_id;
        this.tittle = tittle;
        this.content = content;
        this.dateModified = dateModified;
    }

    public Blog(int id, int category_id, int user_id, String tittle, String content) {
        this.id = id;
        this.category_id = category_id;
        this.user_id = user_id;
        this.tittle = tittle;
        this.content = content;
    }

    public Blog(int category_id, int user_id, String tittle, String content) {
        this.category_id = category_id;
        this.user_id = user_id;
        this.tittle = tittle;
        this.content = content;
    }

    public Blog(int id, int category_id, int user_id, String tittle, String content, String dateModified) {
        this.id = id;
        this.category_id = category_id;
        this.user_id = user_id;
        this.tittle = tittle;
        this.content = content;
        this.dateModified = dateModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getContentPreview(){
        if (content.length() <= MAX_CHAR_PREVIEW)
            return content;
        return content.substring(0, MAX_CHAR_PREVIEW) + "...";
    }
}
