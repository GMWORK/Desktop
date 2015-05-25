/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Gestor.OrmLitetables;
import com.j256.ormlite.table.TableUtils;
import controller.dao.CategoriaDAOController;
import controller.dao.ClienteDAOController;
import controller.dao.HorasDAOController;
import controller.dao.PedidoDAOController;
import controller.dao.PedidoProductoDAOController;
import controller.dao.ProductoDAOController;
import controller.dao.UsuarioDAOController;
import controller.utilidades.MontarSubida;
import java.awt.Rectangle;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Circle;
import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import model.Categoria;

import model.Cliente;
import model.Horas;
import model.Pedido;
import model.PedidoProducto;
import model.Producto;
import model.Usuario;
import model.mapping.CategoriaVista;
import model.mapping.ClienteVista;
import model.mapping.PedidoProductoVista;
import model.mapping.PedidoVista;
import model.mapping.ProductoVista;
import model.mapping.UsuarioVista;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.reflections.Reflections;

//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.persister.entity.AbstractEntityPersister;
//import util.HibernateUtil;
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
    private HorasDAOController hoDAO;
    private PedidoProductoDAOController peProDAO;
    private OrmLitetables cliCat;
    private PersistencyWeb perWeb;

    public PersistencyController() throws SQLException, ClassNotFoundException {
        if (cliCat == null) {
            cliCat = new OrmLitetables();
        }

        if (usuDAO == null) {
            usuDAO = new UsuarioDAOController(cliCat);
        }
        if (peProDAO == null) {
            peProDAO = new PedidoProductoDAOController(cliCat);
        }
        if (proDAO == null) {
            proDAO = new ProductoDAOController(cliCat);
        }
        if (peDAO == null) {
            peDAO = new PedidoDAOController(cliCat);
        }
        if (cliDAO == null) {
            cliDAO = new ClienteDAOController(cliCat);
        }
        if (catDAO == null) {
            catDAO = new CategoriaDAOController(cliCat);
        }
        if (hoDAO == null) {
            hoDAO = new HorasDAOController(cliCat);
        }
        if (perWeb == null) {
            perWeb = new PersistencyWeb();
        }
    }

    public boolean hacerLogin(String username, String password) {
        Usuario usu = new Usuario();

        usu.setUsername(username);
        usu.setPassword(password);
//        usuDAO.insert(usu);
     /*   if (usuDAO.filtrarUsuario(usu) != null) {
         return true;
         } else {
         return false;
         }*/
        return true;
    }

    public void empezarActualizacion() {
        try {
            this.ActualizarDatosBajados(perWeb.actualizarDatos());
        } catch (SQLException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Actualizado con exito");

        }
    }

    public void ActualizarDatosBajados(TreeMap<String, ArrayList> map) throws SQLException {
        try {
            Categoria cat = null;
            DateTime ultima = DateTime.parse(getUltimaBajada());
            for (Object obj : map.get("HoraBajada")) {
                Horas hora = (Horas) obj;
                hoDAO.guardarHoraBajada(hora);
            }

            for (Object obj : map.get("Categoria")) {
                CategoriaVista vista = (CategoriaVista) obj;
                if (vista.getFecha().compareTo(ultima) > 0) {
                    switch (vista.getOp()) {
                        case "I":
                            catDAO.addCategoria(new Categoria(vista.getNombre(), vista.getDescuento()));
                            break;
                        case "U":
                            Categoria catlocal = catDAO.filtrarCategoria(vista.getNombre());
                            catlocal.setNombre(vista.getNombre());
                            catlocal.setDescuento(vista.getDescuento());
                            catDAO.EditarCategoria(catlocal);
                            break;
                        case "D":
                            catDAO.removeCategoria(vista.getNombre());
                            break;
                    }
                }
            }
            for (Object obj : map.get("Productos")) {
                ProductoVista vista = (ProductoVista) obj;
                if (vista.getFecha().compareTo(ultima) > 0) {
                    switch (vista.getOp()) {
                        case "I":
                            Producto pro = new Producto();
                            pro.setImg(vista.getImg());
                            pro.setDescuento(vista.getDescuento());
                            pro.setNombre(vista.getNombre());
                            pro.setPrecio(vista.getPrecio());
                            Categoria catPro = catDAO.filtrarCategoria(vista.getCategoriaid());
                            catPro.addProducto(pro);
                            pro.setCategoria(catPro);
                            catDAO.EditarCategoria(cat);
                            proDAO.addProducto(pro);
                            break;
                        case "U":
                            Producto catlocal = proDAO.filtrarProducto(vista.getNombre());
                            catlocal.setNombre(vista.getNombre());
                            catlocal.setDescuento(vista.getDescuento());
                            catlocal.setImg(vista.getImg());
                            catlocal.setInhabilitats(vista.isInhabilitats());
                            catlocal.setPrecio(vista.getPrecio());
                            Categoria catProEdit = catDAO.filtrarCategoria(vista.getCategoriaid());
                            cat.addProducto(catlocal);
                            catlocal.setCategoria(catProEdit);
                            catDAO.EditarCategoria(catProEdit);
                            proDAO.EditarProducto(catlocal);
                            break;
                        case "D":
                            proDAO.removeProducto(vista.getNombre());
                            break;
                    }
                }

            }

            for (Object obj : map.get("Usuario")) {
                UsuarioVista vista = (UsuarioVista) obj;
                if (vista.getFecha().compareTo(ultima) > 0) {
                    switch (vista.getOp()) {
                        case "I": {
                            try {
                                usuDAO.addUsuario(new Usuario(vista.getNif(), vista.getNombre(), vista.getApellidos(), vista.getCalle(), vista.getPoblacion(), vista.isAdministrador(), vista.getUsername(), vista.getPassword()));
                            } catch (SQLException ex) {
                                Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case "U":
                            Usuario catlocal = usuDAO.filtrarUsuario(vista.getNif());
                            catlocal.setNombre(vista.getNombre());
                            catlocal.setApellidos(vista.getApellidos());
                            catlocal.setCalle(vista.getCalle());
                            catlocal.setPoblacion(vista.getPoblacion());
                            catlocal.setAdministrador(vista.isAdministrador());
                            catlocal.setUsername(vista.getUsername());
                            catlocal.setPassword(vista.getPassword());
                            usuDAO.EditarProducto(catlocal);
                            break;
                        case "D":
                            usuDAO.removeUsuario(vista.getNombre());
                            break;
                    }
                }
            }
            for (Object obj : map.get("Cliente")) {
                ClienteVista vista = (ClienteVista) obj;
                if (vista.getFecha().compareTo(ultima) > 0) {
                    switch (vista.getOp()) {
                        case "I":
                            Cliente cli = new Cliente();
                            cli.setNif(vista.getNif());
                            cli.setNombre(vista.getNombre());
                            cli.setPoblacion(vista.getPoblacion());
                            cli.setCalle(vista.getCalle());
                            cli.setImg(vista.getImg());
                            cli.setLatitud(vista.getLatitud());
                            cli.setLongitud(vista.getLongitud());
                            cli.setProximaVisita(String.valueOf(vista.getProximaVisita()));
                            Usuario usu = usuDAO.filtrarUsuario(vista.getUsuarioid());
                            cli.setUsu(usu);
                            usu.addClientes(cli);
                            usuDAO.EditarProducto(usu);
                            cliDAO.addCliente(cli);
                            break;
                        case "U":
                            Cliente catlocal = cliDAO.filtrarCliente(vista.getNif());
                            catlocal.setNombre(vista.getNombre());
                            catlocal.setApellidos(vista.getApellidos());
                            catlocal.setCalle(vista.getCalle());
                            catlocal.setPoblacion(vista.getPoblacion());
                            catlocal.setLatitud(vista.getLatitud());
                            catlocal.setLongitud(vista.getLongitud());
                            catlocal.setCalle(vista.getCalle());
                            catlocal.setPoblacion(vista.getPoblacion());
                            catlocal.setProximaVisita(vista.getProximaVisita());
                            Usuario usuEdit = usuDAO.filtrarUsuario(vista.getUsuarioid());
                            catlocal.setUsu(usuEdit);
                            usuDAO.EditarProducto(usuEdit);
                            cliDAO.EditarCliente(catlocal);
                            break;
                        case "D":
                            cliDAO.removeCliente(vista.getNombre());
                            break;
                    }
                }
            }
            for (Object obj : map.get("Pedido")) {
                PedidoVista vista = (PedidoVista) obj;
                if (vista.getFecha().compareTo(ultima) > 0) {
                    switch (vista.getOp()) {
                        case "I":
                            Pedido ped = new Pedido();
                            ped.setTotal(vista.getTotal());
                            ped.setFechaEntrega(vista.getFechaEntrega());
                            ped.setEstado(vista.getEstado());
                            Cliente cli = cliDAO.filtrarCliente(vista.getClienteid());
                            ped.setCliente(cli);
                            cli.addPedido(ped);
                            cliDAO.EditarCliente(cli);
                            peDAO.addPedido(ped);
                        case "U":
                            Pedido catlocal = peDAO.filtrarPedido(vista.getId());
                            catlocal.setTotal(vista.getTotal());
                            catlocal.setFechaEntrega(vista.getFechaEntrega());
                            catlocal.setEstado(vista.getEstado());
                            Cliente cliEdit = cliDAO.filtrarCliente(vista.getClienteid());
                            catlocal.setCliente(cliEdit);
                            cliEdit.addPedido(catlocal);
                            cliDAO.EditarCliente(cliEdit);
                            peDAO.EditarPedido(catlocal);
                            break;
                        case "D":
                            peDAO.removePedido(vista.getId());
                            break;
                    }
                }
            }
            for (Object obj : map.get("PedidoProducto")) {
                PedidoProductoVista vista = (PedidoProductoVista) obj;
                if (vista.getFecha().compareTo(ultima) > 0) {
                    switch (vista.getOp()) {
                        case "I":
                            PedidoProducto prePro = new PedidoProducto();
                            prePro.setCantidad(Double.parseDouble(vista.getCantidad()));
                            Pedido pe = peDAO.filtrarPedido(vista.getPedidoid());
                            Producto pro = proDAO.filtrarProducto(vista.getProductoid());
                            pe.addLiniaProducto(prePro);
                            pro.addLiniaPedido(prePro);
                            proDAO.EditarProducto(pro);
                            peDAO.EditarPedido(pe);
                        case "U":
                            PedidoProducto pedProEdit = peProDAO.filtrarPedidoProductoPedido(vista.getPedidoid());

                            pedProEdit.setCantidad(Double.parseDouble(vista.getCantidad()));
                            peProDAO.EditarPedidoProducto(pedProEdit);
                            break;
                        case "D":
                            PedidoProducto pedProEs = peProDAO.filtrarPedidoProductoPedido(vista.getPedidoid());
                            peProDAO.removePedidoProductodePedido(pedProEs);
                            break;
                    }
                }
            }

        } finally {
            this.borrarlogs();
        }
    }

    private void borrarlogs() {
        cliCat.borrarLogs();
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

    public List<Categoria> filtrarCategoria(String campo, String filtro) {
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

    public void removeUsuario(int selected) {
//         usuDAO.delete(usuDAO.getUsuarios().get(selected));
    }

    public void editarUsuario(Usuario usu) {
//         usuDAO.update(usu);
    }

    public void crearCategoria(String nombre, double descuento) {
    }

    public void editarCategoria(String nombre, double descuento) {
    }

    public void removeCategoria(String nombre) {
    }

    public void crearUsuario(String nid, String nombre, String calle, String poblacion, String username, String password) {

    }

    public List<Usuario> filtrarUsuario(String campo, String filtro) {

        return (List<Usuario>) usuDAO.filtrarUsuario(filtro, campo);
    }

    public List<Usuario> mostrarUsuarios() throws SQLException {
        return usuDAO.getUsuarios();
    }

    public void crearPedido(Date fecha, Cliente cliente, ArrayList<Producto> productos) {
    }

    public void removePedido(long id, String client, Date fecha) {
    }

    public ArrayList<Pedido> mostrarPedido() {
        return null;
    }

    public void guardarDatosBajados(TreeMap<String, ArrayList> map) throws SQLException {
        try {
            Categoria cat = null;
            for (Object obj : map.get("HoraBajada")) {
                Horas hora = (Horas) obj;
                this.guardarUltimaBajada();

            }
            for (Object obj : map.get("Categoria")) {
                cat = (Categoria) obj;
                catDAO.addCategoria(cat);
            }
            for (Object obj : map.get("Productos")) {
                Producto pro = (Producto) obj;
                cat = catDAO.filtrarCategoria(pro.getCategoria().getNombre());
                cat.addProducto(pro);
                catDAO.EditarCategoria(cat);
                proDAO.addProducto(pro);
            }
            for (Object obj : map.get("Usuario")) {
                Usuario usu = (Usuario) obj;
                usuDAO.addUsuario(usu);
            }
            for (Object obj : map.get("Cliente")) {
                Cliente cli = (Cliente) obj;
                Usuario usu = usuDAO.filtrarUsuario(cli.getUsu().getNif());
                usu.addClientes(cli);
                usuDAO.EditarProducto(usu);
                cliDAO.addCliente(cli);
            }
            for (Object obj : map.get("Pedido")) {
                Pedido ped = (Pedido) obj;
                Cliente cli = cliDAO.filtrarCliente(ped.getCliente().getNif());
                ped.setCliente(cli);
                cliDAO.EditarCliente(cli);
                peDAO.addPedido(ped);
            }
            for (Object obj : map.get("PedidoProducto")) {
                PedidoProducto pedPro = (PedidoProducto) obj;
                Pedido ped = peDAO.filtrarPedido(pedPro.getPedido().getId());
                Producto pro = proDAO.filtrarProducto(pedPro.getProducto().getNombre());
                pro.addLiniaPedido(pedPro);
                ped.addLiniaProducto(pedPro);
                peDAO.EditarPedido(ped);
                proDAO.EditarProducto(pro);
                // pepoDAO.addPedidoProducto(pedPro);
            }

        } finally {
            this.borrarlogs();
        }
    }
    /* public TreeMap<Usuario, ArrayList<Cliente>> filtrarUsuarioClientes(String nombreUsuario, String nombreCliente) {
     return null;
     }*/

    public List<String> getTableNames() {
        List<String> nombres = new ArrayList<String>();
        nombres.add("Pedido");
        nombres.add("Producto");
        nombres.add("Usuario");
        nombres.add("Categoria");
        nombres.add("Cliente");

        return nombres;

    }
//        while (iterator.hasNext()) {
//            String next = (String) iterator.next();
//            AbstractEntityPersister aep = (AbstractEntityPersister) m.get(next);
//            lista.add(aep.getClassMetadata().getMappedClass());
//        }

    public void remove(Object obj) {

    }

    public void generarDatosLocales() {
        try {
            this.guardarDatosBajados(perWeb.bajarDatos());
        } catch (SQLException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearUsuario(String userName, String pass) {
//        Usuario u = new Usuario();
//        u.setUsername(userName);
//        u.setPassword(pass);
//        usuDAO.insert(u);
    }

    private String getUltimaBajada() {

        return hoDAO.getUltimaBajada().getFecha();

    }

    private void guardarUltimaBajada() {
        hoDAO.guardarHoraBajada(perWeb.getHora());

    }

    private void guardarUltimaSubida() {
        hoDAO.guardarHoraSubidacd(perWeb.getHora());

    }

    private String getUltimaSubida() {

        return hoDAO.getUltimaBajada().getFecha();

    }

    public void sincronizarDatos() {
        try {
            TreeMap<String, List<String[]>> map = new TreeMap<String, List<String[]>>();
            map.put("categoria", MontarSubida.montarCategoria(catDAO, getUltimaSubida()));
            map.put("productos", MontarSubida.montarProducto(proDAO, getUltimaSubida()));
            map.put("usuario", MontarSubida.montarUsuario(usuDAO, getUltimaSubida()));
            map.put("cliente", MontarSubida.montarCliente(cliDAO, getUltimaSubida()));
            map.put("pedido", MontarSubida.montarPedido(peDAO, getUltimaSubida()));
            map.put("pedidoproducto", MontarSubida.montarPedidoProducto(peProDAO, getUltimaSubida()));
            this.guardarUltimaSubida();
            perWeb.subirDatosLocales(map);
        } catch (SQLException ex) {
            Logger.getLogger(PersistencyController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cliCat.borrarLogs();
        }
    }

}
