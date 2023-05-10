package org.example.Controller;

import org.example.Model.ExchangeWithPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_4ExchangePort extends Buttons implements ActionListener{
    protected JButton b1, b2, b3, b4, b5;
    public static Object messageToPass = null;
    public static boolean isPerformed = true;
    public Buttons_4ExchangePort(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.add(label);
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 5));
        buttonPanel.setAlignmentX(label.getAlignmentX());

        Image straw = new ImageIcon("src/main/resources/s_card.png").getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon s = new ImageIcon(straw);
        b1 = new JButton(s);
        b1.setActionCommand("s");
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        Image field = new ImageIcon("src/main/resources/f_card.png").getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon f = new ImageIcon(field);
        b2 = new JButton(f);
        b2.setActionCommand("f");

        Image stone = new ImageIcon("src/main/resources/t_card.png").getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon t = new ImageIcon(stone);
        b3 = new JButton(t);
        b3.setActionCommand("t");

        Image wood = new ImageIcon("src/main/resources/w_card.png").getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon w = new ImageIcon(wood);
        b4 = new JButton(w);
        b4.setActionCommand("w");

        Image brick = new ImageIcon("src/main/resources/b_card.png").getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon b = new ImageIcon(brick);
        b5 = new JButton(b);
        b5.setActionCommand("b");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
        panel.add(buttonPanel);
        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = String.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
}