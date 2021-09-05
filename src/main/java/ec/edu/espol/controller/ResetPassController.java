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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void checkPass(MouseEvent event) {
        try {
            LoginController lc = App.loadFXMLLoader("login").getController();
//            Usuario user = lc.searchUsuario();
//            activeUser = user;
//            if(Util.validarCredenciales(user.getCorreo(), passtxt.getText())){
//                bpane.setCenter(null);
//                GridPane gridPane = new GridPane();
//                bpane.setCenter(gridPane);
//                addPasswordResetForm(gridPane); 
//            }
//            else{
//                Alert a = new Alert(Alert.AlertType.ERROR,"Contraseña incorrecta.");
//                a.show();
//            }
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }
    }
    
    private void actionButton(Button button, String newPass, String passConf){
        button.setOnMouseClicked((MouseEvent me)->{
            if(newPass.equals(passConf)){
                Util.saveNewCredentials(activeUser.getCorreo(), Util.toHexString(Util.getSHA(newPass)) );
                Alert a = new Alert(Alert.AlertType.INFORMATION,"Se ha guardado su nueva contraseña!");
                a.show();
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
        PasswordField newPass = new PasswordField();
        hboxU.getChildren().add(newPass);
        hboxU.setAlignment(Pos.CENTER);
        gridPane.add(hboxU, 0, 0);

        HBox hboxL = new HBox();
        hboxL.getChildren().add(new Text("Confirme su nueva contraseña:"));
        PasswordField passConf = new PasswordField();
        hboxL.getChildren().add(passConf);
        hboxL.setAlignment(Pos.CENTER);
        gridPane.add(hboxL, 0, 1);

        Button button = new Button("Enter");
        button.setAlignment(Pos.CENTER);
        actionButton(button,newPass.getText(),passConf.getText());
        gridPane.add(button, 0, 2);
    }
}
