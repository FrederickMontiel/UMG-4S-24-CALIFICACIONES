package com.fmontiel.calificaciones.models;


import com.fmontiel.calificaciones.bin.ConnectionPgSql;
import com.fmontiel.calificaciones.entities.Alumno;
import com.fmontiel.calificaciones.parsers.AlumnosParser;
import java.sql.*;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
public class AlumnosModel {
    public ArrayList<Alumno> getAlumnos() throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();
        
        ResultSet rs = statement.executeQuery("SELECT * FROM Alumnos");
       
        
        
        return AlumnosParser.parse(rs);
    }
}
