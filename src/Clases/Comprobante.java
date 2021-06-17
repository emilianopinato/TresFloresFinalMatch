/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author joaco
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Comprobante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serieComprobante;
    private int nroComprobante;   
    @Enumerated(EnumType.ORDINAL)
    private tipoMoneda moneda;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private float total;
    private float cotizacion;   
    private float ivaMinimo;
    private float ivaBasico;
    
    //LINKS--------------------------------//
    @ManyToOne    
    private Proveedor proveedor;

    @ManyToOne
    private Usuario usuario;
    //------------------------------------//

    public String getSerieComprobante() {
        return serieComprobante;
    }

    public void setSerieComprobante(String serieComprobante) {
        this.serieComprobante = serieComprobante;
    }

    public int getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(int nroComprobante) {
        this.nroComprobante = nroComprobante;
    }
        
    public tipoMoneda getMoneda() {
        return moneda;
    }

    public void setMoneda(tipoMoneda moneda) {
        this.moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(float cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getIvaMinimo() {
        return ivaMinimo;
    }

    public void setIvaMinimo(float ivaMinimo) {
        this.ivaMinimo = ivaMinimo;
    }

    public float getIvaBasico() {
        return ivaBasico;
    }

    public void setIvaBasico(float ivaBasico) {
        this.ivaBasico = ivaBasico;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.serieComprobante);
        hash = 67 * hash + this.nroComprobante;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comprobante other = (Comprobante) obj;
        if (this.nroComprobante != other.nroComprobante) {
            return false;
        }
        
        if (!Objects.equals(this.serieComprobante, other.serieComprobante)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comprobante{" + "serieComprobante=" + serieComprobante + ", nroComprobante=" + nroComprobante + '}';
    }
      

}
