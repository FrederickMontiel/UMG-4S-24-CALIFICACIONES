/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.calificaciones.parsers;

import com.fmontiel.calificaciones.entities.Materia;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author PC
 */
public class MateriasParser {
    public static ArrayList<Materia> parse(ResultSet rs) throws SQLException {
        ArrayList<Materia> lista = new ArrayList<>();
        
        while(rs.next()){
            lista.add(new Materia(
                rs.getInt("id"),
                rs.getString("nombre")
            ));
        }
        
        return lista;
    }
}
