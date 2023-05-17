package org.example;

import org.example.model.Model;
import org.example.model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import static org.example.Pole.dibs;
import static org.example.model.Model.*;

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
        try {
            for (Dib dib : dibs) {
                pole.drawDib(dib.getRow(), dib.getColumn(), dib.getColor(), g, dib.getType());
            }
            for (Player player : playerList) {
                Collections.sort(player.cards);
                pole.drawCard(player, player.cards, g);
            }
            g.setFont(new Font("Arial", Font.BOLD, (int) (percent * 24)));
            for (int i = 0; i < players; i++) {
                int x = 295;
                int y = 310;
                switch (playerList.get(i).color) {
                    case Orange -> g.setColor(Color.ORANGE);
                    case Blue -> g.setColor(Color.BLUE);
                    case Red -> g.setColor(Color.RED);
                    case Gray -> g.setColor(Color.GRAY);
                }
                switch (i) {
                    case 1 -> x = 1625;
                    case 2 -> y = 725;
                    case 3 -> {
                        y = 725;
                        x = 1625;
                    }
                }
                g.drawString("Player " + (i + 1), (int) (x * percent), (int) (y * percent));
            }
            if (Model.cubesNumber > 0) {
                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.BOLD, (int) (percent * 60)));
                g.drawString(String.valueOf(Model.cubesNumber), (int) (percent * 296), (int) (percent * 530));
            }
                g.setFont(new Font("Arial", Font.BOLD, (int) (percent * 24)));
                g.setColor(Color.WHITE);
                try {
                    String points = "Most points holder is player " + (playerList.indexOf(mostPointsHolder) + 1) + ": " + mostPoints;
                    g.drawString(points, (int) (1514 * percent), (int) (389 * percent));
                } catch (Exception ignored) {}
                try {
                    String knights = "Most knights holder is player " + (playerList.indexOf(mostKnightsHolder) + 1) + ": " + mostKnights;
                    g.drawString(knights, (int) (1514 * percent), (int) (339 * percent));
                } catch (Exception ignored) {}
                String roads =  "Most roads holder is player " + (playerList.indexOf(leastRoadsLeftHolder) + 1) + ": " + (15 - leastRoadsLeft);
                g.drawString(roads, (int) (1514 * percent), (int) (439 * percent));
        } catch (Exception ignored) {}
    }
    public void main() {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JFrame frame = new JFrame("Catan");
        frame.setAlwaysOnTop(false);
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
        return new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage != null ? sourceImage.getSource() : null));
    }
}