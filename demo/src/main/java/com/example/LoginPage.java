package com.example;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {

    public static Member currentMember;

    public static void show(Stage stage){

        TextField userField = new TextField();
        PasswordField passField = new PasswordField();

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");

        VBox root = new VBox(10,
                new Label("Username"),
                userField,
                new Label("Password"),
                passField,
                loginBtn,
                registerBtn
        );

        loginBtn.setOnAction(e -> {

    Member m = LoginSystem.login(
            userField.getText(),
            passField.getText()
    );

    if(m != null){

        currentMember = m;

        if(m.getRole().equals("owner")){
            OwnerDashboard.showDashboard();
        }else{
            CustomerDashboard.showDashboard(m);
        }

        stage.close(); // ปิดหน้า login

    }else{
        new Alert(Alert.AlertType.ERROR,"Login Failed").show();
    }

});

        registerBtn.setOnAction(e -> RegisterPage.show(stage));

        stage.setScene(new Scene(root,300,250));
        stage.setTitle("Login");
        stage.show();
    }
}