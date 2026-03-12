package com.example;

public class Size {

    public static int getPrice(String size){
        switch(size){
            case "S": return 50;
            case "M": return 60;
            case "L": return 70;
        }
        return 0;
    }

}