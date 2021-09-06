/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class UserMenuController implements Initializable {
    private Usuario activeUser;
    @FXML
    private HBox hpane;
    @FXML
    private Text useridtxt;
    @FXML
    private Button offerbtn;
    @FXML
    private Button sellbtn;
    @FXML
    private Button viewofferbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
    }    

    @FXML
    private void verPerfil(MouseEvent event) {
        activeUser = Usuario.searchUsuarioByCorreo(useridtxt.getText());
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
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }
    }
    
    public void transferActiveUser(String userID){
        useridtxt.setText(userID);
    }

    @FXML
    private void backToInicio(MouseEvent event) {
        try{
            FXMLLoader fxmlLoader = App.loadFXMLLoader("inicio");
            App.setRoot(fxmlLoader);
        } catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }
    }
    
    private void showOpciones(){
        activeUser = Usuario.searchUsuarioByCorreo(useridtxt.getText());
        if(activeUser.getUserRole().equals("Comprador")){
            hpane.getChildren().remove(sellbtn);
            hpane.getChildren().remove(viewofferbtn);
        }
        else if(activeUser.getUserRole().equals("Vendedor")){  
            hpane.getChildren().remove(offerbtn);
        }
        else{
          }  
    }

    @FXML
    private void loadOfertarVehiculo(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("ofertarvehiculo");
            App.setRoot(fxmlLoader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }  
    }

    @FXML
    private void loadVenderVehiculo(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("vendervehiculo");
            App.setRoot(fxmlLoader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }  
    }

    @FXML
    private void loadVerOfertas(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("inicio");
            App.setRoot(fxmlLoader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }  
    }

    @FXML
    private void refreshView(MouseEvent event) {
        showOpciones();
    }
}
