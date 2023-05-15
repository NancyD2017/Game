package org.example.controller;

import org.example.model.ExchangeWithPorts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class Buttons_ExchangePort extends Buttons implements ActionListener{
    protected JButton b7;
    public static Object messageToPass = null;
    public Buttons_ExchangePort(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.add(label);
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 7));
        buttonPanel.setAlignmentX(label.getAlignmentX());

        Image four = new ImageIcon("src/main/resources/port_4.png").getImage();
        ImageIcon fo = new ImageIcon(four);
        b7 = new JButton(fo);
        b7.setActionCommand("4");
        b7.addActionListener(this);
        buttonPanel.add(b7);
        for (String item: Set.of("s","f","t","w","b","3")) {
            if (ExchangeWithPorts.player.ports.contains(item)) buttonPanel.add(buttonMaker(item));
        }
        panel.add(buttonPanel);
        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = String.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
    private JButton buttonMaker(String name){
        Image image = new ImageIcon("src/main/resources/port_" + name + ".png").getImage();
        ImageIcon s = new ImageIcon(image);
        JButton b = new JButton(s);
        b.setActionCommand(name);
        b.addActionListener(this);
        return b;
    }
}