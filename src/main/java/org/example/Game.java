package org.example;

import org.example.Controller.StringCatcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import static org.example.Pole.dibs;

public class Game extends Canvas implements Runnable {
    private boolean running;
    public static int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static int HEIGHT= (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static double percent = WIDTH/1920.00;
    Pole pole = new Pole();
    public int x = (int) ((WIDTH - ((getSprite("pole.png").getWidth()) * percent))/2);
    public void run() {
            pole.init();
            while (running) {
                render();
            }
    }
    public void render(){
               BufferStrategy bs = getBufferStrategy();
               if (bs == null) {
                   createBufferStrategy(2);
                   requestFocus();
                   return;
               }
               Graphics g = bs.getDrawGraphics();
               g.fillRect(0, 0, getWidth(), getHeight());
               pole.perform(x, g);
               update(g);
               bs.show();
    }
    public void update(Graphics g) {
        Iterator<Dib> iterator = dibs.iterator();
        while (iterator.hasNext()) {
            Dib dib = iterator.next();
            pole.drawDib(dib.getRow(), dib.getColumn(), dib.getColor(), g, dib.getType());
        }
    }
    public void main() {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JFrame frame = new JFrame("Catan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        game.start();
    }
    public void start() {
        running = true;
        new Thread(this).start();
    }
    public Sprite getSprite(String path) {
        BufferedImage sourceImage = null;
        try{
            URL url = this.getClass().getClassLoader().getResource(path);
            assert url != null;
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));
    }
}