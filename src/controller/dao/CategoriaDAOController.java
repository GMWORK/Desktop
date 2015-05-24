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
//
//    private Session sesion;
//    private Transaction tx;
//
//    private void startOperation() {
//        this.sesion = HibernateUtil.getSessionFactory().openSession();
//        this.tx = this.sesion.beginTransaction();
//    }
//
//    private void finishOperation() {
//        this.tx.commit();
//        this.sesion.close();
//    }
//
//    private void resolveException(Exception e) {
//        this.tx.rollback();
//        e.printStackTrace();
//    }
//
//    public long insert(Object object) {
//        long primaryKey = 0;
//        try {
//            startOperation();
//            primaryKey = (long) this.sesion.save(object);
//            finishOperation();
//        } catch (Exception e) {
//            resolveException(e);
//        }
//        return primaryKey;
//    }
//
//    public void delete(Object object) {
//        try {
//            startOperation();
//            this.sesion.delete(object);
//            finishOperation();
//        } catch (Exception e) {
//            resolveException(e);
//        }
//    }
//
//    public void update(Object object) {
//        try {
//            startOperation();
//            this.sesion.update(object);
//            finishOperation();
//        } catch (Exception e) {
//            resolveException(e);
//        }
//    }
//
//    public void addCategoria(Categoria cat) {
//        startOperation();
//        Session sesion = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = sesion.beginTransaction();
//        Serializable emplId = sesion.save(cat);//Estat Persistent
//        finishOperation();
//    }
//
//    public List<Categoria> getCategorias() {
//        List<Categoria> listaContactos = null;
//
//        try {
//            startOperation();
//            listaContactos = sesion.createQuery("from Cliente").list();
//        } finally {
//            finishOperation();
//        }
//
//        return listaContactos;
//    }
//
//    public Object filtrarCategoria(Categoria cat) {
//        Object cliente = null;
//        /*
//         Prueba local introducir para probar que funcione
//         */
//
//        try {
//            startOperation();
////            usuario = sesion.createQuery("from Usuario u where u.username like ? and u.password like ?")
////                    .setParameter(0, cat.getUsername())
////                    .setParameter(1, cat.getPassword())
////                    .list();
////usuario = sesion.createQuery("from Usuario u where u.username like '"+cat.getUsername()+ "' and u.password like '"+ cat.getPassword()+"' ").list();
//        } finally {
//            finishOperation();
//        }
//
//        return cliente;
//    }
//
//    public void EditarCategoria(Categoria cat) {
//    }

    private Dao<Categoria, Long> daoCat;
    private Dao<CategoriaLog, Long> daoCatlog;

    public CategoriaDAOController(OrmLitetables orm) throws SQLException, ClassNotFoundException {

        this.daoCat = orm.getDAO(Categoria.class);

    }

    public List<Categoria> mostrarCategorias() throws SQLException {
        return daoCat.queryForAll();
    }

    public Categoria filtrarCategoria(String nombre) throws SQLException {
        return daoCat.queryForEq("nombre", nombre).get(0);
    }

    public Categoria filtrarCategoria(int id) throws SQLException {
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
