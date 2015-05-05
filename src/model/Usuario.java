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
public class Usuario  implements Serializable {

    private long id;
    private String nif;
    private String nombre;
    private String apellidos;
    private String calle;
    private String poblacion;
    private boolean administrador;
    private String username;
    private String password;
    private List clientes = new ArrayList<Cliente>();

    public Usuario() {
    }

    public Usuario(String nif, String nombre, String apellidos, String calle, String poblacion, boolean administrador, String username, String password) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.calle = calle;
        this.poblacion = poblacion;
        this.administrador = administrador;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public List getClientes() {
        return clientes;
    }

    public void setClientes(List clientes) {
        this.clientes = clientes;
    }

    public void addClientes(Cliente cli) {
        this.clientes.add(cli);
        cli.setUsuario(this);
    }
}
