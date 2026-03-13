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
        orderArea.setPrefHeight(150);

        addBtn.setOnAction(e -> {
            String menu = menuBox.getValue();
            String size = sizeBox.getValue();
            String sweet = sweetBox.getValue();
            int qty = Integer.parseInt(quantityBox.getValue());

            int price = Size.getPrice(size) + 50; // base drink price
            int subtotal = price * qty;

            total += subtotal;

            orderArea.appendText(menu + "  " + size + "  " + sweet + "  x" + qty + "  = " + subtotal + " bath\n");
            totalLabel.setText("Total : " + total + " bath");
        });

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10);
        form.add(new Label("Menu"),0,0); form.add(menuBox,1,0);
        form.add(new Label("Size"),0,1); form.add(sizeBox,1,1);
        form.add(new Label("Sweet"),0,2); form.add(sweetBox,1,2);
        form.add(new Label("Quantity"),0,3); form.add(quantityBox,1,3);

        // --- ส่วน Gallery ด้วย For Loop ---
        GridPane gallery = new GridPane();
        gallery.setHgap(12); gallery.setVgap(12);
        gallery.setPadding(new Insets(10));

        // ข้อมูล Drinks
        String[] drinkNames = {"Americano", "Espresso", "Cappuccino", "Mocha", "Latte", 
                               "Green Tea", "Thai Tea", "Milk Tea", "Chocolate", "Lemon Tea"};
        String[] drinkPaths = {"/americano.PNG", "/espresso.PNG", "/cappuccino.PNG", "/mocca.PNG", "/latte.PNG", 
                               "/greentea.PNG", "/thaitea.PNG", "/milktea.PNG", "/chocolate.PNG", "/lemontea.PNG"};

                               
        Label cat1 = new Label("      ☕ Drinks");
        cat1.getStyleClass().add("Drinking");
        // เพิ่มขนาดตัวอักษรเป็น 20px และทำให้เป็นตัวหนา (Bold)
        cat1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        gallery.add(cat1, 2, 0, 5, 1);

        for (int i = 0; i < drinkNames.length; i++) {
            gallery.add(createBeverageCard(drinkNames[i], drinkPaths[i]), i % 5, (i / 5) + 1);
        }

        // ข้อมูล Desserts
        String[] dessertNames = {"Butter Cake", "Chocolate Cake", "Cookie", "Crepes", "Croissant"};
        String[] dessertPaths = {"/buttercake.PNG", "/chocolatecake.PNG", "/cookie.PNG", "/crepecake.PNG", "/croissant.PNG"};

        Label cat2 = new Label("      🍰 Dessert");
        cat2.getStyleClass().add("Dessert");
        cat2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        gallery.add(cat2, 2, 3, 5, 1); 

        for (int i = 0; i < dessertNames.length; i++) {
            gallery.add(createBeverageCard(dessertNames[i], dessertPaths[i]), i % 5, 4);
        }

        // --- ประกอบร่าง Layout ---
        VBox root = new VBox(15, title, gallery, form, addBtn, orderArea, totalLabel);
        root.setPadding(new Insets(20));
<<<<<<< HEAD
        
        // ใส่ ScrollPane เพราะเมนูเยอะขึ้นจนอาจล้นจอ
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        Scene scene1 = new Scene(scrollPane, 950, 850);
        
        // ตรวจสอบไฟล์ CSS ก่อนโหลด (ถ้าไม่มีให้คอมเมนต์บรรทัดข้างล่างนี้ออก)
        try {
            scene1.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        } catch (Exception ex) {
            System.err.println("CSS file not found, skipping styles.");
        }
=======

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        Scene scene1 = new Scene(scrollPane, 900, 750);

        scene1.getStylesheets().add(
            getClass().getResource("/style.css").toExternalForm()
        );
>>>>>>> 8f04ae702f4d6cb5f6b70461e8a32fe700890aaf

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
        imageView.setFitHeight(120); // ปรับขนาดรูปให้เล็กลงนิดนึงเพื่อให้เห็นภาพรวมง่ายขึ้น
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);

        Label label = new Label(name);
        label.setStyle("-fx-font-size:12px; -fx-font-weight:bold;");

        VBox card = new VBox(5, imageView, label);
        card.setPadding(new Insets(10));
        card.setAlignment(javafx.geometry.Pos.CENTER);
        card.getStyleClass().add("card");

        // มัดรวม Animation
        card.setOnMouseEntered(e -> {
            card.setScaleX(1.05);
            card.setScaleY(1.05);
        });
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