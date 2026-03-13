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
        menuBox.getSelectionModel().selectFirst();

        ComboBox<String> sizeBox = new ComboBox<>();
        sizeBox.getItems().addAll("S","M","L");
        sizeBox.getSelectionModel().selectFirst();

        ComboBox<String> sweetBox = new ComboBox<>();
        sweetBox.getItems().addAll("0%","50%","100%");
        sweetBox.getSelectionModel().selectFirst();

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

        HBox gallery = new HBox(12,
            createBeverageCard("Mocha", "/mocca.PNG"),
            createBeverageCard("Latte", "/latte.PNG"),
            createBeverageCard("Americano", "/americano.PNG")
        );
        gallery.setPadding(new Insets(10));

        VBox root = new VBox(15, title, gallery, form, addBtn, orderArea);
        root.setPadding(new Insets(20));
        Scene scene1 = new Scene(root, 520, 540);
 


        stage.setTitle("PUNPUN Cafe");
        stage.setScene(scene1);
        stage.show();
    }

    private VBox createBeverageCard(String name, String resourcePath) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(resourcePath)));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label label = new Label(name);
        label.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");

        VBox card = new VBox(6, imageView, label);
        card.setPadding(new Insets(5));
        card.setStyle("-fx-border-color: #ccc; -fx-border-radius: 6; -fx-background-radius: 6; -fx-background-color: #f9f9f9;");
        card.setMinWidth(120);
        card.setMaxWidth(120);
        card.setAlignment(javafx.geometry.Pos.CENTER);
        return card;
    }

    public static void main(String[] args){
        launch();
    }
}