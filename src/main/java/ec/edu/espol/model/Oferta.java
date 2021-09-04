/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Xavier Eduardo
 */
public class Oferta implements Serializable{
    private int id;
    private String idComprador;
    private transient Comprador comprador;
    private int idVehiculo;
    private transient Vehiculo vehiculo;
    private double precioOfrecido;

    public Oferta(int id, String idComprador, int idVehiculo, double precioOfrecido) {
        this.id = id;
        this.idComprador = idComprador;
        this.idVehiculo = idVehiculo;
        this.precioOfrecido = precioOfrecido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(String idComprador) {
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

    /*
    public static void linkInfo(ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, ArrayList<Comprador> compradores){
        for(Oferta oferta: ofertas){
            Vehiculo vehiculo = Vehiculo.searchByID(vehiculos, oferta.getIdVehiculo());
            Comprador comprador = Comprador.searchByID(compradores, oferta.getIdComprador());
            vehiculo.getOfertas().add(oferta);
            comprador.getOfertas().add(oferta);
            oferta.setVehiculo(vehiculo);
            oferta.setComprador(comprador);
        }
    }*/
    
    public static Oferta searchByID(ArrayList<Oferta> ofertas, int idOferta){
        for(Oferta oferta: ofertas){
            if(oferta.getId() == idOferta)
                return oferta;
        }
        return null;
    }
    
     public static ArrayList<Oferta> recuperarOfertasComprador(String idComprador){
        ArrayList<Oferta> ofertas = leerArchivo();
        ArrayList<Oferta> compradorOfertas = new ArrayList<>();
        for(Oferta oferta : ofertas){
            if(oferta.getIdComprador().equals(idComprador)){
                compradorOfertas.add(oferta);
            }
        }
        return compradorOfertas;
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
        return "Correo del comprador: " + idComprador + "\n Precio Ofertado: " + precioOfrecido;
    }
    
}
