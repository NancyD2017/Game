package org.example.controller;

import org.example.model.EvolutionCards;
import org.example.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_Knight extends Buttons implements ActionListener {
    public static Integer messageToPass;
    public Buttons_Knight(JLabel label) {
        super(label);
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
        add(Box.createVerticalGlue());
        if (Model.playerList.indexOf(EvolutionCards.gamer) != 0) add(buttonMaker("1"));
        if (Model.playerList.indexOf(EvolutionCards.gamer) != 1) add(buttonMaker("2"));
        if (Model.players > 2 && Model.playerList.indexOf(EvolutionCards.gamer) != 2) add(buttonMaker("3"));
        if (Model.players == 4 && Model.playerList.indexOf(EvolutionCards.gamer) != 3) add(buttonMaker("4"));
    }
    private JButton buttonMaker(String text){
        JButton b = new JButton(text);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setActionCommand(text);
        b.addActionListener(this);
        return b;
    }
}