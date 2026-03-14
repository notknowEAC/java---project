package com.example;

import com.example.auth.LoginPage;
import com.example.database.JSONDatabase;
import com.example.model.Member;
import com.example.service.PointSystem;
import com.example.service.Size;
import com.example.util.Receipt;

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

        Label usernameLabel = new Label("User: Guest");
        usernameLabel.getStyleClass().add("username-label");

        if(LoginPage.currentMember != null){
            usernameLabel.setText("User: " + LoginPage.currentMember.getUsername());
        }

        Button loginBtn = new Button("Login");

    loginBtn.setOnAction(e -> {
        Stage loginStage = new Stage();
        loginStage.setOnHidden(event -> {
            currentMember = LoginPage.currentMember;
            if(currentMember != null){
                usernameLabel.setText("User: " + currentMember.getUsername());
            }else{
                usernameLabel.setText("User: Guest");
            }
        });
        LoginPage.show(loginStage);
    });
        ComboBox<String> menuBox = new ComboBox<>();
        menuBox.getItems().addAll(
            "Americano","Espresso","Cappuccino","Mocha","Latte",
            "Matcha","Thai Tea","Milk Tea","Chocolate","Lemon Tea",
            "Butter Cake","Chocolate Cake","Cookie","Crepes","Croissant",
            "Matcha Custard","Macaron","Pudding","Pancake","Ice Cream"
        );
        menuBox.getSelectionModel().selectFirst();
        menuBox.getStyleClass().add("combo-box");

        ToggleButton sizeS = new ToggleButton("S");
        ToggleButton sizeM = new ToggleButton("M");
        ToggleButton sizeL = new ToggleButton("L");

        ToggleGroup sizeGroup = new ToggleGroup();
        sizeS.setToggleGroup(sizeGroup);
        sizeM.setToggleGroup(sizeGroup);
        sizeL.setToggleGroup(sizeGroup);

        sizeM.setSelected(true);

        HBox sizeBox = new HBox(10,sizeS,sizeM,sizeL);
        sizeBox.setAlignment(Pos.CENTER_LEFT);

        ToggleButton sweet0 = new ToggleButton("0%");
        ToggleButton sweet50 = new ToggleButton("50%");
        ToggleButton sweet100 = new ToggleButton("100%");

        ToggleGroup sweetGroup = new ToggleGroup();
        sweet0.setToggleGroup(sweetGroup);
        sweet50.setToggleGroup(sweetGroup);
        sweet100.setToggleGroup(sweetGroup);

        sweet50.setSelected(true);

        HBox sweetBox = new HBox(10,sweet0,sweet50,sweet100);
        sweetBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.valueProperty().addListener((obs, oldMenu, newMenu) -> {
            boolean drinkMenu = isDrinkMenu(newMenu);
            sizeBox.setDisable(!drinkMenu);
            sweetBox.setDisable(!drinkMenu);

            if(!drinkMenu){
                sizeS.setSelected(true);
                sweet0.setSelected(true);
            }
        });

        boolean initialDrinkMenu = isDrinkMenu(menuBox.getValue());
        sizeBox.setDisable(!initialDrinkMenu);
        sweetBox.setDisable(!initialDrinkMenu);

        javafx.beans.property.IntegerProperty quantity = new javafx.beans.property.SimpleIntegerProperty(1);
        Label quantityLabel = new Label();
        quantityLabel.textProperty().bind(quantity.asString());
        quantityLabel.getStyleClass().add("quantity-label");

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
        minusQtyBtn.getStyleClass().add("qty-button");

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
        plusQtyBtn.getStyleClass().add("qty-button");

        HBox quantitySelector = new HBox(6, minusQtyBtn, quantityLabel, plusQtyBtn);
        quantitySelector.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        Button addBtn = new Button("Add Order");
        addBtn.getStyleClass().add("button");

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
    ToggleButton sizeSelected = (ToggleButton) sizeGroup.getSelectedToggle();
    String size = sizeSelected.getText();

    ToggleButton sweetSelected = (ToggleButton) sweetGroup.getSelectedToggle();
    String sweet = sweetSelected.getText();
    int qty = quantity.get();
    boolean drinkMenu = isDrinkMenu(menu);

    int basePrice = com.example.model.Menu.getBasePrice(menu);
    int price = drinkMenu ? basePrice + Size.getPrice(size) : basePrice;
    int subtotal = price * qty;

    total += subtotal;

    JSONDatabase.saveOrder(menu, subtotal);

        if(drinkMenu){
       orderArea.appendText(
        String.format("%-25s %7d\n",
                menu + " " + size + " " + sweet + " x" + qty,
                subtotal
        )
);
        }else{
        orderArea.appendText(
    String.format("%-25s %5d\n",
        menu + " x" + qty,
        subtotal
    )
);
        }

    totalLabel.setText("Total : " + total + " bath");
});

        GridPane form = new GridPane();
form.getStyleClass().add("selector-box");
form.setHgap(10);
form.setVgap(10);

Label menuLabel = new Label("Menu");
menuLabel.getStyleClass().add("selector-label");

Label sizeLabel = new Label("Size");
sizeLabel.getStyleClass().add("selector-label");

Label sweetLabel = new Label("Sweet");
sweetLabel.getStyleClass().add("selector-label");

Label quantityText = new Label("Quantity");
quantityText.getStyleClass().add("selector-label");

form.add(menuLabel,0,0);
form.add(menuBox,1,0);

form.add(sizeLabel,0,1);
form.add(sizeBox,1,1);

form.add(sweetLabel,0,2);
form.add(sweetBox,1,2);

form.add(quantityText,0,3);
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
        cat1.getStyleClass().add("category-label");
        gallery.add(cat1, 2, 0, 5, 1);

        for (int i = 0; i < drinkNames.length; i++) {
            gallery.add(
                createBeverageCard(drinkNames[i], drinkPaths[i], menuBox, sizeM, sweet50, quantity),
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
        cat2.getStyleClass().add("category-label");
        gallery.add(cat2, 2, 3, 5, 1);

        for (int i = 0; i < dessertNames.length; i++) {
            gallery.add(
                createBeverageCard(dessertNames[i], dessertPaths[i], menuBox, sizeM, sweet50, quantity),
                i % 5,
                (i / 5) + 4
            );
        }

        Button confirmBtn = new Button("Confirm Order");
        confirmBtn.getStyleClass().add("button");
        confirmBtn.setOnAction(e -> {
            int pointThisTime = 0;
            int totalPoint = 0;

            if(LoginPage.currentMember != null){
                pointThisTime = PointSystem.calculatePoint(total);
                LoginPage.currentMember.addPoint(pointThisTime);
                totalPoint = LoginPage.currentMember.getPoint();
            }

            Receipt receipt = new Receipt(
                    orderArea.getText(),
                    total,
                    pointThisTime);

            Stage receiptStage = new Stage();

            Label cafeTitle = new Label("☕ PUNPUN Cafe");
            cafeTitle.setTextAlignment(TextAlignment.CENTER);
            cafeTitle.setAlignment(Pos.CENTER);
            cafeTitle.setMaxWidth(Double.MAX_VALUE);
            cafeTitle.getStyleClass().add("receipt-title");

            Label receiptSubtitle = new Label("Receipt");
            receiptSubtitle.setTextAlignment(TextAlignment.CENTER);
            receiptSubtitle.setAlignment(Pos.CENTER);
            receiptSubtitle.setMaxWidth(Double.MAX_VALUE);
            receiptSubtitle.getStyleClass().add("receipt-subtitle");
            
            Label information = new Label("123/45, College of Computing Building, Khonkaen");
            information.setTextAlignment(TextAlignment.LEFT);
            information.setAlignment(Pos.CENTER_LEFT);
            information.getStyleClass().add("receipt-info");
            information.setMaxWidth(Double.MAX_VALUE);

            Label contactInfo = new Label("Tel: 088-xxx-xxxx   |   www.punpun-cafe.com");
            contactInfo.setTextAlignment(TextAlignment.LEFT);
            contactInfo.setAlignment(Pos.CENTER_LEFT);
            contactInfo.getStyleClass().add("receipt-contact");
            contactInfo.setMaxWidth(Double.MAX_VALUE);

            TextArea detailText = new TextArea();
String line = "─".repeat(33);

detailText.setText(
        "Order Detail\n\n" +
        orderArea.getText() +
        line + "\n" +
        String.format("%-25s %7d\n", "TOTAL", total) +
        line
);
detailText.setEditable(false);
detailText.setWrapText(true);
detailText.setFocusTraversable(false);
            detailText.getStyleClass().add("receipt-detail");
            detailText.setEditable(false);
            detailText.setWrapText(true);
            detailText.setPrefWidth(440);
            detailText.setPrefHeight(260);
            detailText.setMaxHeight(Double.MAX_VALUE);
            VBox.setVgrow(detailText, Priority.ALWAYS);

                String customerName = LoginPage.currentMember != null
                    ? LoginPage.currentMember.getUsername()
                    : "Guest";
                Label customerLabel = new Label("Customer: " + customerName);
                customerLabel.getStyleClass().add("receipt-customer");
                customerLabel.setAlignment(Pos.CENTER_LEFT);
                customerLabel.setMaxWidth(Double.MAX_VALUE);

                    String purchaseDateTime = java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                    Label dateTimeLabel = new  Label("Date/Time: " + purchaseDateTime);
                    dateTimeLabel.getStyleClass().add("receipt-datetime");
                    dateTimeLabel.setAlignment(Pos.CENTER_LEFT);
                    dateTimeLabel.setMaxWidth(Double.MAX_VALUE);

                    Label pointThisTimeLabel = new Label("Point : " + receipt.getPoint());
                    pointThisTimeLabel.getStyleClass().add("receipt-point");
                    pointThisTimeLabel.setAlignment(Pos.CENTER_LEFT);
                    pointThisTimeLabel.setMaxWidth(Double.MAX_VALUE);

                    Label totalPointLabel = new Label("Total point: " + totalPoint);
                    totalPointLabel.getStyleClass().add("receipt-point");
                    totalPointLabel.setAlignment(Pos.CENTER_LEFT);
                    totalPointLabel.setMaxWidth(Double.MAX_VALUE);
            Label totalPriceLabel = new Label("TOTAL : " + total + " Bath");
totalPriceLabel.getStyleClass().add("receipt-total");
totalPriceLabel.setAlignment(Pos.CENTER);
totalPriceLabel.setMaxWidth(Double.MAX_VALUE);
            Label thankyou = new Label("Thank you for your order!");
            thankyou.getStyleClass().add("thankyou");
            thankyou.setTextAlignment(TextAlignment.CENTER);
            thankyou.setAlignment(Pos.CENTER);
VBox layout = new VBox(
        12,
        cafeTitle,
        receiptSubtitle,
        information,
        contactInfo,
        new Separator(),
        detailText,
        new Separator(),
        totalPriceLabel,
        new Separator(),
        customerLabel,
        dateTimeLabel,
        pointThisTimeLabel,
        totalPointLabel,
        thankyou
);
            
            layout.setAlignment(javafx.geometry.Pos.TOP_CENTER);
            layout.getStyleClass().addAll("receipt-layout", "receipt-section");

            StackPane root = new StackPane(layout);
            root.getStyleClass().add("receipt-root");
            root.setAlignment(javafx.geometry.Pos.CENTER);

            Scene scene = new Scene(root, 560, 620);

scene.getStylesheets().add(
        getClass().getResource("/style.css").toExternalForm()
);

            receiptStage.setTitle("Receipt");
            receiptStage.setScene(scene);
            receiptStage.show();


            orderArea.clear();
            total = 0;
            totalLabel.setText("Total : 0");
            quantity.set(1);
        });

        Button clearBtn = new Button("Clear Order");
        clearBtn.getStyleClass().add("clear-button");
        clearBtn.setOnAction(e -> {
            orderArea.clear();
            total = 0;
            totalLabel.setText("Total : 0");
            quantity.set(1);

            Alert clearAlert = new Alert(Alert.AlertType.INFORMATION);
            clearAlert.setTitle("Clear Order");
            clearAlert.setHeaderText(null);
            clearAlert.setContentText("Order list cleared.");
            clearAlert.showAndWait();
        });

        HBox orderButtons = new HBox(10, confirmBtn, clearBtn);
        orderButtons.setAlignment(Pos.CENTER);

        VBox orderCard = new VBox(10,
                new Label("Order List"),
                orderArea,
                totalLabel,
                orderButtons);
        orderCard.getStyleClass().add("order-card");
        orderCard.getStyleClass().add("card");

        VBox topBar = new VBox(10, title, usernameLabel, loginBtn);
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
        ToggleButton sizeM,
        ToggleButton sweet50,
        IntegerProperty quantity
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
        label.getStyleClass().add("menu-label");

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
            sizeM.setSelected(true);
            sweet50.setSelected(true);
            quantity.set(1);
        });

        return card;
    }

    public static void main(String[] args){
        launch();
    }

    private boolean isDrinkMenu(String menuName){
        if(menuName == null){
            return false;
        }

        switch(menuName){
            case "Americano":
            case "Espresso":
            case "Cappuccino":
            case "Mocha":
            case "Latte":
            case "Matcha":
            case "Thai Tea":
            case "Milk Tea":
            case "Chocolate":
            case "Lemon Tea":
                return true;
            default:
                return false;
        }
    }
}