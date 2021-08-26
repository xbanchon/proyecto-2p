module ec.edu.espol.proyecto2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;

    opens ec.edu.espol.proyecto2p to javafx.fxml;
    opens ec.edu.espol.controller to javafx.fxml;
    exports ec.edu.espol.proyecto2p;
    exports ec.edu.espol.controller;
}
