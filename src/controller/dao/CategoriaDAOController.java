/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author mateo
 */
public class CategoriaDAOController {

    private Session sesion;
    private Transaction tx;

    private void startOperation() {
        this.sesion = HibernateUtil.getSessionFactory().openSession();
        this.tx = this.sesion.beginTransaction();
    }

    private void finishOperation() {
        this.tx.commit();
        this.sesion.close();
    }

    private void resolveException(Exception e) {
        this.tx.rollback();
        e.printStackTrace();
    }

    public long insert(Object object) {
        long primaryKey = 0;
        try {
            startOperation();
            primaryKey = (long) this.sesion.save(object);
            finishOperation();
        } catch (Exception e) {
            resolveException(e);
        }
        return primaryKey;
    }

    public void delete(Object object) {
        try {
            startOperation();
            this.sesion.delete(object);
            finishOperation();
        } catch (Exception e) {
            resolveException(e);
        }
    }

    public void update(Object object) {
        try {
            startOperation();
            this.sesion.update(object);
            finishOperation();
        } catch (Exception e) {
            resolveException(e);
        }
    }

    public void addCategoria(Categoria cat) {
        startOperation();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        Serializable emplId = sesion.save(cat);//Estat Persistent
        finishOperation();
    }

    public List<Categoria> getCategorias() {
        List<Categoria> listaContactos = null;

        try {
            startOperation();
            listaContactos = sesion.createQuery("from Cliente").list();
        } finally {
            finishOperation();
        }

        return listaContactos;
    }

    public Object filtrarCategoria(Categoria cat) {
        Object cliente = null;
        /*
         Prueba local introducir para probar que funcione
         */

        try {
            startOperation();
//            usuario = sesion.createQuery("from Usuario u where u.username like ? and u.password like ?")
//                    .setParameter(0, cat.getUsername())
//                    .setParameter(1, cat.getPassword())
//                    .list();
//usuario = sesion.createQuery("from Usuario u where u.username like '"+cat.getUsername()+ "' and u.password like '"+ cat.getPassword()+"' ").list();
        } finally {
            finishOperation();
        }

        return cliente;
    }

    public void EditarCategoria(Categoria cat) {
    }
}
