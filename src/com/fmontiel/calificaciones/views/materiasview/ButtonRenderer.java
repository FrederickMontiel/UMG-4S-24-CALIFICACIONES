package com.fmontiel.calificaciones.views.materiasview;

import com.fmontiel.calificaciones.views.gradosview.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ButtonRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (column == 2) {
            JPanel panel = new JPanel();
            panel.setBackground(java.awt.Color.white); // Fondo blanco

            JButton btnEditar = new JButton("Editar");
            JButton btnEliminar = new JButton("Eliminar");

            panel.add(btnEditar);
            panel.add(btnEliminar);
            return panel;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    // Método para mostrar detalles del alumno, puedes modificarlo según tu
    // necesidad
    public void mostrarDetallesAlumno(String cui) {
        // Aquí puedes realizar alguna acción con el CUI obtenido
        // Como cargar detalles del alumno o abrir una nueva ventana
        System.out.println("Detalles del alumno con CUI: " + cui);
    }
}
