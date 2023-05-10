package org.example.Controller;

import org.example.Model.ExchangeWithPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons_ExchangePl extends Buttons implements ActionListener{
    protected JButton b1, b2, b3, b4, b5, b6;
    public static Object messageToPass = null;
    public static String message = "";
    public static boolean isPerformed = true;
    private int selectedCount = 1;
    public Buttons_ExchangePl(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        JPanel panel = new JPanel(new GridLayout(2, 0));
        panel.add(label);
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 6));
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
        b6 = new JButton("OK");
        b6.setActionCommand("OK");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        if (ExchangeWithPlayer.nameExchanger.cards.contains('s')) buttonPanel.add(b1);
        if (ExchangeWithPlayer.nameExchanger.cards.contains('f')) buttonPanel.add(b2);
        if (ExchangeWithPlayer.nameExchanger.cards.contains('t')) buttonPanel.add(b3);
        if (ExchangeWithPlayer.nameExchanger.cards.contains('w')) buttonPanel.add(b4);
        if (ExchangeWithPlayer.nameExchanger.cards.contains('b')) buttonPanel.add(b5);
        buttonPanel.add(b6);
        panel.add(buttonPanel);
        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!e.getActionCommand().equals("OK")) {
            message += e.getActionCommand() + " ";
        } else {
            messageToPass = String.valueOf(message);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        }
    }
}