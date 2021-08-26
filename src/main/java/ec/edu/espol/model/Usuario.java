/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Xavier Eduardo
 */
public class Usuario {
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
    
    public static void guardarArchivo(ArrayList<Usuario> usuarios,String nomArchivo){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomArchivo)))
        {
            out.writeObject(usuarios);
            out.flush();
        }
        catch(IOException e){
        }
    }
    
    public static boolean validarCorreo(String correo){
        if(correo.endsWith("@gmail.com")){
            return true;
        }
        else{
            return false;
        }
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
        if (!this.correo.equals(other.correo)) {
            return false;
        }
        return true;
    }
    
    
}
