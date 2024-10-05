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
    
    static Connection conn = null;
    
    //Patron de diseño Singleton, para no realizar mas de una conexión por sesión.
    public ConnectionPgSql(){
        if(ConnectionPgSql.conn == null){
            String url = "jdbc:postgresql://localhost:5432/Calificaciones";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "abc1234567");
            props.setProperty("ssl", "false");
            
            try{
                ConnectionPgSql.conn = DriverManager.getConnection(url, props);
            }catch(Exception e){

                e.printStackTrace();
            }
        }
    }
    
    public Connection getConnection(){
        return ConnectionPgSql.conn;
    }
    
    
}
