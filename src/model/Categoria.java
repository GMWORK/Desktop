/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateo
 */
public class Categoria implements Serializable{

    private long id;
    private String nombre;
    private double descuento;
    private List productos = new ArrayList<Producto>();

    public Categoria() {
    }

    public Categoria(String nombre, double descuento) {
        this.nombre = nombre;
        this.descuento = descuento;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public List getProductos() {
        return productos;
    }

    public void setProductos(List productos) {
        this.productos = productos;
    }

    public void addProducto(Producto pro) {
        this.productos.add(pro);
        pro.setCategoria(this);
         
    }
}
