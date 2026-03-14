package com.example.service;

public class Size {

    public static int getPrice(String size){
        switch(size){
            case "S": return 0;
            case "M": return 10;
            case "L": return 20;
        }
        return 0;
    }
}