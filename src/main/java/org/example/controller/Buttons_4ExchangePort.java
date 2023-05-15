package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_4ExchangePort extends Buttons implements ActionListener{
    public static Object messageToPass = null;
    public Buttons_4ExchangePort(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.add(label);
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 5));
        buttonPanel.setAlignmentX(label.getAlignmentX());

        buttonPanel.add(buttonMaker("s"));
        buttonPanel.add(buttonMaker("f"));
        buttonPanel.add(buttonMaker("t"));
        buttonPanel.add(buttonMaker("w"));
        buttonPanel.add(buttonMaker("b"));
        panel.add(buttonPanel);
        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = String.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
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