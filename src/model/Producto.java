/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateo
 */
public class Producto implements Serializable {

    private long id;
    private String nombre;
    private double precio;
    private byte[] img;
    private boolean inhabilitats;
    private double descuento;
    private Categoria categoria;
    private List liniaPedido = new ArrayList<PedidoProducto>();

    public Producto() {
    }

    public Producto(String nombre, double precio, byte[] img, boolean inhabilitats, double descuento) {
        this.nombre = nombre;
        this.precio = precio;
        this.img = img;
        this.inhabilitats = inhabilitats;
        this.descuento = descuento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public boolean isInhabilitats() {
        return inhabilitats;
    }

    public void setInhabilitats(boolean inhabilitats) {
        this.inhabilitats = inhabilitats;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List getLiniaPedido() {
        return liniaPedido;
    }

    public void setLiniaPedido(List liniaPedido) {
        this.liniaPedido = liniaPedido;
    }

    public void addLiniaPedido(PedidoProducto liPro) {
        this.liniaPedido.add(liPro);
        liPro.setProducto(this);
    }

}
