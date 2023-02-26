package com.example.covid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    //Field
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private App app = new App();
    private Account account = new Account();

    //Override Method
    public void initialize(URL url, ResourceBundle resourceBundle){
        File file = new File("Pic/login1.png");
        Image image = new Image(file.toURI().toString());
        imageView1.setImage(image);
        file = new File("Pic/login2.jpg");
        image = new Image(file.toURI().toString());
        imageView2.setImage(image);
    }

    //Action Method
    public void loginBtnOnAction(ActionEvent event) throws IOException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        if(usernameField.getText().isBlank() || passwordField.getText().isBlank()){
            loginMessage.setText("Please enter username and password.");
        }else{
            if(validateLogin(connection)){
                switchSceneToHome(event);
            }else{
                loginMessage.setText("Invalid username or password.");
            }
        }
    }
    public void cancelBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    public void switchSceneToRegister(ActionEvent event) throws IOException {
        this.app.switchToRegister(event);
    }
    public void switchSceneToHome(ActionEvent event)  throws IOException{
        this.app.switchToHome(event);
    }

    //Method
    public boolean validateLogin(Connection connection){
        boolean valid = false;

        String connectQuery = "SELECT * FROM account_table WHERE username = '"+usernameField.getText()+"' AND password = '"+passwordField.getText()+"'";

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(connectQuery);

            if(queryResult.isBeforeFirst()){
                String idQuery = "SELECT account_id FROM account_table WHERE username = '"+usernameField.getText()+"'";
                ResultSet resultSetID = statement.executeQuery(idQuery);
                while (resultSetID.next()) {
                    this.account.setAccount_id(resultSetID.getString("account_id"));
                    System.out.println(resultSetID.getString("account_id"));
                }
                valid = true;
            }
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return valid;
    }
}