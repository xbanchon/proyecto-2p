/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.model.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger; 
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Xavier Eduardo
 */
public class Util {
    
    private Util(){}
    
    //Método para asignar IDs a partir del tamaño de una lista. 
    //Se asume que en ningún momento se eliminan objetos de esa lista.
    public static int nextID(ArrayList<Usuario> usuarios){
        int id = 0;
        if(!usuarios.isEmpty()){
            id = usuarios.size();
        }
        return id;
    }
    
    public static byte[] getSHA(String string){ 
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-256");
        }
        catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException(e);
        }
        return md.digest(string.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);  
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
        return hexString.toString(); 
    }
   
    public static void guardarCredencialesRegistro(ArrayList<String> credencialesL){
        try(ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("credenciales.ser") ) ){
            out.writeObject(credencialesL);
            out.flush();
        }
        catch(IOException ex){}
    }
    
    public static ArrayList<String> leerCredencialesRegistro(){
        ArrayList<String> credencialesL = null;
        try(ObjectInputStream oin = new ObjectInputStream(new FileInputStream("credenciales.ser"))){
            credencialesL = (ArrayList<String>)oin.readObject();
        }
        catch(FileNotFoundException ex){}
        catch(IOException ex){}
        finally{
            return credencialesL;
        }
    }
    
    public static void saveNewCredentials(String email, String pass){
        String hashPass = Util.toHexString(Util.getSHA(pass));
        String newCredential = email + "," + hashPass;
        ArrayList<String> credencialesL = leerCredencialesRegistro();
        for(String linea : credencialesL){
            if(linea.startsWith(email)){
                credencialesL.remove(linea);
                credencialesL.add(newCredential);
            }
            
        }
        guardarCredencialesRegistro(credencialesL);
    }
    
    public static boolean validarCredenciales(String correo, String clave){
        ArrayList<String> credencialesL = leerCredencialesRegistro();
        if(credencialesL != null){
            for(String registro : credencialesL){
                if(registro.startsWith(correo)){
                    String[] tokens = registro.split(",");
                    if(tokens[1].equals(Util.toHexString(Util.getSHA(clave))))
                        return true;
                }
            }
        }
        return false;
        
    }
    
    public static boolean checkRegistro(String usuario){
        ArrayList<String> credencialesL = leerCredencialesRegistro();
        for(String registro : credencialesL){
            if(registro.startsWith(usuario))
                return true;
        }
        return false;
    }
    
    public static boolean validarCorreo(String correo){
        if(correo.endsWith("@gmail.com")){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void enviarMail(String correoCompr) {
        Properties propmail = new Properties();
        try(InputStream ins = new FileInputStream("mail.properties")){
            propmail.load(ins);
        }
        catch(FileNotFoundException ex){} 
        catch(IOException ex){}
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.mail.sender", propmail.getProperty("email"));
        props.put("mail.smtp.user", "Vendedor");
        props.put("mail.smtp.clave", propmail.getProperty("password")); 
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.port", "587"); 

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress((String)props.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoCompr));
            message.setSubject("Oferta por vehículo");
            message.setText("Su oferta ha sido aceptada!");
            Transport transport = session.getTransport("smtp");
            transport.connect(propmail.getProperty("email"), propmail.getProperty("password"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {}
    }
}
