/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mateo
 */
public class Pedido implements Serializable {

    private long id;
    private Date fecha;
    private String estado;
    private Cliente cliente;
    private List liniaProducto = new ArrayList<PedidoProducto>();
    
    public Pedido() {
    }
    
    public Pedido(Date fecha, String estado) {
        this.fecha = fecha;
        this.estado = estado;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public List getLiniaProducto() {
        return liniaProducto;
    }
    
    public void setLiniaProducto(List liniaProducto) {
        this.liniaProducto = liniaProducto;
    }

    public void addLiniaProducto(PedidoProducto pPro) {
        this.liniaProducto.add(pPro);
        pPro.setPedido(this);
        
    }
    
}
