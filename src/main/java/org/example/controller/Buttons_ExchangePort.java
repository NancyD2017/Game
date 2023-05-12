package org.example.controller;

import org.example.model.ExchangeWithPorts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_ExchangePort extends Buttons implements ActionListener{
    protected JButton b1, b2, b3, b4, b5, b6, b7;
    public static Object messageToPass = null;
    public static boolean isPerformed = true;
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

        Image straw = new ImageIcon("src/main/resources/port_s.png").getImage();
        ImageIcon s = new ImageIcon(straw);
        b1 = new JButton(s);
        b1.setActionCommand("s");

        Image field = new ImageIcon("src/main/resources/port_f.png").getImage();
        ImageIcon f = new ImageIcon(field);
        b2 = new JButton(f);
        b2.setActionCommand("f");

        Image stone = new ImageIcon("src/main/resources/port_t.png").getImage();
        ImageIcon t = new ImageIcon(stone);
        b3 = new JButton(t);
        b3.setActionCommand("t");

        Image wood = new ImageIcon("src/main/resources/port_w.png").getImage();
        ImageIcon w = new ImageIcon(wood);
        b4 = new JButton(w);
        b4.setActionCommand("w");

        Image brick = new ImageIcon("src/main/resources/port_b.png").getImage();
        ImageIcon b = new ImageIcon(brick);
        b5 = new JButton(b);
        b5.setActionCommand("b");

        Image three = new ImageIcon("src/main/resources/port_any.png").getImage();
        ImageIcon th = new ImageIcon(three);
        b6 = new JButton(th);
        b6.setActionCommand("3");

        Image four = new ImageIcon("src/main/resources/port_4.png").getImage();
        ImageIcon fo = new ImageIcon(four);
        b7 = new JButton(fo);
        b7.setActionCommand("4");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        buttonPanel.add(b7);
        if (ExchangeWithPorts.player.ports.contains("s")) buttonPanel.add(b1);
        if (ExchangeWithPorts.player.ports.contains("f")) buttonPanel.add(b2);
        if (ExchangeWithPorts.player.ports.contains("t")) buttonPanel.add(b3);
        if (ExchangeWithPorts.player.ports.contains("w")) buttonPanel.add(b4);
        if (ExchangeWithPorts.player.ports.contains("b")) buttonPanel.add(b5);
        if (ExchangeWithPorts.player.ports.contains("3")) buttonPanel.add(b6);
        panel.add(buttonPanel);
        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = String.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}