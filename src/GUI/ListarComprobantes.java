/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Conexion;
import Clases.Comprobante;
import Clases.Factura;
import Clases.Proveedor;
import Clases.Recibo;
import Clases.tipoComprobante;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author emiliano
 */
public class ListarComprobantes extends javax.swing.JFrame {

    TableFilterHeader filterHeader = null;

    /**
     * Creates new form VerComprobantes
     */
    public ListarComprobantes() {
        initComponents();

        this.setLocationRelativeTo(null);
        AutoCompleteDecorator.decorate(this.jCBProveedor);
        List<Proveedor> LProv = Conexion.getInstance().listadoProveedores();
        LProv.forEach((p) -> {
            if (!p.isDeshabilitado()) {
                this.jCBProveedor.addItem(p);
            }
        });

        this.jCheckBox1.setSelected(true);

        jTableComprobantes.getColumnModel().getColumn(6).setMinWidth(0);
        jTableComprobantes.getColumnModel().getColumn(6).setMaxWidth(0);
        jTableComprobantes.getColumnModel().getColumn(6).setWidth(0);

        //Permite agregar un filtro de búsqueda por cada columna.
        filterHeader = new TableFilterHeader(jTableComprobantes, AutoChoices.ENABLED);

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
        jTableComprobantes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonCerrar = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();
        jCBProveedor = new javax.swing.JComboBox<>();
        jDateChooserHasta = new com.toedter.calendar.JDateChooser();
        jDateChooserDesde = new com.toedter.calendar.JDateChooser();
        jCheckBoxSinFecha = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Proveedor");

        jTableComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Tipo", "Numero", "Moneda", "Importe", "Observación", "objeto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableComprobantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableComprobantesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableComprobantes);
        if (jTableComprobantes.getColumnModel().getColumnCount() > 0) {
            jTableComprobantes.getColumnModel().getColumn(0).setPreferredWidth(90);
            jTableComprobantes.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableComprobantes.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTableComprobantes.getColumnModel().getColumn(5).setPreferredWidth(300);
        }

        jLabel2.setText("Desde");

        jLabel3.setText("Hasta");

        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jButtonConsultar.setText("Consultar");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        jCheckBoxSinFecha.setText("Sin fecha");
        jCheckBoxSinFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxSinFechaItemStateChanged(evt);
            }
        });
        jCheckBoxSinFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSinFechaActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Incluir compras contado y devoluciones contado");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingrese una opción", "Facturas", "Recibos", "Ambos" }));

        jLabel4.setText("Seleccionar: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBoxSinFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonCerrar)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonConsultar)))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jCBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonConsultar)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSinFecha)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCerrar)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxSinFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxSinFechaItemStateChanged
        if (this.jCheckBoxSinFecha.isSelected()) {
            this.jDateChooserHasta.setEnabled(false);
            this.jDateChooserDesde.setEnabled(false);
            this.jDateChooserDesde.setCalendar(null);
            this.jDateChooserHasta.setCalendar(null);
        } else {
            this.jDateChooserHasta.setEnabled(true);
            this.jDateChooserDesde.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBoxSinFechaItemStateChanged

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        DefaultTableModel model = (DefaultTableModel) this.jTableComprobantes.getModel();
        Date fechaDesde = this.jDateChooserDesde.getDate();
        Date fechaHasta = this.jDateChooserHasta.getDate();
        String valorCombo = this.jComboBox1.getSelectedItem().toString();
        Proveedor p = (Proveedor) this.jCBProveedor.getSelectedItem();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

//        if (!jCheckBoxSinFecha.isSelected()) {
//            if (fechaDesde.after(fechaHasta)) {
//                javax.swing.JOptionPane.showMessageDialog(null, "Fecha desde debe ser menor a fecha hasta.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
//            }
//        }

        if (valorCombo.equals("Ingrese una opción")) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una opción", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            //Cuando el usuario selecciona facturas pero sin ingresar una fecha inicio y fecha final.
            if (jCheckBoxSinFecha.isSelected() && valorCombo.equals("Facturas")) {
                model.setRowCount(0);
                List<Factura> ListaFact = Conexion.getInstance().ListarFacturas(p);
                if (ListaFact.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "No existe ninguna factura", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    for (int i = 0; i < ListaFact.size(); i++) {
                        if (!this.jCheckBox1.isSelected()) {
                            if (ListaFact.get(i).getTipo() != tipoComprobante.Contado && ListaFact.get(i).getTipo() != tipoComprobante.DevolucionContado) {
                                String numeroComp = ListaFact.get(i).getSerieComprobante() + "-" + ListaFact.get(i).getNroComprobante();
                                model.addRow(new Object[]{sdf.format(ListaFact.get(i).getFecha()), ListaFact.get(i).getTipo().toString(),
                                    numeroComp, ListaFact.get(i).getMoneda().toString(), ListaFact.get(i).getTotal(), ListaFact.get(i).getObservacion(), ListaFact.get(i)});
                            }
                        } else {
                            String numeroComp = ListaFact.get(i).getSerieComprobante() + "-" + ListaFact.get(i).getNroComprobante();
                            model.addRow(new Object[]{sdf.format(ListaFact.get(i).getFecha()), ListaFact.get(i).getTipo().toString(),
                                numeroComp, ListaFact.get(i).getMoneda().toString(), ListaFact.get(i).getTotal(), ListaFact.get(i).getObservacion(), ListaFact.get(i)});
                        }
                    }
                }

            }

            //Cuando el usuario selecciona facturas pero esta vez selecciona fecha de inicio y fecha de fin.
            if (!jCheckBoxSinFecha.isSelected() && valorCombo.equals("Facturas")) {
                model.setRowCount(0);
                if (fechaDesde == null && fechaHasta == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "No ha ingresado ninguna fecha.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else if (fechaDesde == null || fechaHasta == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Falta ingresar una de las fechas.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    model.setRowCount(0);
                    List<Factura> ListaFact = Conexion.getInstance().ListarFacturasPorFecha(p.getCodigo(), fechaDesde, fechaHasta);
                    if (ListaFact.isEmpty()) {
                        javax.swing.JOptionPane.showMessageDialog(null, "No existe ninguna factura entre las fechas ingresadas", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (int i = 0; i < ListaFact.size(); i++) {
                            if (!this.jCheckBox1.isSelected()) {
                                if (ListaFact.get(i).getTipo() != tipoComprobante.Contado && ListaFact.get(i).getTipo() != tipoComprobante.DevolucionContado) {
                                    String numeroComp = ListaFact.get(i).getSerieComprobante() + "-" + ListaFact.get(i).getNroComprobante();
                                    model.addRow(new Object[]{sdf.format(ListaFact.get(i).getFecha()), ListaFact.get(i).getTipo().toString(),
                                        numeroComp, ListaFact.get(i).getMoneda().toString(), ListaFact.get(i).getTotal(), ListaFact.get(i).getObservacion(), ListaFact.get(i)});
                                }
                            } else {
                                String numeroComp = ListaFact.get(i).getSerieComprobante() + "-" + ListaFact.get(i).getNroComprobante();
                                model.addRow(new Object[]{sdf.format(ListaFact.get(i).getFecha()), ListaFact.get(i).getTipo().toString(),
                                    numeroComp, ListaFact.get(i).getMoneda().toString(), ListaFact.get(i).getTotal(), ListaFact.get(i).getObservacion(), ListaFact.get(i)});
                            }
                        }

                    }
                }
            }

            //Cuando el usuario selecciona recibos pero sin fecha de inicio y sin fecha de fin.
            if (jCheckBoxSinFecha.isSelected() && valorCombo.equals("Recibos")) {
                model.setRowCount(0);
                List<Recibo> listaRecibos = Conexion.getInstance().listarRecibos(p.getCodigo());
                if (listaRecibos.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "No existe ningún recibo entre las fechas ingresadas", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    for (int i = 0; i < listaRecibos.size(); i++) {
                        String numeroComp = listaRecibos.get(i).getSerieComprobante() + "-" + listaRecibos.get(i).getNroComprobante();
                        model.addRow(new Object[]{sdf.format(listaRecibos.get(i).getFecha()), "Recibo",
                            numeroComp, listaRecibos.get(i).getMoneda().toString(), listaRecibos.get(i).getTotal(), listaRecibos.get(i).getObservacion(), listaRecibos.get(i)});
                    }
                }
            }

            //Cuando el usuario selecciona recibos pero esta vez con fecha de inicio y fecha de fin.
            if (!jCheckBoxSinFecha.isSelected() && valorCombo.equals("Recibos")) {
                model.setRowCount(0);
                if (fechaDesde == null && fechaHasta == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "No ha ingresado ninguna fecha.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else if (fechaDesde == null || fechaHasta == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Falta ingresar una de las fechas.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    model.setRowCount(0);
                    List<Recibo> listaRecibos = Conexion.getInstance().listarRecibosPorFecha(p.getCodigo(), fechaDesde, fechaHasta);
                    if (listaRecibos.isEmpty()) {
                        javax.swing.JOptionPane.showMessageDialog(null, "No existe ningún recibo entre las fechas ingresadas", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (int i = 0; i < listaRecibos.size(); i++) {
                            String numeroComp = listaRecibos.get(i).getSerieComprobante() + "-" + listaRecibos.get(i).getNroComprobante();
                            model.addRow(new Object[]{sdf.format(listaRecibos.get(i).getFecha()), "Recibo",
                                numeroComp, listaRecibos.get(i).getMoneda().toString(), listaRecibos.get(i).getTotal(), listaRecibos.get(i).getObservacion(), listaRecibos.get(i)});
                        }
                    }
                }
            }

            //Cuando el usuario selecciona ambos pero sin fecha.
            if (jCheckBoxSinFecha.isSelected() && valorCombo.equals("Ambos")) {
                model.setRowCount(0);
                List<Factura> ListaFact = Conexion.getInstance().ListarFacturas(p);
                List<Recibo> listaRecibos = Conexion.getInstance().listarRecibos(p.getCodigo());
                List<Comprobante> comprobantes = agregarComprobantes(ListaFact, listaRecibos);
                if (comprobantes == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "No existe ningún comprobante", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    for (Comprobante comprobante : comprobantes) {
                        if (comprobante instanceof Factura) {
                            if (!this.jCheckBox1.isSelected()) {
                                Factura f = (Factura) comprobante;
                                if (f.getTipo() != tipoComprobante.Contado && f.getTipo() != tipoComprobante.DevolucionContado) {
                                    String numeroComp = f.getSerieComprobante() + "-" + f.getNroComprobante();
                                    model.addRow(new Object[]{sdf.format(f.getFecha()), f.getTipo().toString(),
                                        numeroComp, f.getMoneda().toString(), f.getTotal(), f.getObservacion(), f});
                                }
                            } else {
                                Factura f = (Factura) comprobante;
                                String numeroComp = f.getSerieComprobante() + "-" + f.getNroComprobante();
                                model.addRow(new Object[]{sdf.format(f.getFecha()), f.getTipo().toString(),
                                    numeroComp, f.getMoneda().toString(), f.getTotal(), f.getObservacion(), f});
                            }
//                            Factura f = (Factura) comprobante;
//                            String numeroComp = f.getSerieComprobante() + "-" + f.getNroComprobante();
//                            model.addRow(new Object[]{sdf.format(f.getFecha()), f.getTipo().toString(),
//                                numeroComp, f.getMoneda().toString(), f.getTotal(), f.getObservacion(), f});
                        } else {
                            Recibo r = (Recibo) comprobante;
                            String numeroComp = r.getSerieComprobante() + "-" + r.getNroComprobante();
                            model.addRow(new Object[]{sdf.format(r.getFecha()), "Recibo",
                                numeroComp, r.getMoneda().toString(), r.getTotal(), r.getObservacion(), r});
                        }
                    }
                }
            }

            //Cuando el usuario selecciona ambos pero con fecha.
            if (!jCheckBoxSinFecha.isSelected() && valorCombo.equals("Ambos")) {
                model.setRowCount(0);
                if (fechaDesde == null && fechaHasta == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "No ha ingresado ninguna fecha.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else if (fechaDesde == null || fechaHasta == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Falta ingresar una de las fechas.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    List<Factura> ListaFact = Conexion.getInstance().ListarFacturasPorFecha(p.getCodigo(), fechaDesde, fechaHasta);
                    List<Recibo> listaRecibos = Conexion.getInstance().listarRecibosPorFecha(p.getCodigo(), fechaDesde, fechaHasta);
                    List<Comprobante> comprobantes = agregarComprobantes(ListaFact, listaRecibos);
                    if (comprobantes.isEmpty()) {
                        javax.swing.JOptionPane.showMessageDialog(null, "No existe ningún comprobante", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (Comprobante comprobante : comprobantes) {
                            if (comprobante instanceof Factura) {
                                if (!this.jCheckBox1.isSelected()) {
                                    Factura f = (Factura) comprobante;
                                    if (f.getTipo() != tipoComprobante.Contado && f.getTipo() != tipoComprobante.DevolucionContado) {
                                        String numeroComp = f.getSerieComprobante() + "-" + f.getNroComprobante();
                                        model.addRow(new Object[]{sdf.format(f.getFecha()), f.getTipo().toString(),
                                            numeroComp, f.getMoneda().toString(), f.getTotal(), f.getObservacion(), f});
                                    }
                                } else {
                                    Factura f = (Factura) comprobante;
                                    String numeroComp = f.getSerieComprobante() + "-" + f.getNroComprobante();
                                    model.addRow(new Object[]{sdf.format(f.getFecha()), f.getTipo().toString(),
                                        numeroComp, f.getMoneda().toString(), f.getTotal(), f.getObservacion(), f});
                                }
//                                Factura f = (Factura) comprobante;
//                                String numeroComp = f.getSerieComprobante() + "-" + f.getNroComprobante();
//                                model.addRow(new Object[]{sdf.format(f.getFecha()), f.getTipo().toString(),
//                                    numeroComp, f.getMoneda().toString(), f.getTotal(), f.getObservacion(), f});
                            } else {
                                Recibo r = (Recibo) comprobante;
                                String numeroComp = r.getSerieComprobante() + "-" + r.getNroComprobante();
                                model.addRow(new Object[]{sdf.format(r.getFecha()), "Recibo",
                                    numeroComp, r.getMoneda().toString(), r.getTotal(), r.getObservacion(), r});
                            }
                        }
                    }

                }
            }

        }
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private List<Comprobante> agregarComprobantes(List<Factura> ListaFact, List<Recibo> listaRecibos) {
        List<Comprobante> comprobantes = new ArrayList<>();

        if (!ListaFact.isEmpty()) {
            for (Factura factura : ListaFact) {
                comprobantes.add(factura);
            }
        }

        if (!listaRecibos.isEmpty()) {
            for (Recibo recibo : listaRecibos) {
                comprobantes.add(recibo);
            }
        }

        if (!comprobantes.isEmpty()) {
            Collections.sort(comprobantes, (Comprobante o1, Comprobante o2) -> o1.getFecha().compareTo(o2.getFecha()));
        }

        return comprobantes;
    }

    private void jTableComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableComprobantesMouseClicked
        if (this.jTableComprobantes.getModel().getValueAt(this.jTableComprobantes.getSelectedRow(), 6) instanceof Factura) {
            Factura fac = (Factura) this.jTableComprobantes.getModel().getValueAt(this.jTableComprobantes.getSelectedRow(), 6);
            AltaFactura af = new AltaFactura(fac);
            af.show();
        } else if (this.jTableComprobantes.getModel().getValueAt(this.jTableComprobantes.getSelectedRow(), 6) instanceof Recibo) {
            Recibo rec = (Recibo) this.jTableComprobantes.getModel().getValueAt(this.jTableComprobantes.getSelectedRow(), 6);
            AltaRecibo ar = new AltaRecibo(rec);
            ar.show();
        }
    }//GEN-LAST:event_jTableComprobantesMouseClicked

    private void jCheckBoxSinFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSinFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxSinFechaActionPerformed

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
            java.util.logging.Logger.getLogger(ListarComprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarComprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarComprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarComprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarComprobantes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JComboBox<Proveedor> jCBProveedor;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxSinFecha;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooserDesde;
    private com.toedter.calendar.JDateChooser jDateChooserHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableComprobantes;
    // End of variables declaration//GEN-END:variables

}
