/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Conexion;
import Clases.F_R;
import Clases.Factura;
import Clases.Proveedor;
import Clases.Recibo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class listarDeudas extends javax.swing.JFrame {

    /**
     * Creates new form listarDeudas
     */
    public listarDeudas() {
        initComponents();
        
        setTitle("Listar deudas de Proveedores");
        
        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(3).setWidth(0);
        
        yaSabesQueHacerXd(new Date());
        
    }
    
    private float getSaldoPendienteProveedor(Proveedor p,Date date) {
        float saldo = 0;
        List<Factura> facturasC = Conexion.getInstance().ListarFacturasCredito(p);
        Iterator itF = facturasC.iterator();
        
        LocalDate fecha1 = new java.sql.Date(date.getTime()).toLocalDate();
        
        while (itF.hasNext()) {
            Factura f = (Factura) itF.next();
            Date date2 = f.getFecha();
            LocalDate fecha2 = new java.sql.Date(date2.getTime()).toLocalDate();
            
            if (fecha1.isAfter(fecha2) || fecha1.equals(fecha2)) {
                saldo += f.getTotal() * f.getCotizacion();
            }
        }
        return saldo;
    }
    
    private float getSaldoEntregadoClienteMmm(Proveedor p,Date date) {
        int codigo = p.getCodigo();
        float saldosRecibo = 0;
        float saldoNotasC = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<Factura> facturas = Conexion.getInstance().ListarFacturasCredito(p);
        List<Factura> notasCredito = Conexion.getInstance().ListarNotasCreditos(p);
        
        Iterator itR = facturas.iterator();
        Iterator itN = notasCredito.iterator();
       
        LocalDate fecha1 = new java.sql.Date(date.getTime()).toLocalDate();
        while(itR.hasNext()){
            
            Factura f = (Factura)itR.next();
            
            
            Date date2 = f.getFecha();
            LocalDate fecha2 = new java.sql.Date(date2.getTime()).toLocalDate();
            
            if(fecha1.isAfter(fecha2) || fecha1.equals(fecha2)){
                saldosRecibo += getSaldoRecibosCliente(f,date);
            }
            
        }
        
        while(itN.hasNext()){
            Factura f = (Factura)itN.next();
            
            Date date2 = f.getFecha();
            LocalDate fecha2 = new java.sql.Date(date2.getTime()).toLocalDate();
        
            if(fecha1.isAfter(fecha2) || fecha1.equals(fecha2)){
                saldoNotasC += f.getTotal();
            }
            
        }
        
        return (saldosRecibo + saldoNotasC);
    }
    
    private float getSaldoRecibosCliente(Factura f,Date date){
        float suma = 0;
        Iterator it = null;
        if (f.getFr_s() != null ) {
            it = f.getFr_s().iterator();
            
            LocalDate fecha1 = new java.sql.Date(date.getTime()).toLocalDate();

            while (it.hasNext()) {
                F_R fr = (F_R) it.next();
                Date date2 = fr.getRecibo().getFecha();
                LocalDate fecha2 = new java.sql.Date(date2.getTime()).toLocalDate();

                if (fecha1.isAfter(fecha2) || fecha1.equals(fecha2)) {
                    suma += fr.getSaldo() * fr.getFactura().getCotizacion();
                }
            }
        }
        return suma;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabelDesde1 = new javax.swing.JLabel();
        jDateChooserDeudas1 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RUT", "Razón Social", "Pendiente en $", "objeto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelDesde1.setText("Fecha: ");

        jButton2.setText("Consultar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabelDesde1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserDeudas1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooserDeudas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelDesde1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Date date = jDateChooserDeudas1.getDate();
        if(date == null){
            date = new Date();
        }
        
        yaSabesQueHacerXd(date);
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
            java.util.logging.Logger.getLogger(listarDeudas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listarDeudas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listarDeudas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listarDeudas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listarDeudas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooserDeudas1;
    private javax.swing.JLabel jLabelDesde1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void pintarTabla(List filas) {
        DefaultTableModel mdl = (DefaultTableModel)jTable1.getModel();
        Iterator it = filas.iterator();
        while (it.hasNext()) {
            Object o = (Object)it.next();
            mdl.addRow((Object[]) o);
        }
    
    }

    private void yaSabesQueHacerXd(Date date) {
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        float saldoPendiente = 0;
        float saldoEntregadoMmm = 0;
        float total = 0;
        List<Object[]> filas = new ArrayList<>();
        List<Proveedor> listaProveedores = Conexion.getInstance().listadoProveedores();
        Iterator itP = listaProveedores.iterator();
        
        while(itP.hasNext()){
            Proveedor p = (Proveedor)itP.next();
            if(!p.isDeshabilitado()){
                saldoPendiente = getSaldoPendienteProveedor(p,date);
                saldoEntregadoMmm = getSaldoEntregadoClienteMmm(p,date);
                total = saldoPendiente - saldoEntregadoMmm;
                Object[] o = new Object[4];
                o[0] = p.getRUT();
                o[1] = p.getRazonSocial();
                o[2] = total;
                o[3] = p;
                filas.add(o);
            }
            
        }
        
        pintarTabla(filas);
        
    }

    

    
}
