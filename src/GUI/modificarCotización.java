/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Conexion;
import Clases.Cotizacion;
import Clases.controladorBasura;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaco
 */
public class modificarCotización extends javax.swing.JFrame {
    String tipo;
    boolean crear;
    LocalDate fecha;
    AltaFactura af;
    /**
     * Creates new form modificarCotización
     */
    public modificarCotización() {
        initComponents();
        this.setTitle("Modificar cotizaciones");
        tipo = "a";
        crear = false;
        this.setLocationRelativeTo(null);
        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(3).setWidth(0);
        this.jButton3.setVisible(false);
        
        DefaultTableModel mdl = (DefaultTableModel) jTable1.getModel();
        Iterator<Cotizacion> it = Conexion.getInstance().listarCotizaciones().iterator();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        while (it.hasNext()) {
            Cotizacion next = it.next();
            Object[] fila = new Object[5];
            
            fila[0] = sdf.format(next.getFecha());
            fila[1] = "U$S";
            fila[2] = next.getImporte();
            fila[3] = next;
            mdl.addRow(fila);
            
        }
    }
    
    public modificarCotización(LocalDate fechaCotizacion, AltaFactura altaFactura) {
        initComponents();
        this.setTitle("Seleccionar cotización");
        tipo = "b";
        af = altaFactura;
        fecha = fechaCotizacion;
        this.setLocationRelativeTo(null);
        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(3).setWidth(0);
        
        jButton2.setText("Seleccionar");

        DefaultTableModel mdl = (DefaultTableModel) jTable1.getModel();
        Iterator<Cotizacion> it = Conexion.getInstance().listarCotizaciones().iterator();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        while (it.hasNext()) {
            Cotizacion next = it.next();
            Object[] fila = new Object[5];

            fila[0] = sdf.format(next.getFecha());
            fila[1] = "U$S";
            fila[2] = next.getImporte();
            fila[3] = next;
            mdl.addRow(fila);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Moneda", "Importe", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Crear nueva cotización");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        crear = false;
        if (tipo.equals("a")) {
            int row = jTable1.getSelectedRow();
            Cotizacion c = (Cotizacion) jTable1.getModel().getValueAt(row, 3);
            altaCotizacion aC = new altaCotizacion(c, jTable1);
            aC.setLocationRelativeTo(null);
            aC.setVisible(true);
        } else {
            if (crear == false) {
                int input = javax.swing.JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea seleccionar esta cotización?", "Seleccione una opción",
                        javax.swing.JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    int row = jTable1.getSelectedRow();
                    Cotizacion c = (Cotizacion) jTable1.getModel().getValueAt(row, 3);
                    controladorBasura.getInstance().setPrecioCotizacion(c.getImporte());
                    double cot = controladorBasura.getInstance().getPrecioCotizacion();
                    if (cot != 0) {
                        af.labelCotizacion.setText("La cotización es: " + cot);
                        af.precioCotizacion = cot;
                    }
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        crear = true;
        if (tipo.equals("b") && crear == true) {
            altaCotizacion aC = new altaCotizacion(this.fecha, jTable1);
            aC.setLocationRelativeTo(null);
            aC.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(modificarCotización.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificarCotización.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificarCotización.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificarCotización.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modificarCotización().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
