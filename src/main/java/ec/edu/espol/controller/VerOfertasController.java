/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class VerOfertasController implements Initializable {
    private Vendedor activeUser;
    @FXML
    private FlowPane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Vehiculo.recuperarVehiculosVendedor(activeUser.getCorreo());
        for(Vehiculo vehiculo: activeUser.getVehiculos()){
            addVehiculo(vehiculo);
        }
    }    
    
    private void addVehiculo(Vehiculo vehiculo){
        VBox vbox = new VBox();
        vbox.getChildren().add(new Text(vehiculo.getMarca() + vehiculo.getModelo() ));
        vbox.getChildren().add(new Text(vehiculo.getPlaca()));
        vbox.getChildren().add(addButton());
        mainpane.getChildren().add(vbox);
    }
    
    private Button addButton(){
        Button button = new Button("Ver ofertas");
        button.setOnMouseClicked(eh->{
            try{
                FXMLLoader fxmlLoader = App.loadFXMLLoader("ofertasvehiculo");
                App.setRoot(fxmlLoader);
            } catch(IOException ex){
                Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
                a.show();
            }
        
        });
        return button;
        
    }
    
}
