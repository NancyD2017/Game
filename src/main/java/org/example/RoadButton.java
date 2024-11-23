package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoadButton extends JComponent {
    private final int angle;
    private final String label;
    public static Object row = null;
    public static Object column = null;
    private boolean isPressed = false;

    public RoadButton(String text, int x, int y, int z) {
        this.angle = z;
        this.label = text;
        if (Integer.parseInt(String.valueOf(text.charAt(0))) % 2 == 0) setBounds(x, y, 37, 88);
        else setBounds(x, y, 100, 100);

        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
                handleClick();
            }
        });
    }

    private void handleClick() {
        String[] position = label.split("_");
        this.row = position[0];
        this.column = position[1];
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            Container parent = getParent();
            if (parent instanceof JPanel) {
                parent.removeAll();
                parent.revalidate();
                parent.repaint();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.translate(getWidth() / 2.0, getHeight() / 2.0);
        g2d.rotate(Math.toRadians(angle));
        g2d.translate(-getWidth() / 2.0, -getHeight() / 2.0);

        g2d.setColor(Color.LIGHT_GRAY);
        if (Integer.parseInt(String.valueOf(label.charAt(0))) % 2 == 0) g2d.drawRect(0, 0, 36, 87);
        else g2d.drawRect(30, 10, 36, 87);
        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
}