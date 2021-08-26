/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import ec.edu.espol.model.Oferta;

/**
 *
 * @author Xavier Eduardo
 */
public class Comprador extends Usuario {
    private ArrayList<Oferta> ofertas;

    public Comprador(int id, String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(id, nombres, apellidos, organizacion, correo, clave);
        this.ofertas = new ArrayList<>();
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    /*
    public static void nextComprador(Scanner sc,String nomArchivo){
        System.out.println("Ingrese sus nombres: ");
        String nombres = sc.next();
        System.out.println("Ingrese sus apellidos: ");
        String apellidos = sc.next();
        System.out.println("Ingrese el nombre de su organización: ");
        String organizacion = sc.next(); 
        String correoEl;
        do{
            System.out.println("Ingrese su correo: ");
            correoEl = sc.nextLine();
        }while(!Usuario.validarCorreo(correoEl));
        if(Comprador.searchByCorreo(Comprador.leerArchivo("compradores.txt"), correoEl.toLowerCase())== null){
            System.out.println("Ingrese una clave: ");
            String clave = sc.next();
            String hashClave = Util.toHexString(Util.getSHA(clave));
            int id = Util.nextID(nomArchivo);
            Comprador comprador = new Comprador(id,nombres,apellidos,organizacion,correoEl.toLowerCase(),hashClave);
            comprador.guardarArchivo(nomArchivo);
            System.out.println("\n ¡Ud. se ha registrado con éxito!"); 
        }
        else{
            if(correoEl.equals(Comprador.searchByCorreo(Comprador.leerArchivo("compradores.txt"), correoEl).getCorreo())){
                System.out.println("Registro incorrecto. El correo ingresado ya está en uso.");
            }
            else{
                System.out.println("Ingrese una clave: ");
                String clave = sc.next();
                String hashClave = Util.toHexString(Util.getSHA(clave));
                int id = Util.nextID(nomArchivo);
                Comprador comprador = new Comprador(id,nombres,apellidos,organizacion,correoEl,hashClave);
                comprador.guardarArchivo(nomArchivo);
                System.out.println("\n ¡Ud. se ha registrado con éxito!"); 
            }
        }
    }*/
    
    public static ArrayList<Comprador> leerArchivo(String nomArchivo){
        ArrayList<Comprador> compradores = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomArchivo))){
            while(sc.hasNextLine())
            {                
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Comprador comprador = new Comprador(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                compradores.add(comprador);
            }
        }
        catch(Exception e){
        }
        return compradores;
    }
    
    public static boolean validarInfo(String correo, String clave){
        String hashClave = Util.toHexString(Util.getSHA(clave));
        ArrayList<Comprador> compradores = Comprador.leerArchivo("compradores.txt");
        for(Comprador comprador: compradores){
            if(Objects.equals(comprador.correo, correo)){
                if(Objects.equals(comprador.clave, hashClave))
                    return true;
            }
        }
        System.out.println("¡CORREO O CLAVE INCORRECTOS! Inténtelo de nuevo.");
        return false;     
    }
    
    public void ofertar(Scanner sc,int idVehEsc){
        System.out.println("Ingrese el precio que desea ofertar: ");
        double precioOfertado = sc.nextDouble();
        Oferta.nextOferta(idVehEsc, this.id, precioOfertado, "ofertas.txt");
        System.out.println("Su oferta se ha realizado con éxito!");
    }
    
    public static Comprador searchByCorreo(ArrayList<Comprador> compradores, String correo){
        for(Comprador comprador: compradores){
            if(correo.equals(comprador.correo))
                return comprador;
        }
        return null;
    }
    
    public static Comprador searchByID(ArrayList<Comprador> compradores, int idComprador){
        for(Comprador comprador: compradores){
            if(comprador.id == idComprador)
                return comprador;
        }
        return null;
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Comprador{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", organizacion=" + organizacion + ", correo=" + correo + ", clave=" + clave + '}';
    }
    
    
    
    
}
