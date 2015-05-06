/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author mateo
 */
public class Categoria implements Serializable {

    private long id;
    private String nombre;
    private double descuento;
    private List productos = new ArrayList<Producto>();
    String[] labels = new String[]{"id", "nombre", "descuento", "productos"};
    Vector<String> tableHeaders;
    Vector tableData;

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

    public String[] getLabels() {
        return labels;
    }

    public Vector<String> getTableHeaders() {
        tableHeaders = new Vector<String>();
        tableHeaders.add("ID");
        tableHeaders.add("NOMBRE");
        tableHeaders.add("DESCUENTO");
        tableHeaders.add("PRODUCTOS");
        return tableHeaders;
    }

    public Vector getTableData() {
        Vector<Object> oneRow = new Vector<Object>();
        oneRow.add(this.getId());
        oneRow.add(this.getNombre());
        oneRow.add(this.getDescuento());
        oneRow.add(this.getProductos());
        tableData.add(oneRow);
        return tableData;
    }

}
