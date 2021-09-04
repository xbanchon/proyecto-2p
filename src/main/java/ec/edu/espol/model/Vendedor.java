/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Xavier Eduardo
 */
public class Vendedor extends Usuario{
    private ArrayList<Vehiculo> vehiculos;
    
    public Vendedor(int id, String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(id, nombres, apellidos, organizacion, correo, clave);
        this.vehiculos = new ArrayList<>();
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
 /*
    public static void nextVendedor(Scanner sc, String nomArchivo){
        System.out.println("Ingrese sus nombres: ");
        String nombres = sc.nextLine();
        System.out.println("Ingrese sus apellidos: ");
        String apellidos = sc.nextLine();
        System.out.println("Ingrese el nombre de su organización: ");
        String organizacion = sc.nextLine();
        String correoEl;
        do{
            System.out.println("Ingrese su correo: ");
            correoEl = sc.nextLine();
        }while(!Usuario.validarCorreo(correoEl));
        if(Vendedor.searchByCorreo(Vendedor.leerArchivo("vendedores.txt"), correoEl.toLowerCase())== null){
            System.out.println("Ingrese una clave: ");
            String clave = sc.nextLine();
            String hashClave = Util.toHexString(Util.getSHA(clave));
            int id = Util.nextID(nomArchivo);
            Vendedor vendedor = new Vendedor(id,nombres,apellidos,organizacion,correoEl.toLowerCase(),hashClave);
            vendedor.guardarArchivo(nomArchivo);
            System.out.println("\n ¡Ud. se ha registrado con éxito!"); 
        }
        else{
            if(correoEl.equals(Vendedor.searchByCorreo(Vendedor.leerArchivo("vendedores.txt"), correoEl).getCorreo())){
                System.out.println("Registro incorrecto. El correo ingresado ya está en uso.");
            }
            else{
                System.out.println("Ingrese una clave: ");
                String clave = sc.nextLine();
                String hashClave = Util.toHexString(Util.getSHA(clave));
                int id = Util.nextID(nomArchivo);
                Vendedor vendedor = new Vendedor(id,nombres,apellidos,organizacion,correoEl,hashClave);
                vendedor.guardarArchivo(nomArchivo);
                System.out.println("\n ¡Ud. se ha registrado con éxito!");  
            }
        }
    }*/    
    
    public static Vendedor searchByCorreo(ArrayList<Vendedor> vendedores, String correo){
        for(Vendedor vendedor: vendedores){
            if(correo.equals(vendedor.getCorreo()))
                return vendedor;
        }
        return null;
    }
    
    public void aceptarOferta(Oferta oferta ,Vehiculo vehiculo){
        Vehiculo delVehiculo = null;
        ArrayList<Vehiculo> vehiculosUpdate = Vehiculo.leerArchivo();
        for(Vehiculo veh: vehiculosUpdate){
            if(veh.getPlaca().equals(vehiculo.getPlaca())){
                delVehiculo = veh;
            }
        }
        vehiculosUpdate.remove(delVehiculo.getId()-1);
        Util.enviarMail(oferta.getIdComprador());
        //Mostrar por alerta un mensaje que indique que la oferta ha sido aceptada con éxito
        //e indicar que se notificará al comprador por medio de un correo.
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
        final Vendedor other = (Vendedor) obj;
        return (this.correo).equals(other.correo);
    }

    @Override
    public String toString() {
        return "Vendedor{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", organizacion=" + organizacion + ", correo=" + correo + ", clave=" + clave + '}';
    }
    
    
}
