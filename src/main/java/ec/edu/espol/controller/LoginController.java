/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class LoginController implements Initializable {
    @FXML
    private TextField usertxt;
    @FXML
    private PasswordField passtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backToInicio(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("inicio");
            App.setRoot(fxmlLoader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }  
    }

    @FXML
    private void iniciarSesion(MouseEvent event) {
        String user = usertxt.getText();
        String pass = passtxt.getText();
        if(Util.validarCredenciales(user, pass)){
            try {
                FXMLLoader fxmlLoader = App.loadFXMLLoader("usermenu");
                Parent root = fxmlLoader.load();
                UserMenuController umc = fxmlLoader.getController();
                umc.transferActiveUser(user);
                App.setRoot(root);   
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
                a.show(); 
            }
        }
        else{
            try{
                throw new LoginException();
            }
            catch(LoginException ex){
                usertxt.setText("");
                passtxt.setText("");
                Alert a = new Alert(AlertType.ERROR,ex.getMessage());
                a.show();
            }
        }
    }
    
    class LoginException extends Exception{
        public LoginException(){
            super("Usuario o contraseña incorrectos.");
        }
    }
}
