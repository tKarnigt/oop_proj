package com.example.covid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TimelineController implements Initializable {
    //Field
    ObservableList<Timeline> timelineList = FXCollections.observableArrayList();

    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private TableView<Timeline> timelineTableView;
    @FXML
    private TableColumn<Timeline, Integer> noTableColumn;
    @FXML
    private TableColumn<Timeline, String> placeTableColumn;
    @FXML
    private TableColumn<Timeline, String> dateTableColumn;

    private App app = new App();
    private Timeline timeline = new Timeline();

    //Override Method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        File file = new File("Pic/timeline1.jpg");
        Image image = new Image(file.toURI().toString());
        imageView1.setImage(image);
        file = new File("Pic/timeline2.png");
        image = new Image(file.toURI().toString());
        imageView2.setImage(image);

        setTimelineList(connection);
        this.timelineTableView.setItems(timelineList);
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
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }
    public Timeline getTimeline() {
        if(this.timeline==null) {
            this.setTimeline(new Timeline());
        }
        return this.timeline;
    }

    //Action Method
    public void logoutBtnOnAction(ActionEvent event) throws IOException {
        getApp().switchToLogin(event);
    }
    public void informationBtnOnAction(ActionEvent event) throws IOException{
        getApp().switchToHome(event);
    }

    //Method
    public void addNewTimeline(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("new_timeline.fxml"));
            Stage addNewTimelineStage = new Stage();
            addNewTimelineStage.initStyle(StageStyle.DECORATED);
            addNewTimelineStage.setResizable(false);
            addNewTimelineStage.setScene(new Scene(parent, 600, 300));
            addNewTimelineStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void setTimelineList(Connection connection) {
        String connectQuery = "SELECT * FROM timeline_table";

        try{
            int count = 0;
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(connectQuery);

            while (queryResult.next()){
                count++;
                Date date = queryResult.getDate("date");
                timelineList.add(new Timeline(count, queryResult.getString("place"), date.toString()));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        this.noTableColumn.setCellValueFactory(new PropertyValueFactory<>("No"));
        this.placeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        this.dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }
}
