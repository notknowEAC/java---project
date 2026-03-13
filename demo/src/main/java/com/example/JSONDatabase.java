package com.example;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONDatabase {

    public static void saveOrder(String menu, int price) {

        JSONArray orders = new JSONArray();

        JSONObject obj = new JSONObject();
        obj.put("menu", menu);
        obj.put("price", price);

        orders.add(obj);

        try {

            FileWriter file = new FileWriter("orders.json");
            file.write(orders.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}