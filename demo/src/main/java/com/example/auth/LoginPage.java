package com.example.auth;

import com.example.dashboard.OwnerDashboard;
import com.example.model.Member;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {

    public static Member currentMember;

    public static void show(Stage stage){

        Label loginTitle = new Label("☕ PUNPUN Cafe");
        loginTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2d2d2d;");

        Label subtitle = new Label("Sign in to continue");
        subtitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #6a6a6a;");

        Label userLabel = new Label("Username");
        userLabel.setStyle("-fx-font-weight: bold;");
        TextField userField = new TextField();
        userField.setPromptText("Enter your username");
        userField.setPrefHeight(40);
        userField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d7d7d7;");

        Label passLabel = new Label("Password");
        passLabel.setStyle("-fx-font-weight: bold;");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter your password");
        passField.setPrefHeight(40);
        passField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d7d7d7;");

        Button loginBtn = new Button("Login");
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setStyle("-fx-background-color: #6f4e37; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 10; -fx-pref-height: 40;");

        Button registerBtn = new Button("Register");
        registerBtn.setMaxWidth(Double.MAX_VALUE);
        registerBtn.setStyle("-fx-background-color: white; -fx-text-fill: #6f4e37; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-color: #6f4e37; -fx-border-radius: 10; -fx-background-radius: 10; -fx-pref-height: 40;");

        VBox card = new VBox(10,
            loginTitle,
            subtitle,
            userLabel,
            userField,
            passLabel,
            passField,
            loginBtn,
            registerBtn
        );
        card.setPadding(new Insets(24));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setMaxWidth(360);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-color: #dddddd; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 14, 0, 0, 6);");

        StackPane root = new StackPane(card);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f6f1eb, #e9dfd3);");

        loginBtn.setOnAction(e -> {

    Member m = LoginSystem.login(
            userField.getText(),
            passField.getText()
    );

    if(m != null){

        currentMember = m;

        if(m.getRole().equals("owner")){
            OwnerDashboard.showDashboard();
        }

        stage.close(); // ปิดหน้า login

    }else{
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Login Error");
        errorAlert.setHeaderText("Sign in failed");
        errorAlert.setContentText("Please check your username and password or register a new account.");
        errorAlert.setGraphic(null);
        errorAlert.showAndWait();
    }

});

        registerBtn.setOnAction(e -> RegisterPage.show(stage));

        Scene scene = new Scene(root, 420, 420);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}