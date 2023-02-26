module com.example.covid {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;
    requires org.jetbrains.annotations;

    opens com.example.covid to javafx.fxml;
    exports com.example.covid;
}