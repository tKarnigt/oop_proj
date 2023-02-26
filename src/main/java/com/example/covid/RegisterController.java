package com.example.covid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
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
    private Label registrationMessage;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordMessage;
    @FXML
    private Label usernameMessage;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField telephoneNumberField;
    @FXML
    private RadioButton genderMaleRadioButton;
    @FXML
    private RadioButton genderFemaleRadioButton;
    @FXML
    private ChoiceBox vaccinationsChoiceBox;
    @FXML
    private ChoiceBox bloodTypeChoiceBox;
    @FXML
    private Label telephoneNumberLabel;
    @FXML
    private Label errorMessage;
    @FXML
    private CheckBox infectedCheckbox;
    @FXML
    private TextField identificationNumberField;

    private App app = new App();

    //Override Method
    public void initialize(URL url, ResourceBundle resourceBundle){
        File file = new File("Pic/register1.jpg");
        Image image = new Image(file.toURI().toString());
        imageView1.setImage(image);
        file = new File("Pic/register2.png");
        image = new Image(file.toURI().toString());
        imageView2.setImage(image);
        file = new File("Pic/register3.jpg");
        image = new Image(file.toURI().toString());
        imageView3.setImage(image);

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
    public void closeBtnOnAction(ActionEvent event) throws IOException {
        getApp().switchToLogin(event);
    }
    public void registerBtnOnAction(ActionEvent event){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        if(checkCondition(connection)){
            registerAccount(connection);
            errorMessage.setText("");
        }else {
            errorMessage.setText("Please check your information again");
        }
    }

    //Method
    public void registerAccount(Connection connection){
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String username = usernameField.getText();
        String password = setPasswordField.getText();
        String gender = checkGender();
        LocalDate birthday = birthDate.getValue();
        String telephoneNumber = telephoneNumberField.getText();
        String vaccinations = vaccinationsChoiceBox.getSelectionModel().getSelectedItem().toString();
        String bloodType = bloodTypeChoiceBox.getSelectionModel().getSelectedItem().toString();
        String infected = checkInfected();
        String identificationNumber = identificationNumberField.getText();

        String insertFields = "INSERT INTO account_table (firstname, lastname, username, password, gender, telephone_number, blood_type, vaccinations, birthdate, infected, identification_number) VALUES('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "','" + gender + "','" + telephoneNumber + "','" + bloodType + "'," + vaccinations + ",'" + birthday + " 00:00:00','" + infected + "','" + identificationNumber + "')";
        String insertToRegister = insertFields+insertValues;
        System.out.println(insertToRegister);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessage.setText("User has been registered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //Check condition method
    public boolean checkConditionUsername(Connection connection){
        boolean condition = false;

        String checkTakenUsername = "SELECT * FROM account_table WHERE username = '" +usernameField.getText()+ "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(checkTakenUsername);

            if(queryResult.next()){
                usernameMessage.setText("This username is already taken.");
            }
            else{
                condition = true;
                usernameMessage.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return condition;
    }
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
        if(infectedCheckbox.isSelected()){
            return "true";
        }
        else{
            return "false";
        }
    }
    public boolean checkFieldEmpty(){
        if(firstnameField.getText().isBlank() || lastnameField.getText().isBlank() || telephoneNumberField.getText().isBlank() || usernameField.getText().isBlank() || setPasswordField.getText().isBlank() || identificationNumberField.getText().isBlank()){
            System.out.println("Empty");
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
    public boolean checkConditionPassword(){
        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            confirmPasswordMessage.setText("");
            return true;
        }else {
            confirmPasswordMessage.setText("Password does not match");
            return false;
        }
    }
    public boolean checkCondition(Connection connection){
        if (checkConditionUsername(connection) && checkConditionGender() && checkFieldEmpty() && checkConditionRadioButton() && checkConditionPassword()){
            errorMessage.setText("");
            return true;
        }
        else {
            errorMessage.setText("Please check your information again");
            return false;
        }
    }
}
