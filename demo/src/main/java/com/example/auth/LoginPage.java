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
        loginTitle.getStyleClass().add("auth-title");

        Label subtitle = new Label("Sign in to continue");
        subtitle.getStyleClass().add("subtitle-label");

        Label userLabel = new Label("Username");
        userLabel.getStyleClass().add("auth-label");
        TextField userField = new TextField();
        userField.setPromptText("Enter your username");
        userField.getStyleClass().add("auth-input");

        Label passLabel = new Label("Password");
        passLabel.getStyleClass().add("auth-label");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter your password");
        passField.getStyleClass().add("auth-input");

        Button loginBtn = new Button("Login");
        loginBtn.getStyleClass().add("login-button");
        loginBtn.setMaxWidth(Double.MAX_VALUE);

        Button registerBtn = new Button("Register");
        registerBtn.getStyleClass().add("register-button");
        registerBtn.setMaxWidth(Double.MAX_VALUE);

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
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(360);
        card.getStyleClass().add("auth-card");

        StackPane root = new StackPane(card);
        root.setPadding(new Insets(30));

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

        scene.getStylesheets().add(
        LoginPage.class.getResource("/style.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}