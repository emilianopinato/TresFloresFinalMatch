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
import Clases.tipoMoneda;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author emiliano
 */
public class AsignarNC extends javax.swing.JFrame {

    /**
     * Creates new form AsignarNC
     */
    Dimension original;

    public AsignarNC() {
        initComponents();
        AutoCompleteDecorator.decorate(this.jComboBoxProveedores);
        this.jCBMoneda.setModel(new DefaultComboBoxModel(tipoMoneda.values()));
        List<Proveedor> LProv = Conexion.getInstance().listadoProveedores();
        LProv.forEach((p) -> {
            if (!p.isDeshabilitado()) {
                this.jComboBoxProveedores.addItem(p);
            }
        });
        this.jTableFacturas.getColumnModel().getColumn(3).setMinWidth(0);
        this.jTableFacturas.getColumnModel().getColumn(3).setMaxWidth(0);
        this.jTableFacturas.getColumnModel().getColumn(3).setWidth(0);

        original = this.getSize();
        this.setSize((int) original.getWidth() - 10, (int) original.getHeight() - 10);

        Color color = new Color(255, 249, 140);
        DefaultTableCellRenderer alinear_derecha_y_color = new DefaultTableCellRenderer();
        alinear_derecha_y_color.setBackground(color);
        alinear_derecha_y_color.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTableFacturas.getColumnModel().getColumn(2).setCellRenderer(alinear_derecha_y_color);

        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
        model.setRowCount(0);
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    String pend = String.valueOf(ListaFactCredit.get(i).getPendiente());
                    String fac = ListaFactCredit.get(i).getFecha().toString() + " / " + numeroComp + " / " + ListaFactCredit.get(i).getMoneda().toString() + String.valueOf(ListaFactCredit.get(i).getTotal());
                    model.addRow(new Object[]{fac, pend, 0, ListaFactCredit.get(i)});
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    String pend = String.valueOf(ListaFactCredit.get(i).getPendiente());
                    String fac = ListaFactCredit.get(i).getFecha().toString() + " / " + numeroComp + " / " + ListaFactCredit.get(i).getMoneda().toString() + String.valueOf(ListaFactCredit.get(i).getTotal());
                    model.addRow(new Object[]{fac, pend, 0, ListaFactCredit.get(i)});
                }
            }
        }

        DefaultListModel<String> modelNC = new DefaultListModel<>();
        this.JListNC.setModel(modelNC);
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListarNC = Conexion.getInstance().ListarNC((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListarNC.size(); i++) {
                if (ListarNC.get(i).getMoneda() == tipoMoneda.$U) {
                    String numeroComp = ListarNC.get(i).getSerieComprobante() + "-" + ListarNC.get(i).getNroComprobante();
                    String pend = ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getPendiente());
                    String fac = numeroComp + " / Total: " + ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getTotal()) + " / Pendiente: " + pend;
                    modelNC.addElement(fac);
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListarNC = Conexion.getInstance().ListarNC((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListarNC.size(); i++) {
                if (ListarNC.get(i).getMoneda() == tipoMoneda.US$) {
                    String numeroComp = ListarNC.get(i).getSerieComprobante() + "-" + ListarNC.get(i).getNroComprobante();
                    String pend = ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getPendiente());
                    String fac = numeroComp + " / Total: " + ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getTotal()) + " / Pendiente: " + pend;
                    modelNC.addElement(fac);
                }
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        JListNC = new javax.swing.JList<>();
        jComboBoxProveedores = new javax.swing.JComboBox<>();
        jButtonAsignar = new javax.swing.JButton();
        jButtonCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFacturas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jCBMoneda = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asignar Nota de Credito");

        jScrollPane1.setViewportView(JListNC);

        jComboBoxProveedores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxProveedoresItemStateChanged(evt);
            }
        });

        jButtonAsignar.setText("Asignar");
        jButtonAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAsignarActionPerformed(evt);
            }
        });

        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Facturas a asigar:");

        jLabel2.setText("Nota de credito:");

        jTableFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Factura", "Pendiente", "Entrega", "Objeto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableFacturas);
        if (jTableFacturas.getColumnModel().getColumnCount() > 0) {
            jTableFacturas.getColumnModel().getColumn(0).setResizable(false);
            jTableFacturas.getColumnModel().getColumn(0).setPreferredWidth(230);
            jTableFacturas.getColumnModel().getColumn(1).setResizable(false);
            jTableFacturas.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableFacturas.getColumnModel().getColumn(2).setResizable(false);
            jTableFacturas.getColumnModel().getColumn(3).setResizable(false);
            jTableFacturas.getColumnModel().getColumn(3).setPreferredWidth(0);
        }

        jLabel3.setText("Moneda");

        jCBMoneda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBMonedaItemStateChanged(evt);
            }
        });

        jLabel4.setText("Proveedor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAsignar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 24, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(257, 257, 257))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCBMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jCBMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAsignar)
                    .addComponent(jButtonCerrar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jComboBoxProveedoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxProveedoresItemStateChanged
        if (this.jTableFacturas.isEditing()) {
            this.jTableFacturas.getCellEditor().stopCellEditing();
        }
        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
        model.setRowCount(0);
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    String pend = String.valueOf(ListaFactCredit.get(i).getPendiente());
                    String fac = ListaFactCredit.get(i).getFecha().toString() + " / " + numeroComp + " / " + ListaFactCredit.get(i).getMoneda().toString() + String.valueOf(ListaFactCredit.get(i).getTotal());
                    model.addRow(new Object[]{fac, pend, 0, ListaFactCredit.get(i)});
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    String pend = String.valueOf(ListaFactCredit.get(i).getPendiente());
                    String fac = ListaFactCredit.get(i).getFecha().toString() + " / " + numeroComp + " / " + ListaFactCredit.get(i).getMoneda().toString() + String.valueOf(ListaFactCredit.get(i).getTotal());
                    model.addRow(new Object[]{fac, pend, 0, ListaFactCredit.get(i)});
                }
            }
        }

        DefaultListModel<String> modelNC = new DefaultListModel<>();
        this.JListNC.setModel(modelNC);
        modelNC.clear();
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListarNC = Conexion.getInstance().ListarNC((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListarNC.size(); i++) {
                if (ListarNC.get(i).getMoneda() == tipoMoneda.$U) {
                    String numeroComp = ListarNC.get(i).getSerieComprobante() + "-" + ListarNC.get(i).getNroComprobante();
                    String pend = ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getPendiente());
                    String fac = numeroComp + " / Total: " + ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getTotal()) + " / Pendiente: " + pend;
                    modelNC.addElement(fac);
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListarNC = Conexion.getInstance().ListarNC((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListarNC.size(); i++) {
                if (ListarNC.get(i).getMoneda() == tipoMoneda.US$) {
                    String numeroComp = ListarNC.get(i).getSerieComprobante() + "-" + ListarNC.get(i).getNroComprobante();
                    String pend = ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getPendiente());
                    String fac = numeroComp + " / Total: " + ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getTotal()) + " / Pendiente: " + pend;
                    modelNC.addElement(fac);
                }
            }
        }
    }//GEN-LAST:event_jComboBoxProveedoresItemStateChanged

    private void jCBMonedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBMonedaItemStateChanged
        if (this.jTableFacturas.isEditing()) {
            this.jTableFacturas.getCellEditor().stopCellEditing();
        }
        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
        model.setRowCount(0);
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    String pend = String.valueOf(ListaFactCredit.get(i).getPendiente());
                    String fac = ListaFactCredit.get(i).getFecha().toString() + " / " + numeroComp + " / " + ListaFactCredit.get(i).getMoneda().toString() + String.valueOf(ListaFactCredit.get(i).getTotal());
                    model.addRow(new Object[]{fac, pend, 0, ListaFactCredit.get(i)});
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    String pend = String.valueOf(ListaFactCredit.get(i).getPendiente());
                    String fac = ListaFactCredit.get(i).getFecha().toString() + " / " + numeroComp + " / " + ListaFactCredit.get(i).getMoneda().toString() + String.valueOf(ListaFactCredit.get(i).getTotal());
                    model.addRow(new Object[]{fac, pend, 0, ListaFactCredit.get(i)});
                }
            }
        }

        DefaultListModel<String> modelNC = new DefaultListModel<>();
        this.JListNC.setModel(modelNC);
        modelNC.clear();
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListarNC = Conexion.getInstance().ListarNC((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListarNC.size(); i++) {
                if (ListarNC.get(i).getMoneda() == tipoMoneda.$U) {
                    String numeroComp = ListarNC.get(i).getSerieComprobante() + "-" + ListarNC.get(i).getNroComprobante();
                    String pend = ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getPendiente());
                    String fac = numeroComp + " / Total: " + ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getTotal()) + " / Pendiente: " + pend;
                    modelNC.addElement(fac);
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListarNC = Conexion.getInstance().ListarNC((Proveedor) this.jComboBoxProveedores.getSelectedItem());
            for (int i = 0; i < ListarNC.size(); i++) {
                if (ListarNC.get(i).getMoneda() == tipoMoneda.US$) {
                    String numeroComp = ListarNC.get(i).getSerieComprobante() + "-" + ListarNC.get(i).getNroComprobante();
                    String pend = ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getPendiente());
                    String fac = numeroComp + " / Total: " + ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getTotal()) + " / Pendiente: " + pend;
                    modelNC.addElement(fac);
                }
            }
        }
    }//GEN-LAST:event_jCBMonedaItemStateChanged

    private void jButtonAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAsignarActionPerformed

        if (this.JListNC.getSelectedIndices().length > 1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar solo una nota de credito.");
        } else {

            String nro_fac = this.JListNC.getSelectedValue();
            if (this.jTableFacturas.isEditing()) {
                this.jTableFacturas.getCellEditor().stopCellEditing();
            }

            if (nro_fac == null) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe seleccionar una nota de credito.");
            } else {

                String[] parts = nro_fac.split(" / ");
                String nro = parts[0];
                String[] parts2 = nro.split("-");
                Factura nc = Conexion.getInstance().getNotaDeCredito(parts2[0], parts2[1]);

                boolean saldomayorpendiente = false;
                DefaultTableModel modelo = (DefaultTableModel) this.jTableFacturas.getModel();
                float saldos = 0;
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    String s = modelo.getValueAt(i, 2).toString();
                    float saldo = Float.parseFloat(s);
                    String p = modelo.getValueAt(i, 1).toString();
                    float pend = Float.parseFloat(p);
                    if (saldo > pend) {
                        saldomayorpendiente = true;
                    }
                    saldos = saldos + saldo;
                }

                if (saldomayorpendiente) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La entrega debe ser igual o menor al importe pendiente de la factura.");
                } else if (saldos > nc.getPendiente()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "No se puede asignar mas de lo pendiente en la nota de credito.");
                } else {
                    List<F_R> listaf_r = new ArrayList<F_R>();
                    int facturas_linkeadas = 0;
                    float saldototal = 0;
                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        String s = modelo.getValueAt(i, 2).toString();
                        float saldo = Float.parseFloat(s);
                        if (saldo > 0) {
                            facturas_linkeadas++;
                            saldototal = saldo + saldototal;
                        }
                    }
                    if (facturas_linkeadas == 0) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Debe asignar la nota de credito a alguna factura.");
                    } else {
                        Recibo rec = new Recibo();
                        rec.setNc(true);
                        rec.setCotizacion(1);
                        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                            rec.setMoneda(tipoMoneda.$U);
                        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                            rec.setMoneda(tipoMoneda.US$);
                        }

                        rec.setSerieComprobante("NC/" + nc.getSerieComprobante());
                        rec.setNroComprobante(nc.getNroComprobante());
//                      rec.setObservacion(this.jTextComentario.getText());
                        rec.setProveedor((Proveedor) this.jComboBoxProveedores.getSelectedItem());
                        rec.setTotal(saldototal);
                        rec.setDeshabilitado(false);

                        for (int i = 0; i < modelo.getRowCount(); i++) {
                            String s = modelo.getValueAt(i, 2).toString();
                            float saldo = Float.parseFloat(s);
                            if (saldo > 0) {
                                F_R f_r = new F_R();
                                f_r.setRecibo(rec);

                                f_r.setSaldo(saldo);

                                Factura f = (Factura) this.jTableFacturas.getModel().getValueAt(i, 3);
                                float pendiente = f.getPendiente() - saldo;
                                f.setPendiente(pendiente);
                                f_r.setFactura(f);
                                Conexion.getInstance().merge(f);
                                listaf_r.add(f_r);
                            }
                        }
                        rec.setFr_s(listaf_r);

                        Float pend_nc = nc.getPendiente() - saldototal;
                        nc.setPendiente(pend_nc);

                        boolean recibo = Conexion.getInstance().persist(rec);
                        List<F_R> lf_r = rec.getFr_s();
                        for (int i = 0; i < lf_r.size(); i++) {
                            boolean fr = Conexion.getInstance().persist(lf_r.get(i));
                            if (!fr) {
                                javax.swing.JOptionPane.showMessageDialog(null, "Ha ocurrido un problema.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if (recibo) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Nota de credito asignada correctamente.", "Enhorabuena", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
                            model.setRowCount(0);
                            if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                                List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jComboBoxProveedores.getSelectedItem());
                                for (int i = 0; i < ListaFactCredit.size(); i++) {
                                    if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                                        String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                                        String pend = String.valueOf(ListaFactCredit.get(i).getPendiente());
                                        String fac = ListaFactCredit.get(i).getFecha().toString() + " / " + numeroComp + " / " + ListaFactCredit.get(i).getMoneda().toString() + String.valueOf(ListaFactCredit.get(i).getTotal());
                                        model.addRow(new Object[]{fac, pend, 0, ListaFactCredit.get(i)});
                                    }
                                }
                            } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                                List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jComboBoxProveedores.getSelectedItem());
                                for (int i = 0; i < ListaFactCredit.size(); i++) {
                                    if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                                        String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                                        String pend = String.valueOf(ListaFactCredit.get(i).getPendiente());
                                        String fac = ListaFactCredit.get(i).getFecha().toString() + " / " + numeroComp + " / " + ListaFactCredit.get(i).getMoneda().toString() + String.valueOf(ListaFactCredit.get(i).getTotal());
                                        model.addRow(new Object[]{fac, pend, 0, ListaFactCredit.get(i)});
                                    }
                                }
                            }

                            DefaultListModel<String> modelNC = new DefaultListModel<>();
                            this.JListNC.setModel(modelNC);
                            if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                                List<Factura> ListarNC = Conexion.getInstance().ListarNC((Proveedor) this.jComboBoxProveedores.getSelectedItem());
                                for (int i = 0; i < ListarNC.size(); i++) {
                                    if (ListarNC.get(i).getMoneda() == tipoMoneda.$U) {
                                        String numeroComp = ListarNC.get(i).getSerieComprobante() + "-" + ListarNC.get(i).getNroComprobante();
                                        String pend = ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getPendiente());
                                        String fac = numeroComp + " / Total: " + ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getTotal()) + " / Pendiente: " + pend;
                                        modelNC.addElement(fac);
                                    }
                                }
                            } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                                List<Factura> ListarNC = Conexion.getInstance().ListarNC((Proveedor) this.jComboBoxProveedores.getSelectedItem());
                                for (int i = 0; i < ListarNC.size(); i++) {
                                    if (ListarNC.get(i).getMoneda() == tipoMoneda.US$) {
                                        String numeroComp = ListarNC.get(i).getSerieComprobante() + "-" + ListarNC.get(i).getNroComprobante();
                                        String pend = ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getPendiente());
                                        String fac = numeroComp + " / Total: " + ListarNC.get(i).getMoneda().toString() + String.valueOf(ListarNC.get(i).getTotal()) + " / Pendiente: " + pend;
                                        modelNC.addElement(fac);
                                    }
                                }
                            }
                        }
                        else {
                            javax.swing.JOptionPane.showMessageDialog(null, "Ha ocurrido un problema.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonAsignarActionPerformed

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
            java.util.logging.Logger.getLogger(AsignarNC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AsignarNC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AsignarNC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AsignarNC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AsignarNC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> JListNC;
    private javax.swing.JButton jButtonAsignar;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JComboBox<String> jCBMoneda;
    private javax.swing.JComboBox<Proveedor> jComboBoxProveedores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableFacturas;
    // End of variables declaration//GEN-END:variables
}
