package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OwnerDashboard {

    public static void showDashboard() {

        Stage stage = new Stage();

        Label title = new Label("Owner Dashboard");

        Label report = new Label("Order Report / Sales Data");

        VBox root = new VBox(20, title, report);

        Scene scene = new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();

    }

}