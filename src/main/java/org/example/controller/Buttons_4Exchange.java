package org.example.controller;
import org.example.model.ExchangeWithPorts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_4Exchange extends Buttons implements ActionListener{
    public static Object messageToPass = null;
    public static String message = "";
    Integer chosen = 0;
    public Buttons_4Exchange(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.add(label);
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 5));
        buttonPanel.setAlignmentX(label.getAlignmentX());

        if (ExchangeWithPorts.player.cards.contains('s')) buttonPanel.add(buttonMaker("s"));
        if (ExchangeWithPorts.player.cards.contains('f')) buttonPanel.add(buttonMaker("f"));
        if (ExchangeWithPorts.player.cards.contains('t')) buttonPanel.add(buttonMaker("t"));
        if (ExchangeWithPorts.player.cards.contains('w')) buttonPanel.add(buttonMaker("w"));
        if (ExchangeWithPorts.player.cards.contains('b')) buttonPanel.add(buttonMaker("b"));
        panel.add(buttonPanel);
        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (chosen < ExchangeWithPorts.useExchange - 1) {
            message += e.getActionCommand() + " ";
            chosen++;
        } else {
            messageToPass = message + e.getActionCommand();
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