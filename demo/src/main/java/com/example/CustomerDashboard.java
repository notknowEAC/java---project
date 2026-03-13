package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerDashboard {

    public static void showDashboard(Member member){

        Stage stage = new Stage();

        Label title = new Label("Customer Dashboard");

        Label user = new Label("Username : " + member.getUsername());

        Label point = new Label("Point : " + member.getPoint());

        VBox root = new VBox(20,title,user,point);

        Scene scene = new Scene(root,400,300);

        stage.setScene(scene);
        stage.show();
    }
}