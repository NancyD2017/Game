package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingWindow extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;
    public Integer players;
    public static Sprite greeting;
    public static boolean isPerformed = true;
    public GreetingWindow() {
        JLabel label = new JLabel("<html><div style='text-align: center;'>Hello, players!<br>Welcome to the game Catan: colonists.<br><br>Please, choose the number of players:<br><br></div></html>");
        label.setFont(new Font("Palatino Linotype", Font.PLAIN, 40));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(label);
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

    public void actionPerformed(ActionEvent e) {
        players = Integer.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
    public static void init() {
        Game q = new Game();
        greeting = q.getSprite("paper.png");
        JFrame frame = new JFrame("Players number");
        frame.setLayout(new BorderLayout());
        frame.add(q, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GreetingWindow gw = new GreetingWindow();
        gw.setPreferredSize(new Dimension((int) (greeting.getWidth() * Game.percent), (int) (greeting.getHeight() * Game.percent)));
        frame.setContentPane(gw);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        greeting.draw(g,0,0);
    }
}