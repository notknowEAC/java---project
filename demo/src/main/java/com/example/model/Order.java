package com.example.model;

public class Order {

    private String menu;
    private String size;
    private String sweet;
    private int quantity;
    private int price;

    public Order(String menu, String size, String sweet, int quantity, int price) {
        this.menu = menu;
        this.size = size;
        this.sweet = sweet;
        this.quantity = quantity;
        this.price = price;
    }

    public String getMenu() {
        return menu;
    }

    public int getPrice() {
        return price;
    }

}