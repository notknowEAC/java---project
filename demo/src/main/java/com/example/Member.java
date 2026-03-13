package com.example;

public class Member {

    private String username;
    private String password;
    private String role;
    private int point;

    public Member(String username,String password,String role){
        this.username = username;
        this.password = password;
        this.role = role;
        this.point = 0;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    public int getPoint(){
        return point;
    }

    public void addPoint(int p){
        point += p;
    }
}