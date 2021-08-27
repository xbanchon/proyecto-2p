/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Xavier Eduardo
 */
public class Oferta {
    private int id;
    private int idComprador;
    private Comprador comprador;
    private int idVehiculo;
    private Vehiculo vehiculo;
    private double precioOfrecido;
    private String correoCompr;

    public Oferta(int id, int idComprador, int idVehiculo, double precioOfrecido, String correoCompr) {
        this.id = id;
        this.idComprador = idComprador;
        this.idVehiculo = idVehiculo;
        this.precioOfrecido = precioOfrecido;
        this.correoCompr = correoCompr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getPrecioOfrecido() {
        return precioOfrecido;
    }

    public void setPrecioOfrecido(double precioOfrecido) {
        this.precioOfrecido = precioOfrecido;
    }

    public String getCorreoCompr() {
        return correoCompr;
    }

    public void setCorreoCompr(String correoCompr) {
        this.correoCompr = correoCompr;
    }
    
    public static void linkInfo(ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, ArrayList<Comprador> compradores){
        for(Oferta oferta: ofertas){
            Vehiculo vehiculo = Vehiculo.searchByID(vehiculos, oferta.getIdVehiculo());
            Comprador comprador = Comprador.searchByID(compradores, oferta.getIdComprador());
            vehiculo.getOfertas().add(oferta);
            comprador.getOfertas().add(oferta);
            oferta.setVehiculo(vehiculo);
            oferta.setComprador(comprador);
        }
    }
    
    public static Oferta searchByID(ArrayList<Oferta> ofertas, int idOferta){
        for(Oferta oferta: ofertas){
            if(oferta.getId() == idOferta)
                return oferta;
        }
        return null;
    }
    
    public void guardarArchivo(ArrayList<Oferta> ofertas){
        ofertas.add(this);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ofertas.ser"))){
            out.writeObject(ofertas);
            out.flush();
        }
        catch(IOException ex){}
    }
    
    public static ArrayList<Oferta> leerArchivo(){
        ArrayList<Oferta> ofertas = null;
        try(ObjectInputStream oin = new ObjectInputStream(new FileInputStream("ofertas.ser"))){
            ofertas = (ArrayList<Oferta>)oin.readObject();
        }
        catch(FileNotFoundException ex){}
        catch(IOException ex){}
        catch(ClassNotFoundException ex){}
        finally{
            return ofertas;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Oferta other = (Oferta) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Correo del comprador: " + correoCompr + "\n Precio Ofertado: " + precioOfrecido;
    }
    
}
