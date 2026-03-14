package com.example.model;
  
public class Dessert extends Menu{

    public Dessert(String name,int price){
        super(name, price);
    }

    public String toString(){
        return name + " Price:" + price;
    }
}