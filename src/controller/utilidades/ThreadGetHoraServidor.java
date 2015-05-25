/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.utilidades;

import static controller.utilidades.ThreadSOS.Content;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Horas;
import org.json.JSONException;

/**
 *
 * @author Mateo
 */
public class ThreadGetHoraServidor implements Runnable {
    
    private String hora;
    
    public ThreadGetHoraServidor() {
    }
    
    public ArrayList<Horas> getHora() {
        ArrayList array = new ArrayList();
        try {
            array.add(parseJson.montarHoraBajada(hora).get(0));
            
        } catch (JSONException ex) {
            Logger.getLogger(ThreadGetHoraServidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return array;
        }
    }
    
    @Override
    public void run() {
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        URL url = null;
        Object serverResponseString = null;
        
        try {
            url = new URL("http://192.168.1.101:8080/WebGMWORK/webresources/date");
            
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            
            while ((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            hora = sb.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ThreadGetHoraServidor.class.getName()).log(Level.SEVERE, null, ex);
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
