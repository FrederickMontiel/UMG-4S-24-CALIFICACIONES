package com.fmontiel.calificaciones.views;

import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;
import com.fmontiel.calificaciones.entities.Grado;
import com.fmontiel.calificaciones.interfaces.CallbackInterface;
import com.fmontiel.calificaciones.interfaces.GradosRowListenerInterface;
import com.fmontiel.calificaciones.models.GradosModel;
import com.fmontiel.calificaciones.views.IndexView.PaddingCellRenderer;
import com.fmontiel.calificaciones.views.seccionesview.ButtonEditor;
import com.fmontiel.calificaciones.views.seccionesview.ButtonRenderer;
import com.fmontiel.calificaciones.views.seccionesview.Formulario;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author PC
 */
public class SeccionesView extends javax.swing.JFrame {
        GradosModel gm;

        ArrayList<Grado> listaGrados;
        String nombre;
        int anio;

        @Override
        public void dispose() {
                super.dispose();
                GradosView gv = new GradosView();
                gv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gv.setLocationRelativeTo(null);
                gv.setVisible(true);

        }

        /**
         * Creates new form GradosView
         * 
         * @param nombre
         * @param anio
         */
        public SeccionesView(
                        String nombre,
                        int anio) {
                this.nombre = nombre;
                this.anio = anio;
                gm = new GradosModel();

                initComponents();
                try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                        obtenerGrados();
                        rellenarTabla();
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

        public void obtenerGrados() {
                try {
                        listaGrados = gm.getAllByNombreAndAnio(
                                        nombre,
                                        anio);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void rellenarTabla() {
                DefaultTableModel modelo = new DefaultTableModel(
                                new Object[][] {},
                                new String[] { "Secciones", "Acciones" }) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return column == 1; // Solo la columna de "Acciones" es editable para permitir los
                        }
                };

                // Limpiar las filas existentes
                modelo.setRowCount(0);

                // Agregar los alumnos a la tabla
                for (Grado alumno : listaGrados) {
                        Object[] fila = {
                                        alumno.getSeccion(),

                                        null
                        };
                        modelo.addRow(fila);
                }

                jTable1.setModel(modelo);
                jTable1.setDefaultRenderer(Object.class, new PaddingCellRenderer());

                // Asignar el renderizador de botones
                jTable1.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());

                // Asignar el editor de botones
                jTable1.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(
                                jTable1,
                                // Editar
                                (JSONObject jo) -> {
                                        Formulario f = new Formulario(
                                                        Formulario.Modos.EDITAR,
                                                        nombre,
                                                        anio,
                                                        jo.getString("seccion").charAt(0),
                                                        (JSONObject jo1) -> {
                                                                this.obtenerGrados();
                                                                this.rellenarTabla();
                                                        });
                                        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        f.setLocationRelativeTo(null);

                                        f.setVisible(true);

                                },
                                // Eliminar
                                (JSONObject jo) -> {
                                        if (gm.eliminarGradoByNombreAndAnioAndSeccion(
                                                        nombre,
                                                        anio,
                                                        jo.getString("seccion").charAt(0))) {
                                                JOptionPane.showMessageDialog(null, "Sección eliminada correctamente");
                                        } else {
                                                JOptionPane.showMessageDialog(null,
                                                                "Error al eliminar la sección, revisa que ningun alumnno esté asignado");
                                        }

                                        this.obtenerGrados();
                                        this.rellenarTabla();
                                }

                ));

                jTable1.setRowHeight(40);
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
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jButton1 = new javax.swing.JButton();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jTextField1 = new javax.swing.JTextField();
                jButton2 = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(255, 255, 255));

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null }
                                },
                                new String[] {
                                                "Title 1", "Title 2", "Title 3", "Title 4"
                                }));
                jScrollPane1.setViewportView(jTable1);

                jButton1.setText("Agregar");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                openAgregarView(evt);
                        }
                });

                jLabel1.setText("Secciones de: ");

                jLabel2.setText("Filtrar por: ");

                jButton2.setText("Regresar");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                regresarAGrados(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                868, Short.MAX_VALUE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(jButton1))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel2)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jTextField1))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel1)
                                                                                                                .addComponent(jButton2))
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addComponent(jButton2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(jTextField1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                251, Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButton1)
                                                                .addContainerGap()));

                getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void regresarAGrados(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_regresarAGrados

                GradosView gv = new GradosView();
                gv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gv.setLocationRelativeTo(null);
                gv.setVisible(true);

                this.dispose();
        }// GEN-LAST:event_regresarAGrados

        private void openAgregarView(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_openAgregarView
                Formulario f = new Formulario(
                                Formulario.Modos.AGREGAR,
                                nombre,
                                anio,
                                (JSONObject jo1) -> {
                                        this.obtenerGrados();
                                        this.rellenarTabla();
                                });
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationRelativeTo(null);

                f.setVisible(true);
        }// GEN-LAST:event_openAgregarView

        /**
         * @param args the command line arguments
         */
        /*
         * public static void main(String args[]) {
         * 
         * //<editor-fold defaultstate="collapsed"
         * desc=" Look and feel setting code (optional) ">
         * 
         * try {
         * for (javax.swing.UIManager.LookAndFeelInfo info :
         * javax.swing.UIManager.getInstalledLookAndFeels()) {
         * if ("Nimbus".equals(info.getName())) {
         * javax.swing.UIManager.setLookAndFeel(info.getClassName());
         * break;
         * }
         * }
         * } catch (ClassNotFoundException ex) {
         * java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.
         * logging.Level.SEVERE, null, ex);
         * } catch (InstantiationException ex) {
         * java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.
         * logging.Level.SEVERE, null, ex);
         * } catch (IllegalAccessException ex) {
         * java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.
         * logging.Level.SEVERE, null, ex);
         * } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         * java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.
         * logging.Level.SEVERE, null, ex);
         * }
         * //</editor-fold>
         * 
         * 
         * java.awt.EventQueue.invokeLater(new Runnable() {
         * public void run() {
         * new GradosView().setVisible(true);
         * }
         * });
         * }
         */

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTable1;
        private javax.swing.JTextField jTextField1;
        // End of variables declaration//GEN-END:variables
}
