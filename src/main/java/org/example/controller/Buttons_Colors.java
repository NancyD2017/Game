package org.example.controller;

import org.example.model.Colors;
import org.example.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_Colors extends Buttons implements ActionListener {
    public static String messageToPass;
    public Buttons_Colors(JLabel label) {
        super(label);
        main();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = String.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
    public void main(){
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        if (Model.colors.contains(Colors.Red)) add(buttonMaker(Color.red));
        if (Model.colors.contains(Colors.Blue)) add(buttonMaker(Color.BLUE));
        if (Model.colors.contains(Colors.Gray)) add(buttonMaker(Color.gray));
        if (Model.colors.contains(Colors.Orange)) add(buttonMaker(Color.ORANGE));
    }
    private JButton buttonMaker(Color color){
        JButton b = new JButton("            ");
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setBackground(color);
        if (Color.red.equals(color)) b.setActionCommand("Red");
        if (Color.BLUE.equals(color)) b.setActionCommand("Blue");
        if (Color.ORANGE.equals(color)) b.setActionCommand("Orange");
        if (Color.gray.equals(color)) b.setActionCommand("Gray");
        b.addActionListener(this);
        return b;
    }
}