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
    }
    
    public static ArrayList<Vendedor> leerArchivo(String nomArchivo){
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomArchivo))){
            while(sc.hasNextLine())
            {                
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Vendedor vendedor = new Vendedor(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                vendedores.add(vendedor);
            }
        }
        catch(Exception e){
        }
        return vendedores;
    }
    
    public static boolean validarInfo(String correo, String clave){
        String hashClave = Util.toHexString(Util.getSHA(clave));
        ArrayList<Vendedor> vendedores = Vendedor.leerArchivo("vendedores.txt");
        for(Vendedor vendedor: vendedores){
            if(correo.equals(vendedor.getCorreo())){
                if(hashClave.equals(vendedor.getClave()))
                    return true;
            }
        }
        return false;     
    }
    
    public static Vendedor searchByCorreo(ArrayList<Vendedor> vendedores, String correo){
        for(Vendedor vendedor: vendedores){
            if(correo.equals(vendedor.getCorreo()))
                return vendedor;
        }
        return null;
    }
    
    public static Vendedor searchByID(ArrayList<Vendedor> vendedores, int idVendedor){
        for(Vendedor vendedor: vendedores){
            if(vendedor.id == idVendedor)
                return vendedor;
        }
        return null;
    }
    
    public void ingresarVehiculo(Scanner sc){ 
        System.out.println("Seleccione el tipo de vehículo a ingresar. \n 1.Carro \n 2.Camioneta \n 3.Motocicleta");
        int tipoVehiculo = sc.nextInt();
        do{
            switch(tipoVehiculo){
                case 1://carro
                    Vehiculo.nextVehiculo(sc,this.id,this,"vehiculos.txt", "carro");
                    System.out.println("Su vehículo ha sido ingresado con éxito!");
                    break;
                case 2://camioneta
                    Vehiculo.nextVehiculo(sc,this.id,this,"vehiculos.txt", "camioneta");
                    System.out.println("Su vehículo ha sido ingresado con éxito!");
                    break;
                case 3://motocicleta
                    Vehiculo.nextVehiculo(sc,this.id,this,"vehiculos.txt", "motocicleta");
                    System.out.println("Su vehículo ha sido ingresado con éxito!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }while(tipoVehiculo>3 && tipoVehiculo<1);
    }
    
    public void aceptarOferta(int idOferta ,Vehiculo vehiculo){
        Vehiculo delVehiculo = null;
        ArrayList<Vehiculo> vehiculosUpdate = Vehiculo.leerArchivo("vehiculos.txt");
        for(Vehiculo veh: vehiculosUpdate){
            if(veh.getPlaca().equals(vehiculo.getPlaca())){
                delVehiculo = veh;
            }
        }
        vehiculosUpdate.remove(delVehiculo.getId()-1);
        Vehiculo.overwriteFile("vehiculos.txt", vehiculosUpdate);
       // Util.enviarMail(Oferta.searchByID(vehiculo.getOfertas(), idOferta).getCorreoCompr(), this.correo);
        System.out.println("¡Oferta aceptada! \nSe enviará un correo al comprador indicando que su oferta fue aceptada.");
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
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", organizacion=" + organizacion + ", correo=" + correo + ", clave=" + clave + '}';
    }
    
    
}
