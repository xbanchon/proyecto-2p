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
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Xavier Eduardo
 */
public class Vehiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private int anio;
    private double recorrido;
    private String color;
    private String tipoCombustible;
    private double precio;
    private int idVendedor;
    private Vendedor vendedor;
    private String tipoVehiculo;
    private String vidrios;//no apica para motos
    private String transmision;//no apica para motos
    private String traccion;//aplica para camionetas
    private ArrayList<Oferta> ofertas;
    
    public Vehiculo(int id, String placa, String marca, String modelo, String tipoMotor, int anio, double recorrido, 
            String color, String tipoCombustible, double precio, int idVendedor, String vidrios, String transmision){//constructor carro
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.idVendedor = idVendedor;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.ofertas = new ArrayList<>();
        
    }
    
    public Vehiculo(int id, String placa, String marca, String modelo, String tipoMotor, int anio, double recorrido, 
            String color, String tipoCombustible, double precio, int idVendedor, String vidrios, String transmision, String traccion){//constructor camioneta
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.idVendedor = idVendedor;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.traccion = traccion;
        this.ofertas = new ArrayList<>();
        
    }
    
    public Vehiculo(int id, String placa, String marca, String modelo, String tipoMotor, int anio, double recorrido, 
            String color, String tipoCombustible, double precio, int idVendedor){
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.idVendedor = idVendedor;
        this.ofertas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
  
    public static ArrayList<Vehiculo> searchByRecorrido(ArrayList<Vehiculo> vehiculos, double maxRec, double minRec){
        ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.recorrido >= minRec && vehiculo.recorrido <= maxRec)
                vehFiltr.add(vehiculo);
        }
        return vehFiltr;
    }
    
    public static ArrayList<Vehiculo> searchByYear(ArrayList<Vehiculo> vehiculos, int maxYr, int minYr){
        ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.anio >= minYr && vehiculo.anio <= maxYr)
                vehFiltr.add(vehiculo);
        }
        return vehFiltr;  
    }
    
    public static ArrayList<Vehiculo> searchByPrecio(ArrayList<Vehiculo> vehiculos, double maxPr, double minPr){
        ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.getPrecio() >= minPr && vehiculo.getPrecio() <= maxPr)
                vehFiltr.add(vehiculo);
        }
        return vehFiltr;
    }
    
    public static ArrayList<Vehiculo> searchByTipoVehiculo(ArrayList<Vehiculo> vehiculos, String tipoVehiculo){
        ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
        for(Vehiculo vehiculo: vehiculos){
            if(tipoVehiculo.equals(vehiculo.getTipoVehiculo()))
                vehFiltr.add(vehiculo);
        }
        return vehFiltr;
    }
    
    public static ArrayList<Vehiculo> searchByIDVendedor(ArrayList<Vehiculo> vehiculos, Vendedor vendedor){
        ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.idVendedor == vendedor.getId())
                vehFiltr.add(vehiculo);
        }
        return vehFiltr;
    }
    
    public static Vehiculo searchByPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        Vehiculo vehiculo = null;
        for(Vehiculo v: vehiculos){
            if(placa.equals(v.placa))
                vehiculo = v;
        }
        return vehiculo;
    }
    
    public static Vehiculo searchByID(ArrayList<Vehiculo> vehiculos, int idVehiculo){
        Vehiculo vehiculo = null;
        for(Vehiculo v: vehiculos){
            if(v.id == idVehiculo)
                vehiculo = v;
        }
        return vehiculo;
    }
    /*             
    public static void linkInfo(ArrayList<Vehiculo> vehiculos ,ArrayList<Vendedor> vendedores){
        for(Vehiculo vehiculo: vehiculos){
            Vendedor vendedor = Vendedor.searchByID(vendedores, vehiculo.getIdVendedor());
            vehiculo.setVendedor(vendedor);
            vendedor.getVehiculos().add(vehiculo);
        }
    }*/
    
    public void guardarArchivo(ArrayList<Vehiculo> vehiculos){
        vehiculos.add(this);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("vehiculos.ser"))){
            out.writeObject(vehiculos);
            out.flush();
        }
        catch(IOException ex){}
    }
    
    public static ArrayList<Vehiculo> leerArchivo(){
        ArrayList<Vehiculo> vehiculos = null;
        try(ObjectInputStream oin = new ObjectInputStream(new FileInputStream("vehiculos.ser"))){
            vehiculos = (ArrayList<Vehiculo>)oin.readObject();
        }
        catch(FileNotFoundException ex){}
        catch(IOException ex){}
        catch(ClassNotFoundException ex){}
        finally{
            return vehiculos;
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
        final Vehiculo other = (Vehiculo) obj;
        return (this.placa).equals(other.placa);
    }

    @Override
    public String toString() {
        if(this.traccion == null){//carro
            return marca + " "  + modelo + " Año " + anio + "\n Precio:" + precio + "\n Placa: " + placa + "\n Motor: " + tipoMotor + "\n Recorrido: " + recorrido + "\n Color: " + color + "\n Combustible: " + tipoCombustible + "\n Vidrios: " + vidrios + "\n Transmision: " + transmision;
        }
        else if(this.traccion == null && this.transmision == null && this.vidrios == null){//motocicleta
            return marca + " "  + modelo + " Año " + anio + "\n Precio:" + precio + "\n Placa: " + placa + "\n Motor: " + tipoMotor + "\n Recorrido: " + recorrido + "\n Color: " + color + "\n Combustible: " + tipoCombustible;
        }
        else//camioneta
            return marca + " " + modelo + " Año " + anio + "\n Precio:" + precio + "\n Placa: " + placa + "\n Motor: " + tipoMotor + "\n Recorrido: " + recorrido + "\n Color: " + color + "\n Combustible: " + tipoCombustible + "\n Vidrios: " + vidrios + "\n Transmision: " + transmision + "\n Traccion: " + traccion;
    }
  
}
