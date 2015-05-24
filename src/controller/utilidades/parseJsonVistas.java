/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.utilidades;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.mapping.CategoriaVista;
import model.mapping.ClienteVista;
import model.mapping.PedidoProductoVista;
import model.mapping.PedidoVista;
import model.mapping.ProductoVista;
import model.mapping.UsuarioVista;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mateo
 */
public class parseJsonVistas {

    public static ArrayList parseCategoriaLog(String s) {
        ArrayList list = new ArrayList();
        JSONArray array;
        try {
            array = new JSONArray(s);

            CategoriaVista catv = new CategoriaVista();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                catv.setNombre(object.getString("nombre"));
                catv.setOp(object.getString("op"));
                catv.setDescuento(object.getDouble("descuento"));
                catv.setFecha(DateTime.parse("fecha"));
                list.add(catv);

            }
        } catch (JSONException ex) {
            Logger.getLogger(parseJsonVistas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }

    public static ArrayList parsePedidoproductoLog(String s) {
        ArrayList list = new ArrayList();
        try {

            JSONArray array = new JSONArray(s);
            PedidoProductoVista catv = new PedidoProductoVista();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                catv.setPedidoid(object.getInt("pe"));
                catv.setOp(object.getString("op"));
                catv.setProductoid(object.getInt("producto_id"));
                catv.setFecha(DateTime.parse("fecha"));
                list.add(catv);

            }

        } catch (JSONException ex) {
            Logger.getLogger(parseJsonVistas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }

    public static ArrayList parseUsuarioLog(String s) {
        ArrayList list = new ArrayList();

        try {

            JSONArray array = new JSONArray(s);
            UsuarioVista catv = new UsuarioVista();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                catv.setNombre(object.getString("nombre"));
                catv.setOp(object.getString("op"));
                catv.setPassword(object.getString("password"));
                catv.setApellidos(object.getString("apellidos"));
                catv.setAdministrador(object.getBoolean("administrador"));
                catv.setNif(object.getString("nif"));
                catv.setCalle(object.getString("calle"));
                catv.setFecha(DateTime.parse(object.getString("fecha")));
                list.add(catv);

            }

        } catch (JSONException ex) {
            Logger.getLogger(parseJsonVistas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }

    public static ArrayList parseClienteLog(String s) {
        ArrayList list = new ArrayList();
        try {

            JSONArray array = new JSONArray(s);
            ClienteVista catv = new ClienteVista();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                catv.setNombre(object.getString("nombre"));
                catv.setOp(object.getString("op"));
                catv.setLatitud(object.getDouble("latitud"));
                catv.setApellidos(object.getString("apellidos"));
                if (object.has("img")) {
                    catv.setImg(object.getString("img").getBytes());
                }
                catv.setNif(object.getString("nif"));
                catv.setCalle(object.getString("calle"));
                catv.setFecha(DateTime.parse(object.getString("fecha")));
                list.add(catv);

            }

        } catch (JSONException ex) {
            Logger.getLogger(parseJsonVistas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }

    public static ArrayList parseProductoVista(String s) {
        ArrayList list = new ArrayList();

        try {

            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                ProductoVista prov = new ProductoVista();
                prov.setId(object.getInt("id"));
                prov.setNombre(object.getString("nombre"));
                prov.setCategoriaid(object.getInt("categoriaid"));
                prov.setOp(object.getString("op"));
                prov.setInhabilitats(object.getBoolean("inhabilitats"));
                prov.setPrecio(object.getDouble("precio"));
                prov.setFecha(DateTime.parse(object.getString("fecha")));
                list.add(prov);
            }

        } catch (JSONException ex) {
            Logger.getLogger(parseJsonVistas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }

    public static ArrayList parsePedido(String s) {
        ArrayList list = new ArrayList();
        try {

            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                PedidoVista prov = new PedidoVista();
                prov.setId(object.getInt("id"));
                prov.setEstado(object.getString("estado"));
                prov.setFechaEntrega(object.getString("fecha"));
                prov.setOp(object.getString("op"));
                prov.setIdPedido(object.getInt("idPedido"));
                // prov.setClienteid(object.getDouble("precio");
                prov.setFecha(DateTime.parse(object.getString("fecha")));
                list.add(prov);
            }

        } catch (JSONException ex) {
            Logger.getLogger(parseJsonVistas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }
}
