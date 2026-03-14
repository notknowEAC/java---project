package com.example.model;

public class Drink extends Menu {

    public String size;
    public String sweet;

    public Drink(String name,int price,String size,String sweet){
        super(name,price);
        this.size = size;
        this.sweet = sweet;
    }

    public String toString(){
        return name + " Size:" + size + " Sweet:" + sweet + " Price:" + price;
    }
}