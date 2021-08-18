module ec.edu.espol.proyecto2p {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyecto2p to javafx.fxml;
    exports ec.edu.espol.proyecto2p;
}
