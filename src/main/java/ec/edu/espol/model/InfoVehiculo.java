/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Erick
 */
public class InfoVehiculo {
    private SimpleStringProperty tipo = new SimpleStringProperty();
    private SimpleIntegerProperty anio = new SimpleIntegerProperty();
    private SimpleDoubleProperty recorrido = new SimpleDoubleProperty();
    private SimpleDoubleProperty precio = new SimpleDoubleProperty();
    private SimpleStringProperty imagen = new SimpleStringProperty();

   public InfoVehiculo(){
       
   }

    public String getTipo() {
        return tipo.get();
    }

    public int getAnio() {
        return anio.get();
    }

    public double getRecorrido() {
        return recorrido.get();
    }

    public double getPrecio() {
        return precio.get();
    }

    public String getImagen() {
        return imagen.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public void setAnio(int anio) {
        this.anio.set(anio);
    }

    public void setRecorrido(SimpleDoubleProperty recorrido) {
        this.recorrido = recorrido;
    }

    public void setPrecio(SimpleDoubleProperty precio) {
        this.precio = precio;
    }

    public void setImagen(SimpleStringProperty imagen) {
        this.imagen = imagen;
    }
    
   
}
