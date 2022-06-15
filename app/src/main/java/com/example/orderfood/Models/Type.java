package com.example.orderfood.Models;

public class Type {
    private String photo;
    private String photo1;
    private String name;
    private int id;

    public Type(){}
    public Type(String photo, String name, String id,String photo1) {
        this.photo = photo;
        this.photo1 = photo1;
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
}
