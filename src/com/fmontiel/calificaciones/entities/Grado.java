/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.calificaciones.entities;

/**
 *
 * @author PC
 */
public class Grado {
    int id;
    String nombre;
    char seccion;
    int anio;

    public Grado(int id, String nombre, char seccion, int anio) {
        this.id = id;
        this.nombre = nombre;
        this.seccion = seccion;
        this.anio = anio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Character getSeccion() {
        return seccion;
    }

    public int getAnio() {
        return anio;
    }

    
}
