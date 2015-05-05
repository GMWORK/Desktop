/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author mateo
 */
public class PopupError extends JFrame {

    public PopupError(String tipoError) throws HeadlessException {
        super("Error");
        this.label = new JLabel(tipoError);
        this.button = new JButton("Aceptar");
        initComponents();

    }

    public void initComponents() {
        setFormat();
        setEvents();
        this.add(label);
        this.add(button);

        this.setVisible(true);

    }

    public void setEvents() {
        this.button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public void setFormat() {

        this.setSize(new Dimension(200, 200));
        this.button.setSize(new Dimension(10, 10));

    }
    private JLabel label;
    private JButton button;
}
