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
    private int total = 0;
    @Override
    public void start(Stage stage) {

        Label title = new Label("☕ PUNPUN Cafe");
        title.getStyleClass().add("title");

        ComboBox<String> menuBox = new ComboBox<>();
        menuBox.getItems().addAll(
            "Americano","Espresso","Cappuccino","Mocha","Latte",
            "Green Tea","Thai Tea","Milk Tea","Chocolate","Lemon Tea",
            "Butter Cake","Chocolate Cake","Cookie","Crepes","Croissant"
        );
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
        Label totalLabel = new Label("Total : 0");
        totalLabel.getStyleClass().add("title");
        orderArea.setPrefHeight(200);

        addBtn.setOnAction(e -> {

    String menu = menuBox.getValue();
    String size = sizeBox.getValue();
    String sweet = sweetBox.getValue();
    int qty = Integer.parseInt(quantityBox.getValue());

    int price = Size.getPrice(size) + 50; // base drink price
    int subtotal = price * qty;

    total += subtotal;

    orderArea.appendText(menu + "  " + size + "  " + sweet + "  x" + qty + "  = " + subtotal + "\n");

    totalLabel.setText("Total : " + total + " บาท");
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

    //row1
        gallery.add(createBeverageCard("Americano", "/americano.PNG"), 0, 0);
        gallery.add(createBeverageCard("Espresso", "/espresso.PNG"), 1, 0);
        gallery.add(createBeverageCard("Cappuccino", "/cappuccino.PNG"), 2, 0);
        gallery.add(createBeverageCard("Mocha", "/mocca.PNG"), 3, 0);
        gallery.add(createBeverageCard("Latte", "/latte.PNG"), 4, 0);
    // row2
        gallery.add(createBeverageCard("Green Tea", "/greentea.PNG"), 0, 1);
        gallery.add(createBeverageCard("Thai Tea", "/thaitea.PNG"), 1, 1);
        gallery.add(createBeverageCard("Milk Tea", "/milktea.PNG"), 2, 1);
        gallery.add(createBeverageCard("Chocolate", "/chocolate.PNG"), 3, 1);
        gallery.add(createBeverageCard("Lemon Tea", "/lemontea.PNG"), 4, 1);
    
        //row 3 dessert
        gallery.add(createBeverageCard("Butter Cake", "/buttercake.PNG"), 0, 2);
        gallery.add(createBeverageCard("Chocolate Cake", "/chocolatecake.PNG"), 1, 2);
        gallery.add(createBeverageCard("Cookie", "/cookie.PNG"), 2, 2);
        gallery.add(createBeverageCard("Crepes", "/crepecake.PNG"), 3, 2);
        gallery.add(createBeverageCard("Croissant", "/croissant.PNG"), 4, 2);

        VBox root = new VBox(15, title, gallery, form, addBtn, orderArea, totalLabel);
        root.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        Scene scene1 = new Scene(scrollPane, 900, 750);

        scene1.getStylesheets().add(
            getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("PUNPUN Cafe");
        stage.setScene(scene1);
        stage.show();
    }

    private VBox createBeverageCard(String name, String resourcePath) {

    Image image;

    java.io.InputStream stream = getClass().getResourceAsStream(resourcePath);
    if (stream == null) {
        image = new Image("https://via.placeholder.com/180");
    } else {
        image = new Image(stream);
    }

    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(140);
    imageView.setFitWidth(140);
    imageView.setPreserveRatio(true);

    Label label = new Label(name);
    label.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");

    VBox card = new VBox(7, imageView, label);
    card.setPadding(new Insets(15));
    card.setAlignment(javafx.geometry.Pos.CENTER);

    card.getStyleClass().add("card");

    card.setOnMouseEntered(e -> card.setScaleX(1.05));
    card.setOnMouseEntered(e -> card.setScaleY(1.05));

    card.setOnMouseExited(e -> {
        card.setScaleX(1);
        card.setScaleY(1);
    });

    return card;
}    
public static void main(String[] args){
        launch();
    }
}