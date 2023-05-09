package org.example.Controller;

import org.example.Model.Colors;
import org.example.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_Colors extends Buttons implements ActionListener {
    protected JButton b1, b2, b3, b4;
    public static String messageToPass;
    public static boolean isPerformed = true;
    public Buttons_Colors(JLabel label) {
        super(label);
        add(label, BoxLayout.X_AXIS);
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
        b1 = new JButton("            ");
        b1.setBackground(Color.RED);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setActionCommand("Red");

        b2 = new JButton("            ");
        b2.setBackground(Color.blue);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setActionCommand("Blue");

        b3 = new JButton("            ");
        b3.setBackground(Color.WHITE);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setActionCommand("White");

        b4 = new JButton("            ");
        b4.setBackground(Color.ORANGE);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);
        b4.setActionCommand("Orange");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        add(Box.createVerticalGlue());
        if (Model.colors.contains(Colors.Red)) add(b1);
        if (Model.colors.contains(Colors.Blue)) add(b2);
        if (Model.colors.contains(Colors.White)) add(b3);
        if (Model.colors.contains(Colors.Orange)) add(b4);
    }
}