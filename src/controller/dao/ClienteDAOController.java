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
import model.Cliente;

import model.ClienteLog;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import util.HibernateUtil;

/**
 *
 * @author mateo
 */
public class ClienteDAOController {
//
//    private Session sesion;
//    private Transaction tx;
//
//    public ClienteDAOController() {
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
//    public void addCliente(Cliente cat) {
//        startOperation();
//        Session sesion = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = sesion.beginTransaction();
//        Serializable emplId = sesion.save(cat);//Estat Persistent
//        finishOperation();
//    }
//
//    public List<Cliente> getClientes() {
//        List<Cliente> listaContactos = null;
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
//    public Object filtrarCliente(Cliente cat) {
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
//    public void EditarCliente(Cliente cat) {
//    }

    private Dao<Cliente, Long> daoCli;
    private Dao<ClienteLog, Long> daoClilog;

    public ClienteDAOController(OrmLitetables clidao) throws SQLException, ClassNotFoundException {

        this.daoCli = clidao.getDAO(Cliente.class);
        daoClilog = clidao.getDAO(ClienteLog.class);
    }

    public List<ClienteLog> getLog() throws SQLException {
        return daoClilog.queryForAll();
    }

    public void addCliente(Cliente cat) {
        try {
            daoCli.createOrUpdate(cat);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Cliente> getClientes() throws SQLException {
        ArrayList<Cliente> todos = (ArrayList<Cliente>) daoCli.queryForAll();
        return todos;
    }

    public void removeCliente(String nif) throws SQLException {

        daoCli.delete(daoCli.queryForEq("nif", nif));
    }

    public Cliente filtrarCliente(String nif) throws SQLException {
        Cliente client = daoCli.queryForEq("nif", nif).get(0);
        return client;
    }

    public Cliente filtrarCliente(int id) throws SQLException {
        Cliente client = daoCli.queryForEq("id", id).get(0);
        return client;
    }

    public void EditarCliente(Cliente client) throws SQLException {
        daoCli.update(client);
    }

    public Cliente filtrarID(long id) throws SQLException {
        return daoCli.queryForEq("id", id).get(0);
    }
    /*  public List<Map<Cliente,ClienteLog>> getVista() throws SQLException {
     List <Map<Cliente,ClienteLog>>clientsvista = new ArrayList<Map<Cliente,ClienteLog>>();

     List<Cliente> clientes = getClientes();
     List<ClienteLog> logclientes = daoClilog.queryForAll();
     for (int i = 0 ; i< clientes.size();i++){
     for (int j = 0 ; j<logclientes.size();i++){
     if(clientes.get(i).getId() == logclientes.get(j).getIdCliente()){
     getVista().add(new TreeMap<Cliente,ClienteLog>().put(clientes.get(i),logclientes.get(j)));
     }
     }
     }
     }*/
}
