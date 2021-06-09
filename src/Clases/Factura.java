/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author joaco
 */
@Entity
public class Factura extends Comprobante implements Serializable {
    private tipoComprobante tipo;
    private float Pendiente;

    private boolean deshabilitado;
    private boolean cerrada;
    
    //LINKS-------------------------//
      
    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<F_P> fp_s;
    
    @OneToMany(mappedBy = "factura")
    private List<F_R> fr_s;
    //------------------------------//

    public tipoComprobante getTipo() {
        return tipo;
    }

    public void setTipo(tipoComprobante tipo) {
        this.tipo = tipo;
    }

    public float getPendiente() {
        return Pendiente;
    }

    public void setPendiente(float Pendiente) {
        this.Pendiente = Pendiente;
    }

    public List<F_P> getFp_s() {
        return fp_s;
    }

    public void setFp_s(List<F_P> fp_s) {
        this.fp_s = fp_s;
    }

    public List<F_R> getFr_s() {
        return fr_s;
    }

    public void setFr_s(List<F_R> fr_s) {
        this.fr_s = fr_s;
    }
    
        public boolean isDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }
    
    
}
