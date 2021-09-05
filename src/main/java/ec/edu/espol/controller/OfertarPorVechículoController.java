/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.InfoVehiculo;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.VehiculosException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    @FXML
    private ComboBox<String> VCBox;
    private TextField TRecorrrido;
    private TextField TAnio;
    private TextField TPrecio;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.vehiculos = Vehiculo.leerArchivo();
        ArrayList<String> opciones = new ArrayList<>();
        String[] opcArray = new String[]{"Auto","Camioneta","Moto"};
        Collections.addAll(opciones, opcArray);
        VCBox.setItems(FXCollections.observableArrayList(opciones));
        
        CTipo.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, String>("tipo"));
        CAnio.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, Integer>("año"));
        CRecorrido.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, Double>("recorrido"));
        CPrecio.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, Double>("precio"));
        CFoto.setCellValueFactory(new PropertyValueFactory<InfoVehiculo, String>("foto"));

        
    }    

    @FXML
    private void buscarVehiculos(MouseEvent event) {
        ObservableList<InfoVehiculo> vehiculosOB = FXCollections.observableArrayList();
        if (VCBox.getValue()==null && TRecorrrido.getText()==null && TAnio.getText()==null && TPrecio.getText()==null){
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : vehiculos){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()!=null && TRecorrrido.getText()==null && TAnio.getText()==null && TPrecio.getText()==null){
            ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : vehiculosPorTipo){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()==null && TRecorrrido.getText()!=null && TAnio.getText()==null && TPrecio.getText()==null){
            ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : vehiculosPorRecorrido){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()==null && TRecorrrido.getText()==null && TAnio.getText()!=null && TPrecio.getText()==null){
            ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : vehiculosPorAnio){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()==null && TRecorrrido.getText()==null && TAnio.getText()==null && TPrecio.getText()!=null){
            ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : vehiculosPorPrecio){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()!=null && TRecorrrido.getText()!=null && TAnio.getText()==null && TPrecio.getText()==null){
            ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
            ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorRecorrido);
            listCopy1.retainAll(vehiculosPorTipo);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()!=null && TRecorrrido.getText()==null && TAnio.getText()!=null && TPrecio.getText()==null){
            ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
            ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorAnio);
            listCopy1.retainAll(vehiculosPorTipo);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()!=null && TRecorrrido.getText()==null && TAnio.getText()==null && TPrecio.getText()!=null){
            ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
            ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorPrecio);
            listCopy1.retainAll(vehiculosPorTipo);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()==null && TRecorrrido.getText()!=null && TAnio.getText()!=null && TPrecio.getText()==null){
            ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
            ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorRecorrido);
            listCopy1.retainAll(vehiculosPorAnio);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()==null && TRecorrrido.getText()==null && TAnio.getText()!=null && TPrecio.getText()!=null){
            ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
            ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorPrecio);
            listCopy1.retainAll(vehiculosPorAnio);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()==null && TRecorrrido.getText()!=null && TAnio.getText()==null && TPrecio.getText()!=null){
            ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
            ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorRecorrido);
            listCopy1.retainAll(vehiculosPorPrecio);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()!=null && TRecorrrido.getText()!=null && TAnio.getText()!=null && TPrecio.getText()==null){
            ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
            ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
            ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorTipo);
            listCopy1.retainAll(vehiculosPorRecorrido);
            listCopy1.retainAll(vehiculosPorAnio);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()!=null && TRecorrrido.getText()!=null && TAnio.getText()==null && TPrecio.getText()!=null){
            ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
            ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
            ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorTipo);
            listCopy1.retainAll(vehiculosPorRecorrido);
            listCopy1.retainAll(vehiculosPorPrecio);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()!=null && TRecorrrido.getText()==null && TAnio.getText()!=null && TPrecio.getText()!=null){
            ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
            ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
            ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorTipo);
            listCopy1.retainAll(vehiculosPorAnio);
            listCopy1.retainAll(vehiculosPorPrecio);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()==null && TRecorrrido.getText()!=null && TAnio.getText()!=null && TPrecio.getText()!=null){
            ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
            ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
            ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorAnio);
            listCopy1.retainAll(vehiculosPorRecorrido);
            listCopy1.retainAll(vehiculosPorPrecio);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
        else if (VCBox.getValue()!=null && TRecorrrido.getText()!=null && TAnio.getText()!=null && TPrecio.getText()!=null){
            ArrayList<Vehiculo> vehiculosPorTipo = Vehiculo.searchByTipoVehiculo(vehiculos, VCBox.getValue());
            ArrayList<Vehiculo> vehiculosPorAnio = Vehiculo.searchByYear(vehiculos, Integer.parseInt(TAniomax.getText()), Integer.parseInt(TAniomin.getText()));
            ArrayList<Vehiculo> vehiculosPorRecorrido = Vehiculo.searchByRecorrido(vehiculos, Double.parseDouble(TRecorridomax.getText()), Double.parseDouble(TRecorridomin.getText()));
            ArrayList<Vehiculo> vehiculosPorPrecio = Vehiculo.searchByPrecio(vehiculos, Double.parseDouble(TPreciomax.getText()), Double.parseDouble(TPreciomin.getText()));
            ArrayList<Vehiculo> listCopy1 = new ArrayList<Vehiculo>(vehiculosPorTipo);
            listCopy1.retainAll(vehiculosPorRecorrido);
            listCopy1.retainAll(vehiculosPorPrecio);
            listCopy1.retainAll(vehiculosPorAnio);
            InfoVehiculo infovehi = new InfoVehiculo();
            ArrayList<InfoVehiculo> vehiMostrados = new ArrayList<InfoVehiculo>();
            try{
                for (Vehiculo v : listCopy1){
                    infovehi.setTipo(v.getTipoVehiculo());
                    infovehi.setAnio(v.getAnio());
                    infovehi.setRecorrido(v.getRecorrido());
                    infovehi.setPrecio(v.getPrecio());
                    vehiMostrados.add(infovehi);
                }
            }
            catch (VehiculosException v){
            Alert a  = new Alert(Alert.AlertType.WARNING,"No se encontraron vehículos con los parámetros buscados");
            a.show();
            }
            vehiculosOB = FXCollections.observableArrayList(vehiMostrados);
            vehiculosTable.setItems(vehiculosOB);
        }
    }
    
}
