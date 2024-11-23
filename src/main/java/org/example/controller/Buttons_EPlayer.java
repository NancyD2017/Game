package org.example.controller;

import org.example.model.ExchangeWithPlayer;
import org.example.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_EPlayer extends Buttons implements ActionListener {
    public static Integer messageToPass;
    public Buttons_EPlayer(JLabel label) {
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
        if (Model.playerList.indexOf(ExchangeWithPlayer.player) != 0) add(buttonMaker("1"));
        if (Model.playerList.indexOf(ExchangeWithPlayer.player) != 1) add(buttonMaker("2"));
        if (Model.players > 2 && Model.playerList.indexOf(ExchangeWithPlayer.player) != 2) add(buttonMaker("3"));
        if (Model.players == 4 && Model.playerList.indexOf(ExchangeWithPlayer.player) != 3) add(buttonMaker("4"));
        add(buttonMaker("Cancel"));
    }
    private JButton buttonMaker(String command){
        JButton b1 = new JButton(command);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (command.equals("Cancel")) b1.setActionCommand("0");
        else b1.setActionCommand(command);
        b1.addActionListener(this);
        return b1;
    }
}