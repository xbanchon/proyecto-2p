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
public class Usuario implements Serializable{
    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    protected String correo;
    protected transient String clave;
    private String rol;

    public Usuario(int id, String nombres, String apellidos, String organizacion, String correo, String clave) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        if(this instanceof Comprador)
            this.rol = "Comprador";
        else if(this instanceof Vendedor)
            this.rol = "Vendedor";
        else
            this.rol = "Comprador y Vendedor";
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
    
    public String getUserRole(){
        return rol;
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
    
    public void setUserRole(String rol){
        this.rol = rol;
    }
    
    public void changeUserRole(ArrayList<Usuario> users, String newRole) {
        if(!this.getUserRole().equals(newRole)){
            //ArrayList<Usuario> users = leerUsuarios();
            for(Usuario usuario: users){
                if(this.equals(usuario)){
                    if(newRole.equals("Comprador"))
                        usuario.setUserRole("Comprador");
                    else if(newRole.equals("Vendedor"))
                        usuario.setUserRole("Vendedor");
                    else
                        usuario.setUserRole("Comprador y Vendedor");  
                }
            }
            guardarUsuarios(users);
        } 
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
            if( (user.getCorreo()).equals(correo) )
                usuario = user;
        }
        return usuario;
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
