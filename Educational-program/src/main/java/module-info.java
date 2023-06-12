module com.example.countires {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.h2database;

    opens com.example.countires to javafx.fxml;
    opens com.example.countires.controllers to javafx.fxml;
    opens com.example.countires.domain to javafx.fxml;
    exports com.example.countires;
    exports com.example.countires.controllers;
    exports com.example.countires.domain;
    exports com.example.countires.h2database;
}