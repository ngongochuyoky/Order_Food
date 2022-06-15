package com.example.orderfood.Models;

public class CartItem {
    private int id;
    private String photo;
    private String name;
    private int price;
    private int number;
    private int max;
    public CartItem(){}

    public CartItem(String photo, String name, int price, int number,int id,int max) {
        this.photo = photo;
        this.max = max;
        this.id = id;
        this.name = name;
        this.price = price;
        this.number = number;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
