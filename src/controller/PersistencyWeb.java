/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.utilidades.ThreadActualizarBajada;
import controller.utilidades.ThreadSOS;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.TreeMap;
import model.Categoria;
import model.Cliente;
import model.Pedido;
import model.Producto;
import model.Usuario;
import org.json.JSONException;

/**
 *
 * @author mateo
 */
public class PersistencyWeb {

    public void subirDatos(TreeMap<String, ArrayList> datos) {
    }

    public void subirUsuarios(ArrayList<Usuario> usu) {
    }

    public void subirCategoria(ArrayList<Categoria> usu) {
    }

    public void subirProducto(ArrayList<Producto> usu) {
    }

    public void subirUsuario(ArrayList<Usuario> usu) {
    }

    public void subirPedido(ArrayList<Pedido> usu) {
    }

    public TreeMap<String, ArrayList> bajarDatos() throws InterruptedException, JSONException, UnsupportedEncodingException {
        String[] urls = new String[]{"categoria", "producto", "usuario", "cliente", "pedido", "productohaspedido", "date"};
        ThreadSOS sos = new ThreadSOS(urls);
        Thread threadsos = new Thread(sos);
        threadsos.start();
        threadsos.join();
        return sos.getSOS();
    }

    public TreeMap<String, ArrayList> actualizarDatos() throws InterruptedException, JSONException, UnsupportedEncodingException {
        String[] urls = new String[]{"categoriasadescargar", "productosadescargar", "usuarioadescargar", "clienteadescargar", "pedidosadescargar", "pedidoproductosadescargar", "date"};
        ThreadActualizarBajada actualizar = new ThreadActualizarBajada(urls);
        Thread threadsos = new Thread(actualizar);
        threadsos.start();
        threadsos.join();
        return actualizar.doOperation();
    }

    public ArrayList<Usuario> bajarUsuarios() {
        return null;
    }

    public ArrayList<Cliente> bajarClientes() {
        return null;
    }

    public ArrayList<Categoria> bajarCategorias() {
        return null;
    }

    public ArrayList<Producto> bajarProductos() {
        return null;
    }

    public ArrayList<Pedido> bajarPedidos() {
        return null;
    }
}
