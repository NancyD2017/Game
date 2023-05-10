package org.example.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingWindow extends Buttons implements ActionListener{
    protected JButton b1,b2,b3;
    public static Object messageToPass;
    public GreetingWindow(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        b1 = new JButton("2 players");
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setActionCommand("2");

        b2 = new JButton("3 players");
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setActionCommand("3");

        b3 = new JButton("4 players");
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setActionCommand("4");

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
        messageToPass = Integer.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}