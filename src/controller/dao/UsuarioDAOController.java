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

    private Dao<Usuario, Long> daoUsu;
    private Dao<UsuarioLog, Long> daoUsulog;
    private OrmLitetables prodao;

    public UsuarioDAOController(OrmLitetables clica) throws SQLException, ClassNotFoundException {

        daoUsu = prodao.getDAO(Usuario.class);
        daoUsulog = prodao.getDAO(UsuarioLog.class);

    }

    public List<UsuarioLog> getLog() throws SQLException {
        return daoUsulog.queryForAll();
    }

    public List<Usuario> getUsuarios() throws SQLException {
        List<Usuario> todos = daoUsu.queryForAll();
        return todos;
    }

    public Usuario filtrarUsuario(String nif) throws SQLException {
        return daoUsu.queryForEq("nif", nif).get(0);
    }

    public Usuario filtrarUsuario(long nif) throws SQLException {
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
