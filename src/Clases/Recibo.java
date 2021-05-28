/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author joaco
 */
@Entity
public class Recibo extends Comprobante implements Serializable {
    
    private String observacion;
    private boolean deshabilitado;

    //LINKS------------------------//
    @OneToMany(mappedBy = "recibo")
    private List<F_R> fr_s;
    //-----------------------------//

    public List<F_R> getFr_s() {
        return fr_s;
    }

    public void setFr_s(List<F_R> fr_s) {
        this.fr_s = fr_s;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

}
