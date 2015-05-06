/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.dao.CategoriaDAOController;
import controller.dao.ClienteDAOController;
import controller.dao.PedidoDAOController;
import controller.dao.ProductoDAOController;
import controller.dao.UsuarioDAOController;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.scene.shape.Circle;
import model.Categoria;
import model.Cliente;
import model.Pedido;
import model.Producto;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.persister.entity.AbstractEntityPersister;
import util.HibernateUtil;

/**
 *
 * @author mateo
 */
public class PersistencyController {

    private UsuarioDAOController usuDAO;
    private ProductoDAOController proDAO;
    private PedidoDAOController peDAO;
    private ClienteDAOController cliDAO;
    private CategoriaDAOController catDAO;

    public PersistencyController() {
        if (usuDAO == null) {
            usuDAO = new UsuarioDAOController();
        }
        if (proDAO == null) {
            proDAO = new ProductoDAOController();
        }
        if (peDAO == null) {
            peDAO = new PedidoDAOController();
        }
        if (cliDAO == null) {
            cliDAO = new ClienteDAOController();
        }
        if (catDAO == null) {
            catDAO = new CategoriaDAOController();
        }
    }

    public boolean hacerLogin(String username, String password) {
        Usuario usu = new Usuario();

        usu.setUsername(username);
        usu.setPassword(password);
        usuDAO.addUsuario(usu);
        if (usuDAO.filtrarUsuario(usu) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void guardarDatosBajados() {
    }

    public void removeProducto(String nombre) {
    }

    public void editarProducto(String nombre, double precio, byte[] img, boolean inhabilitats, double descuento, Categoria categoria) {
    }

    public void crearProducto(String nombre, double precio, byte[] img, boolean inhabilitats, double descuento, Categoria categoria) {
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

    public List<Categoria> filtrarCategoria(String campo,String filtro) {
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

    public List<Usuario> filtrarUsuario(String campo,String filtro) {
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

    public List<Object> getTableNames() {
        List<Object> lista = new ArrayList<Object>();
        Map m = HibernateUtil.getSessionFactory().getAllClassMetadata();
        Iterator iterator = m.keySet().iterator();
        while (iterator.hasNext()) {
            String next = (String) iterator.next();
            AbstractEntityPersister aep = (AbstractEntityPersister) m.get(next);
            lista.add(aep.getClassMetadata().getMappedClass());
        }
        return lista;
    }

    public void remove(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
