/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.utilidades;

import com.google.gson.Gson;
import controller.dao.CategoriaDAOController;
import controller.dao.ClienteDAOController;
import controller.dao.PedidoDAOController;
import controller.dao.PedidoProductoDAOController;
import controller.dao.UsuarioDAOController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import model.Categoria;
import model.CategoriaLog;
import model.Cliente;
import model.ClienteLog;
import model.Pedido;
import model.PedidoLog;
import model.PedidoProducto;
import model.PedidoProductoLog;
import model.Usuario;
import model.UsuarioLog;
import org.joda.time.DateTime;

/**
 *
 * @author Mateo
 */
public class MontarSubida {

    public static List<String[]> montarCliente(ClienteDAOController dao, String ultimabajada) throws SQLException {
        List<ClienteLog> cliLog = dao.getLog();
        ClienteLog logCli = null;
        Gson gson = new Gson();
        Cliente cli = null;
        List<String[]> cliente = new ArrayList<String[]>();
        TreeMap<String, List<String[]>> map = new TreeMap<String, List<String[]>>();
        DateTime ultimaBajada = DateTime.parse(ultimabajada);
        for (int i = 0; i < cliLog.size(); i++) {
            logCli = cliLog.get(i);
            if (DateTime.parse(logCli.getFecha()).compareTo(ultimaBajada) > 0) {
                switch (logCli.getOp()) {
                    case "I":
                        cli = dao.filtrarID(logCli.getIdCliente());
                        cliente.add(new String[]{"POST", "cliente", gson.toJson(cli)});
                        break;
                    case "U":
                        cli = dao.filtrarID(logCli.getIdCliente());
                        cliente.add(new String[]{"PUT", "cliente/" + logCli.getIdCliente() + "", gson.toJson(cli)});

                        break;
                    case "D":
                        cliente.add(new String[]{"DELETE", "cliente", String.valueOf(cliLog.get(i).getIdCliente())});
                        break;
                }
            }
        }
        return cliente;
    }

    public static List<String[]> montarUsuario(UsuarioDAOController dao, String ultimabajada) throws SQLException {
        List<UsuarioLog> cliLog = dao.getLog();
        UsuarioLog logCli = null;
        Gson gson = new Gson();
        Usuario cli = null;
        List<String[]> cliente = new ArrayList<String[]>();
        TreeMap<String, List<String[]>> map = new TreeMap<String, List<String[]>>();
        DateTime ultimaBajada = DateTime.parse(ultimabajada);
        for (int i = 0; i < cliLog.size(); i++) {
            logCli = cliLog.get(i);
            if (DateTime.parse(logCli.getFecha()).compareTo(ultimaBajada) > 0) {
                switch (logCli.getOperacion()) {
                    case "I":
                        cli = dao.filtrarUsuario(logCli.getIdUsuario());
                        cliente.add(new String[]{"POST", "cliente", gson.toJson(cli)});
                        break;
                    case "U":
                        cli = dao.filtrarUsuario(logCli.getIdUsuario());
                        cliente.add(new String[]{"PUT", "cliente/" + logCli.getIdUsuario() + "", gson.toJson(cli)});

                        break;
                    case "D":
                        cliente.add(new String[]{"DELETE", "cliente", String.valueOf(cliLog.get(i).getIdUsuario())});
                        break;
                }
            }
        }
        return cliente;
    }

    public static List<String[]> montarCategoria(CategoriaDAOController dao, String ultimabajada) throws SQLException {
        List<CategoriaLog> cliLog = dao.getLog();
        CategoriaLog logCli = null;
        Gson gson = new Gson();
        Categoria cli = null;
        List<String[]> cliente = new ArrayList<String[]>();
        TreeMap<String, List<String[]>> map = new TreeMap<String, List<String[]>>();
        DateTime ultimaBajada = DateTime.parse(ultimabajada);
        for (int i = 0; i < cliLog.size(); i++) {
            logCli = cliLog.get(i);
            if (DateTime.parse(logCli.getFecha()).compareTo(ultimaBajada) > 0) {
                switch (logCli.getOperacion()) {
                    case "I":
                        cli = dao.filtrarCategoria(logCli.getidCategoria());
                        cliente.add(new String[]{"POST", "categoria", gson.toJson(cli)});
                        break;
                    case "U":
                        cli = dao.filtrarCategoria(logCli.getidCategoria());
                        cliente.add(new String[]{"PUT", "categoria/" + logCli.getidCategoria() + "", gson.toJson(cli)});

                        break;
                    case "D":
                        cliente.add(new String[]{"DELETE", "categoria", String.valueOf(cliLog.get(i).getidCategoria())});
                        break;
                }
            }
        }
        return cliente;
    }

    public static List<String[]> montarPedidoProducto(PedidoProductoDAOController dao, String ultimabajada) throws SQLException {
        List<PedidoProductoLog> cliLog = dao.getLog();
        PedidoProductoLog logCli = null;
        Gson gson = new Gson();
        PedidoProducto cli = null;
        List<String[]> cliente = new ArrayList<String[]>();
        TreeMap<String, List<String[]>> map = new TreeMap<String, List<String[]>>();
        DateTime ultimaBajada = DateTime.parse(ultimabajada);
        for (int i = 0; i < cliLog.size(); i++) {
            logCli = cliLog.get(i);
            if (DateTime.parse(logCli.getFecha()).compareTo(ultimaBajada) > 0) {
                switch (logCli.getOperacion()) {
                    /*case "I":
                        cli = dao.filtrarPedidoProducto(logCli.getIdPedidoProducto());
                        cliente.add(new String[]{"POST", "productohaspedido", gson.toJson(cli)});
                        break;
                    case "U":
                        cli = dao.filtrarCategoria(logCli.getIdPedidoProducto());
                        cliente.add(new String[]{"PUT", "productohaspedido/" + logCli.getIdPedidoProducto() + "", gson.toJson(cli)});

                        break;
                    case "D":
                        cliente.add(new String[]{"DELETE", "productohaspedido", String.valueOf(cliLog.get(i).getIdPedidoProducto())});
                        break;*/
                }
            }
        }
        return cliente;
    }

    public static List<String[]> montarPedido(PedidoDAOController dao, String ultimabajada) throws SQLException {
        List<PedidoLog> cliLog = dao.getPedidosLog();
        PedidoLog logCli = null;
        Gson gson = new Gson();
        Pedido cli = null;
        List<String[]> cliente = new ArrayList<String[]>();
        TreeMap<String, List<String[]>> map = new TreeMap<String, List<String[]>>();
        DateTime ultimaBajada = DateTime.parse(ultimabajada);
        for (int i = 0; i < cliLog.size(); i++) {
            logCli = cliLog.get(i);
            if (DateTime.parse(logCli.getFecha()).compareTo(ultimaBajada) > 0) {
                switch (logCli.getOperacion()) {
                    case "I":
                        cli = dao.filtrarPedido(logCli.getIdPedido());
                        cliente.add(new String[]{"POST", "pedido", gson.toJson(cli)});
                        break;
                    case "U":
                        cli = dao.filtrarPedido(logCli.getIdPedido());
                        cliente.add(new String[]{"PUT", "pedido/", gson.toJson(cli)});

                        break;
                    case "D":
                        cliente.add(new String[]{"DELETE", "pedido", String.valueOf(cliLog.get(i).getIdPedido())});
                        break;
                }
            }
        }
        return cliente;
    }
}
