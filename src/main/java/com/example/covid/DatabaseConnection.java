package com.example.covid;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
//        String databaseName = "covid";
//        String databaseUser = "root";
//        String databasePassword = "por1234";
//        String url =  "jdbc:mysql://localhost/"+ databaseName;

        String databaseName = "covid";
        String databaseUser = "root";
        String databasePassword = "jinojino1234";
        String url =  "jdbc:mysql://localhost/"+ databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }
}
