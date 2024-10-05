/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.calificaciones.parsers;

import com.fmontiel.calificaciones.entities.Grado;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class GradosParser {
    public static ArrayList<Grado> parse(ResultSet rs) throws SQLException {
        ArrayList<Grado> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new Grado(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("seccion").charAt(0),
                    rs.getInt("anio")));
        }

        return lista;
    }
}
