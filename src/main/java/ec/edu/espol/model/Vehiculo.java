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
    
    
    public static void nextVehiculo(Scanner sc,int idVendedor, Vendedor vendedor,String nomArchivo,String tipoVehiculo){
        System.out.println("Ingrese la placa del vehículo: ");
        String placa = sc.next();
        System.out.println("Ingrese la marca del vehiculo: ");
        String marca = sc.next();
        System.out.println("Ingrese el modelo:");
        String modelo = sc.next();
        System.out.println("Ingrese el tipo de motor del vehiculo: ");
        String tipoMotor = sc.next();
        System.out.println("Ingrese el año del vehículo: ");
        int anio = sc.nextInt();
        System.out.println("Ingrese el recorrido (en kilometros): ");
        double recorrido = sc.nextDouble();
        System.out.println("Ingrese el color del vehículo: ");
        String color = sc.next();
        System.out.println("Ingrese el tipo de combustible del vehículo: ");
        String tipoCombustible = sc.next();
        System.out.println("Ingrese el precio del vehículo: ");
        double precio = sc.nextDouble();
        int id = Util.nextID(nomArchivo);
        if(tipoVehiculo.equals("motocicleta")){
            Vehiculo vehiculo = new Vehiculo(id,placa,marca,modelo,tipoMotor,anio,recorrido,color,tipoCombustible,precio,idVendedor);
            vehiculo.setTipoVehiculo(tipoVehiculo);
            vehiculo.guardarArchivo(nomArchivo);
        }
        if(tipoVehiculo.equals("carro") || tipoVehiculo.equals("camioneta")){
            System.out.println("Ingrese el tipo de vidrios: ");
            String vidrios = sc.next();
            System.out.println("Ingrese la transmisión: ");
            String transmision = sc.next();
            if(tipoVehiculo.equals("camioneta")){
                System.out.println("Ingrese la tracción:");
                String traccion = sc.next();
                Vehiculo vehiculo = new Vehiculo(id,placa,marca,modelo,tipoMotor,anio,recorrido,color,tipoCombustible,precio,idVendedor,vidrios,transmision,traccion);
                vehiculo.setTipoVehiculo(tipoVehiculo);
                vehiculo.guardarArchivo(nomArchivo);
            }
            else{
                Vehiculo vehiculo = new Vehiculo(id,placa,marca,modelo,tipoMotor,anio,recorrido,color,tipoCombustible,precio,idVendedor,vidrios,transmision);
                vehiculo.setTipoVehiculo(tipoVehiculo);
                vehiculo.guardarArchivo(nomArchivo);
            }
        }
        linkInfo(leerArchivo(nomArchivo),Vendedor.leerArchivo("vendedores.txt"));
    }
    
    public static ArrayList<Vehiculo> searchByRecorrido(ArrayList<Vehiculo> vehiculos, Scanner sc){
        int opcion;
        do{
            System.out.println("Desea buscar por RECORRIDO? \n Ingrese una opción. 1:Si , 0:No");
            opcion = sc.nextInt();
        }while(opcion>1 && opcion<0);
        if(opcion == 1){
            System.out.println("Ingrese el recorrido mínimo: ");
            double recMin = sc.nextDouble();
            System.out.println("Ingrese el recorrido máximo: ");
            double recMax = sc.nextDouble();
            ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
            for(Vehiculo vehiculo: vehiculos){
                if(vehiculo.recorrido >= recMin && vehiculo.recorrido <= recMax)
                    vehFiltr.add(vehiculo);
            }
            return vehFiltr;
        }
        else
            return vehiculos;
    }
    
    public static ArrayList<Vehiculo> searchByYear(ArrayList<Vehiculo> vehiculos, Scanner sc){
        int opcion;
        do{
            System.out.println("Desea buscar por AÑO? \n Ingrese una opción. 1:Si , 0:No");
            opcion = sc.nextInt();
        }while(opcion>1 && opcion<0);
        if(opcion == 1){
            System.out.println("Ingrese el año mínimo: ");
            double minYr = sc.nextInt();
            System.out.println("Ingrese el año máximo: ");
            double maxYr = sc.nextInt();
            ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
            for(Vehiculo vehiculo: vehiculos){
                if(vehiculo.anio >= minYr && vehiculo.anio <= maxYr)
                    vehFiltr.add(vehiculo);
            }
            return vehFiltr;
            }
        else
            return vehiculos;
    }
    
    public static ArrayList<Vehiculo> searchByPrecio(ArrayList<Vehiculo> vehiculos,Scanner sc){
        int opcion;
        do{
            System.out.println("Desea buscar por PRECIO? \n Ingrese una opción. 1:Si , 0:No");
            opcion = sc.nextInt();
        }while(opcion>1 && opcion<0);
        if(opcion == 1){
            System.out.println("Ingrese el precio mínimo: ");
            double minPrecio = sc.nextDouble();
            System.out.println("Ingrese el precio máximo: ");
            double maxPrecio = sc.nextDouble();
            ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
            for(Vehiculo vehiculo: vehiculos){
                if(vehiculo.getPrecio() >= minPrecio && vehiculo.getPrecio() <= maxPrecio)
                    vehFiltr.add(vehiculo);
            }
            return vehFiltr;
        }
        else
            return vehiculos;
    }
    
    public static ArrayList<Vehiculo> searchByTipoVehiculo(ArrayList<Vehiculo> vehiculos, Scanner sc){
        int opcion;
        do{
            System.out.println("Desea buscar por TIPO VEHICULO? \n Ingrese una opción. 1:Si , 0:No");
            opcion = sc.nextInt();
        }while(opcion>1 && opcion<0);
        if(opcion == 1){
            ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
            System.out.println("Seleccione el tipo de vehículo a buscar. \n 1.Carro \n 2.Camioneta \n 3.Motocicleta");
            int opcBuscador = sc.nextInt();
            switch (opcBuscador) {
                case 1:
                    for(Vehiculo vehiculo: vehiculos){
                        if("carro".equals(vehiculo.getTipoVehiculo()))
                            vehFiltr.add(vehiculo);
                    }
                    return vehFiltr;
                case 2:
                    for(Vehiculo vehiculo: vehiculos){
                        if("camioneta".equals(vehiculo.getTipoVehiculo()))
                            vehFiltr.add(vehiculo);
                    }
                    return vehFiltr;
                case 3:
                    for(Vehiculo vehiculo: vehiculos){
                        if("motocicleta".equals(vehiculo.getTipoVehiculo()))
                            vehFiltr.add(vehiculo);
                    }
                    return vehFiltr;        
                default:
                    return null;
            }
        }
        else
            return vehiculos;
    }
    
    public static ArrayList<Vehiculo> searchByIDVendedor(ArrayList<Vehiculo> vehiculos, int idVendedor){
        ArrayList<Vehiculo> vehFiltr = new ArrayList<>();
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.idVendedor == idVendedor)
                vehFiltr.add(vehiculo);
        }
        return vehFiltr;
    }
    
    public static Vehiculo searchByPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        for(Vehiculo vehiculo: vehiculos){
            if(placa.equals(vehiculo.placa))
                return vehiculo;
        }
        return null;
    }
    
    public static Vehiculo searchByID(ArrayList<Vehiculo> vehiculos, int idVehiculo){
        for(Vehiculo vehiculo: vehiculos){
            if(vehiculo.id == idVehiculo)
                return vehiculo;
        }
        return null;
    }
    
    public static ArrayList<Vehiculo> searchVehiculos(ArrayList<Vehiculo> vehiculos,Scanner sc){
        ArrayList<Vehiculo> filtrParam1 = Vehiculo.searchByTipoVehiculo(vehiculos, sc);
        ArrayList<Vehiculo> filtrParam2 = Vehiculo.searchByRecorrido(filtrParam1, sc);
        ArrayList<Vehiculo> filtrParam3 = Vehiculo.searchByYear(filtrParam2, sc);
        ArrayList<Vehiculo> filtrParam4 = Vehiculo.searchByPrecio(filtrParam3, sc);
        if (filtrParam4.isEmpty()){
            System.out.println("No se han encontrado vehículos.");
        }
        return filtrParam4;
    }
    
    public static int navegarVehiculos(ArrayList<Vehiculo> vehiculos, int idVehiculo,Scanner sc){
        if(idVehiculo == 1 && vehiculos.size()>1){
            System.out.println(Vehiculo.searchByID(vehiculos, idVehiculo).toString());
            System.out.println("\n1.Siguiente vehiculo \n2.Realizar oferta");
            switch(sc.nextInt()){
                case 1:
                    return 1;
                case 2: 
                    return 2;
                default:
            }
        }
        else if(vehiculos.size() == 1){
            System.out.println(Vehiculo.searchByID(vehiculos, idVehiculo).toString());
            System.out.println("\n 2.Realizar oferta");
            switch(sc.nextInt()){
                case 2: 
                    return 2;
                default:
            }
        }
        
        else if(idVehiculo > 1 && idVehiculo < vehiculos.size()){
            System.out.println(Vehiculo.searchByID(vehiculos, idVehiculo).toString());
            System.out.println("\n1.Siguiente vehiculo \n2.Realizar oferta \n3.Anterior vehiculo");
            switch(sc.nextInt()){
                case 1:
                    return 1;
                case 2: 
                    return 2;
                case 3:
                    return 3;
                default:
            }
        }
        else{
            System.out.println(Vehiculo.searchByID(vehiculos, idVehiculo).toString());
            System.out.println("\n1.Realizar oferta \n2.Anterior vehiculo");
            switch(sc.nextInt()){
                case 1:
                    return 2;
                case 2: 
                    return 3;
                default:
            }
        }
        return 0;
    }

    public static int escogerVehiculo(ArrayList<Vehiculo> vehiculos, int idVehiculo,Scanner sc){
        do{
            if(Vehiculo.navegarVehiculos(vehiculos, idVehiculo, sc) == 1){
                idVehiculo += 1;
                Vehiculo.navegarVehiculos(vehiculos, idVehiculo, sc);
            }
            if(Vehiculo.navegarVehiculos(vehiculos, idVehiculo, sc) == 3){
                idVehiculo -= 1;
                Vehiculo.navegarVehiculos(vehiculos, idVehiculo, sc);
            }
        }while(Vehiculo.navegarVehiculos(vehiculos, idVehiculo, sc) != 2);
        return idVehiculo;
    }
    
    public static void displayOferta(String placaVehiculo,Vendedor vendedor){
        Vehiculo vehiculo = Vehiculo.searchByPlaca(vendedor.getVehiculos(), placaVehiculo);
        vehiculo.navegarOfertas(vendedor);
    }
    
    public void navegarOfertas(Vendedor vendedor){
        int numOferta = 1; //Primera oferta en la lista de ofertas del vehiculo.
        Scanner sc = new Scanner(System.in);
        System.out.println(this.marca + " " + this.modelo + " " + this.tipoMotor + " Precio: " + this.precio);
        System.out.println("\n Se han realizado " + this.ofertas.size() + " ofertas.");
        int opc=0;//Valor default
        do{
            System.out.println("\n Oferta " + (numOferta) + "\n \n" + this.ofertas.get(numOferta-1).toString());
            if(numOferta == 1 && this.getOfertas().size()>1){
                do{
                    System.out.println("1.Siguiente oferta \n2.Aceptar oferta");
                    opc = sc.nextInt();
                }while(opc>2 && opc<1);
                switch(opc){
                    case 1:
                        numOferta += 1;
                        break;
                    case 2:
                        vendedor.aceptarOferta(this.ofertas.get(numOferta-1).getId(), this);
                        break;
                }       
            }
            else if(numOferta>1 && numOferta<this.getOfertas().size()){
                do{
                    System.out.println("1.Siguiente oferta \n2.Aceptar oferta \n3.Anterior oferta");
                    opc = sc.nextInt();
                }while(opc>3 && opc<1);
                switch(opc){
                    case 1:
                        numOferta += 1;
                        break;
                    case 2:
                        vendedor.aceptarOferta(this.getOfertas().get(numOferta-1).getId(), this);
                        break;
                    case 3:
                        numOferta -= 1;
                        break;
                } 
            }
            else if(numOferta == this.getOfertas().size() && this.getOfertas().size()>1){
                do{
                    System.out.println("2.Aceptar oferta \n3.Anterior oferta");
                    opc = sc.nextInt();
                }while(opc>3 && opc<2);
                switch(opc){
                    case 2:
                        vendedor.aceptarOferta(this.getOfertas().get(numOferta-1).getId(), this);
                        break;
                    case 3:
                        numOferta -= 1;
                        break;
                } 
            }
            else{
                do{
                    System.out.println("2.Aceptar oferta");
                    opc = sc.nextInt();
                }while(opc!=2);
                switch(opc){
                    case 2:
                        vendedor.aceptarOferta(this.getOfertas().get(0).getId(), this);
                        break;
                }
            }
        }while(opc != 2);
    }
    
    public static void linkInfo(ArrayList<Vehiculo> vehiculos ,ArrayList<Vendedor> vendedores){
        for(Vehiculo vehiculo: vehiculos){
            Vendedor vendedor = Vendedor.searchByID(vendedores, vehiculo.getIdVendedor());
            vehiculo.setVendedor(vendedor);
            vendedor.getVehiculos().add(vehiculo);
        }
    }
    
    public void guardarArchivo(String nomArchivo){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomArchivo),true)))
        {
            pw.println(this.id+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.anio+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio+"|"+this.idVendedor+"|"+this.vidrios+"|"+this.transmision+"|"+this.traccion);
        }
        catch(Exception e){
        }
    }
    
    public static void overwriteFile(String nomArchivo, ArrayList<Vehiculo> vehiculosUpdate){
         try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomArchivo))))
        {
            for(Vehiculo vehiculo: vehiculosUpdate){
                vehiculo.guardarArchivo(nomArchivo);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    
    public static ArrayList<Vehiculo> leerArchivo(String nomArchivo){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomArchivo))){
            while(sc.hasNextLine())
            {                
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                if(tokens[13]==null && tokens[12]==null && tokens[11]==null){//motocicleta
                    Vehiculo vehiculo = new Vehiculo(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6]),tokens[7],tokens[8],Double.parseDouble(tokens[9]),Integer.parseInt(tokens[10]));
                    vehiculos.add(vehiculo);
                }
                else if(tokens[13]==null){//carro
                    Vehiculo vehiculo = new Vehiculo(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6]),tokens[7],tokens[8],Double.parseDouble(tokens[9]),Integer.parseInt(tokens[10]),tokens[11],tokens[12]);
                    vehiculos.add(vehiculo);
                }
                else{//camioneta
                    Vehiculo vehiculo = new Vehiculo(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6]),tokens[7],tokens[8],Double.parseDouble(tokens[9]),Integer.parseInt(tokens[10]),tokens[11],tokens[12],tokens[13]);
                    vehiculos.add(vehiculo);
                }
            }    
        }
        catch(Exception e){
        }
        return vehiculos;
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
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        return true;
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
