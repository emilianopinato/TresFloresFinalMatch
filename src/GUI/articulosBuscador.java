/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Conexion;
import Clases.Articulo;
import Clases.Proveedor;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author emiliano
 */
public class articulosBuscador extends javax.swing.JFrame {

    int sizeconsulta;
    AltaFactura AT = null;
    Articulo[] articulos = null;

    public articulosBuscador(String texto, AltaFactura AT) {
        initComponents();
        this.setResizable(false);
        //bordes redondeados
        Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 30, 30);
        AWTUtilities.setWindowShape(this, forma);
        
        this.AT = AT;
        Proveedor p = this.AT.getProveedor();
        List<Articulo> ListaArticulo = Conexion.getInstance().getArticuloxNombre_Descripcion(texto);
        sizeconsulta = ListaArticulo.size();
        articulos = new Articulo[ListaArticulo.size()];
        int cont = 0;
        Iterator it = ListaArticulo.iterator();
        while (it.hasNext()) {
            articulos[cont] = (Articulo) it.next();
            cont++;
        }
        
        if (sizeconsulta == 1) {
            if (!ListaArticulo.get(0).getProveedores().contains(p)) {
                //articulodeesteprov = true;
                int resp = JOptionPane.showConfirmDialog(this, "El articulo seleccionado no esta asociado a este proveedor, desea asociarlo?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    p.getArticulos().add(ListaArticulo.get(0));
                    ListaArticulo.get(0).getProveedores().add(p);
                    Conexion.getInstance().merge(p);
                    Conexion.getInstance().merge(ListaArticulo.get(0));
                    this.AT.articulo_seleccionado = articulos[0];
                    String textoArticulo = articulos[0].getCodigo() + " - " + articulos[0].getNombre() + " - " + articulos[0].getDescripcion();
                    this.AT.setTextArticulo(textoArticulo);
                }
            } else {
                this.AT.articulo_seleccionado = articulos[0];
                String textoArticulo = articulos[0].getCodigo() + " - " + articulos[0].getNombre() + " - " + articulos[0].getDescripcion();
                this.AT.setTextArticulo(textoArticulo);
            }

        }

        //para que liste primero los articulos de este prov
        this.jCheckBoxArtdeesteProv.setSelected(true);
        if (this.jCheckBoxArtdeesteProv.isSelected()) {

            int tamaño = 0;
            for (int i = 0; i < this.articulos.length; i++) {
                for (int b = 0; b < this.articulos[i].getProveedores().size(); b++) {
                    if (this.articulos[i].getProveedores().get(b).getRazonSocial().equals(p.getRazonSocial())) {
                        tamaño++;
                    }
                }
            }
            Articulo[] articulosnuevos = new Articulo[tamaño];
            int cont2 = 0;

            for (int i = 0; i < this.articulos.length; i++) {
                for (int b = 0; b < this.articulos[i].getProveedores().size(); b++) {
                    if (this.articulos[i].getProveedores().get(b).getRazonSocial().equals(p.getRazonSocial())) {
                        articulosnuevos[cont2] = this.articulos[i];
                        cont2++;
                    }
                }
            }
            this.JListArticulos.setListData(articulosnuevos);

        } else {
            this.JListArticulos.setListData(this.articulos);
        }

        this.setLocationRelativeTo(null);
        this.JListArticulos.setSelectedIndex(0);
        this.JListArticulos.requestFocus();
        this.getContentPane().setBackground(Color.GRAY);

    }

    public articulosBuscador() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        JListArticulos = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jCheckBoxArtdeesteProv = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("nb.output.link.foreground"));
        setUndecorated(true);

        JListArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JListArticulosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(JListArticulos);

        jButton1.setText("x");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBoxArtdeesteProv.setText("articulos de este prov.");
        jCheckBoxArtdeesteProv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxArtdeesteProvItemStateChanged(evt);
            }
        });
        jCheckBoxArtdeesteProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxArtdeesteProvMouseClicked(evt);
            }
        });
        jCheckBoxArtdeesteProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxArtdeesteProvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBoxArtdeesteProv)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jCheckBoxArtdeesteProv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JListArticulosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JListArticulosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Proveedor p = this.AT.getProveedor();
            if(!this.JListArticulos.getSelectedValue().getProveedores().contains(p)){
                int resp = JOptionPane.showConfirmDialog(this, "El articulo seleccionado no esta asociado a este proveedor, desea asociarlo?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    p.getArticulos().add(this.JListArticulos.getSelectedValue());
                    this.JListArticulos.getSelectedValue().getProveedores().add(p);
                    Conexion.getInstance().merge(p);
                    Conexion.getInstance().merge(this.JListArticulos.getSelectedValue());
                    this.AT.articulo_seleccionado = this.JListArticulos.getSelectedValue();
                    String textoArticulo = this.JListArticulos.getSelectedValue().getCodigo() + " - " + this.JListArticulos.getSelectedValue().getNombre() + " - " + this.JListArticulos.getSelectedValue().getDescripcion();
                    this.AT.setTextArticulo(textoArticulo);
                    this.dispose();
                }
                
            this.dispose();
            }else{
                this.AT.articulo_seleccionado = this.JListArticulos.getSelectedValue();
                String textoArticulo = this.JListArticulos.getSelectedValue().getCodigo() + " - " + this.JListArticulos.getSelectedValue().getNombre() + " - " + this.JListArticulos.getSelectedValue().getDescripcion();
                this.AT.setTextArticulo(textoArticulo);
                this.dispose();
            }
        }
    }//GEN-LAST:event_JListArticulosKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxArtdeesteProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxArtdeesteProvActionPerformed

    }//GEN-LAST:event_jCheckBoxArtdeesteProvActionPerformed

    private void jCheckBoxArtdeesteProvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxArtdeesteProvItemStateChanged
        if (this.jCheckBoxArtdeesteProv.isSelected()) {
            Proveedor p = this.AT.getProveedor();
            int tamaño = 0;
            for (int i = 0; i < this.articulos.length; i++) {
                for (int b = 0; b < this.articulos[i].getProveedores().size(); b++) {
                    if (this.articulos[i].getProveedores().get(b).getRazonSocial().equals(p.getRazonSocial())) {
                        tamaño++;
                    }
                }
            }
            Articulo[] articulosnuevos = new Articulo[tamaño];
            int cont = 0;

            for (int i = 0; i < this.articulos.length; i++) {
                for (int b = 0; b < this.articulos[i].getProveedores().size(); b++) {
                    if (this.articulos[i].getProveedores().get(b).getRazonSocial().equals(p.getRazonSocial())) {
                        articulosnuevos[cont] = this.articulos[i];
                        cont++;
                    }
                }
            }
            this.JListArticulos.setListData(articulosnuevos);
            this.JListArticulos.requestFocus();
        } else {
            this.JListArticulos.setListData(this.articulos);
            this.JListArticulos.requestFocus();
        }
    }//GEN-LAST:event_jCheckBoxArtdeesteProvItemStateChanged

    private void jCheckBoxArtdeesteProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxArtdeesteProvMouseClicked

    }//GEN-LAST:event_jCheckBoxArtdeesteProvMouseClicked

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
            java.util.logging.Logger.getLogger(articulosBuscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(articulosBuscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(articulosBuscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(articulosBuscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new articulosBuscador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Articulo> JListArticulos;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBoxArtdeesteProv;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
