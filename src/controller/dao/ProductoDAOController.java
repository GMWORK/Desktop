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
import model.Pedido;
import model.Producto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author mateo
 */
public class ProductoDAOController extends AbstractDAO {

   
    public void addProducto(Producto cat) {
    }

    public ArrayList<Producto> getProductos() {
        return null;
    }

    public Producto filtrarProducto(Pedido cat) {
        return null;
    }

    public void EditarProducto(Producto cat) {
    }
}
