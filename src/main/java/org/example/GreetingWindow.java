package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingWindow extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;
    public Integer players;
    public static Sprite greeting;
    private boolean running;
    public GreetingWindow() {
        b1 = new JButton("2 players");
        b1.setActionCommand("2");

        b2 = new JButton("3 players");
        b2.setActionCommand("3");

        b3 = new JButton("4 players");
        b3.setActionCommand("4");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        add(b1);
        add(b2);
        add(b3);
    }

    public void actionPerformed(ActionEvent e) {
        players = Integer.valueOf(e.getActionCommand());
    }
    public static void init() {
        Game q = new Game();
        greeting = q.getSprite("greeting.png");
        JFrame frame = new JFrame("Players number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GreetingWindow newContentPane = new GreetingWindow();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        greeting.draw(g,0,0);
    }


}