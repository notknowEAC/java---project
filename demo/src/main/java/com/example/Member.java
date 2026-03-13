package com.example;
public class Member {

    private String username;
    private String password;
    private int point;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
        this.point = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int p) {
        point += p;
    }

}