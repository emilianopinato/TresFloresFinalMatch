/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaco
 */
public class controladorBasura {

    private static Usuario u;
    List<Articulo> articulosDeshabilitados;
    double precioCotizacion;
    
    private controladorBasura() {
        articulosDeshabilitados = new ArrayList<>();
    }

    public static controladorBasura getInstance() {
        return controladorBasuraHolder.INSTANCE;
    }

    private static class controladorBasuraHolder {

        private static final controladorBasura INSTANCE = new controladorBasura();
    }

    public static Usuario getU() {
        return u;
    }

    public static void setU(Usuario u) {
        controladorBasura.u = u;
    }
    
    public void agregarArticuloDeshabilitado(Articulo a){
        articulosDeshabilitados.add(a);
    }
    
    public List<Articulo> getAtrticulosDeshabilitados(){
        return articulosDeshabilitados;
    }

    public double getPrecioCotizacion() {
        return precioCotizacion;
    }

    public void setPrecioCotizacion(double precioCotizacion) {
        this.precioCotizacion = precioCotizacion;
    }
    
    
    
}
