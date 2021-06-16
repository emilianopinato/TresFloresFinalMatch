/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author joaco
 */
@Entity
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String codigo;
    
    private String nombre;
    private String descripcion;
    private boolean deshabilitado;
   
    //LINKS----------------------------//
    @ManyToMany
    private List<Proveedor> proveedores;

    @ManyToOne
    private Usuario usuario;
    
    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER)
    private List<Historial> historiales;

    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER )
    private List<F_P> f_ps;
    
    @ManyToOne
    private IVA iva;
    //----------------------------------//

    public Articulo() {
        this.proveedores = new ArrayList<>();
        this.f_ps = new ArrayList<F_P>();
        this.historiales = new ArrayList<>();
    }
   
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Historial> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(List<Historial> historiales) {
        this.historiales = historiales;
    }

    public List<F_P> getF_ps() {
        return f_ps;
    }

    public void setF_ps(List<F_P> f_ps) {
        this.f_ps = f_ps;
    }

    public IVA getIva() {
        return iva;
    }

    public void setIva(IVA iva) {
        this.iva = iva;
    }

    public boolean isDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }
         
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.codigo);
        return hash;
    }
      
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getCodigo() + " - " + this.getNombre() + " - " + this.getDescripcion();
    }

    public void setProveedor(Proveedor p) {
        this.proveedores.add(p);
    }
    
    public void addF_P(F_P fp){
        if(this.f_ps == null){
            this.f_ps = new ArrayList<F_P>();
            this.f_ps.add(fp);
        }else{
            this.f_ps.add(fp);   
        }
    }

}
