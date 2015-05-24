/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author Mateo
 */
public class ThreadActualizarBajada implements Runnable {

    private String[] Content;

    private String[] string;

    public ThreadActualizarBajada(String[] string) {

        this.string = string;

    }

    public TreeMap<String, ArrayList> doOperation() {

        TreeMap<String, ArrayList> map = new TreeMap<String, ArrayList>();
        try {
            map.put("Categoria", parseJsonVistas.parseCategoriaLog(Content[0]));
            map.put("Productos", parseJsonVistas.parseProductoVista(Content[1]));
            map.put("Usuario", parseJsonVistas.parseUsuarioLog(Content[2]));
            map.put("Cliente", parseJsonVistas.parseClienteLog(Content[3]));
            map.put("Pedido", parseJsonVistas.parsePedido(Content[4]));
            map.put("PedidoProducto", parseJsonVistas.parsePedidoproductoLog(Content[5]));

            map.put("HoraBajada", parseJson.montarHoraBajada(Content[6]));
        } catch (JSONException ex) {
            Logger.getLogger(ThreadActualizarBajada.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return map;
        }

    }

    @Override
    public void run() {
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        try {

            Content = new String[string.length];
            for (int i = 0; i < string.length; i++) {
                URL url = new URL("http://192.168.1.101:8080/WebGMWORK/webresources/" + string[i]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setRequestProperty("Accept", "application/json");
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "");
                }
                Content[i] = sb.toString();

            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                reader.close();
                conn.disconnect();

            } catch (Exception ex) {

            }
        }
    }
}
