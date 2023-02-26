package com.example.covid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditController implements Initializable{
    //Field
    ObservableList<String> vaccinationsList = FXCollections.observableArrayList("0", "1", "2", "3", "4");
    ObservableList<String> bloodTypeList = FXCollections.observableArrayList("A", "B", "O", "AB");

    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private ImageView imageView4;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private RadioButton genderMaleRadioButton;
    @FXML
    private RadioButton genderFemaleRadioButton;
    @FXML
    private ChoiceBox vaccinationsChoiceBox;
    @FXML
    private ChoiceBox bloodTypeChoiceBox;
    @FXML
    private TextField telephoneNumberTextField;
    @FXML
    private TextField identificationTextField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private CheckBox infectedCheckBox;
    @FXML
    private Label doneLabel;
    private App app;

    //Override Method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("Pic/home1.jpg");
        Image image = new Image(file.toURI().toString());
        imageView1.setImage(image);
        file = new File("Pic/home2.png");
        image = new Image(file.toURI().toString());
        imageView2.setImage(image);
        file = new File("Pic/home3.png");
        image = new Image(file.toURI().toString());
        imageView3.setImage(image);
        file = new File("Pic/home4.png");
        image = new Image(file.toURI().toString());
        imageView4.setImage(image);


        vaccinationsChoiceBox.setItems(vaccinationsList);
        bloodTypeChoiceBox.setItems(bloodTypeList);
    }
    //Access Method
    public void setApp(App app){
            this.app = app;
        }
    public App getApp() {
        if (app == null){
            setApp(new App());
        }
        return app;
    }

    //Action Method
    public void cancelBtnOnAction(ActionEvent event) throws IOException{
        getApp().switchToHome(event);
    }
    public void doneBtnOnAction(ActionEvent event) throws IOException{
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        if(checkCondition()){
            updateAccount(connection);
            getApp().switchToHome(event);
        }
    }


    public void updateAccount(Connection connection){
        String firstname = firstNameTextField.getText();
        String lastname = lastNameTextField.getText();
        String gender = checkGender();
        LocalDate birthday = birthDatePicker.getValue();
        String telephoneNumber = telephoneNumberTextField.getText();
        String vaccinations = vaccinationsChoiceBox.getSelectionModel().getSelectedItem().toString();
        String bloodType = bloodTypeChoiceBox.getSelectionModel().getSelectedItem().toString();
        String infected = checkInfected();
        String identificationNumber = identificationTextField.getText();

        String insertToEdit = "UPDATE account_table SET firstname ='"+firstname+"', lastname = '"+lastname+"', gender = '"+gender+"', telephone_number = '"+telephoneNumber+"', blood_type = '"+bloodType+"', vaccinations = '"+vaccinations+"', birthdate = '"+birthday+" 00:00:00', infected = '"+infected+"', identification_number = '"+identificationNumber+"' WHERE account_id = "+Account.getAccount_id();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToEdit);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    //Check condition method
    public boolean checkConditionGender(){
        if(genderFemaleRadioButton.isSelected() || genderMaleRadioButton.isSelected()){
            return true;
        }
        else {
            System.out.println("Gender");
            return false;
        }
    }
    public String checkGender(){
        if (genderMaleRadioButton.isSelected()){
            return "Male";
        }
        else {
            return "Female";
        }
    }
    public String checkInfected(){
        if(infectedCheckBox.isSelected()){
            return "true";
        }
        else{
            return "false";
        }
    }
    public boolean checkFieldEmpty(){
            if(firstNameTextField.getText().isBlank() || lastNameTextField.getText().isBlank() || telephoneNumberTextField.getText().isBlank()){
                return false;
            } else{
                return true;
            }
        }
    public boolean checkConditionRadioButton(){
            if(vaccinationsChoiceBox.getSelectionModel().isEmpty() || bloodTypeChoiceBox.getSelectionModel().isEmpty()){
                return false;
            }else{
                return true;
            }
        }
    public boolean checkCondition(){
        if (checkConditionGender() && checkFieldEmpty() && checkConditionRadioButton()){
            doneLabel.setText("");
            return true;
        }
        else {
            doneLabel.setText("Please check your information again.");
            return false;
        }
    }
}
