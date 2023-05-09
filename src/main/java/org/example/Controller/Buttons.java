package org.example.Controller;

import org.example.Game;
import org.example.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.Game.percent;

public class Buttons extends JPanel implements ActionListener {
    public static boolean isPerformed = true;
    final JLabel label;
    static java.util.List<Character> dataToShow;
    public static Sprite getSprite;
    static Sprite s;
    static Sprite f;
    static Sprite t;
    static Sprite w;
    static Sprite b;
    static String name;
    public static Game q = new Game();
    public Buttons(JLabel label) {
        this.label = label;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(Box.createVerticalGlue());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    public static void init(JLabel label, String className) {
        getSprite = q.getSprite("paper.png");
        JFrame frame = new JFrame("");
        frame.setLayout(new BorderLayout());
        frame.add(q, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Buttons buttons;
        name = className;
        switch (className) {
            case "GreetingWindow" -> {
                buttons = new GreetingWindow(label);
                buttons.setPreferredSize(new Dimension((int) (getSprite.getWidth() * percent), (int) (getSprite.getHeight() * percent)));
            }
            case "Buttons_Colors" -> {
                buttons = new Buttons_Colors(label);
                buttons.setPreferredSize(new Dimension((int) (getSprite.getWidth() * percent), (int) (getSprite.getHeight() * percent)));
            }
            case "Turn" -> {
                buttons = new Buttons_Turns(label);
                buttons.setPreferredSize(new Dimension((int) (getSprite.getWidth() * percent), (int) (getSprite.getHeight() * percent)));
            }
            case "Buttons_Build" -> {
                buttons = new Buttons_Build(label);
                buttons.setPreferredSize(new Dimension((int) (getSprite.getWidth() * percent), (int) (getSprite.getHeight() * percent)));
            }
            case "Buttons_Forward1" -> {
                buttons = new Buttons_Forward1(label);
                buttons.setPreferredSize(new Dimension(1000, 750));
            }
            case "Buttons_Forward2" -> {
                buttons = new Buttons_Forward2(label);
                buttons.setPreferredSize(new Dimension(1000, 750));
            }
            case "Buttons_Knight" -> {
                buttons = new Buttons_Knight(label);
                buttons.setPreferredSize(new Dimension((int) (getSprite.getWidth() * percent), (int) (getSprite.getHeight() * percent)));
            }
            default -> {
                buttons = new Buttons(label);
                buttons.setPreferredSize(new Dimension((int) (getSprite.getWidth() * percent), (int) (getSprite.getHeight() * percent)));
                Timer timer = new Timer(7000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
        frame.setContentPane(buttons);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        getSprite.draw(g,0,0);
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