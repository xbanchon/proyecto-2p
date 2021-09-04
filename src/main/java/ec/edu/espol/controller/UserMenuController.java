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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
        try {
            //Recuperar el controller de login
            FXMLLoader fxmlLoader = App.loadFXMLLoader("login");
            LoginController lc = fxmlLoader.getController();
            Usuario user = lc.searchUsuario();
            //Recuperar el usuario y verificar su instancia
            //Dependiendo de su instancia mostrar los botones de comprar y vender.
            //Añadirle el comportamiento onMOuseClicked a cada botón.
            //Cargar las escenas respectivas.
//            if(user instanceof Comprador){
//                addButton( createButton("" , "Comprar un vehículo") );//agregar el fxml de la vista para comprar vehiculos.
//            }
//            else if(user instanceof Vendedor){  
//                addButton( createButton("" , "Vender un vehículo") );//agregar el fxml de la vista para vender un vehiculo.
//                addButton( createButton("" , "Ver ofertas") );//agregar el fxml de la vista para ver ofertas de un vehiculo.
//            }
//            else{
//                addButton( createButton("" , "Comprar un vehículo") );//agregar el fxml de la vista para comprar vehiculos.
//                addButton( createButton("" , "Vender un vehículo") );//agregar el fxml de la vista para vender un vehiculo.
//                addButton( createButton("" , "Ver ofertas") );//agregar el fxml de la vista para ver ofertas de un vehiculo.
//            }  
        } catch (IOException ex) {}
    }    

    @FXML
    private void verPerfil(MouseEvent event) {
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("perfil");
            App.setRoot(fxmlloader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }
    }
    
    private Button createButton(String fxml, String label){
        Button button = new Button(label);
        button.setOnMouseClicked((MouseEvent me)->{
            try {
                FXMLLoader fxmlLoader = App.loadFXMLLoader(fxml);
                App.setRoot(fxmlLoader);
            } catch (IOException ex) {}   
        });
        return button;
    }
    private void addButton(Button button){
        hpane.getChildren().add(button);
    }
    
}
