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

    public static int getBasePrice(String menuName){
        switch(menuName){
            case "Espresso": return 55;
            case "Americano": return 60;
            case "Cappuccino": return 70;
            case "Mocha": return 75;
            case "Latte": return 70;
            case "Matcha": return 65;
            case "Thai Tea": return 60;
            case "Milk Tea": return 60;
            case "Chocolate": return 65;
            case "Lemon Tea": return 55;

            case "Butter Cake": return 55;
            case "Chocolate Cake": return 75;
            case "Cookie": return 45;
            case "Crepes": return 65;
            case "Croissant": return 60;
            case "Matcha Custard": return 70;
            case "Macaron": return 50;
            case "Pudding": return 55;
            case "Pan Cake": return 60;
            case "Pancake": return 60;
            case "Ice Cream": return 50;
            default: return 60;
        }
    }
}