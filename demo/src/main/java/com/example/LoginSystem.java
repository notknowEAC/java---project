package com.example;

import java.util.HashMap;

public class LoginSystem {

    private static HashMap<String, Member> members = new HashMap<>();

    static {
        members.put("owner", new Member("owner", "1234", "owner"));
    }

    public static void register(String user, String pass) {
        members.put(user, new Member(user, pass, "customer"));
    }

    public static Member login(String user, String pass) {

        Member m = members.get(user);

        if (m != null && m.getPassword().equals(pass)) {
            return m;
        }

        return null;
    }
}