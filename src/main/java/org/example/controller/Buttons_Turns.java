package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_Turns extends Buttons implements ActionListener{
    public static Object messageToPass;
    public Buttons_Turns(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(buttonMaker("Buy something (Road, Town, City)","1"));
        add(buttonMaker("Exchange with ports", "2"));
        add(buttonMaker("Exchange with players","3"));
        add(buttonMaker("Buy evolution card", "4"));
        add(buttonMaker("Pass your turn","5"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = Integer.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
    private JButton buttonMaker(String command, String action){
        JButton b1 = new JButton(command);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setActionCommand(action);
        b1.addActionListener(this);
        return b1;
    }
}