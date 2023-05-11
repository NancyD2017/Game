package org.example.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_Turns extends Buttons implements ActionListener{
    protected JButton b1, b2, b3, b4, b5;
    public static Object messageToPass;
    public static boolean isPerformed = true;
    public Buttons_Turns(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        b1 = new JButton("Build something (Road, Town, City)");
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setActionCommand("1");

        b2 = new JButton("Exchange with ports");
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setActionCommand("2");

        b3 = new JButton("Exchange with players");
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setActionCommand("3");

        b4 = new JButton("Buy evolution card");
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);
        b4.setActionCommand("4");

        b5 = new JButton("Pass your turn");
        b5.setAlignmentX(Component.CENTER_ALIGNMENT);
        b5.setActionCommand("5");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        add(Box.createVerticalGlue());
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = Integer.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}