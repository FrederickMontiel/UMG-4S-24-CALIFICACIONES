/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.calificaciones.entities;

import java.math.BigInteger;

/**
 *
 * @author PC
 */
public class Alumno {
    BigInteger cui;
    String nombres;
    String apellidos;

    public Alumno(BigInteger cui, String nombres, String apellidos) {
        this.cui = cui;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public BigInteger getCui() {
        return cui;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
}
