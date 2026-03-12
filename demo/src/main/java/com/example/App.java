package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Button btn = new Button("Hello JavaFX");

        Scene scene = new Scene(btn, 400, 300);

        stage.setTitle("My JavaFX App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}