/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyecto2p.App;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class VenderVehiculoController implements Initializable {

    @FXML
    private TextField placatxt;
    @FXML
    private TextField marcatxt;
    @FXML
    private TextField modelotxt;
    @FXML
    private TextField motortxt;
    @FXML
    private TextField yeartxt;
    @FXML
    private TextField recorridotxt;
    @FXML
    private TextField colortxt;
    @FXML
    private TextField combustibletxt;
    @FXML
    private TextField preciotxt;
    @FXML
    private ComboBox<String> tipovCbox;
    @FXML
    private VBox imagebox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tipovCbox.setItems(FXCollections.observableArrayList("Carro","Camioneta","Motocicleta"));
    }    

    @FXML
    private void callFileChooser(MouseEvent event) {
        FileChooser fc = new FileChooser();
        Stage stage = new Stage();
        File selectedfile = fc.showOpenDialog(stage);
        
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG Images","*.jpg")
                ,new FileChooser.ExtensionFilter("PNG Images", "*.png")
                ,new FileChooser.ExtensionFilter("JPEG Images", "*.jpeg")
        );
    }

    @FXML
    private void ingresarVehiculo(MouseEvent event) {
        if(placatxt.getText()!= null && marcatxt.getText()!= null && modelotxt.getText()!= null && motortxt.getText()!= null && yeartxt.getText()!= null && recorridotxt.getText()!= null && colortxt.getText()!= null && combustibletxt.getText()!= null && preciotxt.getText()!= null && tipovCbox.getValue()!= null){
            Vehiculo vehiculo = new Vehiculo(0,placatxt.getText(), marcatxt.getText(), modelotxt.getText(), motortxt.getText(), Integer.parseInt(yeartxt.getText()), Double.parseDouble(recorridotxt.getText()), colortxt.getText(), combustibletxt.getText(), Double.parseDouble(preciotxt.getText()), tipovCbox.getValue());
            ArrayList<Vehiculo> vehiculos = Vehiculo.leerArchivo();
            if(vehiculos.isEmpty())
                vehiculos = new ArrayList<>();
            vehiculo.guardarArchivo(vehiculos);
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR,"Rellene todos los campos.");
            a.show();
        }
    }
    
    private void validarCampos(){
        
    }

    @FXML
    private void goBack(MouseEvent event) {
        try{
            FXMLLoader fxmlLoader = App.loadFXMLLoader("usermenu");
            App.setRoot(fxmlLoader);
        } catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }
    }
    
}
