/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import Gestor.OrmLitetables;
import com.j256.ormlite.dao.Dao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;

import model.PedidoLog;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import util.HibernateUtil;

/**
 * dsfsd
 *
 * @author mateo
 */
public class PedidoDAOController extends AbstractDAO {

//    public void addPedido(Categoria cat) {
//    }
//
//    public ArrayList<Categoria> getPedidos() {
//        return null;
//    }
//
//    public Pedido filtrarPedido(Pedido cat) {
//        return null;
//    }
//
//    public void EditarPedido(Pedido cat) {
//    }
    private Dao<Pedido, Long> daoPe;
    private Dao<PedidoLog, Long> daoPelog;

    public PedidoDAOController(OrmLitetables clidao) throws SQLException, ClassNotFoundException {

        this.daoPe = clidao.getDAO(Pedido.class);
        daoPelog = clidao.getDAO(PedidoLog.class);
    }

    public void addPedido(Pedido cat) {
        try {
            daoPe.create(cat);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Pedido> getPedidos() throws SQLException {
        List<Pedido> todos = daoPe.queryForAll();
        return todos;
    }

    public List<PedidoLog> getPedidosLog() throws SQLException {
        List<PedidoLog> todos = daoPelog.queryForAll();
        return todos;
    }

    public Pedido filtrarPedido(long id) throws SQLException {

        Pedido client = daoPe.queryForId(id);
        return client;
    }

    public void removePedido(long id) throws SQLException {
        daoPe.delete(daoPe.queryForEq("id", id));
    }

    public void EditarPedido(Pedido cat) throws SQLException {
        daoPe.updateId(cat, cat.getId());
    }

}
