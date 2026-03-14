package com.example.auth;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage {

    public static void show(Stage stage){

        Label title = new Label("Create Account");
        title.getStyleClass().add("auth-title");

        Label subtitle = new Label("Join PUNPUN Cafe and earn points!");
        subtitle.getStyleClass().add("subtitle-label");

        Label userLabel = new Label("Username");
        userLabel.getStyleClass().add("auth-label");
        TextField user = new TextField();
        user.setPromptText("Enter your username");
        user.getStyleClass().add("auth-input");

        Label passLabel = new Label("Password");
        passLabel.getStyleClass().add("auth-label");
        PasswordField pass = new PasswordField();
        pass.setPromptText("Enter your password");
        pass.getStyleClass().add("auth-input");

        Button create = new Button("Create Account");
        create.getStyleClass().add("register-button");
        create.setMaxWidth(Double.MAX_VALUE);

        create.setOnAction(e->{
            LoginSystem.register(user.getText(), pass.getText());

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Registration Successful");
            success.setHeaderText(null);
            success.setContentText("Your account has been created. Please sign in.");
            success.showAndWait();

            LoginPage.show(stage);
        });

        VBox card = new VBox(12,
                title,
                subtitle,
                userLabel,
                user,
                passLabel,
                pass,
                create
        );
        card.setPadding(new Insets(24));
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(360);
        card.getStyleClass().add("auth-card");

        StackPane root = new StackPane(card);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 420, 460);

scene.getStylesheets().add(
        RegisterPage.class.getResource("/style.css").toExternalForm()
);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }
}
