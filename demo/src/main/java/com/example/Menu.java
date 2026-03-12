package com.example;

public class Menu {
    public String name;
    public int price;
    
    public Menu(String name , int price){
        this.name=name;
        this.price=price;
    }
    public String getname(){
        return name;
    }
    public int getprice(){
        return price;
    }
}