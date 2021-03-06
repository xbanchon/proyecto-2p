/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class InicioController implements Initializable {

    @FXML
    private Button loginbtn;
    @FXML
    private Button registrobtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changeToLogin(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("login");
            App.setRoot(fxmlLoader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }  
    }

    @FXML
    private void changeToRegistro(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("registro");
            App.setRoot(fxmlLoader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        } 
    }
}
