package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    private ComboBox<String> menuBox;

    @FXML
    private ComboBox<String> sizeBox;

    @FXML
    private ComboBox<String> sweetBox;

    @FXML
    private TextArea orderArea;

    @FXML
    public void initialize(){
        menuBox.getItems().addAll("Mocha","Latte","Americano");
        menuBox.getSelectionModel().selectFirst();

        sizeBox.getItems().addAll("S","M","L");
        sizeBox.getSelectionModel().selectFirst();

        sweetBox.getItems().addAll("0%","50%","100%");
        sweetBox.getSelectionModel().selectFirst();
    }

    @FXML
    void addOrder(){
        String menu = menuBox.getValue();
        String size = sizeBox.getValue();
        String sweet = sweetBox.getValue();

        int price = Size.getPrice(size);

        orderArea.appendText(menu+" "+size+" "+sweet+" "+price+"\n");
    }
}