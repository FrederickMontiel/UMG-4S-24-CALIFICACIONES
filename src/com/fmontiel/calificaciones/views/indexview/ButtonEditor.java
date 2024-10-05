package com.fmontiel.calificaciones.views.indexview;

import com.fmontiel.calificaciones.interfaces.EditarCallback;
import com.fmontiel.calificaciones.interfaces.EliminarCallback;
import com.fmontiel.calificaciones.interfaces.VerCallback;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnVer;
    private JButton btnEditar;
    private JButton btnEliminar;
    private String currentCui;

    public ButtonEditor(JTable table, VerCallback verCallback, EditarCallback editarCallback, EliminarCallback eliminarCallback) {
        panel = new JPanel();
        panel.setBackground(java.awt.Color.white);

        btnVer = new JButton("Ver");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        // Agregar ActionListener para el botón "Ver"
        // Agregar ActionListener para el botón "Ver"
        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verCallback != null) {
                    verCallback.ver(currentCui);
                }
            }
        });

        // Agregar ActionListener para el botón "Editar"
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editarCallback != null) {
                    editarCallback.editar(currentCui);
                }
            }
        });

        // Agregar ActionListener para el botón "Eliminar"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eliminarCallback != null) {
                    eliminarCallback.eliminar(currentCui);
                }
            }
        });

        panel.add(btnVer);
        panel.add(btnEditar);
        panel.add(btnEliminar);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Obtener el valor del CUI de la fila actual
        currentCui = (String) table.getValueAt(row, 0);
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
