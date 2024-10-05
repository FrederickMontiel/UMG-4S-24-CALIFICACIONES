package com.fmontiel.calificaciones.models;

import com.fmontiel.calificaciones.bin.ConnectionPgSql;
import com.fmontiel.calificaciones.entities.Alumno;
import com.fmontiel.calificaciones.parsers.AlumnosParser;
import com.fmontiel.calificaciones.types.TiposBusqueda;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    public ArrayList<Alumno> getAll() throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM Alumnos");

        return AlumnosParser.parse(rs);
    }

    public ArrayList<Alumno> getAlumnos(int id, TiposBusqueda tipo) throws SQLException, Exception {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();
        String query = "SELECT DISTINCT A.* FROM Alumnos A INNER JOIN AsignacionAlumno aa ON ";

        if (tipo == TiposBusqueda.GRADO) {
            query += " aa.grado = " + id + " ";
        } else if (tipo == TiposBusqueda.MATERIA) {
            query += " aa.materia = " + id + " ";
        } else {
            throw new Exception("No se puede buscar por medio de int la sección");
        }

        ResultSet rs = statement.executeQuery(query);

        return AlumnosParser.parse(rs);
    }

    public ArrayList<Alumno> getAlumnos(char seccion, TiposBusqueda tipo) throws SQLException, Exception {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();
        String query = "SELECT DISTINCT A.* FROM Alumnos A INNER JOIN AsignacionAlumno aa ON ";

        if (tipo == TiposBusqueda.GRADO) {
            query += " aa.grado = '" + seccion + "' ";
        } else if (tipo == TiposBusqueda.MATERIA) {
            query += " aa.materia = '" + seccion + "' ";
        } else {
            throw new Exception("No se puede buscar por medio de int la sección");
        }

        ResultSet rs = statement.executeQuery(query);

        return AlumnosParser.parse(rs);
    }

    public ArrayList<Alumno> getAlumnos(int grado, int materia, char seccion) throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();
        String query = "SELECT DISTINCT A.* FROM Alumnos A INNER JOIN AsignacionAlumno aa ON ";

        query += " aa.idMateria = " + materia + " ";
        query += " AND aa.idMateria = " + grado + " ";

        ResultSet rs = statement.executeQuery(query);

        return AlumnosParser.parse(rs);
    }

    public boolean eliminarAlumno(BigInteger cui) {
        try {
            ConnectionPgSql pgsql = new ConnectionPgSql();
            // Statement statement = pgsql.getConnection().createStatement();
            String query = "DELETE FROM Alumnos WHERE cui = ?";

            PreparedStatement ps = pgsql.getConnection().prepareStatement(query);

            ps.setBigDecimal(1, new BigDecimal(cui));

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void agregarAlumno(Alumno alumno) throws SQLException {

        ConnectionPgSql pgsql = new ConnectionPgSql();
        // Statement statement = pgsql.getConnection().createStatement();
        String query = "INSERT INTO Alumnos (cui, nombres, apellidos) VALUES (" + alumno.getCui() + ", ?, ?)";

        PreparedStatement ps = pgsql.getConnection().prepareStatement(query);

        ps.setString(1, alumno.getNombres());
        ps.setString(2, alumno.getApellidos());

        ps.executeUpdate();

    }

    public void actualizarAlumno(BigInteger id, Alumno alumno) throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        // Statement statement = pgsql.getConnection().createStatement();
        String query = "UPDATE Alumnos SET cui = " + alumno.getCui() + ", nombres = ?, apellidos = ? WHERE cui = " + id;

        PreparedStatement ps = pgsql.getConnection().prepareStatement(query);

        ps.setString(1, alumno.getNombres());
        ps.setString(2, alumno.getApellidos());

        ps.executeUpdate();
    }

    public boolean existWithCUI(BigInteger cui) throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();
        String query = "SELECT * FROM Alumnos WHERE cui = " + cui;

        ResultSet rs = statement.executeQuery(query);

        return rs.next();
    }
}
