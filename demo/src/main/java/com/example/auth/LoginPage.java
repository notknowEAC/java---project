package com.example.auth;

import com.example.dashboard.CustomerDashboard;
import com.example.dashboard.OwnerDashboard;
import com.example.model.Member;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginPage {

    public static Member currentMember;

    public static void show(Stage stage){

        Label title = new Label("☕ PUNPUN Cafe Login");
        title.setStyle("-fx-font-size:24px; -fx-font-weight:bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label message = new Label();

        Button loginBtn = new Button("Login");
        loginBtn.setPrefWidth(200);

        loginBtn.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            Member m = LoginSystem.login(username,password);

            if(m != null){

                currentMember = m;

                message.setText("Login success");

                if(m.getRole().equals("owner")){
                    OwnerDashboard.showDashboard();
                }else{
                    CustomerDashboard.showDashboard(m);
                }

                stage.close();

            }else{

                message.setText("Username or Password incorrect");

            }

        });

        Button registerBtn = new Button("Register");
        registerBtn.setPrefWidth(200);

        registerBtn.setOnAction(e -> RegisterPage.show(stage));

        VBox root = new VBox(15,title,usernameField,passwordField,message,loginBtn,registerBtn);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root,400,350);

        stage.setScene(scene);
        stage.show();
    }
}