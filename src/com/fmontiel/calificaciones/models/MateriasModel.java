package com.fmontiel.calificaciones.models;

import com.fmontiel.calificaciones.bin.ConnectionPgSql;
import com.fmontiel.calificaciones.entities.Materia;
import com.fmontiel.calificaciones.parsers.MateriasParser;
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
public class MateriasModel {
    public ArrayList<Materia> getAll() throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        Statement statement = pgsql.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM Materias");

        return MateriasParser.parse(rs);
    }

    public void addMateria(Materia materia) throws SQLException {
        try {
            ConnectionPgSql pgsql = new ConnectionPgSql();
            PreparedStatement ps = pgsql.getConnection()
                    .prepareStatement("INSERT INTO Materias (nombre) VALUES (?)");

            ps.setString(1, materia.getNombre());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateMateria(Materia materia) throws SQLException {
        try {
            ConnectionPgSql pgsql = new ConnectionPgSql();
            PreparedStatement ps = pgsql.getConnection()
                    .prepareStatement("UPDATE Materias SET nombre = ? WHERE id = ?");

            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteMateria(int id) throws SQLException {
        ConnectionPgSql pgsql = new ConnectionPgSql();
        PreparedStatement ps = pgsql.getConnection().prepareStatement("DELETE FROM Materias WHERE id = ?");

        ps.setInt(1, id);

        ps.executeUpdate();
    }

    public boolean existWithName(String nombre) throws SQLException {

        ConnectionPgSql pgsql = new ConnectionPgSql();
        PreparedStatement statement = pgsql.getConnection()
                .prepareStatement("SELECT * FROM Materias WHERE nombre LIKE ?");
        statement.setString(1, nombre);

        ResultSet rs = statement.executeQuery();

        ArrayList<Materia> materias = MateriasParser.parse(rs);

        return materias.size() > 0;

    }

}
