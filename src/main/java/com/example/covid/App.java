package com.example.covid;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    //Field
    private Stage stage = new Stage();

    //Override Method
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        this.stage.initStyle(StageStyle.DECORATED);
        this.stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 800, 497);
        this.stage.setTitle("Covid-19");
        this.stage.setScene(scene);
        this.stage.show();
    }

    //Switch Scene Method
    public void switchToLogin(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(parent, 800, 497);
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.getScene().setRoot(parent);
    }
    public void switchToRegister(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(parent, 800, 615);
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.getScene().setRoot(parent);
    }
    public void switchToHome(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(parent, 1080, 720);
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.getScene().setRoot(parent);
    }
    public void switchToTimeline(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("timeline.fxml"));
        Scene scene = new Scene(parent, 1080, 720);
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.getScene().setRoot(parent);
    }
    public void switchToEdit(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("edit.fxml"));
        Scene scene = new Scene(parent, 1080, 720);
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.getScene().setRoot(parent);
    }

    //Method


    public static void main(String[] args) {
        launch();
    }
}