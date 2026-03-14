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
        if (total%10 == 0) {
            point= total%10;
        }
        else if(total%10 >= 5){
                int p=total%10;
                total=total-p;
                point=(total%10)+1;
        }
        else if(total%10 < 5){
                int p=total%10;
                total=total-p;
                point=total%10;
        }
        return point;
    }

    public int getTotal() {
        return total;
    }

    public String getOrder() {
        return order;
    }
}
