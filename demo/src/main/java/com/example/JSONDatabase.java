package com.example;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONDatabase {

    public static void saveOrder(String menu, int price) {

        JSONArray orders = new JSONArray();

        JSONObject obj = new JSONObject();
        obj.put("menu", menu);
        obj.put("price", price);

        orders.add(obj);

        try {

            FileWriter file = new FileWriter("orders.json", true);
            file.write(orders.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getTotalSales(){

        int total = 0;

        try{

            File file = new File("orders.json");

            if(!file.exists()){
                return 0;
            }

            JSONParser parser = new JSONParser();

            FileReader reader = new FileReader(file);

            JSONArray orders = (JSONArray) parser.parse(reader);

            for(Object o : orders){

                JSONObject obj = (JSONObject) o;

                long price = (long) obj.get("price");

                total += price;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return total;
    }
}