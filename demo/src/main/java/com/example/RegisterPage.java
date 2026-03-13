package com.example;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage {

    public static void show(Stage stage){

        TextField user = new TextField();
        PasswordField pass = new PasswordField();

        Button create = new Button("Create Account");

        create.setOnAction(e->{

            LoginSystem.register(user.getText(),pass.getText());

            new Alert(Alert.AlertType.INFORMATION,
                    "Register Success").show();

            LoginPage.show(stage);

        });

        VBox root = new VBox(10,
                new Label("New Username"),
                user,
                new Label("Password"),
                pass,
                create
        );

        stage.setScene(new Scene(root,300,250));
    }
}