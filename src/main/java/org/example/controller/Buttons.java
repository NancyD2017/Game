package org.example.controller;

import org.example.Game;
import org.example.Sprite;
import org.example.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.Game.percent;

public class Buttons extends JPanel implements ActionListener {
    final JLabel label;
    static java.util.List<Character> dataToShow;
    public static Sprite getSprite;
    Sprite s;
    Sprite f;
    Sprite t;
    Sprite w;
    Sprite b;
    static String name;
    public static Game q = Model.q;
    public Buttons(JLabel label) {
        this.label = label;
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    public static void init(JLabel label, String className) {
        getSprite = q.getSprite("paper.png");
        JFrame frame = new JFrame("");
        frame.add(q);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Buttons buttons;
        name = className;
        switch (className) {
            case "GreetingWindow" -> buttons = new GreetingWindow(label);
            case "Buttons_Colors" -> buttons = new Buttons_Colors(label);
            case "Turn" -> buttons = new Buttons_Turns(label);
            case "Buttons_Build" -> buttons = new Buttons_Build(label);
            case "Buttons_Forward1" -> buttons = new Buttons_Forward1(label);
            case "Buttons_Forward2" -> buttons = new Buttons_Forward2(label);
            case "Buttons_Knight" -> buttons = new Buttons_Knight(label);
            case "Buttons_EPlayer" -> buttons = new Buttons_EPlayer(label);
            case "Buttons_ExchangePlayer" -> buttons = new Buttons_ExchangePlayer(label);
            case "Buttons_ExchangePl" -> buttons = new Buttons_ExchangePl(label);
            case "Buttons_ExchangeValidation" -> buttons = new Buttons_ExchangeValidation(label);
            case "Buttons_4ExchangePort" -> buttons = new Buttons_4ExchangePort(label);
            case "Buttons_4Exchange" -> buttons = new Buttons_4Exchange(label);
            case "Buttons_ExchangePort" -> buttons = new Buttons_ExchangePort(label);
            default -> {
                buttons = new Buttons(label);
                Timer timer = new Timer(7000, e -> frame.dispose());
                timer.setRepeats(false);
                timer.start();
            }
        }
        if (className.equals("Buttons_Forward1") || className.equals("Buttons_Forward2")) buttons.setPreferredSize(new Dimension(1000, 750));
        else {
            buttons.setPreferredSize(new Dimension((int) (getSprite.getWidth() * percent), (int) (getSprite.getHeight() * percent)));
        }
        frame.setContentPane(buttons);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!(name.equals("Buttons_Forward1") || name.equals("Buttons_Forward2") || name.equals("Buttons_ExchangePlayer")
                || name.equals("Buttons_ExchangePl"))) getSprite.draw(g,0,0);
        if (name.equals("Resources")) {
                int x = 50;
                int y = 80;
                s = Buttons.q.getSprite("s_card.png");
                f = Buttons.q.getSprite("f_card.png");
                t = Buttons.q.getSprite("t_card.png");
                w = Buttons.q.getSprite("w_card.png");
                b = Buttons.q.getSprite("b_card.png");
                for (Character element : dataToShow) {
                    switch (element) {
                        case 's' -> s.draw(g, x, y);
                        case 'f' -> f.draw(g, x, y);
                        case 't' -> t.draw(g, x, y);
                        case 'b' -> b.draw(g, x, y);
                        case 'w' -> w.draw(g, x, y);
                    }
                    x += (633 / dataToShow.size());
                }
        }
    }
        @Override
        public void actionPerformed(ActionEvent e) {
        }
}