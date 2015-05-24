/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.io.Serializable;
import java.util.List;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import util.HibernateUtil;

/**
 *
 * @author matthew
 */
public abstract class AbstractDAO {
//
//    private Session sesion;
//
//    protected void iniciaOperacion() {
//        sesion = HibernateUtil.getSessionFactory().openSession();
//        sesion.getTransaction().begin();
//    }
//
//    protected void terminaOperacion() {
//        sesion.getTransaction().commit();
//        sesion.close();
//    }
//
//    protected void manejaExcepcion(HibernateException he) throws HibernateException {
//        sesion.getTransaction().rollback();
//        throw he;
//    }
//
//    protected Session getSession() {
//        return sesion;
//    }
//
//    public static void almacenaEntidad(Object entidad) throws HibernateException {
//        AbstractDAO dummy = new AbstractDAO() {
//        };
//
//        try {
//            dummy.iniciaOperacion();
//            dummy.getSession().saveOrUpdate(entidad);
//            dummy.getSession().flush();
//        } catch (HibernateException he) {
//            dummy.manejaExcepcion(he);
//        } finally {
//            dummy.terminaOperacion();
//        }
//    }
//
//    public static <T> T getEntidad(Serializable id, Class<T> claseEntidad) throws HibernateException {
//        AbstractDAO dummy = new AbstractDAO() {
//        };
//
//        T objetoRecuperado = null;
//
//        try {
//            dummy.iniciaOperacion();
//            objetoRecuperado = (T) dummy.getSession().get(claseEntidad, id);
//        } catch (HibernateException he) {
//            dummy.manejaExcepcion(he);
//        } finally {
//            dummy.terminaOperacion();
//        }
//
//        return objetoRecuperado;
//    }
//
//    public static <T> List<T> getListaEntidades(Class<T> claseEntidad) throws HibernateException {
//        AbstractDAO dummy = new AbstractDAO() {
//        };
//
//        List<T> listaResultado = null;
//
//        try {
//            dummy.iniciaOperacion();
//            listaResultado = dummy.getSession().createQuery("FROM " + claseEntidad.getSimpleName()).list();
//        } catch (HibernateException he) {
//            dummy.manejaExcepcion(he);
//        } finally {
//            dummy.terminaOperacion();
//        }
//
//        return listaResultado;
//    }
}
