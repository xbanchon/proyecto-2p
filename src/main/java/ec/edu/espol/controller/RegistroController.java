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
import javafx.scene.control.Button;
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
    @FXML
    private Button returnbtn;
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
    private Button signupbtn;
    @FXML
    private ComboBox<String> cbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> opciones = new ArrayList<>();
        String[] opcArray = new String[]{"Comprador","Vendedor","Comprador y Vendedor"};
        Collections.addAll(opciones, opcArray);
        cbox.setItems(FXCollections.observableArrayList(opciones));
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
        if(passtxt.getText()==null || nametxt.getText()==null || lastnametxt.getText()==null || orgtxt.getText()==null || emailtxt.getText()==null || cbox.getValue() == null){
            Alert a = new Alert(AlertType.WARNING,"Rellene todos los campos.");
            a.show();
        }
        else{
            validarCampos();
            //Crear nuevo usuario
            Usuario usuario = crearNuevoUsuario(cbox.getValue(),"usuarios.ser");
            //Añadirlo a una lista de usuarios y mandarla a serializar
            usuarios.add(usuario);
            Usuario.guardarArchivo(usuarios,"usuarios.ser");//"usuarios.ser" es un nombre de archivo temporal.
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
                throw new InputMismatchException("Se ingresaron números cuando se esperaban letras.");
            }
            catch(InputMismatchException ex){
                Alert a = new Alert(AlertType.ERROR,ex.getMessage());
                a.show();
            }
        }
        else if(Usuario.validarCorreo(emailtxt.getText())){
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
    
    Usuario crearNuevoUsuario(String value, String nomArchivo){
        Usuario usuario;
        if(value.equals("Comprador")){
              int idComp = Util.nextID(usuarios);
              usuario = new Comprador(idComp,nametxt.getText(),nametxt.getText(),orgtxt.getText(),emailtxt.getText(),Util.toHexString(Util.getSHA(passtxt.getText())));
        }
        else if(value.equals("Vendedor")){
            int idVend = Util.nextID(usuarios);
            usuario = new Vendedor(idVend,nametxt.getText(),nametxt.getText(),orgtxt.getText(),emailtxt.getText(),Util.toHexString(Util.getSHA(passtxt.getText())));
        }
        else{
            int id = Util.nextID(usuarios);
            usuario = new Usuario(id,nametxt.getText(),nametxt.getText(),orgtxt.getText(),emailtxt.getText(),Util.toHexString(Util.getSHA(passtxt.getText())));
        }
        return usuario;
    }
}
