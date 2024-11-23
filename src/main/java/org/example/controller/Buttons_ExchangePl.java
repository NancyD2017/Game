package org.example.controller;

import org.example.model.ExchangeWithPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class Buttons_ExchangePl extends Buttons implements ActionListener{
    protected JButton b6;
    public static Object messageToPass = null;
    public static String message = "";
    public Buttons_ExchangePl(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.add(label);
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 6));
        buttonPanel.setAlignmentX(label.getAlignmentX());

        b6 = new JButton("OK");
        b6.setActionCommand("OK");
        b6.addActionListener(this);
        for (Character item: Set.of('s','f','t','w','b')) {
            if (ExchangeWithPlayer.nameExchanger.cards.contains(item)) buttonPanel.add(buttonMaker(item.toString()));
        }
        JButton b7 = new JButton("Cancel");
        b7.setActionCommand("0");
        b7.addActionListener(this);
        buttonPanel.add(b6);
        buttonPanel.add(b7);
        panel.add(buttonPanel);
        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!e.getActionCommand().equals("OK") && !e.getActionCommand().equals("0")) {
            message += e.getActionCommand() + " ";
        } else if (!e.getActionCommand().equals("0")) {
            messageToPass = String.valueOf(message);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        } else {
            messageToPass = "0";
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        }
    }
    private JButton buttonMaker(String resource){
        Image image = new ImageIcon("src/main/resources/" + resource + "_card.png").getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon s = new ImageIcon(image);
        JButton b = new JButton(s);
        b.setActionCommand(resource);
        b.addActionListener(this);
        return b;
    }
}