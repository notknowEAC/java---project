package com.example;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerPage {

    public static void show(Member member){

        Stage stage = new Stage();

        Label title = new Label("Customer Page");

        Label pointLabel = new Label("Point : " + member.getPoint());

        Button buy = new Button("Buy Coffee (100 bath)");

        buy.setOnAction(e->{

            int point = PointSystem.calculatePoint(100);
            member.addPoint(point);

            pointLabel.setText("Point : " + member.getPoint());

            JSONDatabase.saveOrder("Coffee",100);

        });

        VBox root = new VBox(20,title,pointLabel,buy);

        Scene scene = new Scene(root,400,300);

        stage.setScene(scene);
        stage.show();
    }
}