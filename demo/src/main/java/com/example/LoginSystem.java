package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LoginSystem {

    public static Member login(String user,String pass){

        JSONArray members = JSONDatabase.loadMembers();

        for(Object o:members){

            JSONObject obj = (JSONObject)o;

            String u = (String)obj.get("username");
            String p = (String)obj.get("password");
            String r = (String)obj.get("role");

            if(u.equals(user) && p.equals(pass)){
                return new Member(u,p,r);
            }
        }

        return null;
    }

    public static void register(String user,String pass){

        Member m = new Member(user,pass,"customer");

        JSONDatabase.saveMember(m);
    }
}