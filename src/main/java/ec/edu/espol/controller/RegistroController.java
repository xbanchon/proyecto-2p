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
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Xavier Eduardo
 */
public class RegistroController implements Initializable {
    private ArrayList<Usuario> usuarios;
    private ArrayList<String> credencialesL;
    @FXML
    private PasswordField passtxt;
    @FXML
    private TextField nametxt;
    @FXML
    private TextField lastnametxt;
    @FXML
    private TextField orgtxt;
    @FXML
    private TextField emailtxt;
    @FXML
    private ComboBox<String> cbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = new ArrayList<>();
        ArrayList<String> opciones = new ArrayList<>();
        String[] opcArray = new String[]{"Comprador","Vendedor","Comprador y Vendedor"};
        Collections.addAll(opciones, opcArray);
        cbox.setItems(FXCollections.observableArrayList(opciones));
        if(Util.leerCredencialesRegistro() == null)
            credencialesL = new ArrayList<>();
        else
            credencialesL = Util.leerCredencialesRegistro();
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
    private void registrar(MouseEvent event){
        if("".equals(passtxt.getText()) || "".equals(nametxt.getText()) || "".equals(lastnametxt.getText()) || "".equals(orgtxt.getText()) || "".equals(emailtxt.getText()) || cbox.getValue() == null){
            Alert a = new Alert(AlertType.WARNING,"Rellene todos los campos.");
            a.show();
        }
        else{
            validarCampos();
            //Crear nuevo usuario
            Usuario usuario = crearNuevoUsuario(cbox.getValue());
            //A??adirlo a una lista de usuarios y mandarla a serializar
            usuario.guardarUsuarios(usuarios);
            Util.guardarCredencialesRegistro(credencialesL, emailtxt.getText(), passtxt.getText());
            //Regresar a la pantalla de inicio
            try {
                FXMLLoader fxmlLoader = App.loadFXMLLoader("inicio");
                App.setRoot(fxmlLoader);
            } 
            catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
                a.show();
            } 
            //Mostrar mensaje para indicar el registro exitoso
            Alert alert = new Alert(AlertType.INFORMATION,"Registro exitoso!");
            alert.show();   
        }
    }
    
    void validarCampos(){
        if(nametxt.getText().matches(".*\\d.*") || lastnametxt.getText().matches(".*\\d.*") || orgtxt.getText().matches(".*\\d.*")){
            try{
                throw new InputMismatchException("Se ingresaron n??meros cuando se esperaban letras.");
            }
            catch(InputMismatchException ex){
                Alert a = new Alert(AlertType.ERROR,ex.getMessage());
                a.show();
            }
        }
        else if(!Util.validarCorreo(emailtxt.getText())){
            try{
                throw new InputMismatchException("Ingrese un correo de Gmail.");
            }
            catch(InputMismatchException ex){
                Alert a = new Alert(AlertType.ERROR,ex.getMessage());
                a.show();
            }
        }
        else if(cbox.getValue() == null ){
            try{
                throw new Exception("Seleccione un rol.");
            }
            catch(Exception ex){
                Alert a = new Alert(AlertType.ERROR,ex.getMessage());
                a.show();
            }
        }
    }
    
    Usuario crearNuevoUsuario(String value){
        Usuario usuario;
        if(value.equals("Comprador")){
              int idComp = Util.nextID(usuarios);
              usuario = new Comprador(idComp,nametxt.getText(),lastnametxt.getText(),orgtxt.getText(),emailtxt.getText(),Util.toHexString(Util.getSHA(passtxt.getText())));
        }
        else if(value.equals("Vendedor")){
            int idVend = Util.nextID(usuarios);
            usuario = new Vendedor(idVend,nametxt.getText(),lastnametxt.getText(),orgtxt.getText(),emailtxt.getText(),Util.toHexString(Util.getSHA(passtxt.getText())));
        }
        else{
            int id = Util.nextID(usuarios);
            usuario = new Usuario(id,nametxt.getText(),lastnametxt.getText(),orgtxt.getText(),emailtxt.getText(),Util.toHexString(Util.getSHA(passtxt.getText())));
        }
        return usuario;
    }
}
