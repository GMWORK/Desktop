/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author matthew
 */
public class Message extends JFrame {

    public Message(String title,String message , String buto) {
        initComponents(message, buto);
        setFormat();
        setEvents();
    }

    private void initComponents(String label , String button) {

        jpanel = new JPanel();

        text = new JLabel("Inserció completada");

        boto = new JButton("aceptar");

    }

    private void setEvents() {
        boto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchEvent(new WindowEvent(Message.this, WindowEvent.WINDOW_CLOSING));
            }
        });

    }
    private JLabel text;
    private JPanel jpanel;
    private JButton boto;

    private void setFormat() {
        this.setSize(new Dimension(200, 100));
        this.setVisible(true);
        this.add(jpanel);
        jpanel.setVisible(true);
        jpanel.setBounds(50, 50, 50, 50);
        jpanel.add(text);
        jpanel.add(boto);
    }

}
