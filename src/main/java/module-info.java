module com.upgrade {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.desktop;
    requires java.sql;


    opens com.upgrade to javafx.fxml;
    exports com.upgrade;
}