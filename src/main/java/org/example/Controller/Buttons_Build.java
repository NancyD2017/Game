package org.example.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_Build extends Buttons implements ActionListener{
    protected JButton b1, b2, b3;
    public static Object messageToPass;
    public static boolean isPerformed = true;
    public Buttons_Build(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        b1 = new JButton("Road");
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setActionCommand("Road");

        b2 = new JButton("Town");
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setActionCommand("Town");

        b3 = new JButton("City");
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setActionCommand("City");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        add(Box.createVerticalGlue());
        add(b1);
        add(b2);
        add(b3);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = String.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}