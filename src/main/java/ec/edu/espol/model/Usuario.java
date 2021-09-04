/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Xavier Eduardo
 */
public class Usuario implements Serializable{
    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    protected String correo;
    protected String clave;

    public Usuario(int id, String nombres, String apellidos, String organizacion, String correo, String clave) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public void guardarUsuarios(ArrayList<Usuario> usuarios){
        usuarios.add(this);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("usuarios.ser")))//Nombre de archivo provisional
        {
            out.writeObject(usuarios);
            out.flush();
        }
        catch(IOException e){
        }
    }

    public static ArrayList<Usuario> leerUsuarios(){
        ArrayList<Usuario> usuarios = null;
        try(ObjectInputStream oin = new ObjectInputStream(new FileInputStream("usuarios.ser"))){
            usuarios = (ArrayList<Usuario>)oin.readObject();
        }
        catch(FileNotFoundException ex){}
        catch(IOException ex){}
        catch(ClassNotFoundException ex){}
        finally{
            return usuarios;
        }
    }
    
    public static Usuario searchByID(ArrayList<Usuario> usuarios, int idUsuario){
        for(Usuario usuario: usuarios){
            if(usuario.id == idUsuario)
                return usuario;
        }
        return null;
    }
    
    public static Usuario searchUsuarioByCorreo(String correo){
        Usuario usuario = null;
        for(Usuario user: leerUsuarios()){
            if( (user.correo).equals(correo) )
                usuario = user;
        }
        return usuario;
    }
    
    public String getUserRole(){
        if(this instanceof Comprador)
            return "Comprador";
        else if(this instanceof Vendedor)
            return "Vendedor";
        else
            return "Comprador y Vendedor";
    }
    
    public void changeUserRole(String newRole) {
        if(!this.getUserRole().equals(newRole)){
            ArrayList<Usuario> users = leerUsuarios();
            for(Usuario usuario: users){
                if(newRole.equals("Comprador")){
                    usuario = (Comprador) this;
                }
                else if(newRole.equals("Vendedor")){
                    usuario = (Vendedor) this;
                }
                else{
                    
                }
            }
        }
        
    }
    
    public ArrayList<Oferta> recuperarOfertasComprador(){
        ArrayList<Oferta> ofertas = Oferta.leerArchivo();
        ArrayList<Oferta> compradorOfertas = new ArrayList<>();
        for(Oferta oferta : ofertas){
            if(oferta.getIdComprador() == this.id){
                compradorOfertas.add(oferta);
            }
        }
        return compradorOfertas;
    }
    
    public ArrayList<Vehiculo> recuperarVehiculosVendedor(){
        ArrayList<Vehiculo> vehiculos = Vehiculo.leerArchivo();
        ArrayList<Vehiculo> vendedorVehiculos = new ArrayList<>();
        for(Vehiculo vehiculo : vehiculos){
            if(vehiculo.getIdVendedor() == this.id){
                vendedorVehiculos.add(vehiculo);
            }
        }
        return vendedorVehiculos;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", organizacion=" + organizacion + ", correo=" + correo + ", clave=" + clave + '}';
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
        
        return (this.correo).equals(other.correo);
    }
    
    
}
