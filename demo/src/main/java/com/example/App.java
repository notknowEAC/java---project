package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.property.*;

public class App extends Application {

    private int total = 0;
    private Member currentMember;

    @Override
    public void start(Stage stage) {

        Label title = new Label("☕ PUNPUN Cafe");
        title.getStyleClass().add("title");

        Button loginBtn = new Button("Login");

    loginBtn.setOnAction(e -> {
        LoginPage.show(new Stage());
    });
        ComboBox<String> menuBox = new ComboBox<>();
        menuBox.getItems().addAll(
            "Americano","Espresso","Cappuccino","Mocha","Latte",
            "Matcha","Thai Tea","Milk Tea","Chocolate","Lemon Tea",
            "Butter Cake","Chocolate Cake","Cookie","Crepes","Croissant",
            "Matcha Custard","Macaron","Pudding","Pan Cake","Ice Cream"
        );
        menuBox.getSelectionModel().selectFirst();

        ComboBox<String> sizeBox = new ComboBox<>();
        sizeBox.getItems().addAll("S","M","L");
        sizeBox.getSelectionModel().selectFirst();

        ComboBox<String> sweetBox = new ComboBox<>();
        sweetBox.getItems().addAll("0%","50%","100%");
        sweetBox.getSelectionModel().selectFirst();

        javafx.beans.property.IntegerProperty quantity = new javafx.beans.property.SimpleIntegerProperty(1);
        Label quantityLabel = new Label();
        quantityLabel.textProperty().bind(quantity.asString());
        quantityLabel.setStyle("-fx-font-size: 16px; -fx-padding: 0;");

        Button minusQtyBtn = new Button("-");
        minusQtyBtn.setOnAction(e -> {
            if (quantity.get() > 1) {
                quantity.set(quantity.get() - 1);
                
            }
        });
        minusQtyBtn.setPrefWidth(28);
        minusQtyBtn.setMinWidth(28);
        minusQtyBtn.setMaxWidth(28);
        minusQtyBtn.setPrefHeight(28);
        minusQtyBtn.setMinHeight(28);
        minusQtyBtn.setMaxHeight(28);
        minusQtyBtn.setAlignment(javafx.geometry.Pos.CENTER);
        minusQtyBtn.setStyle("-fx-font-size: 14px; -fx-padding: 0;");

        Button plusQtyBtn = new Button("+");
        plusQtyBtn.setOnAction(e -> {
            if (quantity.get() < 99) {
                quantity.set(quantity.get() + 1);
            }
        });
        plusQtyBtn.setPrefWidth(28);
        plusQtyBtn.setMinWidth(28);
        plusQtyBtn.setMaxWidth(28);
        plusQtyBtn.setPrefHeight(28);
        plusQtyBtn.setMinHeight(28);
        plusQtyBtn.setMaxHeight(28);
        plusQtyBtn.setAlignment(javafx.geometry.Pos.CENTER);
        plusQtyBtn.setStyle("-fx-font-size: 14px; -fx-padding: 0;");

        HBox quantitySelector = new HBox(6, minusQtyBtn, quantityLabel, plusQtyBtn);
        quantitySelector.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        Button addBtn = new Button("Add Order");

        TextArea orderArea = new TextArea();
        orderArea.setPrefSize(760,200);
        orderArea.setPrefSize(760,200);
        orderArea.setMinSize(760, 200);
        orderArea.setMaxSize(760, 200);
        orderArea.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(orderArea, Priority.ALWAYS);
        orderArea.getStyleClass().add("order-list");

        Label totalLabel = new Label("Total : 0");
        totalLabel.getStyleClass().add("title");

        addBtn.setOnAction(e -> {

    String menu = menuBox.getValue();
    String size = sizeBox.getValue();
    String sweet = sweetBox.getValue();
    int qty = quantity.get();

    int price = Size.getPrice(size) + 60;
    int subtotal = price * qty;

    total += subtotal;

    if(LoginPage.currentMember != null){

        int point = PointSystem.calculatePoint(subtotal);

        LoginPage.currentMember.addPoint(point);

    }

    JSONDatabase.saveOrder(menu, subtotal);

    orderArea.appendText(
            menu + " " + size + " " + sweet +
            " x" + qty + " = " + subtotal + " bath\n"
    );

    totalLabel.setText("Total : " + total + " bath");
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
        form.add(quantitySelector,1,3);

        GridPane gallery = new GridPane();
        gallery.setHgap(12);
        gallery.setVgap(12);
        gallery.setPadding(new Insets(10));

        String[] drinkNames = {
            "Americano","Espresso","Cappuccino","Mocha","Latte",
            "Matcha","Thai Tea","Milk Tea","Chocolate","Lemon Tea"
        };

        String[] drinkPaths = {
            "/americano.PNG","/espresso.PNG","/cappuccino.PNG","/mocca.PNG","/latte.PNG",
            "/matcha.PNG","/thaitea.PNG","/milktea.PNG","/chocolate.PNG","/lemontea.PNG"
        };

        Label cat1 = new Label("☕ Drinks");
        cat1.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");
        gallery.add(cat1, 2, 0, 5, 1);

        for (int i = 0; i < drinkNames.length; i++) {
            gallery.add(
                createBeverageCard(drinkNames[i], drinkPaths[i], menuBox, sizeBox, sweetBox, quantity),
                i % 5,
                (i / 5) + 1
            );
        }

        String[] dessertNames = {
            "Butter Cake","Chocolate Cake","Cookie","Crepes","Croissant",
            "Matcha Custard","Macaron","Pudding","Pancake","Ice Cream"
        };

        String[] dessertPaths = {
            "/buttercake.PNG","/chocolatecake.PNG","/cookie.PNG","/crepecake.PNG","/croissant.PNG",
            "/matchacustard.PNG","/macaron.PNG","/pudding.PNG","/pancake.PNG","/icecream.PNG"
        };

        Label cat2 = new Label("🍰 Dessert");
        cat2.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");
        gallery.add(cat2, 2, 3, 5, 1);

        for (int i = 0; i < dessertNames.length; i++) {
            gallery.add(
                createBeverageCard(dessertNames[i], dessertPaths[i], menuBox, sizeBox, sweetBox, quantity),
                i % 5,
                (i / 5) + 4
            );
        }

        Button confirmBtn = new Button("Confirm Order");
        confirmBtn.setOnAction(e -> {
            Receipt receipt = new Receipt(
                    orderArea.getText(),
                    total,
                    LoginPage.currentMember != null ? LoginPage.currentMember.getPoint() : 0);

            Stage receiptStage = new Stage();

            Label receipttitle = new Label("☕ PUNPUN Cafe\n" +"Receipt");

            receipttitle.setTextAlignment(TextAlignment.CENTER);
            receipttitle.setAlignment(Pos.CENTER);
            receipttitle.setMaxWidth(Double.MAX_VALUE);
            receipttitle.setStyle("-fx-font-size:22px; -fx-font-weight:bold;");
            
            Label information = new Label("123/45, College of Computing Building, Khonkaen\n      Tel: 088-xxx-xxxx, www.punpun-cafe.com");
            information.setTextAlignment(TextAlignment.LEFT);
            information.setAlignment(Pos.CENTER_LEFT);
            information.setStyle("-fx-font-size:14px;");

            TextArea detailText = new TextArea();
            detailText.setText(
                    "Order Detail\n" +
                            "-------------------\n" +
                            orderArea.getText() +
                            "\nTotal : " + total +
                            "\nPoint : " + receipt.getPoint());
            detailText.setStyle("-fx-font-family: monospace; -fx-font-size:18px;");
            detailText.setEditable(false);
            detailText.setWrapText(true);
            detailText.setPrefWidth(440);
            detailText.setPrefHeight(260);
            detailText.setMaxHeight(Double.MAX_VALUE);
            VBox.setVgrow(detailText, Priority.ALWAYS);

            VBox layout = new VBox(10, receipttitle,information, detailText);
            layout.setAlignment(javafx.geometry.Pos.TOP_CENTER);
            layout.setStyle("-fx-padding:20;");

            StackPane root = new StackPane(layout);
            root.setAlignment(javafx.geometry.Pos.CENTER);

            Scene scene = new Scene(root, 480, 520);

            receiptStage.setTitle("Receipt");
            receiptStage.setScene(scene);
            receiptStage.show();


            orderArea.clear();
            total = 0;
            totalLabel.setText("Total : 0");
            quantity.set(1);
        });
        VBox orderCard = new VBox(10,
                new Label("Order List"),
                orderArea,
                totalLabel,
                confirmBtn);
        orderCard.setStyle("-fx-font-size: 22px;");
        orderCard.getStyleClass().add("card");

        VBox topBar = new VBox(10, title, loginBtn);
        topBar.setAlignment(javafx.geometry.Pos.TOP_LEFT);

        VBox mainContent = new VBox(20, topBar, gallery, form, addBtn, orderCard);
        mainContent.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        mainContent.setMaxWidth(820);

        StackPane centered = new StackPane(mainContent);
        centered.setPadding(new Insets(20));
        StackPane.setAlignment(mainContent, javafx.geometry.Pos.TOP_CENTER);

        VBox root = new VBox(centered);
        root.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 900, 750);

        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("PUNPUN Cafe");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createBeverageCard(
            String name,
            String resourcePath,
            ComboBox<String> menuBox,
            ComboBox<String> sizeBox,
            ComboBox<String> sweetBox,
            javafx.beans.property.IntegerProperty quantity
    ) {

        Image image;

        java.io.InputStream stream = getClass().getResourceAsStream(resourcePath);

        if (stream == null) {
            image = new Image("https://via.placeholder.com/180");
        } else {
            image = new Image(stream);
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(160);
        imageView.setFitWidth(160);
        imageView.setPreserveRatio(true);

        Label label = new Label(name);
        label.setStyle("-fx-font-size:14px; -fx-font-weight:bold;");

        VBox card = new VBox(5, imageView, label);
        card.setPadding(new Insets(10));
        card.setAlignment(javafx.geometry.Pos.CENTER);
        card.getStyleClass().add("card");

        card.setOnMouseEntered(e -> {
            card.setScaleX(1.05);
            card.setScaleY(1.05);
        });

        card.setOnMouseExited(e -> {
            card.setScaleX(1);
            card.setScaleY(1);
        });

        card.setOnMouseClicked(e -> {
            menuBox.setValue(name);
            sizeBox.getSelectionModel().selectFirst();
            sweetBox.getSelectionModel().selectFirst();
            quantity.set(1);
        });

        return card;
    }

    public static void main(String[] args){
        launch();
    }
}