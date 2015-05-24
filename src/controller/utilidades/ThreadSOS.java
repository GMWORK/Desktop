/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.json.JSONException;

/**
 *
 * @author Mateo
 */
public class ThreadSOS implements Runnable {

    String[] urls;
    static String[] Content;

    public ThreadSOS(String[] urls) {
        this.urls = urls;
        Content = new String[urls.length];
    }

    public static TreeMap<String, ArrayList> getSOS() throws JSONException, UnsupportedEncodingException {
        //categoria ,producto, usuario ,cliente,  pedido , productoPedido
        TreeMap<String, ArrayList> map = new TreeMap<String, ArrayList>();
        map.put("Categoria", parseJson.montarCategoria(Content[0]));
        map.put("Productos", parseJson.montarProductos(Content[1]));
        map.put("Usuario", parseJson.montarUsuarios(Content[2]));
        map.put("Cliente", parseJson.montarClientes(Content[3]));
        map.put("Pedido", parseJson.montarPedido(Content[4]));
        map.put("PedidoProducto", parseJson.montarPedidoProducto(Content[5]));
        map.put("HoraBajada", parseJson.montarHoraBajada(Content[6]));
        /* map.put("Categoria", parseJsonSubida.JsontoObject(string[0],Categoria.class));
         map.put("Productos", parseJsonSubida.JsontoObject(string[1],Producto.class));
         map.put("Usuario",parseJsonSubida.JsontoObject(string[2],Usuario.class));
         map.put("Cliente",parseJsonSubida.JsontoObject(string[3],Cliente.class));
         map.put("Pedido",parseJsonSubida.JsontoObject(string[4],Pedido.class));
         map.put("PedidoProducto",parseJsonSubida.JsontoObject(string[5],PedidoProducto.class));
         map.put("HoraBajada", parseJsonSubida.JsontoObject(string[6],Horas.class));*/

        //per.SOSUsuario(montarUsuarios(string[1]));
        //per.SOSCliente(montarClientes(string[2]));
        // per.SOSPedido(montarPedido(string[3]));
        // per.SOSProducto(montarProductos(string[4]));
        //this.bd.SOS(parseJson.SOS(string , bd));
        return map;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        URL url = null;
        Object serverResponseString = null;
        try {
            for (int i = 0; i < urls.length; i++) {
                url = new URL("http://192.168.1.101:8080/WebGMWORK/webresources/" + urls[i]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/json");

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "");
                }
                Content[i] = sb.toString();
            }
        } catch (Exception e) {

        } finally {
            try {
                reader.close();
                conn.disconnect();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
