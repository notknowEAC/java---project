package com.example;

public class Receipt {
    
    private String order;
    private int total;
    private int point;

    public Receipt(String order, int total, int point) {
        this.order = order;
        this.total = total;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public int getTotal() {
        return total;
    }

    public String getOrder() {
        return order;
    }
}
