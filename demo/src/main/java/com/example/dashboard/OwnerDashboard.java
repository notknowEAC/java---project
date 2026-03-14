package com.example.dashboard;

import com.example.auth.LoginPage;
import com.example.database.JSONDatabase;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class OwnerDashboard {

    public static void showDashboard(){

        Stage stage = new Stage();

        int totalSales = JSONDatabase.getTotalSales();
        int profit = totalSales * 40 / 100;

        Label title = new Label("☕ PUNPUN Cafe - Owner Dashboard");
        title.setStyle("-fx-font-size:28px; -fx-font-weight:bold;");

        String ownerUsername = LoginPage.currentMember != null
            ? LoginPage.currentMember.getUsername()
            : "Unknown";
        Label ownerLabel = new Label("Username: " + ownerUsername);
        ownerLabel.setStyle("-fx-font-size:16px; -fx-font-weight:bold; -fx-text-fill:#333333;");

        VBox salesCard = createCard("Total Sales", totalSales + " ฿");
        VBox profitCard = createCard("Profit", profit + " ฿");
        VBox orderCard = createCard("Orders", JSONDatabase.loadOrders().size() + "");

        HBox stats = new HBox(20, salesCard, profitCard, orderCard);
        stats.setAlignment(Pos.CENTER);

        Label reportTitle = new Label("Sales Report");
        reportTitle.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");

        Label reportContent = new Label(JSONDatabase.generateReport());
        reportContent.setStyle("-fx-font-family:monospace; -fx-font-size:14px;");
        reportContent.setWrapText(true);
        
        Button clearDataBtn = new Button("Clear Data");
        clearDataBtn.setStyle(
            "-fx-background-color:#e74c3c;" +
            "-fx-text-fill:white;" +
            "-fx-font-weight:bold;" +
            "-fx-background-radius:10;"
        );
        clearDataBtn.setOnAction(e -> {

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Clear all order data?",
                ButtonType.OK,
                ButtonType.CANCEL);
            confirm.setHeaderText(null);

            if(confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK){
            JSONDatabase.clearOrders();

            Alert done = new Alert(Alert.AlertType.INFORMATION, "Data cleared successfully.");
            done.setHeaderText(null);
            done.showAndWait();

            stage.close();
            showDashboard();
            }
        });

        VBox reportBox = new VBox(10, reportTitle, reportContent, clearDataBtn);
        reportBox.setPadding(new Insets(20));
        reportBox.setAlignment(Pos.CENTER_LEFT);
        reportBox.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:15;" +
                "-fx-effect:dropshadow(three-pass-box,rgba(0,0,0,0.2),10,0,0,5);"
        );

        VBox root = new VBox(12, title, ownerLabel, stats, reportBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));

        root.setStyle(
                "-fx-background-color:linear-gradient(to bottom,#f6f1eb,#e9dfd3);"
        );

        Scene scene = new Scene(root,760,520);
        scene.getStylesheets().add(
        OwnerDashboard.class.getResource("/style.css").toExternalForm()
);
        stage.setTitle("Owner Dashboard");
        stage.setScene(scene);
        stage.show();

    }

    private static VBox createCard(String title,String value){

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size:16px;");

        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-font-size:24px; -fx-font-weight:bold;");

        VBox card = new VBox(10,titleLabel,valueLabel);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));

        card.setPrefWidth(180);

        card.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:15;" +
                "-fx-effect:dropshadow(three-pass-box,rgba(0,0,0,0.2),10,0,0,5);"
        );

        return card;
    }
}