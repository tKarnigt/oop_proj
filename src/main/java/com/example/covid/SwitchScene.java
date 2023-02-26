package com.example.covid;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SwitchScene {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void switchToLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        scene = new Scene(fxmlLoader.load(), 800, 497);
        stage.setTitle("Covid-19");
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRegister(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        scene = new Scene(fxmlLoader.load(), 800, 615);
        stage.setTitle("Covid-19");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(App.class.getResource("home.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Covid-19");
        stage.setScene(scene);
        stage.show();
    }
}
