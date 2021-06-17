/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Conexion;
import Clases.Factura;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author joaco
 */
public class abrirMes extends javax.swing.JFrame {

    /**
     * Creates new form abrirMes
     */
    public abrirMes() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Abrir mes");

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Abrir mes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Seleccione mes: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, 155, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
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
        //Traigo el mes seleccionado en el combo.
        String mesSeleccionado = jComboBox1.getSelectedItem().toString();

        if (mesSeleccionado != null) {
            //Traigo la fecha actual.
            LocalDate fechaActual = LocalDate.now();

            //Comparo los meses.
            String mesFechaActual = fechaActual.getMonth().name(); //JUNIO
            String mesActual = "";
            boolean mayor;

            if ("JANUARY".equals(mesFechaActual)) {
                mesActual = "Enero";
            } else if ("FEBRUARY".equals(mesFechaActual)) {
                mesActual = "Febrero";
            } else if ("MARCH".equals(mesFechaActual)) {
                mesActual = "Marzo";
            } else if ("APRIL".equals(mesFechaActual)) {
                mesActual = "Abril";
            } else if ("MAY".equals(mesFechaActual)) {
                mesActual = "Mayo";
            } else if ("JUNE".equals(mesFechaActual)) {
                mesActual = "Junio";
            } else if ("JULY".equals(mesFechaActual)) {
                mesActual = "Julio";
            } else if ("AUGUST".equals(mesFechaActual)) {
                mesActual = "Agosto";
            } else if ("SEPTEMBER".equals(mesFechaActual)) {
                mesActual = "Septiembre";
            } else if ("OCTOBER".equals(mesFechaActual)) {
                mesActual = "Octubre";
            } else if ("NOVEMBER".equals(mesFechaActual)) {
                mesActual = "Noviembre";
            } else if ("DECEMBER".equals(mesFechaActual)) {
                mesActual = "Diciembre";
            }

            boolean shrek = mesCerrado(mesSeleccionado);
            if (!shrek) {
                boolean exito = abrirMes(mesSeleccionado);
                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(null, "El mes se ha abierto exitosamente.", "Enhorabuena", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "El mes que quiere abrir, ya está abierto.", "Enhorabuena", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }


        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(abrirMes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(abrirMes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(abrirMes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(abrirMes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new abrirMes().setVisible(true);
            }
        });
    }

    private boolean mesCerrado(String mesSeleccionado) {

        boolean retorno = true;
        LocalDate date1 = convertirAFecha(mesSeleccionado);
        LocalDate date2 = convertirAFecha2(mesSeleccionado);

        List<Factura> facturas = Conexion.getInstance().ListarFacturasPorFechaSinProveedor(date1, date2);
        
        if (!facturas.isEmpty()) {
            for (Factura f : facturas) {
                if (f.isCerrada()) {
                    retorno = false;
                    break;
                }
            }
        }
        
        return retorno;
    }

    private LocalDate convertirAFecha(String mesSeleccionado) {
        LocalDate dateSeleccionada = null;
        LocalDate fechaActual = LocalDate.now();
        if (mesSeleccionado.equals("Enero")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 1, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Febrero")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 2, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Marzo")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 3, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Abril")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 4, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Mayo")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 5, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Junio")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 6, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Julio")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 7, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Agosto")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 8, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Septiembre")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 9, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Octubre")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 10, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Noviembre")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 11, 1);
            dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
        } else if (mesSeleccionado.equals("Diciembre")) {
            //Si el valor del mes es 1 (enero), significa que estoy en enero del año siguiente.
            //Por lo tanto tengo que restarle un año a la fecha que traigo del sistema.
            if (fechaActual.getMonthValue() == 1) {
                LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 12, 1);
                fechaMesSeleccionado.minusYears(1);
                dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
            } //En cambio si el mes es 12 (diciembre), no tengo que restarle un año a la fecha actual ya que las dos fechas tienen el mismo año.
            else if (fechaActual.getMonthValue() == 12) {
                LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 12, 1);
                dateSeleccionada = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), 1);
            }
        }
        return dateSeleccionada;
    }

    private LocalDate convertirAFecha2(String mesSeleccionado) {
        LocalDate fechaACerrar = null;
        LocalDate fechaActual = LocalDate.now();

        //Dependiendo del mes que selecciono, creo una fecha con el último día del mes a partir de ese mes, y el año actual.
        if (mesSeleccionado.equals("Enero")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 1, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth()); // 31/01/2021
        } else if (mesSeleccionado.equals("Febrero")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 2, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Marzo")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 3, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Abril")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 4, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Mayo")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 5, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Junio")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 6, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Julio")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 7, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Agosto")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 8, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Septiembre")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 9, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Octubre")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 10, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Noviembre")) {
            LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 11, 1);
            fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
        } else if (mesSeleccionado.equals("Diciembre")) {
            //Si el valor del mes es 1 (enero), significa que estoy en enero del año siguiente.
            //Por lo tanto tengo que restarle un año a la fecha que traigo del sistema.
            if (fechaActual.getMonthValue() == 1) {
                LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 12, 1);
                fechaMesSeleccionado.minusYears(1);
                fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
            } //En cambio si el mes es 12 (diciembre), no tengo que restarle un año a la fecha actual ya que las dos fechas tienen el mismo año.
            else if (fechaActual.getMonthValue() == 12) {
                LocalDate fechaMesSeleccionado = LocalDate.of(fechaActual.getYear(), 12, 1);
                fechaACerrar = LocalDate.of(fechaMesSeleccionado.getYear(), fechaMesSeleccionado.getMonth(), fechaMesSeleccionado.lengthOfMonth());
            }

        }
        return fechaACerrar;
    }

    private boolean abrirMes(String mesSeleccionado) {
        LocalDate fechaACerrar = convertirAFecha2(mesSeleccionado);

        if (fechaACerrar != null) {
            LocalDate fechaDesde = LocalDate.of(fechaACerrar.getYear(), fechaACerrar.getMonth(), 1);
            List<Factura> facturas = Conexion.getInstance().ListarFacturasPorFechaSinProveedor(fechaDesde, fechaACerrar);
            for (Factura f : facturas) {
                f.setCerrada(false);
                Conexion.getInstance().merge(f);
            }
            return true;
        }

        return false;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}