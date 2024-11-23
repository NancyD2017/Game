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
        setBounds(x, y, 70, 70);
        setActionCommand(text);
        addActionListener(this);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Включаем антиалиасинг
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Если кнопка нажата, меняем цвет
        if (getModel().isArmed()) {
            g2d.setColor(Color.LIGHT_GRAY);
        } else {
            g2d.setColor(getBackground());
        }

        // Рисуем круг
        g2d.fillOval(0, 0, getWidth() - 1, getHeight() - 1);

        g2d.dispose();

        // Убираем любые дополнительные отрисовки кнопки
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
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
