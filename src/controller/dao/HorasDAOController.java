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
import model.Horas;

/**
 *
 * @author Mateo
 */
public class HorasDAOController {

    private Dao<Horas, Long> daoHora;

    public HorasDAOController(OrmLitetables clidao) throws SQLException, ClassNotFoundException {
        this.daoHora = clidao.getDAO(Horas.class);
    }

    public void addPedido(Horas cat) {
        try {
            daoHora.createOrUpdate(cat);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Horas> getPedidos() throws SQLException {
        List<Horas> todos = daoHora.queryForAll();
        return todos;
    }

    public Horas getUltimaBajada() {
        Horas client = null;
        try {
            client = daoHora.queryForEq("id", "1").get(0);
            return client;
        } catch (SQLException ex) {
            Logger.getLogger(HorasDAOController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return client;
        }
    }

    public Horas getUltimaSubida() throws SQLException {
        Horas hora = null;
        try {
            hora = daoHora.queryForEq("id", "2").get(0);
            if (hora != null) {
                return hora;
            } else {
                hora.setFecha("1999-07-15T00:00:00+02:00");
                hora.setId(2);
                return hora;

            }
        } catch (IndexOutOfBoundsException ex) {
            hora = new Horas();
            hora.setFecha("1999-07-15T00:00:00+02:00");
            hora.setId(2);
            return hora;
        }

    }

    public void removeHora(int id) throws SQLException {
        daoHora.delete(daoHora.queryForEq("id", id));
    }

    public void EditarPedido(Horas cat) throws SQLException {
        daoHora.update(cat);
    }
}
