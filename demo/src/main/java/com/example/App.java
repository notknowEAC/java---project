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
        menuBox.getItems().addAll("Mocha","Latte","Americano","Lemon Tea","Chocolate");
        menuBox.getSelectionModel().selectFirst();

        ComboBox<String> sizeBox = new ComboBox<>();
        sizeBox.getItems().addAll("S","M","L");
        sizeBox.getSelectionModel().selectFirst();

        ComboBox<String> sweetBox = new ComboBox<>();
        sweetBox.getItems().addAll("0%","50%","100%");
        sweetBox.getSelectionModel().selectFirst();

        ComboBox<String> quantityBox = new ComboBox<>();
        quantityBox.getItems().addAll("1","2","3","4","5");
        quantityBox.getSelectionModel().selectFirst();

        Button addBtn = new Button("Add Order");

        TextArea orderArea = new TextArea();
        orderArea.setPrefHeight(200);

        addBtn.setOnAction(e -> {
            String menu = menuBox.getValue();
            String size = sizeBox.getValue();
            String sweet = sweetBox.getValue();
            String qty = quantityBox.getValue();

            int price = Size.getPrice(size);

            orderArea.appendText(menu + "  " + size + "  " + sweet + "  " + qty + "  " + price + "\n");
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

        form.add(new Label("Quantity"),0,3);
        form.add(quantityBox,1,3);

        GridPane gallery = new GridPane();
        gallery.setHgap(12);
        gallery.setVgap(12);
        gallery.setPadding(new Insets(10));

        // top row: five main drinks (sixth cell left empty)
        gallery.add(createBeverageCard("Mocha", "/mocca.PNG"), 0, 0);
        gallery.add(createBeverageCard("Latte", "/latte.PNG"), 1, 0);
        gallery.add(createBeverageCard("Americano", "/americano.PNG"), 2, 0);
        gallery.add(createBeverageCard("Green Tea", "/greentea.PNG"), 3, 0);
        gallery.add(createBeverageCard("Thai Tea", "/thaitea.PNG"), 4, 0);
        // bottom row: the requested three beverages
        gallery.add(createBeverageCard("Milk Tea", "/milktea.PNG"), 0, 1);
        gallery.add(createBeverageCard("Chocolate", "/chocolate.PNG"), 1, 1);
        gallery.add(createBeverageCard("Lemon Tea", "/lemontea.PNG"), 2, 1);


        VBox root = new VBox(15, title, gallery, form, addBtn, orderArea);
        root.setPadding(new Insets(20));
        Scene scene1 = new Scene(root, 600, 700);
 


        stage.setTitle("PUNPUN Cafe");
        stage.setScene(scene1);
        stage.show();
    }

    private VBox createBeverageCard(String name, String resourcePath) {

        Image image;

        java.io.InputStream stream = getClass().getResourceAsStream(resourcePath);
        if (stream == null) {
            System.err.println("Resource not found: " + resourcePath);
            image = new Image("https://via.placeholder.com/180");
        } else {
            try {
                image = new Image(stream);
            } catch (Exception e) {
                System.err.println("Failed to load image " + resourcePath + ": " + e);
                image = new Image("https://via.placeholder.com/180");
            }
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(160);
        imageView.setFitWidth(160);
        imageView.setPreserveRatio(true);

        Label label = new Label(name);
        label.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");

        VBox card = new VBox(7, imageView, label);
        card.setPadding(new Insets(5));
        card.setStyle("-fx-border-color: #ccc; -fx-border-radius: 6; -fx-background-radius: 6; -fx-background-color: #f9f9f9;");
        card.setMinWidth(200);
        card.setMaxWidth(200);
        card.setAlignment(javafx.geometry.Pos.CENTER);

    return card;
}
    public static void main(String[] args){
        launch();
    }
}