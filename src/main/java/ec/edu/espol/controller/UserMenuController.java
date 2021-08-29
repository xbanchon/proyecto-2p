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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class UserMenuController implements Initializable {

    @FXML
    private HBox hpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Recuperar el controller de login
        //Recuperar el usuario y verificar su instancia 
        //Dependiendo de su instancia mostrar los botones de comprar y vender.
        //Añadirle el comportamiento onMOuseClicked a cada botón 
        //Cargar las escenas respectivas.
    }    

    @FXML
    private void verPerfil(MouseEvent event) {
        try {
                FXMLLoader fxmlloader = App.loadFXMLLoader("perfil");
                App.setRoot(fxmlloader);
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
                a.show();
            }
    }
    
}
