package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoundButton extends JButton implements ActionListener {
    public static Object row = null;
    public static Object column = null;

    public RoundButton(String text, int x, int y) {
        super();
        setBounds(x, y, 50, 50);
        setActionCommand(text);
        addActionListener(this);
        setContentAreaFilled(false);
        setBackground(new Color(0, 255,0,55));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g2d.dispose();
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
    }
    public void actionPerformed(ActionEvent e) {
        String[] position;
        position = String.valueOf(e.getActionCommand()).split("_");
        row = position[0];
        column = position[1];
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}
