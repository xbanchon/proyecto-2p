/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class PerfilController implements Initializable {
    private Usuario activeUser;
    @FXML
    private Text nametxt;
    @FXML
    private Text lastnametxt;
    @FXML
    private Text orgtxt;
    @FXML
    private Text emailtxt;
    @FXML
    private ComboBox<String> roleCbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void saveChanges(MouseEvent event) {
        String role = roleCbox.getValue();
        activeUser.changeUserRole(role);
        Alert a = new Alert(Alert.AlertType.INFORMATION,"Se han guardado los cambios.");
        a.show();
    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("usermenu");
            Parent root = fxmlLoader.load();
            UserMenuController umc = fxmlLoader.getController();
            umc.transferActiveUser(emailtxt.getText());
            App.setRoot(root);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }
    }

    @FXML
    private void changePassword(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("resetpass");
            App.setRoot(fxmlLoader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }
    }
    public void setComboBox(){
        activeUser = Usuario.searchUsuarioByCorreo(emailtxt.getText());
        if(activeUser instanceof Comprador){
            roleCbox.setValue("Comprador");
            roleCbox.setItems(FXCollections.observableArrayList("Vendedor","Comprador y Vendedor"));
        }
        else if(activeUser instanceof Vendedor){
            roleCbox.setValue("Vendedor");
            roleCbox.setItems(FXCollections.observableArrayList("Comprador","Comprador y Vendedor"));
        }
        else{
            roleCbox.setValue("Comprador y Vendedor");
            roleCbox.setItems(FXCollections.observableArrayList("Comprador","Vendedor"));
        }
    }
    
    public void setName(String name){
        nametxt.setText(name);
    }
    
    public void setLastName(String lastName){
        lastnametxt.setText(lastName);
    }
    
    public void setOrganization(String org){
        orgtxt.setText(org);
    }
    
    public void setEmail(String email){
        emailtxt.setText(email);
    }  
}
