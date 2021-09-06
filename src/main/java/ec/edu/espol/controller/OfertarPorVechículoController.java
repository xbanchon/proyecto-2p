/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.InfoVehiculo;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.VehiculosException;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Erick
 */
public class OfertarPorVechículoController implements Initializable {
    private ArrayList<Vehiculo> vehiculos;
    ObservableList<InfoVehiculo> vehiculosOB;
    @FXML
    private ComboBox<String> VCBox;
    @FXML
    private Button BuscarButton;
    @FXML
    private TableView<InfoVehiculo> vehiculosTable;
    @FXML
    private TableColumn<InfoVehiculo, String> CTipo;
    @FXML
    private TableColumn<InfoVehiculo, Integer> CAnio;
    @FXML
    private TableColumn<InfoVehiculo, Double> CRecorrido;
    @FXML
    private TableColumn<InfoVehiculo, Double> CPrecio;
    @FXML
    private TableColumn<InfoVehiculo, String> CFoto;
    @FXML
    private TextField TAniomin;
    @FXML
    private TextField TPreciomin;
    @FXML
    private TextField TAniomax;
    @FXML
    private TextField TPreciomax;
    @FXML
    private TextField TRecorridomin;
    @FXML
    private TextField TRecorridomax;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.vehiculos = Vehiculo.leerArchivo();
        vehiculosOB = FXCollections.observableArrayList();
        ArrayList<String> opciones = new ArrayList<>();
        String[] opcArray = new String[]{"Auto","Camioneta","Moto"};
        Collections.addAll(opciones, opcArray);
        VCBox.setItems(FXCollections.observableArrayList(opciones));
        
        CTipo.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, String>("tipo"));
        CAnio.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, Integer>("año"));
        CRecorrido.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, Double>("recorrido"));
        CPrecio.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, Double>("precio"));
        CFoto.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, String>("foto"));
        vehiculosTable.setItems(vehiculosOB);
        
    }    

    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXMLLoader("usermenu");
            App.setRoot(fxmlLoader);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo leer el archivo FXML.");
            a.show();
        }
    }
    
    @FXML
    private void buscarVehiculos(MouseEvent event) {
        if (VCBox.getValue()==null && "".equals(TRecorridomax.getText()) && "".equals(TAniomax.getText()) && "".equals(TPreciomax.getText())){
            InfoVehiculo infovehi = new InfoVehiculo();
            
            try{
                for (Vehiculo v : vehiculos){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }            
        }
        else if (VCBox.getValue()!=null && "".equals(TRecorridomax.getText()) && "".equals(TAniomax.getText()) && "".equals(TPreciomax.getText())){                     
            try{
                ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : vehiculosPorTipo){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()==null && !"".equals(TRecorridomax.getText()) && "".equals(TAniomax.getText()) && "".equals(TPreciomax.getText())){
            try{
                ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : vehiculosPorRecorrido){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()==null && "".equals(TRecorridomax.getText()) && !"".equals(TAniomax.getText()) && "".equals(TPreciomax.getText())){            
            try{
                ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : vehiculosPorAnio){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if ("".equals(VCBox.getValue()) && "".equals(TRecorridomax.getText()) && "".equals(TAniomax.getText()) && !"".equals(TPreciomax.getText())){            
            try{
                ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : vehiculosPorPrecio){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()!=null && !"".equals(TRecorridomax.getText()) && "".equals(TAniomax.getText()) && "".equals(TPreciomax.getText())){            
            try{
                ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
                ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorRecorrido);
                listCopy1.retainAll(vehiculosPorTipo);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()!=null && "".equals(TRecorridomax.getText()) && !"".equals(TAniomax.getText()) && "".equals(TPreciomax.getText())){          
            try{
                ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
                ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorAnio);
                listCopy1.retainAll(vehiculosPorTipo);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()!=null && "".equals(TRecorridomax.getText()) && "".equals(TAniomax.getText()) && !"".equals(TPreciomax.getText())){           
            try{
                ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
                ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorPrecio);
                listCopy1.retainAll(vehiculosPorTipo);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()==null && !"".equals(TRecorridomax.getText()) && !"".equals(TAniomax.getText()) && "".equals(TPreciomax.getText())){            
            try{
                ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
                ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorRecorrido);
                listCopy1.retainAll(vehiculosPorAnio);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()==null && "".equals(TRecorridomax.getText()) && !"".equals(TAniomax.getText()) && !"".equals(TPreciomax.getText())){           
            try{
                ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
                ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorPrecio);
                listCopy1.retainAll(vehiculosPorAnio);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()==null && !"".equals(TRecorridomax.getText()) && "".equals(TAniomax.getText()) && !"".equals(TPreciomax.getText())){           
            try{
                ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
                ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorRecorrido);
                listCopy1.retainAll(vehiculosPorPrecio);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()!=null && !"".equals(TRecorridomax.getText()) && !"".equals(TAniomax.getText()) && "".equals(TPreciomax.getText())){
            try{
                ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
                ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
                ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorTipo);
                listCopy1.retainAll(vehiculosPorRecorrido);
                listCopy1.retainAll(vehiculosPorAnio);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()!=null && !"".equals(TRecorridomax.getText()) && "".equals(TAniomax.getText()) && !"".equals(TPreciomax.getText())){
            
            try{
                ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
                ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
                ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorTipo);
                listCopy1.retainAll(vehiculosPorRecorrido);
                listCopy1.retainAll(vehiculosPorPrecio);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()!=null && "".equals(TRecorridomax.getText()) && !"".equals(TAniomax.getText()) && !"".equals(TPreciomax.getText())){            
            try{
                ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
                ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
                ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorTipo);
                listCopy1.retainAll(vehiculosPorAnio);
                listCopy1.retainAll(vehiculosPorPrecio);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if ("".equals(VCBox.getValue()) && !"".equals(TRecorridomax.getText()) && !"".equals(TAniomax.getText()) && !"".equals(TPreciomax.getText())){            
            try{
                ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
                ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
                ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorAnio);
                listCopy1.retainAll(vehiculosPorRecorrido);
                listCopy1.retainAll(vehiculosPorPrecio);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
        else if (VCBox.getValue()!=null && !"".equals(TRecorridomax.getText()) && !"".equals(TAniomax.getText()) && !"".equals(TPreciomax.getText())){          
            try{
                ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
                ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
                ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
                ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
                ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorTipo);
                listCopy1.retainAll(vehiculosPorRecorrido);
                listCopy1.retainAll(vehiculosPorPrecio);
                listCopy1.retainAll(vehiculosPorAnio);
                InfoVehiculo infovehi = new InfoVehiculo();
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(new SimpleDoubleProperty(v.getRecorrido()));
                    infovehi.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
                    vehiculosOB.add(infovehi);
                }
            }
            catch (Exception e){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
        }
    } 
}
