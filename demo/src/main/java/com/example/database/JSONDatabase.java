package com.example.database;

import java.io.FileWriter;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.model.Member;

public class JSONDatabase {

    public static void saveMember(Member m){

        JSONArray members = loadMembers();

        JSONObject obj = new JSONObject();
        obj.put("username",m.getUsername());
        obj.put("password",m.getPassword());
        obj.put("role",m.getRole());

        members.add(obj);

        try{

            FileWriter file = new FileWriter("members.json");
            file.write(members.toJSONString());
            file.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static JSONArray loadMembers(){

        try{

            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("members.json");

            return (JSONArray) parser.parse(reader);

        }catch(Exception e){
            return new JSONArray();
        }
    }

    public static void saveOrder(String menu,int price){

        JSONArray orders = loadOrders();

        JSONObject obj = new JSONObject();
        obj.put("menu",menu);
        obj.put("price",price);

        orders.add(obj);

        try{

            FileWriter file = new FileWriter("orders.json");
            file.write(orders.toJSONString());
            file.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static JSONArray loadOrders(){

        try{

            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("orders.json");

            return (JSONArray) parser.parse(reader);

        }catch(Exception e){
            return new JSONArray();
        }
    }

    public static int getTotalSales(){

        JSONArray orders = loadOrders();

        int total = 0;

        for(Object o:orders){

            JSONObject obj = (JSONObject)o;

            long price = (long)obj.get("price");

            total += price;
        }

        return total;
    }
}