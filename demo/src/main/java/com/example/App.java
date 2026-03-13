package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Cafe Order");
        title.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");

        ComboBox<String> menuBox = new ComboBox<>();
        menuBox.getItems().addAll("Mocha","Latte","Americano");

        ComboBox<String> sizeBox = new ComboBox<>();
        sizeBox.getItems().addAll("S","M","L");

        ComboBox<String> sweetBox = new ComboBox<>();
        sweetBox.getItems().addAll("0%","50%","100%");

        Button addBtn = new Button("Add Order");

        TextArea orderArea = new TextArea();
        orderArea.setPrefHeight(200);

        addBtn.setOnAction(e -> {
            String menu = menuBox.getValue();
            String size = sizeBox.getValue();
            String sweet = sweetBox.getValue();

            int price = Size.getPrice(size);

            orderArea.appendText(menu + "  " + size + "  " + sweet + "  " + price + "\n");
        });

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Menu"),0,0);
        form.add(menuBox,1,0);

        form.add(new Label("Size"),0,1);
        form.add(sizeBox,1,1);

        form.add(new Label("Sweet"),0,2);
        form.add(sweetBox,1,2);

        Image img = new Image(getClass().getResourceAsStream("/Coffee.png"));
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);   

        VBox root = new VBox(15,imageView,title,form,addBtn,orderArea);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root,400,400);

        stage.setTitle("Cafe System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}