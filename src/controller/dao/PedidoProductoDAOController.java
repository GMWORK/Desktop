/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import Gestor.OrmLitetables;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PedidoProducto;
import model.PedidoProductoLog;

/**
 *
 * @author Mateo
 */
public class PedidoProductoDAOController {

    private Dao<PedidoProducto, Long> daoPePo;
    private Dao<PedidoProductoLog, Long> daoPePolog;

    public PedidoProductoDAOController(OrmLitetables cliCat) {
        try {
            this.daoPePo = cliCat.getDAO(PedidoProducto.class);
            this.daoPePolog = cliCat.getDAO(PedidoProductoLog.class);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoProductoDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<PedidoProducto> mostrarPedidoProducto() throws SQLException {
        return daoPePo.queryForAll();
    }

    public PedidoProducto filtrarPedidoProducto(int id) throws SQLException {
        return daoPePo.queryForEq("id", id).get(0);
    }

    public PedidoProducto filtrarPedidoProductoPedido(int idPedido) throws SQLException {
        return daoPePo.queryForEq("id_pedido", idPedido).get(0);
    }

    public void addPedidoProducto(PedidoProducto cat) throws SQLException {
        daoPePo.createOrUpdate(cat);
    }

    public void removePedidoProducto(int id) throws SQLException {

        daoPePo.delete(this.filtrarPedidoProducto(id));
    }

    public void removePedidoProductodePedido(PedidoProducto proPe) throws SQLException {

        daoPePo.delete(proPe);
    }

    public void EditarPedidoProducto(PedidoProducto cat) throws SQLException {
        daoPePo.updateId(cat, cat.getId());
    }
}
