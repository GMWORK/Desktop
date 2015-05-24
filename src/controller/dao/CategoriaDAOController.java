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
import model.Categoria;
import model.CategoriaLog;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import util.HibernateUtil;

/**
 *
 * @author mateo
 */
public class CategoriaDAOController {

    private Dao<Categoria, Long> daoCat;
    private Dao<CategoriaLog, Long> daoCatlog;

    public CategoriaDAOController(OrmLitetables orm) throws SQLException, ClassNotFoundException {

        this.daoCat = orm.getDAO(Categoria.class);
        daoCatlog = orm.getDAO(CategoriaLog.class);

    }
     public List<CategoriaLog> getLog() throws SQLException {
        return daoCatlog.queryForAll();
    }

    public List<Categoria> mostrarCategorias() throws SQLException {
        return daoCat.queryForAll();
    }

    public Categoria filtrarCategoria(String nombre) throws SQLException {
        return daoCat.queryForEq("nombre", nombre).get(0);
    }

    public Categoria filtrarCategoria(long id) throws SQLException {
        return daoCat.queryForEq("id", id).get(0);
    }

    public void addCategoria(Categoria cat) throws SQLException {
        daoCat.createOrUpdate(cat);
    }

    public void removeCategoria(String nombre) throws SQLException {

        daoCat.delete(this.filtrarCategoria(nombre));
    }

    public void EditarCategoria(Categoria cat) throws SQLException {
        daoCat.update(cat);
    }

}
