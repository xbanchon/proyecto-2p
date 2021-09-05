/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
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
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class ResetPassFormController implements Initializable {
    private Usuario activeUser;
    @FXML
    private PasswordField newpasstxt;
    @FXML
    private PasswordField confpasstxt;
    @FXML
    private Text useridtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void saveNewPass(MouseEvent event) {
        activeUser = Usuario.searchUsuarioByCorreo(useridtxt.getText());
        String newPass = newpasstxt.getText();
        String passConf = confpasstxt.getText();
        if(newPass.equals(passConf) && !newPass.equals("")){
                Util.saveNewCredentials(activeUser.getCorreo(), Util.toHexString(Util.getSHA(newPass)) );
                Alert a = new Alert(Alert.AlertType.INFORMATION,"Se ha guardado su nueva contraseña!");
                a.show();
                try{
                    FXMLLoader fxmlloader = App.loadFXMLLoader("perfil");
                    Parent root = fxmlloader.load();
                    PerfilController pc = fxmlloader.getController();
                    pc.setName(activeUser.getNombres());
                    pc.setLastName(activeUser.getApellidos());
                    pc.setOrganization(activeUser.getOrganizacion());
                    pc.setEmail(activeUser.getCorreo());
                    pc.setComboBox();
                    App.setRoot(root);
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
                    alert.show();
                }
            }
            else{
                newpasstxt.setText("");
                confpasstxt.setText("");
                Alert a = new Alert(Alert.AlertType.ERROR,"Las contraseñas no coinciden. Inténtelo de nuevo");
                a.show();
            }
    }
    
    public void transferActiveUser(String userID){
        useridtxt.setText(userID);
    }
    
}
