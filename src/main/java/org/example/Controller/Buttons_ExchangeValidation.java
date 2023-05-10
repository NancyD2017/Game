package org.example.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_ExchangeValidation extends Buttons implements ActionListener{
    protected JButton b1, b2;
    public static Object messageToPass = null;
    public static boolean isPerformed = true;
    public Buttons_ExchangeValidation(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        add(label);
        add(Box.createVerticalGlue());

        b1 = new JButton("yes");
        b1.setActionCommand("yes");

        b2 = new JButton("no");
        b2.setActionCommand("no");

        b1.addActionListener(this);
        b2.addActionListener(this);

        add(b1);
        add(b2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = String.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}