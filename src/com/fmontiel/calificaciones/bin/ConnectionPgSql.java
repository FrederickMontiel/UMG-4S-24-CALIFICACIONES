/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.calificaciones.bin;

import java.util.Properties;
import java.sql.*;

/**
 *
 * @author PC
 */
public class ConnectionPgSql {
    
    Connection conn = null;
    public ConnectionPgSql(){
        System.out.println("Se está iniciando la conexion");
        String url = "jdbc:postgresql://localhost:5432/Calificaciones";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "abc1234567");
        props.setProperty("ssl", "false");
        try{
                    

            this.conn = DriverManager.getConnection(url, props);
            System.out.println("Se Conectó");
        }catch(Exception e){
            System.out.println("Error al conectar");
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return this.conn;
    }
    
    
}
