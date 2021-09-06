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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class ResetPassController implements Initializable {
    private Usuario activeUser;
    @FXML
    private BorderPane bpane;
    @FXML
    private PasswordField passtxt;
    @FXML
    private Text useridtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void checkPass(MouseEvent event) {
        activeUser = Usuario.searchUsuarioByCorreo(useridtxt.getText());
        if(Util.validarCredenciales(activeUser.getCorreo(), passtxt.getText())){
            try{
                FXMLLoader fxmlLoader = App.loadFXMLLoader("resetpassform");
                Parent root = fxmlLoader.load();
                ResetPassFormController rpfc = fxmlLoader.getController();
                rpfc.transferActiveUser(useridtxt.getText());
                App.setRoot(root);
            } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
                    alert.show();
                }
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR,"Contraseña incorrecta.");
            a.show();
        }
    }
    
    private void actionButton(Button button, String newPass, String passConf){
        button.setOnMouseClicked((MouseEvent me)->{
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
                Alert a = new Alert(Alert.AlertType.ERROR,"Las contraseñas no coinciden. Inténtelo de nuevo");
                a.show();
            }
        });
        
    }
    private void addPasswordResetForm(GridPane gridPane){
        HBox hboxU = new HBox();
        hboxU.getChildren().add(new Text("Ingrese su nueva contraseña:"));
        final PasswordField newPass = new PasswordField();
        hboxU.getChildren().add(newPass);
        hboxU.setAlignment(Pos.CENTER);
        gridPane.add(hboxU, 0, 0);

        HBox hboxL = new HBox();
        hboxL.getChildren().add(new Text("Confirme su nueva contraseña:"));
        final PasswordField passConf = new PasswordField();
        hboxL.getChildren().add(passConf);
        hboxL.setAlignment(Pos.CENTER);
        gridPane.add(hboxL, 0, 1);

        Button button = new Button("Enter");
        button.setAlignment(Pos.CENTER);
        actionButton(button,newPass.getText(),passConf.getText());
        gridPane.add(button, 0, 2);
    }

    @FXML
    private void goBack(MouseEvent event) {
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
}
