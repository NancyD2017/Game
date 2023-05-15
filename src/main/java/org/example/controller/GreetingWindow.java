package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingWindow extends Buttons implements ActionListener{
    public static Object messageToPass;
    public GreetingWindow(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(buttonMaker("2 players"));
        add(buttonMaker("3 players"));
        add(buttonMaker("4 players"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = Integer.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
    private JButton buttonMaker(String command){
        JButton b1 = new JButton(command);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setActionCommand(String.valueOf(command.charAt(0)));
        b1.addActionListener(this);
        return b1;
    }
}