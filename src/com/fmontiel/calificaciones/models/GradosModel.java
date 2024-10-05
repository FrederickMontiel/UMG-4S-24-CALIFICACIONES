package com.fmontiel.calificaciones.models;

import com.fmontiel.calificaciones.bin.ConnectionPgSql;
import com.fmontiel.calificaciones.entities.Grado;
import com.fmontiel.calificaciones.parsers.GradosParser;

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
public class GradosModel {
    public ArrayList<Grado> getAll() throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT DISTINCT id, nombre, 'A' seccion, 1 anio FROM Grados");

        return GradosParser.parse(rs);
    }

    public ArrayList<Grado> getAllAndGroupByName() throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();

        ResultSet rs = statement.executeQuery(
                "SELECT DISTINCT 1 id, nombre, 'A' seccion, anio FROM Grados GROUP BY id, nombre, seccion, anio");

        return GradosParser.parse(rs);
    }

    public ArrayList<Grado> getAllByNombreAndAnio(
            String nombre,
            int anio) throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        // "SELECT id, nombre, seccion, anio FROM Grados WHERE nombre = ?
        // AND anio = ?"
        PreparedStatement preparedStatement = pgsql.getConnection().prepareStatement(
                "SELECT id, nombre, seccion, anio FROM Grados WHERE nombre = ? AND anio = ?");
        preparedStatement.setString(1, nombre);
        preparedStatement.setInt(2, anio);

        ResultSet rs = preparedStatement.executeQuery();

        return GradosParser.parse(rs);
    }

    public boolean addGrado(Grado grado) throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();

        String query = "INSERT INTO Grados (nombre, seccion, anio) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = pgsql.getConnection().prepareStatement(query);
        preparedStatement.setString(1, grado.getNombre());
        preparedStatement.setString(2, String.valueOf(grado.getSeccion()));
        preparedStatement.setInt(3, grado.getAnio());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean editarGrado(Grado grado) {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        try {
            Statement statement = pgsql.getConnection().createStatement();
            return statement.executeUpdate("UPDATE Grados SET seccion = '" + grado.getSeccion() + "' WHERE nombre = '"
                    + grado.getNombre() + "' AND anio = "
                    + grado.getAnio()) > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public Grado getById(int id) {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        try {
            Statement statement = pgsql.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Grados WHERE id = " + id);

            return GradosParser.parse(rs).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Grado firstWithName(String nombre) {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        try {
            Statement statement = pgsql.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Grados WHERE nombre = ?");

            return GradosParser.parse(rs).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Grado firstWithNombreAndAnioAndSeccion(String nombre, int anio, char seccion) {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        try {
            PreparedStatement statement = pgsql.getConnection().prepareStatement(
                    "SELECT * FROM Grados WHERE nombre like ? AND anio = ? AND seccion = ?");

            statement.setString(1, nombre);
            statement.setInt(2, anio);
            statement.setString(3, String.valueOf(seccion));

            ResultSet rs = statement.executeQuery();

            ArrayList<Grado> grados = GradosParser.parse(rs);
            if (!grados.isEmpty()) {
                return grados.get(0);
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void editSeccion(int id, Grado grado) {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        try {
            Statement statement = pgsql.getConnection().createStatement();
            statement.executeUpdate("UPDATE Grados SET seccion = '" + grado.getSeccion() + "' WHERE id = " + id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean eliminarGradoByName(String nombre) {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        try {
            Statement statement = pgsql.getConnection().createStatement();
            return statement.executeUpdate("DELETE FROM Grados WHERE nombre = '" + nombre + "'") > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean eliminarGradoByNombreAndAnioAndSeccion(String nombre, int anio, char seccion) {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        try {
            Statement statement = pgsql.getConnection().createStatement();
            return statement.executeUpdate("DELETE FROM Grados WHERE nombre = '" + nombre + "' AND anio = " + anio
                    + " AND seccion = '" + seccion + "'") > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
