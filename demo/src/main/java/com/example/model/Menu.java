package com.example.model;

public class Menu {
    public String name;
    public int price;

    public Menu(String name , int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}