/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.fmontiel.calificaciones.views;

import com.fmontiel.calificaciones.views.indexview.ButtonRenderer;
import com.fmontiel.calificaciones.entities.Alumno;
import com.fmontiel.calificaciones.entities.Grado;
import com.fmontiel.calificaciones.entities.Materia;
import com.fmontiel.calificaciones.interfaces.EditarCallback;
import com.fmontiel.calificaciones.interfaces.EliminarCallback;
import com.fmontiel.calificaciones.interfaces.VerCallback;
import com.fmontiel.calificaciones.models.AlumnosModel;
import com.fmontiel.calificaciones.models.GradosModel;
import com.fmontiel.calificaciones.models.MateriasModel;
import com.fmontiel.calificaciones.views.indexview.ButtonEditor;
import java.awt.Component;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class IndexView extends javax.swing.JFrame {

    /**
     * Creates new form IndexView
     */

    ArrayList<Alumno> listaAlumnos;
    ArrayList<Alumno> listaAlumnosTemp;
    ArrayList<Materia> listaMaterias;
    ArrayList<Grado> listaGrados;

    AlumnosModel am;
    MateriasModel mm;
    GradosModel gm;

    public IndexView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();

        am = new AlumnosModel();
        mm = new MateriasModel();
        gm = new GradosModel();

        try {
            actualizarListaAlumnos();
            rellenarTabla();
            rellenarComboBoxMaterias();
            rellenarComboBoxGrados();

        } catch (SQLException ex) {
            Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class PaddingCellRenderer extends DefaultTableCellRenderer {
        private int padding = 10; // Padding en píxeles

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());

            // Establecer padding
            if (cell instanceof JComponent) {
                ((JComponent) cell).setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
            }

            return cell;
        }
    }

    public void actualizarListaAlumnos() {
        try {
            listaAlumnos = am.getAll();
            listaAlumnosTemp = listaAlumnos;
        } catch (SQLException ex) {
            Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rellenarTabla() throws SQLException {

        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][] {},
                new String[] { "CUI", "Nombres", "Apellidos", "Acciones" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Solo la columna de "Acciones" es editable para permitir los botones
            }
        };

        // Limpiar las filas existentes
        modelo.setRowCount(0);

        // Agregar los alumnos a la tabla
        for (Alumno alumno : listaAlumnosTemp) {
            Object[] fila = {
                    alumno.getCui().toString(),
                    alumno.getNombres(),
                    alumno.getApellidos(),
                    null // Este es el lugar para los botones (Ver / Editar / Eliminar)
            };
            modelo.addRow(fila);
        }

        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer(Object.class, new PaddingCellRenderer());

        // Asignar el renderizador de botones
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());

        // Asignar el editor de botones
        jTable1.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(
                jTable1,
                (String id) -> {
                    // Lógica para ver el alumno
                },
                (String id) -> {
                    // Lógica para editar el alumno
                }, (String id) -> {
                    try {
                        if (am.eliminarAlumno(new BigInteger(id))) {
                            actualizarListaAlumnos();
                            rellenarTabla();
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Alumno eliminado con CUI: " + id,
                                    "Éxito",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Error al eliminar el alumno con CUI (Verifica si este alumno tiene notas, y está asignado a algun grado o materia): "
                                            + id,
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);

                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }));

        jTable1.setRowHeight(40);
    }

    public void rellenarComboBoxMaterias() throws SQLException {
        listaMaterias = mm.getAll();
        // Limpiar los elementos existentes en el comboBox
        comboBoxMaterias.removeAllItems();

        // Agregar cada materia al comboBox
        for (Materia materia : listaMaterias) {
            comboBoxMaterias.addItem(materia.getNombre());
        }
    }

    public void rellenarComboBoxGrados() throws SQLException {
        listaGrados = gm.getAllAndGroupByName();
        // Limpiar los elementos existentes en el comboBox
        comboBoxGrados.removeAllItems();

        // Agregar cada materia al comboBox
        for (Grado grado : listaGrados) {
            comboBoxGrados.addItem(grado.getNombre());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        comboBoxMaterias = new javax.swing.JComboBox<>();
        textFieldFiltroConcidencias = new javax.swing.JTextField();
        comboBoxGrados = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        textFieldSeccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textFieldAnio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Sistema de Calificaciones Proyecto UMG");

        jLabel2.setText("Frederick Eduardo Montiel Tórtola");

        jLabel3.setText("7690-23-8975");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "CUI", "Nombres", "Apellidos", "Acciones"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        comboBoxMaterias.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        textFieldFiltroConcidencias.setToolTipText("");

        comboBoxGrados.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Agregar Alumno");

        jLabel4.setText("Cui, Nombres o Apellidos a buscar ");

        jLabel5.setText("Sección:");

        jLabel6.setText("Año");

        jButton2.setText("Materias");

        jButton3.setText("Grados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToGradosView(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton1))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 457,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        582, Short.MAX_VALUE)
                                                .addComponent(jLabel2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jLabel3))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel4,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 457,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(textFieldFiltroConcidencias))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(comboBoxMaterias, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboBoxGrados, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textFieldSeccion, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textFieldAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 99,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(textFieldAnio, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6))
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(textFieldFiltroConcidencias,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboBoxMaterias, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboBoxGrados, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(textFieldSeccion, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3))
                                .addContainerGap()));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goToGradosView(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goToGradosView
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GradosView gv = new GradosView();
                gv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gv.setLocationRelativeTo(null);
                // gv.setExtendedState(JFrame.MAXIMIZED_BOTH);
                gv.setVisible(true);

                dispose();
            }
        });
    }// GEN-LAST:event_goToGradosView

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxGrados;
    private javax.swing.JComboBox<String> comboBoxMaterias;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField textFieldAnio;
    private javax.swing.JTextField textFieldFiltroConcidencias;
    private javax.swing.JTextField textFieldSeccion;
    // End of variables declaration//GEN-END:variables
}
