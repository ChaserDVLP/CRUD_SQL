/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ad_crud_sql;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author xChas
 */
public class AD_CRUD_SQL {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "admin";
    static final String PASS = "1234";
    static Statement stmt = null;
    
    static public boolean buscarNombre(String nombreBuscado) throws SQLException {
        
        String querySelect = "SELECT Nombre FROM videojuegos WHERE Nombre =  '" + nombreBuscado + "'";
        boolean res = false;
        
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(querySelect);
            
            while (rs.next()) {
                System.out.println("Se ha encontrado el nombre: "+rs.getString("Nombre"));
                res = true;
            }
        
        } catch (SQLException ex) {
            System.out.println("Error al conectar la BBDD: "+ex);
            ex.printStackTrace();
            
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
        return res;
    }
    
    static public void ejecutarConsulta(String query) throws SQLException {
        
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " +rs.getString("Nombre"));
                System.out.println("Genero: " + rs.getString("Genero"));
                System.out.println("Fecha Lanzamiento: " + rs.getDate("FechaLanzamiento"));
                System.out.println("Compañía: " + rs.getString("Compañia"));
                System.out.println("Precio: " + rs.getFloat("Precio")+"\n");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar la BBDD: "+ex);
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    static public void nuevoRegistro (String nombre, String genero, String fechaLanzamiento, 
            String compañia, float precio) throws SQLException {
        
        String query = "INSERT INTO videojuegos (Nombre, Genero, FechaLanzamiento, Compañia, Precio) VALUES ('" 
                        + nombre + "', '" + genero + "', '" + fechaLanzamiento + "', '" + compañia + "', " + precio + ")";
        
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            
            if (stmt.executeUpdate(query) > 0) { //Devuelve la cantidad de filas afectadas
                System.out.println("Se ha insertado el registro");
            } else {
                System.out.println("fallo al insertar");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al conectar la BBDD: "+e);
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    static public void nuevoRegistroManual() throws SQLException {
        
        String nombre, genero, fechaLanzamiento, compañia;
        float precio;
        Scanner teclado = new Scanner(System.in); 
        System.out.println("Introduce el nombre");
        nombre = teclado.nextLine();
        System.out.println("Introduce el genero");
        genero = teclado.nextLine();
        System.out.println("Introduce la fecha de lanzamiento");
        fechaLanzamiento = teclado.nextLine();
        System.out.println("Introduce la compañia");
        compañia = teclado.nextLine();
        System.out.println("Introduce el precio");
        precio = teclado.nextFloat();
        
        String query = "INSERT INTO videojuegos (Nombre, Genero, FechaLanzamiento, Compañia, Precio) VALUES ('" 
                        + nombre + "', '" + genero + "', '" + fechaLanzamiento + "', '" + compañia + "', " + precio + ")";
        
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            
            if (stmt.executeUpdate(query) > 0) { //Devuelve la cantidad de filas afectadas
                System.out.println("Se ha insertado el registro");
            } else {
                System.out.println("fallo al insertar");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al conectar la BBDD: "+e);
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
    }
    
    static public boolean eliminarRegistro (String nombre) throws SQLException {
        
        boolean res = false;
        
        String query = "DELETE FROM videojuegos WHERE Nombre = '" +nombre+"'";
        
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            
            if (stmt.executeUpdate(query) > 0) { //Devuelve la cantidad de filas afectadas
                System.out.println("Se ha eliminado el registro");
                res = true;
            } else {
                System.out.println("fallo al eliminar");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al conectar la BBDD: "+e);
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
        return res;
    }

    public static void main(String[] args) throws SQLException {
        
        Scanner teclado = new Scanner(System.in); 
        System.out.print("Introduce el nombre a buscar: ");
        String nombre = teclado.nextLine();
        
        
        if (!buscarNombre(nombre)) {
            System.out.println("No se ha encontrado ningun registro");
        } 
        
        ejecutarConsulta("SELECT * FROM videojuegos WHERE Nombre = 'Monster Hunter World'");
        
        
        //COMENTADOS LOS MÉTODOS PARA NO LLENAR LA BBDD DE BASURA
        
        //nuevoRegistro("prueba", "test", "2010-10-10", "test", 50);
        //nuevoRegistroManual();
        eliminarRegistro("yo");
        
        
    }
}
