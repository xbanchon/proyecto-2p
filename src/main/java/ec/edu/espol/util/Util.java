/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Vendedor;
import java.io.File;
import java.util.Scanner;
import java.math.BigInteger; 
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;/*


/**
 *
 * @author Xavier Eduardo
 */
public class Util {
    
    private Util(){}
    
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

    public static void menu(){
        System.out.println("Bienvenido al servicio de Compra-Venta de vehículos de segunda mano");
        System.out.println("\n Escoga una opción: \n1.Vendedor \n2.Comprador \n3.Salir");
        
        Scanner sc = new Scanner(System.in);
        int numOpcion = sc.nextInt();
        
        switch(numOpcion){
            case 1://Vendedor
                System.out.println("Usted ha elegido VENDEDOR");
                int opcVendedor;
                do{
                System.out.println("\n Escoga una opción: \n1.Registrarse \n2.Ingresar un nuevo vehiculo \n3.Aceptar ofertas \n4.Regresar");
                opcVendedor = sc.nextInt();
                Util.menuVendedor(opcVendedor);
                }while(opcVendedor<4);
                break;
            case 2://Comprador
                System.out.println("Usted ha elegido COMPRADOR");
                int opcComprador;
                do{
                System.out.println("\n Escoga una opción: \n1.Registrarse \n2.Ofertar por un vehiculo \n3.Regresar");
                opcComprador = sc.nextInt();
                Util.menuComprador(opcComprador);
                }while(opcComprador<4);
                break;
            case 3://Terminar el programa
                System.exit(0);
                break;
            default:
                
        }
    }
    
    public static void menuVendedor(int opcVendedor){
        String correo, clave;
        Vendedor vendedor;
        Scanner sc = new Scanner(System.in);
        ArrayList<Vehiculo> vehiculos = Vehiculo.leerArchivo("vehiculos.txt");
        ArrayList<Vendedor> vendedores = Vendedor.leerArchivo("vendedores.txt");
        ArrayList<Comprador> compradores = Comprador.leerArchivo("compradores.txt");
        ArrayList<Oferta> ofertas = Oferta.leerArchivo("ofertas.txt");
        if(vehiculos.size() > 0){
            Vehiculo.linkInfo(vehiculos, vendedores);
            Oferta.linkInfo(vehiculos, ofertas, compradores);
        }
        switch(opcVendedor){
            case 1://Registrar vendedor
                //Vendedor.nextVendedor(sc,"vendedores.txt");
                break;
            case 2://Ingresar nuevo vehículo
                do{
                System.out.println("Ingrese su correo: ");
                correo = sc.next();
                System.out.println("Ingrese su clave:");
                clave = sc.next();
                if(Vendedor.validarInfo(correo,clave)){
                    vendedor = Vendedor.searchByCorreo(vendedores,correo);
                    vendedor.ingresarVehiculo(sc);
                }
                else
                    System.out.println("¡CORREO O CLAVE INCORRECTOS! Inténtelo de nuevo.");
                }while(!Vendedor.validarInfo(correo,clave));//validar credenciales
                break;
            case 3://Aceptar ofertas
                do{
                System.out.println("Ingrese su correo: ");
                correo = sc.next();
                System.out.println("Ingrese su clave:");
                clave = sc.next();
                if(Vendedor.validarInfo(correo,clave)){
                    vendedor = Vendedor.searchByCorreo(vendedores,correo);
                    Util.menuAceptarOferta(sc, vendedor);
                }
                else
                    System.out.println("¡CORREO O CLAVE INCORRECTOS! Inténtelo de nuevo.");
                }while(!Vendedor.validarInfo(correo,clave));//validar credenciales
                break;
            case 4://Regresar al menu pincipal
                Util.menu();
                break;
            default:
        }
    }
    
    public static void menuComprador(int opcComprador){
        String correo, clave;
        Comprador comprador;
        Scanner sc = new Scanner(System.in);
        switch(opcComprador){
            case 1://Registrar comprador
                //Comprador.nextComprador(sc,"compradores.txt");
                break;
            case 2://Ofertar por vehículo
                do{
                System.out.println("Ingrese su correo: ");
                correo = sc.next();
                System.out.println("Ingrese su clave: ");
                clave = sc.next();
                if(Comprador.validarInfo(correo,clave)){
                    comprador = Comprador.searchByCorreo(Comprador.leerArchivo("compradores.txt"), correo);
                    ArrayList<Vehiculo> vehFiltr = Vehiculo.searchVehiculos(Vehiculo.leerArchivo("vehiculos.txt"), sc);
                    if(vehFiltr.isEmpty()){
                        System.out.println("Intente realizando una nueva búsqueda. \n");
                    }
                    else{
                        int idVehiculo=1;
                        int idVehEsc = Vehiculo.escogerVehiculo(vehFiltr, idVehiculo, sc);
                        comprador.ofertar(sc,idVehEsc);
                    }
                }
                }while(!Comprador.validarInfo(correo,clave));//validar credenciales
                break;
            case 3://Regresar al menu principal
                Util.menu();
                break;
            default:
        }  
    }
    
    public static void menuAceptarOferta(Scanner sc, Vendedor vendedor){
        System.out.println("Ingrese la placa del vehículo que desea consultar: ");
        String placaVehiculo = sc.next();
        Vehiculo.displayOferta(placaVehiculo,vendedor);      
    }
    
    public static int escogerOpcion(Scanner sc){//Menu Ver Vehiculos.
        int opc;
        do{
            opc = sc.nextInt();
        }while(opc<1 && opc>3);
        return opc;
    }
   /*
    public static void enviarMail(String correoCompr, String correoVend) {
        Scanner sc = new Scanner(System.in);
        String usuario = correoVend.split("@")[0];  //Para la dirección usuario@gmail.com
        System.out.println("Ingrese la contraseña de su cuenta de G-Mail:");
        sc.useDelimiter("\n");
        String pass = sc.nextLine();
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.mail.sender", correoVend);
        props.put("mail.smtp.user", usuario);
        props.put("mail.smtp.clave", pass);  //Clave de la cuenta (Se la pedirá al usuario antes de enviar el correo) 
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
            transport.connect(usuario,pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   
        }
    }*/
}
