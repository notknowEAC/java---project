package com.example.database;

import java.io.FileWriter;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

        @SuppressWarnings("unchecked")
        List<JSONObject> orderList = (List<JSONObject>)(List<?>) orders;

        return orderList.stream()
            .mapToInt(obj -> ((Long) obj.get("price")).intValue())
            .sum();
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

        @SuppressWarnings("unchecked")
        List<JSONObject> orderList = (List<JSONObject>)(List<?>) orders;

        Map<String, Long> quantityByMenu = orderList.stream()
            .collect(Collectors.groupingBy(
                obj -> (String) obj.get("menu"),
                LinkedHashMap::new,
                Collectors.counting()));

        Map<String, Integer> totalByMenu = orderList.stream()
            .collect(Collectors.groupingBy(
                obj -> (String) obj.get("menu"),
                LinkedHashMap::new,
                Collectors.summingInt(obj -> ((Long) obj.get("price")).intValue())));

        StringBuilder report = new StringBuilder();
        report.append(String.format("%-18s %10s %8s %10s\n", "Product", "Price", "Qty", "Total"));
        report.append("--------------------------------------------------------\n");

        for(String menu : quantityByMenu.keySet()){
            int qty = quantityByMenu.get(menu).intValue();
            int total = totalByMenu.get(menu);
            int avgPrice = total / qty;

            report.append(String.format("%-18s %10d %8d %10d\n", menu, avgPrice, qty, total));
        }

        return report.toString();
    }
}