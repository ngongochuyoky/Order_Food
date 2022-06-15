package com.example.orderfood.Models;

public class Product {
    private int id;
    private String photo;
    private String name;
    private String des;
    private String des1;
    private int price;
    private int available;
    public Product(){}

    public Product(String photo, String name, String des,String des1, int price,int id,int available) {
        this.photo = photo;
        this.available = available;
        this.name = name;
        this.des = des;
        this.des1 = des1;
        this.price = price;
        this.id = id;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes1() {
        return des1;
    }

    public void setDes1(String des1) {
        this.des1 = des1;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
