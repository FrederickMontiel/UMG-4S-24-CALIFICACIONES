/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.calificaciones.parsers;

import com.fmontiel.calificaciones.entities.Alumno;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author PC
 */
public class AlumnosParser {
    public static ArrayList<Alumno> parse(ResultSet rs) throws SQLException {
        ArrayList<Alumno> lista = new ArrayList<>();
        
        while(rs.next()){
            lista.add(new Alumno(
              rs.getBigDecimal("cui").toBigInteger(),
              rs.getString("nombres"),
              rs.getString("apellidos")
            ));
        }
        
        return lista;
    }
}
