/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Conexion;
import Clases.Administrador;
import Clases.Cotizacion;
import Clases.controladorBasura;
import WebService.ArrayOfint;
import WebService.DatoscotizacionesDato;
import WebService.WsbcucotizacionesExecute;
import WebService.WsbcucotizacionesExecuteResponse;
import WebService.Wsbcucotizacionesin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author joaco
 */
public class pantallaPrincipal extends javax.swing.JFrame {
  
    /**
     * Creates new form pantallaPrincipal
     */
    public pantallaPrincipal() {
        initComponents();

        if (controladorBasura.getU() instanceof Administrador) {
            Administrador admin = (Administrador) controladorBasura.getU();
            if (admin.isSuperAdmin()) {
                javax.swing.JMenuItem jm = new JMenuItem("Alta usuario");
                jm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        altaUsuario aU = new altaUsuario();
                        aU.setVisible(true);
                    }
                });
                jMenu4.add(jm);
            }

            javax.swing.JMenuItem jm = new JMenuItem("Modificar mis datos");
            jm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    altaUsuario aU = new altaUsuario(controladorBasura.getU());
                    aU.setVisible(true);
                }
            });

            jMenu4.add(jm);

        } else {
            jMenu4.removeAll();
            javax.swing.JMenuItem jm = new JMenuItem("Modificar mis datos");
            jm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    altaUsuario aU = new altaUsuario(controladorBasura.getU());
                    aU.setVisible(true);
                }
            });

            jMenu4.add(jm);
        }
        setLocationRelativeTo(null);
        
        try{
           cargarCotizaciones(); 
        }catch(Exception e){
            //alerta.
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

        jMenuItem11 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuFactura = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();

        jMenuItem11.setText("jMenuItem11");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tres Flores - Proveedores");

        jLabel1.setText("Agregar fecha en la que el IVA empezó a usarse.");

        jLabel2.setText("Esta fecha la selecciona el usuario.");

        jMenu1.setText("Artículo");

        jMenuItem1.setText("Alta");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Baja");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Modificar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem12.setText("Comparar Precios");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Proveedor");

        jMenuItem5.setText("Nuevo");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem4.setText("Deshabilitar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem7.setText("Modificar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem13.setText("Recuperar");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuBar1.add(jMenu3);

        jMenuFactura.setText("Factura");

        jMenuItem6.setText("Ingresar Factura");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuFactura.add(jMenuItem6);

        jMenuItem8.setText("Ingresar Recibo");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenuFactura.add(jMenuItem8);

        jMenuItem9.setText("Listar Comprobantes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenuFactura.add(jMenuItem9);

        jMenuItem19.setText("Listar Deudas");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenuFactura.add(jMenuItem19);

        jMenuItem20.setText("Recuperar Comprobantes");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenuFactura.add(jMenuItem20);

        jMenuItem14.setText("Estado de Cuenta");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenuFactura.add(jMenuItem14);

        jMenuBar1.add(jMenuFactura);

        jMenu2.setText("IVA");

        jMenuItem10.setText("Alta de IVA");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Usuario");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Cotizaciones");

        jMenuItem15.setText("Crear cotización");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem15);

        jMenuItem17.setText("Modificar cotización");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem17);

        jMenuItem18.setText("Eliminar cotización");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem18);

        jMenuItem16.setText("Listado de cotizaciones");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem16);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Cierre de mes");

        jMenuItem21.setText("Cierre y listado");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem21);

        jMenuItem22.setText("Abrir mes");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem22);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        altaArticulo aP = new altaArticulo();
        aP.setLocationRelativeTo(null);
        aP.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        bajaArticulo bP = new bajaArticulo();
        bP.setLocationRelativeTo(null);
        bP.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        modificarArticulo mA = new modificarArticulo();
        mA.setLocationRelativeTo(null);
        mA.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        altaProveedor ap = new altaProveedor();
        ap.setLocationRelativeTo(null);
        ap.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        modificarProveedor mp = new modificarProveedor();
        mp.setLocationRelativeTo(null);
        mp.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        bajaProveedor bp = new bajaProveedor();
        bp.setLocationRelativeTo(null);
        bp.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        
        AltaFactura f = new AltaFactura();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        AltaRecibo r = new AltaRecibo();
        r.setLocationRelativeTo(null);
        r.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        ListarComprobantes lc = new ListarComprobantes();
        lc.setLocationRelativeTo(null);
        lc.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        altaIVA aI = new altaIVA();
        aI.setLocationRelativeTo(null);
        aI.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        compararPrecios CPuwu = new compararPrecios();
        CPuwu.setLocationRelativeTo(null);
        CPuwu.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        recuperarProveedor rp = new recuperarProveedor();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        altaCotizacion aC = new altaCotizacion();
        aC.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        listadoCotizacionesExistentes lCE = new listadoCotizacionesExistentes();
        lCE.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        modificarCotización mC = new modificarCotización();
        mC.setLocationRelativeTo(null);
        mC.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        listarDeudas ld = new listarDeudas();
        ld.setLocationRelativeTo(null);
        ld.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        RecuperarComprobantes RC = new RecuperarComprobantes();
        RC.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
        listadoComprasIVAs lCI = new listadoComprasIVAs();
        lCI.setLocationRelativeTo(null);
        lCI.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
        abrirMes aM = new abrirMes();
        aM.setLocationRelativeTo(null);
        aM.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        bajaCotizacion bC = new bajaCotizacion();
        bC.setLocationRelativeTo(null);
        bC.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        EstadodeCuenta estadodecuenta = new EstadodeCuenta();
        estadodecuenta.setLocationRelativeTo(null);
        estadodecuenta.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

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
            java.util.logging.Logger.getLogger(pantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pantallaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFactura;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables

    private void cargarCotizaciones() {
        Wsbcucotizacionesin datosCotizacion = new Wsbcucotizacionesin();

        //Tomamos la fecha de hoy.
        Date fechaHoy = new Date();
        
        SimpleDateFormat getAnioFormato = new SimpleDateFormat("yyyy");
        SimpleDateFormat getMesFormato = new SimpleDateFormat("MM");
        SimpleDateFormat getDiaFormato = new SimpleDateFormat("dd");
        
        int anio = Integer.parseInt(getAnioFormato.format(fechaHoy));
        int mes = Integer.parseInt(getMesFormato.format(fechaHoy)) - 2;
        int dia = Integer.parseInt(getDiaFormato.format(fechaHoy));
        

        //Tomamos la fecha de 2 meses para atrás.
        Calendar calendario = Calendar.getInstance();
        calendario.set(anio, mes, dia);
        Date fechaAnterior = calendario.getTime();
      
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.format(fechaHoy);
        sdf.format(fechaAnterior);

        //El calendario xml gregoriano lo pide el web service.
        XMLGregorianCalendar fec;
        XMLGregorianCalendar fec2;
        
        GregorianCalendar c = new GregorianCalendar();
        c.clear();
        c.setTime(fechaHoy);
        
        GregorianCalendar c2 = new GregorianCalendar();
        c2.clear();
        c2.setTime(fechaAnterior);
        try {
            fec = DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1,
                    c.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
                    DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
            
            fec2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH),
                    c2.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
                    DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);

            datosCotizacion.setFechaDesde(fec2);
            datosCotizacion.setFechaHasta(fec);

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(AltaFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Tipo de moneda que pide el web service, 2225 es el dólar---//
        ArrayOfint tipoMoneda = new ArrayOfint();
        List<Short> nroMoneda = tipoMoneda.getItem();
        Short numero = 2225;
        nroMoneda.add(numero);
        datosCotizacion.setMoneda(tipoMoneda);
        //-----------------------------------------------------------//

        //Grupo------------------------//
        byte grupo = 0;
        datosCotizacion.setGrupo(grupo);
        //-----------------------------//

        //Inserto los datos con los que va a consultar.
        WsbcucotizacionesExecute ejecutar = new WsbcucotizacionesExecute();
        ejecutar.setEntrada(datosCotizacion);

        //Acá está la respuesta del web service.
        WsbcucotizacionesExecuteResponse respuesta = execute(ejecutar);
        List<DatoscotizacionesDato> dtc = respuesta.getSalida().getDatoscotizaciones().getDatoscotizacionesDato();

        if (dtc.isEmpty()) {
            //javax.swing.JOptionPane.showMessageDialog(null, "No se pudo obtener cotización en la fecha seleccionada.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            for (DatoscotizacionesDato dtc1 : dtc) {
                Date fechaConvertida = null;
                XMLGregorianCalendar fecha = dtc1.getFecha();
                Calendar calendario2 = Calendar.getInstance();
                calendario2.set(fecha.getYear(), fecha.getMonth()-1, fecha.getDay());
                fechaConvertida = calendario2.getTime();
                sdf.format(fechaConvertida); 
                if(!Conexion.getInstance().existeFecha(fechaConvertida)){                                  
                    Cotizacion cot = new Cotizacion();
                    cot.setFecha(fechaConvertida);
                    cot.setImporte(dtc1.getTCC());
                    Conexion.getInstance().persist(cot);
                }
            }
        }
    }

    private static WsbcucotizacionesExecuteResponse execute(WebService.WsbcucotizacionesExecute parameters) {
        WebService.Wsbcucotizaciones service = new WebService.Wsbcucotizaciones();
        WebService.WsbcucotizacionesSoapPort port = service.getWsbcucotizacionesSoapPort();
        return port.execute(parameters);
    }

}
