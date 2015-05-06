/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Background extends javax.swing.JPanel {

    private String path;

    public Background(Dimension di, String path) {
        this.setSize(di);
        this.path = path;
    }

    @Override
    public void paintComponent(Graphics g) {
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource(path));

        g.drawImage(imagenFondo.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        setOpaque(false);
        super.paintComponent(g);
    }
}
