package com.example;

public class Size {

    public static int getPrice(String size){

        switch(size){
            case "S": return 40;
            case "M": return 50;
            case "L": return 60;
        }

        return 0;
    }
}