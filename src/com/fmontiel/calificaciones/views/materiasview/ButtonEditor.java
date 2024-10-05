package com.fmontiel.calificaciones.views.materiasview;

import com.fmontiel.calificaciones.views.gradosview.*;
import com.fmontiel.calificaciones.views.indexview.*;
import com.fmontiel.calificaciones.interfaces.CallbackInterface;
import com.fmontiel.calificaciones.interfaces.EditarCallback;
import com.fmontiel.calificaciones.interfaces.EliminarCallback;
import com.fmontiel.calificaciones.interfaces.GradosRowListenerInterface;
import com.fmontiel.calificaciones.interfaces.VerCallback;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.json.JSONObject;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnVer;
    private JButton btnEditar;
    private JButton btnEliminar;
    private String nombre;
    private int id;

    public ButtonEditor(JTable table, CallbackInterface verCallback, CallbackInterface addCallback,
            CallbackInterface eliminarCallback) {
        panel = new JPanel();
        panel.setBackground(java.awt.Color.white);

        // btnVer = new JButton("Secciones");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        // Agregar ActionListener para el botón "Ver"
        // Agregar ActionListener para el botón "Ver"
        /*
         * btnVer.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * if (verCallback != null) {
         * JSONObject jo = new JSONObject();
         * 
         * jo.put("nombre", nombre);
         * jo.put("anio", anio);
         * 
         * verCallback.call(jo);
         * }
         * }
         * });
         */

        // Agregar ActionListener para el botón "Editar"
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addCallback != null) {
                    JSONObject jo = new JSONObject();

                    jo.put("id", id);
                    jo.put("nombre", nombre);

                    addCallback.call(jo);
                }
            }
        });

        // Agregar ActionListener para el botón "Eliminar"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eliminarCallback != null) {
                    JSONObject jo = new JSONObject();

                    jo.put("id", id);

                    eliminarCallback.call(jo);
                }
            }
        });

        // panel.add(btnVer);
        panel.add(btnEditar);
        panel.add(btnEliminar);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Obtener el valor del CUI de la fila actual
        id = (int) table.getValueAt(row, 0);
        nombre = (String) table.getValueAt(row, 1);
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null; // No necesitamos devolver un valor editable aquí
    }

    // Métodos para realizar las acciones
    public void mostrarDetallesAlumno(String cui) {
        // Implementa la acción de mostrar detalles del alumno
        System.out.println("Detalles del alumno con CUI: " + cui);
    }

    public void editarAlumno(String cui) {
        // Implementa la acción de editar el alumno
        System.out.println("Editar alumno con CUI: " + cui);
    }

    public void eliminarAlumno(String cui) {
        // Implementa la acción de eliminar el alumno
        System.out.println("Eliminar alumno con CUI: " + cui);
    }
}
