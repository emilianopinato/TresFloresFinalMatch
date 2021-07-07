/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Conexion;
import Clases.ColorearFilas;
import Clases.F_R;
import Clases.Factura;
import Clases.Proveedor;
import Clases.Recibo;
import Clases.tipoMoneda;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author emiliano
 */
public class AltaRecibo extends javax.swing.JFrame {

    boolean vista = false;
    Recibo r;
    float importerecordado = 0;
    Dimension original;
    List<Factura> facturas_recordadas = new ArrayList<Factura>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form AltaRecibo
     */
    public AltaRecibo() {
        initComponents();
        this.setTitle("Nuevo recibo");
        original = this.getSize();
        this.setSize((int) original.getWidth() - 18, (int) original.getHeight() - 40);
        this.setLocationRelativeTo(null);
        this.jMenuBar1.setVisible(false);
        AutoCompleteDecorator.decorate(this.jCBProveedor);
        List<Proveedor> LProv = Conexion.getInstance().listadoProveedores();
        LProv.forEach((p) -> {
            if (!p.isDeshabilitado()) {
                this.jCBProveedor.addItem(p);
            }
        });
        this.jCBMoneda.setModel(new DefaultComboBoxModel(tipoMoneda.values()));
        this.jPanelModificar.setVisible(false);

        this.jTableFacturas.getColumnModel().getColumn(5).setMinWidth(0);
        this.jTableFacturas.getColumnModel().getColumn(5).setMaxWidth(0);
        this.jTableFacturas.getColumnModel().getColumn(5).setWidth(0);

        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
        model.setRowCount(0);
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                        numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                        numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                }
            }
        }

        Color color = new Color(255, 249, 140);
        DefaultTableCellRenderer alinear_derecha_y_color = new DefaultTableCellRenderer();
        alinear_derecha_y_color.setBackground(color);
        alinear_derecha_y_color.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTableFacturas.getColumnModel().getColumn(4).setCellRenderer(alinear_derecha_y_color);
    }

    public AltaRecibo(Recibo rec) {
        initComponents();
        this.setTitle("Recibo: " + rec.getSerieComprobante() + " - " + rec.getNroComprobante());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.jMenuBar1.setVisible(true);
        AutoCompleteDecorator.decorate(this.jCBProveedor);
        this.jCBMoneda.setModel(new DefaultComboBoxModel(tipoMoneda.values()));
        this.jPanelModificar.setVisible(false);
        this.vista = true;
        this.r = rec;

        this.jTextSerie.setEditable(false);
        this.jTextNumero.setEditable(false);
        this.jTextImporte.setEditable(false);
        this.jTextComentario.setEditable(false);
        this.jCBProveedor.setEnabled(false);
        this.jCBMoneda.setEnabled(false);
        this.jDateChooser.setEnabled(false);
        this.jButtonIngresar.setVisible(false);

        this.jTableFacturas.getColumnModel().getColumn(5).setMinWidth(0);
        this.jTableFacturas.getColumnModel().getColumn(5).setMaxWidth(0);
        this.jTableFacturas.getColumnModel().getColumn(5).setWidth(0);
        this.jTableFacturas.setEnabled(false);

        this.jCBProveedor.addItem(rec.getProveedor());
        this.jTextSerie.setText(rec.getSerieComprobante());
        this.jTextNumero.setText(String.valueOf(rec.getNroComprobante()));
        this.jTextImporte.setText(String.valueOf(rec.getTotal()));
        this.jDateChooser.setDate(rec.getFecha());
        this.jTextComentario.setText(rec.getObservacion());
        if (rec.getMoneda() == tipoMoneda.$U) {
            this.jCBMoneda.setSelectedIndex(0);
        } else if (rec.getMoneda() == tipoMoneda.US$) {
            this.jCBMoneda.setSelectedIndex(1);
        }

        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
        List<F_R> listaf_r = rec.getFr_s();

        for (int i = 0; i < listaf_r.size(); i++) {
            //if (listaf_r.get(i).getSaldo() >= 0) {
            String numeroComp = listaf_r.get(i).getFactura().getSerieComprobante() + "-" + listaf_r.get(i).getFactura().getNroComprobante();
            model.addRow(new Object[]{sdf.format(listaf_r.get(i).getFactura().getFecha()), numeroComp, listaf_r.get(i).getFactura().getTotal(), listaf_r.get(i).getFactura().getPendiente(),
                listaf_r.get(i).getSaldo(), listaf_r.get(i).getFactura()});
            this.facturas_recordadas.add(listaf_r.get(i).getFactura());
            //}
        }
        Color color = new Color(255, 249, 140);
        DefaultTableCellRenderer alinear_derecha_y_color = new DefaultTableCellRenderer();
        alinear_derecha_y_color.setBackground(color);
        alinear_derecha_y_color.setHorizontalAlignment(SwingConstants.RIGHT);
        this.jTableFacturas.getColumnModel().getColumn(4).setCellRenderer(alinear_derecha_y_color);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextSerie = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextImporte = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextComentario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFacturas = new javax.swing.JTable();
        jButtonIngresar = new javax.swing.JButton();
        jButtonCerrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jCBMoneda = new javax.swing.JComboBox<>();
        jCBProveedor = new javax.swing.JComboBox<>();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jTextNumero = new javax.swing.JTextField();
        jPanelModificar = new javax.swing.JPanel();
        jButtonCerrarMod = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuEdicion = new javax.swing.JMenu();
        jMenuItemModificar = new javax.swing.JMenuItem();
        jMenuItemEliminar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Fecha");

        jLabel2.setText("Proveedor");

        jLabel3.setText("Numero");

        jTextSerie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextSerieFocusLost(evt);
            }
        });
        jTextSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextSerieKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextSerieKeyTyped(evt);
            }
        });

        jLabel4.setText("Importe");

        jTextImporte.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextImporteFocusLost(evt);
            }
        });
        jTextImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextImporteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextImporteKeyTyped(evt);
            }
        });

        jLabel5.setText("Comentario");

        jTextComentario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextComentarioKeyPressed(evt);
            }
        });

        jTableFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Numero", "Importe", "Pendiente", "Entrega", "Objeto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFacturas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableFacturasFocusLost(evt);
            }
        });
        jTableFacturas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableFacturasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableFacturasKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFacturas);

        jButtonIngresar.setText("Ingresar");
        jButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarActionPerformed(evt);
            }
        });

        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jLabel6.setText("Moneda");

        jCBMoneda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBMonedaItemStateChanged(evt);
            }
        });

        jCBProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBProveedorItemStateChanged(evt);
            }
        });
        jCBProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBProveedorActionPerformed(evt);
            }
        });
        jCBProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCBProveedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCBProveedorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jCBProveedorKeyTyped(evt);
            }
        });

        jTextNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextNumeroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNumeroKeyTyped(evt);
            }
        });

        jPanelModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153)));

        jButtonCerrarMod.setText("x");
        jButtonCerrarMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarModActionPerformed(evt);
            }
        });

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelModificarLayout = new javax.swing.GroupLayout(jPanelModificar);
        jPanelModificar.setLayout(jPanelModificarLayout);
        jPanelModificarLayout.setHorizontalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCerrarMod)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelModificarLayout.setVerticalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonModificar)
                    .addComponent(jButtonCerrarMod))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuEdicion.setText("Edición");

        jMenuItemModificar.setText("Modificar");
        jMenuItemModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarActionPerformed(evt);
            }
        });
        jMenuEdicion.add(jMenuItemModificar);

        jMenuItemEliminar.setText("Deshabilitar");
        jMenuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarActionPerformed(evt);
            }
        });
        jMenuEdicion.add(jMenuItemEliminar);

        jMenuBar1.add(jMenuEdicion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanelModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonIngresar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonCerrar))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(7, 7, 7)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextNumero))
                                    .addComponent(jTextImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jCBMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel1)
                        .addComponent(jTextImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonIngresar)
                        .addComponent(jButtonCerrar))
                    .addComponent(jPanelModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jCBProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBProveedorItemStateChanged
        if (this.vista == false) {
            if (this.jTableFacturas.isEditing()) {
                this.jTableFacturas.getCellEditor().stopCellEditing();
            }
            DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
            model.setRowCount(0);
            if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
                for (int i = 0; i < ListaFactCredit.size(); i++) {
                    if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                        String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                        model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                            numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                    }
                }
            } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
                for (int i = 0; i < ListaFactCredit.size(); i++) {
                    if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                        String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                        model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                            numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                    }
                }
            }
        }
    }//GEN-LAST:event_jCBProveedorItemStateChanged

    private void jButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarActionPerformed
        boolean saldomayorpendiente = false;
        boolean valornulo = false;
        DefaultTableModel modelo = (DefaultTableModel) this.jTableFacturas.getModel();

        if (this.jTableFacturas.isEditing()) {
            this.jTableFacturas.getCellEditor().stopCellEditing();
        }
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 4) == null) {
                valornulo = true;
            }
        }
        if (valornulo) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe ingresar un valor correcto");
        } else {

            float saldos = 0;
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String s = modelo.getValueAt(i, 4).toString();
                float saldo = Float.parseFloat(s);
                String p = modelo.getValueAt(i, 3).toString();
                float pend = Float.parseFloat(p);
                if (saldo > pend) {
                    saldomayorpendiente = true;
                }
                saldos = saldos + saldo;
            }
            if (this.jTextImporte.getText().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Es necesario ingresar el importe total del recibo");
            } else if (saldomayorpendiente) {
                javax.swing.JOptionPane.showMessageDialog(null, "La entrega debe ser igual o menor al importe pendiente de la factura.");
            } else if (this.jTextSerie.getText().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe ingresar la serie.");
            } else if (this.jTextNumero.getText().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe ingresar el numero del recibo.");
            } else if (this.jDateChooser.getDate() == null) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe ingresar la fecha del recibo.");
            } else if (saldos > Float.parseFloat(this.jTextImporte.getText())) {
                javax.swing.JOptionPane.showMessageDialog(null, "No se puede asignar mas de lo ingresado en el importe.");
            } else {
                String prov = String.valueOf(((Proveedor) this.jCBProveedor.getSelectedItem()).getCodigo());
                if (Conexion.getInstance().existeRec(this.jTextSerie.getText(), this.jTextNumero.getText(), prov) == true) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Ya ingresaste un recibo con ese numero anteriormente.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {

                    if (this.jTableFacturas.isEditing()) {
                        this.jTableFacturas.getCellEditor().stopCellEditing();
                    }
                    List<F_R> listaf_r = new ArrayList<F_R>();
                    int facturas_linkeadas = 0;
                    float saldototal = 0;
                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        String s = modelo.getValueAt(i, 4).toString();
                        float saldo = Float.parseFloat(s);
                        if (saldo > 0) {
                            facturas_linkeadas++;
                            saldototal = saldo + saldototal;
                        }
                    }
                    if (facturas_linkeadas == 0 || saldototal < Float.parseFloat(this.jTextImporte.getText())) {
                        int resp = JOptionPane.showConfirmDialog(this, "El saldo de las facturas es menor al importe total ¿Continuar igualmente?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (resp == 0) {
                            Recibo rec = new Recibo();
                            rec.setFecha(this.jDateChooser.getDate());
                            rec.setCotizacion(1);
                            if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                                rec.setMoneda(tipoMoneda.$U);
                            } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                                rec.setMoneda(tipoMoneda.US$);
                            }
                            rec.setSerieComprobante(this.jTextSerie.getText());
                            rec.setNroComprobante(Integer.parseInt(this.jTextNumero.getText()));
                            rec.setObservacion(this.jTextComentario.getText());
                            rec.setProveedor((Proveedor) this.jCBProveedor.getSelectedItem());
                            rec.setTotal(Integer.parseInt(this.jTextImporte.getText()));
                            rec.setDeshabilitado(false);
                            boolean recibo = Conexion.getInstance().persist(rec);

                            for (int i = 0; i < modelo.getRowCount(); i++) {
                                String s = modelo.getValueAt(i, 4).toString();
                                float saldo = Float.parseFloat(s);
                                if (saldo > 0) {
                                    F_R f_r = new F_R();
                                    f_r.setRecibo(rec);

                                    f_r.setSaldo(saldo);

                                    Factura f = (Factura) this.jTableFacturas.getModel().getValueAt(i, 5);
                                    float pendiente = f.getPendiente() - saldo;
                                    f.setPendiente(pendiente);
                                    f_r.setFactura(f);
                                    f.getFr_s().add(f_r);
                                    boolean fr = Conexion.getInstance().persist(f_r);
                                    Conexion.getInstance().merge(f);
                                    listaf_r.add(f_r);
                                }
                            }
                            rec.setFr_s(listaf_r);
                            if (recibo) {
                                javax.swing.JOptionPane.showMessageDialog(null, "Recibo ingreado correctamente.", "Enhorabuena", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                                this.jTextSerie.setText("");
                                this.jTextNumero.setText("");
                                this.jTextComentario.setText("");
                                this.jTextImporte.setText("");
                                this.jDateChooser.setDate(new Date());
                                this.jCBMoneda.setSelectedIndex(0);

                                DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
                                model.setRowCount(0);
                                if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                                    List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
                                    for (int i = 0; i < ListaFactCredit.size(); i++) {
                                        if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                                            String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                                            model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                                                numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                                        }
                                    }
                                } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                                    List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
                                    for (int i = 0; i < ListaFactCredit.size(); i++) {
                                        if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                                            String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                                            model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                                                numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                                        }
                                    }
                                }
                                //this.dispose();
                            } else {
                                javax.swing.JOptionPane.showMessageDialog(null, "Ha ocurrido un problema.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        Recibo rec = new Recibo();
                        rec.setFecha(this.jDateChooser.getDate());
                        rec.setCotizacion(1);
                        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                            rec.setMoneda(tipoMoneda.$U);
                        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                            rec.setMoneda(tipoMoneda.US$);
                        }
                        rec.setSerieComprobante(this.jTextSerie.getText());
                        rec.setNroComprobante(Integer.parseInt(this.jTextNumero.getText()));
                        rec.setObservacion(this.jTextComentario.getText());
                        rec.setProveedor((Proveedor) this.jCBProveedor.getSelectedItem());
                        rec.setTotal(Integer.parseInt(this.jTextImporte.getText()));
                        rec.setDeshabilitado(false);
                        boolean recibo = Conexion.getInstance().persist(rec);

                        for (int i = 0; i < modelo.getRowCount(); i++) {
                            String s = modelo.getValueAt(i, 4).toString();
                            float saldo = Float.parseFloat(s);
                            if (saldo > 0) {
                                F_R f_r = new F_R();
                                f_r.setRecibo(rec);

                                f_r.setSaldo(saldo);

                                Factura f = (Factura) this.jTableFacturas.getModel().getValueAt(i, 5);
                                float pendiente = f.getPendiente() - saldo;
                                f.setPendiente(pendiente);
                                f_r.setFactura(f);
                                f.getFr_s().add(f_r);
                                boolean fr = Conexion.getInstance().persist(f_r);
                                Conexion.getInstance().merge(f);
                                listaf_r.add(f_r);
                            }
                        }
                        rec.setFr_s(listaf_r);
                        if (recibo) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Recibo ingresado correctamente.", "Enhorabuena", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            this.jTextSerie.setText("");
                            this.jTextNumero.setText("");
                            this.jTextComentario.setText("");
                            this.jTextImporte.setText("");
                            this.jDateChooser.setDate(new Date());
                            this.jCBMoneda.setSelectedIndex(0);

                            DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
                            model.setRowCount(0);
                            if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                                List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
                                for (int i = 0; i < ListaFactCredit.size(); i++) {
                                    if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                                        String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                                        model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                                            numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                                    }
                                }
                            } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                                List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
                                for (int i = 0; i < ListaFactCredit.size(); i++) {
                                    if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                                        String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                                        model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                                            numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                                    }
                                }
                            }
                            //this.dispose();
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "Ha ocurrido un problema.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonIngresarActionPerformed

    private void jMenuItemModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarActionPerformed
        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                    if (!this.facturas_recordadas.contains(ListaFactCredit.get(i))) {
                        String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                        model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                            numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                    }
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                    if (!this.facturas_recordadas.contains(ListaFactCredit.get(i))) {
                        String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                        model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                            numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                    }
                }
            }
        }

        this.jPanelModificar.setVisible(true);

        this.jTextSerie.setEditable(true);
        this.jTextNumero.setEditable(true);
        this.jTextImporte.setEditable(true);
        this.jTextComentario.setEditable(true);
        this.jCBProveedor.setEnabled(true);
        this.jCBMoneda.setEnabled(true);
        this.jDateChooser.setEnabled(true);
        this.jTableFacturas.setEnabled(true);
    }//GEN-LAST:event_jMenuItemModificarActionPerformed

    private void jMenuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarActionPerformed
        int resp = JOptionPane.showConfirmDialog(this, "Seguro desea deshabilitar este recibo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == 0) {
            this.r.setDeshabilitado(true);
            List<F_R> listaf_r = this.r.getFr_s();
            for (int i = 0; i < listaf_r.size(); i++) {
                Float pen_nuevo = listaf_r.get(i).getFactura().getPendiente() + listaf_r.get(i).getSaldo();
                listaf_r.get(i).getFactura().setPendiente(pen_nuevo);
            }
            Conexion.getInstance().merge(r);
            javax.swing.JOptionPane.showMessageDialog(this, "El recibo se ha deshabilitado correctamente.");

        }
    }//GEN-LAST:event_jMenuItemEliminarActionPerformed

    private void jTextNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNumeroKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_jTextNumeroKeyTyped

    private void jTextImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextImporteKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_jTextImporteKeyTyped

    private void jTextSerieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextSerieKeyTyped
        if (!Character.isLetter(evt.getKeyChar())) {
            evt.consume();
        } else if (this.jTextSerie.getText().length() > 1) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextSerieKeyTyped

    private void jTextSerieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextSerieKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.jTextNumero.requestFocus();
        }
    }//GEN-LAST:event_jTextSerieKeyPressed

    private void jTextNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNumeroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.jTextImporte.requestFocus();
        }
    }//GEN-LAST:event_jTextNumeroKeyPressed

    private void jCBProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCBProveedorKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            this.jTextImporte.requestFocus();
//        }
    }//GEN-LAST:event_jCBProveedorKeyPressed

    private void jCBProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCBProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBProveedorKeyTyped

    private void jCBProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCBProveedorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBProveedorKeyReleased

    private void jTextImporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextImporteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.jTextComentario.requestFocus();
        }
    }//GEN-LAST:event_jTextImporteKeyPressed

    private void jTextComentarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextComentarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.jButtonIngresar.requestFocus();
        }
    }//GEN-LAST:event_jTextComentarioKeyPressed

    private void jTableFacturasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableFacturasKeyTyped
        char caracter = evt.getKeyChar();
        /*DefaultTableModel modelo = (DefaultTableModel) this.jTableFacturas.getModel();
        String entrega = modelo.getValueAt(this.jTableFacturas.getSelectedRow(), 4).toString();
        if (caracter == '.' && entrega.length() == 0) {
            evt.consume();
        } else if (caracter == '.') {
            if (entrega.contains(".")) {
                evt.consume();
            }
        } else*/ if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_jTableFacturasKeyTyped

    private void jTableFacturasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableFacturasKeyPressed
        char caracter = evt.getKeyChar();
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_jTableFacturasKeyPressed

    private void jButtonCerrarModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarModActionPerformed
        if (this.jTableFacturas.isEditing()) {
            this.jTableFacturas.getCellEditor().stopCellEditing();
        }
        this.jTextSerie.setEditable(false);
        this.jTextNumero.setEditable(false);
        this.jTextImporte.setEditable(false);
        this.jTextComentario.setEditable(false);
        this.jCBProveedor.setEnabled(false);
        this.jCBMoneda.setEnabled(false);
        this.jDateChooser.setEnabled(false);
        this.jButtonIngresar.setVisible(false);

        this.jPanelModificar.setVisible(false);
        this.jTableFacturas.setEnabled(false);

        this.vista = true;
        Recibo rec = this.r;

        this.jCBProveedor.addItem(rec.getProveedor());
        this.jTextSerie.setText(rec.getSerieComprobante());
        this.jTextNumero.setText(String.valueOf(rec.getNroComprobante()));
        this.jTextImporte.setText(String.valueOf(rec.getTotal()));
        this.jDateChooser.setDate(rec.getFecha());
        this.jTextComentario.setText(rec.getObservacion());
        if (rec.getMoneda() == tipoMoneda.$U) {
            this.jCBMoneda.setSelectedIndex(0);
        } else if (rec.getMoneda() == tipoMoneda.US$) {
            this.jCBMoneda.setSelectedIndex(1);
        }

        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
        model.setRowCount(0);
        List<F_R> listaf_r = rec.getFr_s();

        for (int i = 0; i < listaf_r.size(); i++) {
            String numeroComp = listaf_r.get(i).getFactura().getSerieComprobante() + "-" + listaf_r.get(i).getFactura().getNroComprobante();
            model.addRow(new Object[]{sdf.format(listaf_r.get(i).getFactura().getFecha()), numeroComp, listaf_r.get(i).getFactura().getTotal(), listaf_r.get(i).getFactura().getPendiente(),
                listaf_r.get(i).getSaldo(), listaf_r.get(i).getFactura()});
        }
    }//GEN-LAST:event_jButtonCerrarModActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        //modificar
        Recibo rec = this.r;
        boolean saldomayorpendiente = false;
        DefaultTableModel modelo = (DefaultTableModel) this.jTableFacturas.getModel();
        float saldos = 0;
        if (this.jTableFacturas.isEditing()) {
            this.jTableFacturas.getCellEditor().stopCellEditing();
        }
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String s = modelo.getValueAt(i, 4).toString();
            float saldo = Float.parseFloat(s);
            if (saldo > 0) {
                Factura f = (Factura) this.jTableFacturas.getModel().getValueAt(i, 5);
                if (rec.getFr_s().size() > i) {
                    if (rec.getFr_s().get(i).getFactura() == f) {
                        if (rec.getFr_s().get(i) != null) {
                            float saldoanterior = rec.getFr_s().get(i).getSaldo();
                            //rec.getFr_s().get(i).setSaldo(saldo);
                            float pendiente = f.getPendiente() + saldoanterior - saldo;
                            //f.setPendiente(pendiente);
                            if (pendiente < 0) {
                                saldomayorpendiente = true;
                            }
                            saldos = saldos + saldo;
                        }
                    }
                } else {
                    String e = modelo.getValueAt(i, 4).toString();
                    float entrega = Float.parseFloat(e);
                    String p = modelo.getValueAt(i, 3).toString();
                    float pend = Float.parseFloat(p);
                    if (entrega > pend) {
                        saldomayorpendiente = true;
                    }
                    saldos = saldos + saldo;
                }
            }
        }
        if (saldos > Float.parseFloat(this.jTextImporte.getText())) {
            javax.swing.JOptionPane.showMessageDialog(null, "No se puede asignar mas de lo ingresado en el importe total.");
        } else if (saldomayorpendiente) {
            javax.swing.JOptionPane.showMessageDialog(null, "La entrega debe ser igual o menor al importe pendiente de la factura.");
        } else if (this.jTextSerie.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe ingresar la serie.");
        } else if (this.jTextNumero.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe ingresar el numero del recibo.");
        } else if (this.jDateChooser.getDate() == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe ingresar la fecha del recibo.");
        } else if (this.jTextImporte.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Es necesario ingresar el importe total del recibo");
        } else {
            if (Conexion.getInstance().existeRecModificar(rec.getSerieComprobante(), String.valueOf(rec.getNroComprobante()), String.valueOf(rec.getProveedor().getCodigo()),
                    this.jTextSerie.getText(), this.jTextNumero.getText(), String.valueOf(this.r.getProveedor().getCodigo())) == true) {
                javax.swing.JOptionPane.showMessageDialog(null, "Ya existe otro recibo con ese numero.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {

                if (this.jTableFacturas.isEditing()) {
                    this.jTableFacturas.getCellEditor().stopCellEditing();
                }
                int facturas_linkeadas = 0;
                float saldototal = 0;
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    String s = modelo.getValueAt(i, 4).toString();
                    float saldo = Float.parseFloat(s);
                    if (saldo > 0) {
                        facturas_linkeadas++;
                        saldototal = saldo + saldototal;
                    }
                }
                if (facturas_linkeadas == 0 || saldototal < Float.parseFloat(this.jTextImporte.getText())) {
                    int resp = JOptionPane.showConfirmDialog(this, "El saldo de las facturas es menor al importe total ¿Continuar igualmente?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {

                        rec.setFecha(this.jDateChooser.getDate());
                        rec.setCotizacion(1);
                        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                            rec.setMoneda(tipoMoneda.$U);
                        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                            rec.setMoneda(tipoMoneda.US$);
                        }
                        rec.setSerieComprobante(this.jTextSerie.getText());
                        rec.setNroComprobante(Integer.parseInt(this.jTextNumero.getText()));
                        rec.setObservacion(this.jTextComentario.getText());
                        rec.setProveedor((Proveedor) this.jCBProveedor.getSelectedItem());
                        rec.setTotal(Float.parseFloat(this.jTextImporte.getText()));
                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        String s = modelo.getValueAt(i, 4).toString();
                        float saldo = Float.parseFloat(s);
                        if (saldo > 0) {
                            Factura f = (Factura) this.jTableFacturas.getModel().getValueAt(i, 5);
                            if (rec.getFr_s().size() > i) {
                                if (rec.getFr_s().get(i).getFactura() == f) {
                                    float saldoanterior = rec.getFr_s().get(i).getSaldo();
                                    rec.getFr_s().get(i).setSaldo(saldo);
                                    float pendiente = f.getPendiente() + saldoanterior - saldo;
                                    f.setPendiente(pendiente);
                                }
                            } else {
                                float pend = f.getPendiente() - saldo;

                                f.setPendiente(pend);
                                
                                F_R f_r = new F_R();
                                f_r.setFactura(f);
                                f_r.setRecibo(rec);
                                f_r.setSaldo(saldo);
                                f.getFr_s().add(f_r);
                                rec.getFr_s().add(f_r);
                                Conexion.getInstance().persist(f_r);
                                Conexion.getInstance().merge(f);
                            }
                        } else if (saldo == 0) {
                            Factura f = (Factura) this.jTableFacturas.getModel().getValueAt(i, 5);
                            if (rec.getFr_s().size() > i) {
                                for (int x = 0; f.getFr_s().size() > x; x++) {
                                    F_R f_r = f.getFr_s().get(x);
                                    if (rec.getFr_s().contains(f_r)) {
                                        float saldo_anterior = f_r.getSaldo();
                                        float pend_nuevo = f.getPendiente() + saldo_anterior;
                                        rec.getFr_s().remove(f_r);
                                        f.getFr_s().remove(f_r);
                                        f.setPendiente(pend_nuevo);
                                        Conexion.getInstance().merge(f);
                                        Conexion.getInstance().borrarFR(f_r.getId());
                                    }
                                }
                            }
                        }
                    }
                        Conexion.getInstance().merge(rec);
                        javax.swing.JOptionPane.showMessageDialog(null, "Recibo modificado correctamente.", "Enhorabuena", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        this.jTextSerie.setEditable(false);
                        this.jTextNumero.setEditable(false);
                        this.jTextImporte.setEditable(false);
                        this.jTextComentario.setEditable(false);
                        this.jCBProveedor.setEnabled(false);
                        this.jCBMoneda.setEnabled(false);
                        this.jDateChooser.setEnabled(false);
                        this.jButtonIngresar.setVisible(false);

                        this.jPanelModificar.setVisible(false);
                        this.jTableFacturas.setEnabled(false);

                        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
                        List<F_R> listaf_r = rec.getFr_s();
                        model.setRowCount(0);
                        this.facturas_recordadas.clear();
                        for (int i = 0; i < listaf_r.size(); i++) {
                            this.facturas_recordadas.add(listaf_r.get(i).getFactura());
                            String numeroComp = listaf_r.get(i).getFactura().getSerieComprobante() + "-" + listaf_r.get(i).getFactura().getNroComprobante();
                            model.addRow(new Object[]{sdf.format(listaf_r.get(i).getFactura().getFecha()), numeroComp, listaf_r.get(i).getFactura().getTotal(), listaf_r.get(i).getFactura().getPendiente(),
                                listaf_r.get(i).getSaldo(), listaf_r.get(i).getFactura()});
                        }
                    }
                } else {

                    rec.setFecha(this.jDateChooser.getDate());
                    rec.setCotizacion(1);
                    if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
                        rec.setMoneda(tipoMoneda.$U);
                    } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
                        rec.setMoneda(tipoMoneda.US$);
                    }
                    rec.setSerieComprobante(this.jTextSerie.getText());
                    rec.setNroComprobante(Integer.parseInt(this.jTextNumero.getText()));
                    rec.setObservacion(this.jTextComentario.getText());
                    rec.setProveedor((Proveedor) this.jCBProveedor.getSelectedItem());
                    rec.setTotal(Float.parseFloat(this.jTextImporte.getText()));
                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        String s = modelo.getValueAt(i, 4).toString();
                        float saldo = Float.parseFloat(s);
                        if (saldo > 0) {
                            Factura f = (Factura) this.jTableFacturas.getModel().getValueAt(i, 5);
                            if (rec.getFr_s().size() > i) {
                                if (rec.getFr_s().get(i).getFactura() == f) {
                                    float saldoanterior = rec.getFr_s().get(i).getSaldo();
                                    rec.getFr_s().get(i).setSaldo(saldo);
                                    float pendiente = f.getPendiente() + saldoanterior - saldo;
                                    f.setPendiente(pendiente);
                                }
                            } else {
                                float pend = f.getPendiente() - saldo;

                                f.setPendiente(pend);
                                
                                F_R f_r = new F_R();
                                f_r.setFactura(f);
                                f_r.setRecibo(rec);
                                f_r.setSaldo(saldo);
                                f.getFr_s().add(f_r);
                                rec.getFr_s().add(f_r);
                                Conexion.getInstance().persist(f_r);
                                Conexion.getInstance().merge(f);
                            }
                        } else if (saldo == 0) {
                            Factura f = (Factura) this.jTableFacturas.getModel().getValueAt(i, 5);
                            if (rec.getFr_s().size() > i) {
                                for (int x = 0; f.getFr_s().size() > x; x++) {
                                    F_R f_r = f.getFr_s().get(x);
                                    if (rec.getFr_s().contains(f_r)) {
                                        float saldo_anterior = f_r.getSaldo();
                                        float pend_nuevo = f.getPendiente() + saldo_anterior;
                                        rec.getFr_s().remove(f_r);
                                        f.getFr_s().remove(f_r);
                                        f.setPendiente(pend_nuevo);
                                        Conexion.getInstance().merge(f);
                                        Conexion.getInstance().borrarFR(f_r.getId());
                                    }
                                }
                            }
                        }
                    }
                    Conexion.getInstance().merge(rec);
                    javax.swing.JOptionPane.showMessageDialog(null, "Recibo modificado correctamente.", "Enhorabuena", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    this.jTextSerie.setEditable(false);
                    this.jTextNumero.setEditable(false);
                    this.jTextImporte.setEditable(false);
                    this.jTextComentario.setEditable(false);
                    this.jCBProveedor.setEnabled(false);
                    this.jCBMoneda.setEnabled(false);
                    this.jDateChooser.setEnabled(false);
                    this.jButtonIngresar.setVisible(false);

                    this.jPanelModificar.setVisible(false);
                    this.jTableFacturas.setEnabled(false);

                    DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
                    List<F_R> listaf_r = rec.getFr_s();
                    model.setRowCount(0);
                    this.facturas_recordadas.clear();
                    for (int i = 0; i < listaf_r.size(); i++) {
                        this.facturas_recordadas.add(listaf_r.get(i).getFactura());
                        String numeroComp = listaf_r.get(i).getFactura().getSerieComprobante() + "-" + listaf_r.get(i).getFactura().getNroComprobante();
                        model.addRow(new Object[]{sdf.format(listaf_r.get(i).getFactura().getFecha()), numeroComp, listaf_r.get(i).getFactura().getTotal(), listaf_r.get(i).getFactura().getPendiente(),
                            listaf_r.get(i).getSaldo(), listaf_r.get(i).getFactura()});
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jCBProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBProveedorActionPerformed

    private void jTextSerieFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextSerieFocusLost
        String cadena = (this.jTextSerie.getText()).toUpperCase();
        this.jTextSerie.setText(cadena);
    }//GEN-LAST:event_jTextSerieFocusLost

    private void jCBMonedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBMonedaItemStateChanged
        if (this.jTableFacturas.isEditing()) {
            this.jTableFacturas.getCellEditor().stopCellEditing();
        }
        this.jTextImporte.setText("");
        DefaultTableModel model = (DefaultTableModel) this.jTableFacturas.getModel();
        model.setRowCount(0);
        if (this.jCBMoneda.getSelectedItem() == tipoMoneda.$U) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.$U) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                        numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                }
            }
        } else if (this.jCBMoneda.getSelectedItem() == tipoMoneda.US$) {
            List<Factura> ListaFactCredit = Conexion.getInstance().ListarFacturasCredito((Proveedor) this.jCBProveedor.getSelectedItem());
            for (int i = 0; i < ListaFactCredit.size(); i++) {
                if (ListaFactCredit.get(i).getMoneda() == tipoMoneda.US$) {
                    String numeroComp = ListaFactCredit.get(i).getSerieComprobante() + "-" + ListaFactCredit.get(i).getNroComprobante();
                    model.addRow(new Object[]{sdf.format(ListaFactCredit.get(i).getFecha()),
                        numeroComp, ListaFactCredit.get(i).getTotal(), ListaFactCredit.get(i).getPendiente(), 0, ListaFactCredit.get(i)});
                }
            }
        }
    }//GEN-LAST:event_jCBMonedaItemStateChanged

    private void jTextImporteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextImporteFocusLost
        float importe = 0;
        DefaultTableModel modelo = (DefaultTableModel) this.jTableFacturas.getModel();
        if (this.jTextImporte.getText() != null && !this.jTextImporte.getText().equals("")) {
            importe = Float.parseFloat(this.jTextImporte.getText());
            if (importe != this.importerecordado) {
                this.importerecordado = importe;
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    this.jTableFacturas.setValueAt(0, i, 4);
                }
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    if (importe > 0) {
                        String p = modelo.getValueAt(i, 3).toString();
                        float pendiente = Float.parseFloat(p);
                        if (importe >= pendiente) {
                            Locale currentLocale = getLocale();
                            DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(currentLocale);
                            unusualSymbols.setDecimalSeparator('.');
                            String strange = "#.00";
                            DecimalFormat formatoFloat = new DecimalFormat(strange, unusualSymbols);
                            this.jTableFacturas.setValueAt(formatoFloat.format(pendiente), i, 4);
                            importe = importe - pendiente;
                        } else {
                            Locale currentLocale = getLocale();
                            DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(currentLocale);
                            unusualSymbols.setDecimalSeparator('.');
                            String strange = "#.00";
                            DecimalFormat formatoFloat = new DecimalFormat(strange, unusualSymbols);
                            this.jTableFacturas.setValueAt(formatoFloat.format(importe), i, 4);
                            importe = 0;
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jTextImporteFocusLost

    private void jTableFacturasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableFacturasFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableFacturasFocusLost

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
            java.util.logging.Logger.getLogger(AltaRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AltaRecibo().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonCerrarMod;
    private javax.swing.JButton jButtonIngresar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JComboBox<String> jCBMoneda;
    private javax.swing.JComboBox<Proveedor> jCBProveedor;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdicion;
    private javax.swing.JMenuItem jMenuItemEliminar;
    private javax.swing.JMenuItem jMenuItemModificar;
    private javax.swing.JPanel jPanelModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFacturas;
    private javax.swing.JTextField jTextComentario;
    private javax.swing.JTextField jTextImporte;
    private javax.swing.JTextField jTextNumero;
    private javax.swing.JTextField jTextSerie;
    // End of variables declaration//GEN-END:variables
}
