/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calendarie_fechascivicas;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CConexion {
    Connection conectar = null;
    String usuario = "root";
    String contraseña = "admin";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd; // cadena De conexion
    
    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // la ruta del driver q emos traido de nuestra dependencia el conector
            conectar = DriverManager.getConnection(cadena,usuario,contraseña); 
// obtener la coneccion dentro de comillas los valoreess
            JOptionPane.showMessageDialog(null, "La connecion se ha realizado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos error " + e.toString()); // me devuelve en cadena con tostring
        }
        return conectar;
    }
}
