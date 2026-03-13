package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OwnerDashboard {

    public static void showDashboard(){

        Stage stage = new Stage();

        int totalSales = JSONDatabase.getTotalSales();
        int profit = totalSales * 40 / 100; // กำไร 40%

        Label title = new Label("☕ Owner Dashboard");

        Label sales = new Label("Total Sales : " + totalSales + " bath");

        Label profitLabel = new Label("Profit : " + profit + " bath");

        VBox root = new VBox(20,title,sales,profitLabel);
        root.setStyle("-fx-padding:20; -fx-alignment:center;");

        Scene scene = new Scene(root,400,300);

        stage.setScene(scene);
        stage.setTitle("Dashboard");

        stage.show();
    }
}