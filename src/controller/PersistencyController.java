/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javafx.scene.shape.Circle;
import model.Categoria;
import model.Cliente;
import model.Pedido;
import model.Producto;
import model.Usuario;

/**
 *
 * @author mateo
 */
public class PersistencyController {

    public void hacerLogin(String username, String password) {
    }

    public void guardarDatosBajados() {
    }

    public void removeProducto(String nombre) {
    }

    public void editarProducto(String nombre, double precio, Image img, boolean inhabilitats, double descuento, Categoria categoria) {
    }

    public void crearProducto(String nombre, double precio, Image img, boolean inhabilitats, double descuento, Categoria categoria) {
    }

    public ArrayList<Producto> mostrarProducto() {
        return null;
    }

    public Producto filtrarProducto(String nombre) {
        return null;
    }

    public ArrayList<Categoria> mostrarCategoria() {
        return null;
    }

    public Categoria filtrarCategoria(String nombre) {
        return null;
    }

    public Circle crearGrafico(String tipo) {
        return null;
    }

    public Rectangle crearEstadisticas(String tipo) {
        return null;
    }

    public Cliente mostrarClientes() {
        return null;
    }

    public Cliente filtrarCliente(String nombre) {
        return null;
    }

    public void editarCliente(String nif, int edad, String nombre, String apellidos, double latitud, double longitud, Date proximaVisita) {
    }

    public void crearCliente(String nif, int edad, String nombre, String apellidos, double latitud, double longitud, Date proximaVisita) {
    }

    public void removeUsuario(String nombre) {
    }

    public void crearCategoria(String nombre, double descuento) {
    }

    public void editarCategoria(String nombre, double descuento) {
    }

    public void removeCategoria(String nombre) {
    }

    public void crearUsuario(String nid, String nombre, String calle, String poblacion, String username, String password) {
    }

    public Usuario filtrarUsuario(String nombre) {
        return null;
    }

    public ArrayList<Usuario> mostrarUsuarios() {
        return null;
    }

    public void crearPedido(Date fecha, Cliente cliente, ArrayList<Producto> productos) {
    }

    public void removePedido(long id, String client, Date fecha) {
    }

    public ArrayList<Pedido> mostrarPedido() {
        return null;
    }

    public TreeMap<Usuario, ArrayList<Cliente>> filtrarUsuarioClientes(String nombreUsuario, String nombreCliente) {
        return null;
    }

}
