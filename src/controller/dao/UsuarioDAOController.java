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
import model.Usuario;
import model.UsuarioLog;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import util.HibernateUtil;

/**
 *
 * @author mateo
 */
public class UsuarioDAOController extends AbstractDAO {

//    private Session sesion;
//    private Transaction tx;
//
//    public UsuarioDAOController() {
//
//    }
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
//    public void addUsuario(Usuario cat) {
//        startOperation();
//        Session sesion = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = sesion.beginTransaction();
//        Serializable emplId = sesion.save(cat);//Estat Persistent
//        tx.commit();
//        sesion.flush();
//        finishOperation();
//    }
//
//    public List<Usuario> getUsuarios() {
//        List<Usuario> listaContactos = null;
//
//        try {
//            startOperation();
//            listaContactos = sesion.createQuery("from Usuario").list();
//        } finally {
//            finishOperation();
//        }
//
//        return listaContactos;
//    }
//
//    public List<Usuario> filtrarUsuario(String campo, String filtro) {
//        Object usuario = null;
//        /*
//         Prueba local introducir para probar que funcione
//         */
//
//        try {
//            startOperation();
//            usuario = sesion.createQuery("from Usuario u where u."+filtro+"   like '"+campo+"' ")
//                    .list();
////usuario = sesion.createQuery("from Usuario u where u.username like '"+cat.getUsername()+ "' and u.password like '"+ cat.getPassword()+"' ").list();
//        } finally {
//            finishOperation();
//        }
//
//        return (List<Usuario>) usuario;
//    }
//
//    public void EditarUsuario(Usuario cat) {
//        
//    }
    private Dao<Usuario, Long> daoUsu;
    private Dao<UsuarioLog, Long> daoUsulog;
    private OrmLitetables prodao;

    public UsuarioDAOController(OrmLitetables clica) throws SQLException, ClassNotFoundException {

        daoUsu = prodao.getDAO(Usuario.class);
        
    }

    public List<Usuario> getUsuarios() throws SQLException {
        List<Usuario> todos = daoUsu.queryForAll();
        return todos;
    }

    public Usuario filtrarUsuario(String nif) throws SQLException {
        return daoUsu.queryForEq("nif", nif).get(0);
    }
    public Usuario filtrarUsuario(int nif) throws SQLException {
        return daoUsu.queryForEq("nif", nif).get(0);
    }

    public void addUsuario(Usuario cat) throws SQLException {
        daoUsu.createOrUpdate(cat);
    }

    public void removeUsuario(String nif) throws SQLException {

        daoUsu.delete(this.filtrarUsuario(nif));
    }

    public void EditarProducto(Usuario cat) throws SQLException {
        daoUsu.updateId(cat, cat.getId());
    }

    public boolean hacerLogin(Usuario cat) throws SQLException {
        try {
            Usuario usu = daoUsu.queryForEq("username", cat.getUsername()).get(0);

            if (usu.getPassword().equals(cat.getPassword())) {
                return true;

            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            
            return false;
        }
    }

    public Usuario filtrarUsuario(String filtro, String campo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
