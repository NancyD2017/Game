package org.example.Controller;

import org.example.Model.EvolutionCards;
import org.example.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_Knight extends Buttons implements ActionListener {
    protected JButton b1, b2, b3, b4;
    public static Integer messageToPass;
    public static boolean isPerformed = true;
    public Buttons_Knight(JLabel label) {
        super(label);
        add(label, BoxLayout.X_AXIS);
        main();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = Integer.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
    public void main(){
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        b1 = new JButton("1");
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setActionCommand("1");

        b2 = new JButton("2");
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setActionCommand("2");

        b3 = new JButton("3");
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setActionCommand("3");

        b4 = new JButton("4");
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);
        b4.setActionCommand("4");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        add(Box.createVerticalGlue());
        if (Model.playerList.indexOf(EvolutionCards.player) != 0) add(b1);
        if (Model.playerList.indexOf(EvolutionCards.player) != 1) add(b2);
        if (Model.players > 2 && Model.playerList.indexOf(EvolutionCards.player) != 2) add(b3);
        if (Model.players == 4 && Model.playerList.indexOf(EvolutionCards.player) != 3) add(b4);
    }
}