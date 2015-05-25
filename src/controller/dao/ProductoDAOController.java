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
import model.Producto;

import model.ProductoLog;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import util.HibernateUtil;

/**
 *
 * @author mateo
 */
public class ProductoDAOController extends AbstractDAO {

    private Dao<Producto, Long> daoPro;
    private Dao<ProductoLog, Long> daoProlog;
    private OrmLitetables prodao;

    public ProductoDAOController(OrmLitetables cli) throws SQLException, ClassNotFoundException {
        daoPro = prodao.getDAO(Producto.class);
        daoProlog = prodao.getDAO(ProductoLog.class);

    }

    public List<ProductoLog> getLog() throws SQLException {
        return daoProlog.queryForAll();
    }

    public List<Producto> getPedidos() throws SQLException {
        List<Producto> todos = daoPro.queryForAll();
        return todos;
    }

    public Producto filtrarProducto(String nombre) throws SQLException {
        return daoPro.queryForEq("nombre", nombre).get(0);
    }

    public Producto filtrarProducto(int id) throws SQLException {
        return daoPro.queryForEq("id", id).get(0);
    }

    public Producto filtrarProducto(long id) throws SQLException {
        return daoPro.queryForEq("id", id).get(0);
    }

    public void addProducto(Producto cat) throws SQLException {
        daoPro.createOrUpdate(cat);
    }

    public void removeProducto(String nombre) throws SQLException {

        daoPro.delete(this.filtrarProducto(nombre));
    }

    public void EditarProducto(Producto cat) throws SQLException {
        daoPro.updateId(cat, cat.getId());
    }

}
