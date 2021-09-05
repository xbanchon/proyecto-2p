/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author Erick
 */
public class InfoVehiculo {
    private String tipo;
    private int anio;
    private double recorrido;
    private double precio;
    private String imagen;

    public InfoVehiculo(String tipo, int anio, double recorrido, double precio, String imagen) {
        this.tipo = tipo;
        this.anio = anio;
        this.recorrido = recorrido;
        this.precio = precio;
        this.imagen = imagen;
    }

    public InfoVehiculo() {
    }

    public String getTipo() {
        return tipo;
    }

    public int getAnio() {
        return anio;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
