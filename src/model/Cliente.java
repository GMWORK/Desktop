/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mateo
 */
public class Cliente {
    
    private long id;
    private String nif;
    private String nombre;
    private String apellidos;
    private double longitud;
    private double latitud;
    private String calle;
    private String poblacion;
    private Date proximaVisita;
    private Usuario usuario;
    private List pedido = new ArrayList<Pedido>();
    
    public Cliente() {
    }
    
    public Cliente(String nif, String nombre, String apellidos, double longitud, double latitud, String calle, String poblacion, Date proximaVisita) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.longitud = longitud;
        this.latitud = latitud;
        this.calle = calle;
        this.poblacion = poblacion;
        this.proximaVisita = proximaVisita;
    }
    
    public long getId() {
        return id;
    }
    
    private void setId(long id) {
        this.id = id;
    }
    
    public String getNif() {
        return nif;
    }
    
    public void setNif(String nif) {
        this.nif = nif;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public double getLongitud() {
        return longitud;
    }
    
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    
    public double getLatitud() {
        return latitud;
    }
    
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    
    public String getCalle() {
        return calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    public String getPoblacion() {
        return poblacion;
    }
    
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
    
    public Date getProximaVisita() {
        return proximaVisita;
    }
    
    public void setProximaVisita(Date proximaVisita) {
        this.proximaVisita = proximaVisita;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List getPedido() {
        return pedido;
    }
    
    public void setPedido(List pedido) {
        this.pedido = pedido;
    }

    public void addPedido(Pedido ped) {
        this.pedido.add(ped);
        ped.setCliente(this);
    }
}
