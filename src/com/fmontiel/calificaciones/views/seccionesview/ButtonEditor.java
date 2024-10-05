package com.fmontiel.calificaciones.views.seccionesview;

import com.fmontiel.calificaciones.interfaces.CallbackInterface;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.json.JSONObject;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private String seccion;
    private JPanel panel;

    public ButtonEditor(JTable table, CallbackInterface addCallback, CallbackInterface eliminarCallback) {
        panel = new JPanel();
        panel.setBackground(java.awt.Color.white);

        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        btnEditar.addActionListener((ActionEvent e) -> {
            if (addCallback != null) {
                JSONObject jo = new JSONObject();
                jo.put("seccion", seccion);
                addCallback.call(jo);
            }
        });

        btnEliminar.addActionListener((ActionEvent e) -> {
            if (eliminarCallback != null) {
                JSONObject jo = new JSONObject();
                jo.put("seccion", seccion);
                eliminarCallback.call(jo);
            }
        });

        panel.add(btnEditar);
        panel.add(btnEliminar);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        seccion = String.valueOf(table.getValueAt(row, 0));

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
