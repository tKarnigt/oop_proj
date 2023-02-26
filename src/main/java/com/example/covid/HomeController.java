package com.example.covid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.ResourceBundle;

public class HomeController implements Initializable{
    //Field
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private ImageView imageView4;
    @FXML
    private Label identificationNumberLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label vaccinationsLabel;
    @FXML
    private Label bloodTypeLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label infectedLabel;

    private App app = new App();
    private Account account = new Account();

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

        createUserData();
        setAllLabel();
    }

    //Action Method
    public void logoutBtnOnAction(ActionEvent event) throws IOException {
        this.app.switchToLogin(event);
    }
    public void timelineBtnOnAction(ActionEvent event) throws IOException{
        this.app.switchToTimeline(event);
    }
    public void editBtnOnAction(ActionEvent event) throws IOException {
        this.app.switchToEdit(event);
    }

    //Method
    public void createUserData(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String connectQuery = "SELECT firstname, lastname, username, password, gender, telephone_number, blood_type, vaccinations, birthdate, infected, identification_number FROM account_table WHERE account_id = '" +Account.getAccount_id()+ "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(connectQuery);
            while (queryResult.next()){
                String firstname = queryResult.getString("firstname");
                String lastname = queryResult.getString("lastname");
                String username = queryResult.getString("username");
                String password = queryResult.getString("password");
                String gender = queryResult.getString("gender");
                System.out.println(gender);
                String telephoneNumber = queryResult.getString("telephone_number");
                String bloodType = queryResult.getString("blood_type");
                int vaccinations = Integer.parseInt(queryResult.getString("vaccinations"));
                Date date = queryResult.getDate("birthdate");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                Calendar currentCalendar = Calendar.getInstance();
                int age = currentCalendar.get(Calendar.YEAR)-calendar.get(Calendar.YEAR);
                String identificationNumber = queryResult.getString("identification_number");

                if(queryResult.getString("infected").equals("true")) {
                    this.account.setInfected(true);
                }else{
                    this.account.setInfected(false);
                }
                this.account.setUsername(username);
                this.account.setPassword(password);
                this.account.setFirstname(firstname);
                this.account.setLastname(lastname);
                this.account.setAge(age);
                this.account.setGender(gender);
                this.account.setVaccinations(vaccinations);
                this.account.setBloodType(bloodType);
                this.account.setTelephoneNumber(telephoneNumber);
                this.account.setIdentificationNumber(identificationNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    //Display Method
    public void setAllLabel(){
        setNameLabel(this.nameLabel);
        setLastnameLabel(this.lastnameLabel);
        setAgeLabel(this.ageLabel);
        setGenderLabel(this.genderLabel);
        setVaccinationsLabel(this.vaccinationsLabel);
        setBloodTypeLabel(this.bloodTypeLabel);
        setPhoneNumberLabel(this.phoneNumberLabel);
        setInfectedLabel(this.infectedLabel);
        setIdentificationNumberLabel(this.identificationNumberLabel);
    }
    public void setBloodTypeLabel(@NotNull Label bloodTypeLabel) {
        bloodTypeLabel.setText("Blood Type : "+this.account.getBloodType());
    }
    public void setInfectedLabel(@NotNull Label infectedLabel) {
        if(this.account.isInfected()){
            infectedLabel.setStyle("-fx-text-fill: #ff0000;");
            infectedLabel.setText("Infected");
        }else {
            infectedLabel.setStyle("-fx-text-fill: #011bff;");
            infectedLabel.setText("Not infected");
        }
    }
    public void setLastnameLabel(@NotNull Label lastnameLabel) {
        lastnameLabel.setText(this.account.getLastname());
    }
    public void setNameLabel(@NotNull Label nameLabel) {
        nameLabel.setText(this.account.getFirstname());
    }
    public void setPhoneNumberLabel(@NotNull Label phoneNumberLabel) {
        phoneNumberLabel.setText(this.account.getTelephoneNumber());
    }
    public void setVaccinationsLabel(@NotNull Label vaccinationsLabel) {
        if(this.account.getVaccinations() == 0){
            vaccinationsLabel.setText("Vaccinations : -");
        }else {
            vaccinationsLabel.setText("Vaccinations : " + this.account.getVaccinations());
        }
    }
    public void setGenderLabel(@NotNull Label genderLabel) {
        genderLabel.setText("Gender : "+this.account.getGender());
    }
    public void setAgeLabel(@NotNull Label ageLabel) {
        ageLabel.setText("Age : "+this.account.getAge());
    }

    public void setIdentificationNumberLabel(@NotNull Label identificationNumberLabel) {
        identificationNumberLabel.setText(this.account.getIdentificationNumber());
    }
}
