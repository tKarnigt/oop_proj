package com.example.covid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewTimelineController implements Initializable {
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private TextField placeTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label errorMessage;

    private App app = new App();

    //Override Method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("Pic/add1.png");
        Image image = new Image(file.toURI().toString());
        imageView1.setImage(image);
        file = new File("Pic/add2.jpg");
        image = new Image(file.toURI().toString());
        imageView2.setImage(image);
        file = new File("Pic/add1.png");
        image = new Image(file.toURI().toString());
        imageView3.setImage(image);
    }

    //Access Method
    public App getApp() {
        if(this.app == null){
            setApp(new App());
        }
        return this.app;
    }
    public void setApp(App app) {
        this.app = app;
    }

    //Action Method
    public void doneBtnOnAction(ActionEvent event){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        if(checkCondition()){
            addTimeline(connection);
        }
    }
    public void cancelBtnOnAction(ActionEvent event){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //Method
    public void addTimeline(Connection connection){
        String place = this.placeTextField.getText();
        LocalDate date = this.datePicker.getValue();

        String insertFields = "INSERT INTO timeline_table(place, date, account_id) VALUES('";
        String insertValues = place + "','" + date + " 00:00:00'," + Account.getAccount_id() + ")";
        String insertToRegister = insertFields+insertValues;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);

            this.errorMessage.setStyle("-fx-text-fill: #5C7AEA;");
            this.errorMessage.setText("Add timeline successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //Check Condition Method
    public boolean checkPlaceTextField(){
        if(this.placeTextField.getText().isBlank()){
            return false;
        }else {
            return true;
        }
    }
    public boolean checkDatePicker(){
        if (this.datePicker.getValue() == null){
            return false;
        }else{
            return true;
        }
    }
    public boolean checkCondition(){
        if(this.checkDatePicker() && this.checkPlaceTextField()){
            this.errorMessage.setText("");
            return true;
        }
        else{
            this.errorMessage.setStyle("-fx-text-fill: #ff0000;");
            this.errorMessage.setText("Please fill in all the information.");
            return false;
        }
    }
}
