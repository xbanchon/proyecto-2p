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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
            //Recuperar el controller de login
        
            //Recuperar el usuario y verificar su instancia
            //Dependiendo de su instancia mostrar los botones de comprar y vender.
            //Añadirle el comportamiento onMOuseClicked a cada botón.
            //Cargar las escenas respectivas.
            
            activeUser = Usuario.searchUsuarioByCorreo(useridtxt.getText());
            if(activeUser instanceof Comprador){
                addButton( createButton("ofertarvehiculo" , "Ofertar por un vehículo") );//agregar el fxml de la vista para comprar vehiculos.
            }
            else if(activeUser instanceof Vendedor){  
                addButton( createButton("inicio" , "Vender un vehículo") );//agregar el fxml de la vista para vender un vehiculo.
                addButton( createButton("inicio" , "Ver ofertas") );//agregar el fxml de la vista para ver ofertas de un vehiculo.
            }
            else{
                addButton( createButton("ofertarvehiculo" , "Ofertar por un vehículo") );//agregar el fxml de la vista para comprar vehiculos.
                addButton( createButton("inicio" , "Vender un vehículo") );//agregar el fxml de la vista para vender un vehiculo.
                addButton( createButton("inicio" , "Ver ofertas") );//agregar el fxml de la vista para ver ofertas de un vehiculo.
            }  
            
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
    
    public void transferActiveUser(String userID){
        useridtxt.setText(userID);
    }
}
