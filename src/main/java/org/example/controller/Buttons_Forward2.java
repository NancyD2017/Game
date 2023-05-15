package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.Game.percent;

public class Buttons_Forward2 extends Buttons implements ActionListener{
    public static Object messageToPass;
    public Buttons_Forward2(JLabel label) {
        super(label);
        main();
    }
    public void main(){
        JPanel panel = new JPanel(new GridLayout(4, 0));
        JLabel label1 = new JLabel("Congratulations!You've got");
        label1.setFont(new Font("Arial", Font.PLAIN, (int) (42 * percent)));
        panel.add(label1);
        Image forward2 = new ImageIcon("src/main/resources/c_map.png").getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(forward2));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(imageLabel);
        panel.add(label);
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 5));
        buttonPanel.setAlignmentX(label.getAlignmentX());

        buttonPanel.add(buttonMaker("s"));
        buttonPanel.add(buttonMaker("f"));
        buttonPanel.add(buttonMaker("t"));
        buttonPanel.add(buttonMaker("w"));
        buttonPanel.add(buttonMaker("b"));
        panel.add(buttonPanel);
        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        messageToPass = String.valueOf(e.getActionCommand());
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }
    private JButton buttonMaker(String resource){
        Image image = new ImageIcon("src/main/resources/" + resource + "_card.png").getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon s = new ImageIcon(image);
        JButton b = new JButton(s);
        b.setActionCommand(resource);
        b.addActionListener(this);
        return b;
    }
}