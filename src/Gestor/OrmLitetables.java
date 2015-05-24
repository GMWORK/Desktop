/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestor;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categoria;
import model.CategoriaLog;
import model.Cliente;
import model.ClienteLog;
import model.Horas;
import model.Pedido;
import model.PedidoLog;
import model.PedidoProducto;
import model.PedidoProductoLog;
import model.Producto;
import model.ProductoLog;
import model.Usuario;
import model.UsuarioLog;
import org.reflections.Reflections;

/**
 *
 * @author Mateo
 */
public class OrmLitetables {

    private static ConnectionSource connectionSource;

    public OrmLitetables() throws SQLException, ClassNotFoundException {
        this.connectionSource = new JdbcConnectionSource("jdbc:sqlite:db.prueba");
        //crearTablas();
        TableUtils.createTableIfNotExists(connectionSource, Categoria.class);
        TableUtils.createTableIfNotExists(connectionSource, Cliente.class);
        TableUtils.createTableIfNotExists(connectionSource, Pedido.class);
        TableUtils.createTableIfNotExists(connectionSource, PedidoProducto.class);
        TableUtils.createTableIfNotExists(connectionSource, Producto.class);
        TableUtils.createTableIfNotExists(connectionSource, Usuario.class);
        TableUtils.createTableIfNotExists(connectionSource, PedidoLog.class);
        TableUtils.createTableIfNotExists(connectionSource, ClienteLog.class);
        TableUtils.createTableIfNotExists(connectionSource, ProductoLog.class);
        TableUtils.createTableIfNotExists(connectionSource, CategoriaLog.class);
        TableUtils.createTableIfNotExists(connectionSource, UsuarioLog.class);
        TableUtils.createTableIfNotExists(connectionSource, PedidoProductoLog.class);
        TableUtils.createTableIfNotExists(connectionSource, Horas.class);

        createTriggers("categoria", "CategoriaLog");
        createTriggers("cliente", "ClienteLog");
        createTriggers("pedido", "PedidoLog");
        createTriggers("producto", "ProductoLog");
        createTriggers("usuario", "UsuarioLog");
        createTriggers("pedidoProducto", "PedidoProductoLog");
    }

    public void borrarLogs() {
        try {
            createTriggers("categoria", "CategoriaLog");
            createTriggers("cliente", "ClienteLog");
            createTriggers("pedido", "PedidoLog");
            createTriggers("producto", "ProductoLog");
            createTriggers("usuario", "UsuarioLog");
            createTriggers("pedidoProducto", "PedidoProductoLog");
        } catch (SQLException ex) {
            Logger.getLogger(OrmLitetables.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrmLitetables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T> Dao<T, Long> getDAO(Class<T> classRepresentingTable) throws SQLException {

        Dao<T, Long> dao = null;
        dao = DaoManager.createDao(connectionSource, classRepresentingTable);
        return dao;

    }

    public void createTriggers(String table, String claseLog) throws SQLException, ClassNotFoundException {

        //Class< ? extends Object > clas = Class.forName("mode.definitivo."+table.replace(0, toUpperCase())+"");
        Dao dao = null;

        dao = getDAO(Class.forName("model." + Character.toUpperCase(table.charAt(0)) + table.substring(1) + ""));

        //Dao dao = getDAO(Class.forName("model.definitivo." + Character.toUpperCase(table.charAt(0)) + table.substring(1) + ""));
        dao.executeRaw("DROP TRIGGER IF EXISTS " + table + "_UP");
        dao.executeRaw("DROP TRIGGER IF EXISTS " + table + "_IN");
        dao.executeRaw("DROP TRIGGER IF EXISTS " + table + "_D");
        dao.executeRaw("CREATE TRIGGER " + table + "_UP AFTER UPDATE ON " + table + "\n"
                + "FOR EACH ROW \n"
                + "BEGIN\n"
                + "INSERT INTO " + claseLog + " (Op, fecha,Id" + Character.toUpperCase(table.charAt(0)) + table.substring(1) + ") VALUES('U',  CURRENT_TIMESTAMP ,NEW.id);\n"
                + "END;\n");
        dao.executeRaw("CREATE TRIGGER " + table + "_IN AFTER INSERT ON " + table + "\n"
                + "FOR EACH ROW\n"
                + "BEGIN\n"
                + "INSERT INTO " + claseLog + " (Op, fecha,Id" + Character.toUpperCase(table.charAt(0)) + table.substring(1) + ") VALUES('I',  CURRENT_TIMESTAMP ,NEW.id);\n"
                + "END;\n");
        dao.executeRaw("CREATE TRIGGER " + table + "_D AFTER DELETE ON " + table + "\n"
                + "FOR EACH ROW\n"
                + "BEGIN\n"
                + "INSERT INTO " + claseLog + " (Op, fecha,Id" + Character.toUpperCase(table.charAt(0)) + table.substring(1) + ") VALUES('D',  CURRENT_TIMESTAMP ,NEW.id);\n"
                + "END;\n");

    }

    private void crearTablas(String table) throws SQLException, ClassNotFoundException {
        if (table.equals("pedido_has_producto")) {
            TableUtils.createTableIfNotExists(connectionSource, Class.forName("model.PedidoProducto"));
        } else {
            TableUtils.createTableIfNotExists(connectionSource, Class.forName("model." + Character.toUpperCase(table.charAt(0)) + table.substring(1) + ""));

        }
    }

    /* private List<String> getTablesName() {

     }*/

    /*    private Set getClasses() {
     //        Reflections reflections = new Reflections("model.definitivo");

     /* Set<Class<? extends Object>> allClasses
     = reflections.getSubTypesOf(Object.class);
     return allClasses;*/
//      s
}
