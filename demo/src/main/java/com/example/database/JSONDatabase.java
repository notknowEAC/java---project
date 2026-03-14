package com.example.database;

import java.io.FileWriter;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
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

    public static void clearOrders(){

        try{

            FileWriter file = new FileWriter("orders.json");
            file.write("[]");
            file.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String generateReport(){

        JSONArray orders = loadOrders();

        if(orders.isEmpty()){
            return "No sales data";
        }

        Map<String, Integer> quantityByMenu = new LinkedHashMap<>();
        Map<String, Integer> totalByMenu = new LinkedHashMap<>();

        for(Object o : orders){

            JSONObject obj = (JSONObject) o;

            String menu = (String) obj.get("menu");
            long priceLong = (long) obj.get("price");
            int price = (int) priceLong;

            quantityByMenu.put(menu, quantityByMenu.getOrDefault(menu, 0) + 1);
            totalByMenu.put(menu, totalByMenu.getOrDefault(menu, 0) + price);
        }

        StringBuilder report = new StringBuilder();
        report.append(String.format("%-18s %10s %8s %10s\n", "Product", "Price", "Qty", "Total"));
        report.append("--------------------------------------------------------\n");

        for(String menu : quantityByMenu.keySet()){
            int qty = quantityByMenu.get(menu);
            int total = totalByMenu.get(menu);
            int avgPrice = total / qty;

            report.append(String.format("%-18s %10d %8d %10d\n", menu, avgPrice, qty, total));
        }

        return report.toString();
    }
}