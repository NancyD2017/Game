package org.example;

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
            if (cubesNumber > 0) {
                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.BOLD, (int) (percent * 60)));
                g.drawString(String.valueOf(cubesNumber), (int) (percent * 296), (int) (percent * 530));
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
        if (false) addRoadButtons(frame);
        if (false) addCityOrTownButtons(frame);
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

    public void addRoadButtons(JFrame frame){
//        frame.add(new RoadButton("2_1",650,140, 0));
//        frame.add(new RoadButton("2_2",840,140, 0));
//        frame.add(new RoadButton("2_3",1035,140, 0));
//        frame.add(new RoadButton("2_4",1225,140, 0));
//        frame.add(new RoadButton("4_1",560,308, 0));
//        frame.add(new RoadButton("4_2",738,308, 0));
//        frame.add(new RoadButton("4_3",935,308, 0));
//        frame.add(new RoadButton("4_4",1129,308, 0));
//        frame.add(new RoadButton("4_5",1325,308, 0));
//        frame.add(new RoadButton("6_1",453,480, 0));
//        frame.add(new RoadButton("6_2",640,480, 0));
//        frame.add(new RoadButton("6_3",835,480, 0));
//        frame.add(new RoadButton("6_4",1032,480, 0));
//        frame.add(new RoadButton("6_5",1225,480, 0));
//        frame.add(new RoadButton("6_6",1406,480, 0));
//        frame.add(new RoadButton("4_1",560,652, 0));
//        frame.add(new RoadButton("4_2",738,652, 0));
//        frame.add(new RoadButton("4_3",935,652, 0));
//        frame.add(new RoadButton("4_4",1129,652, 0));
//        frame.add(new RoadButton("4_5",1325,652, 0));
//        frame.add(new RoadButton("2_1",650,818, 0));
//        frame.add(new RoadButton("2_2",840,818, 0));
//        frame.add(new RoadButton("2_3",1035,818, 0));
//        frame.add(new RoadButton("2_4",1225,818, 0));

        frame.add(new RoadButton("1_2",680,75,60));
        frame.add(new RoadButton("1_6",857,136, 60));
        frame.add(new RoadButton("1_10",1040,136, 60));
        frame.add(new RoadButton("3_2",1237,13, 60));
        frame.add(new RoadButton("3_6",561,343, 60));
        frame.add(new RoadButton("3_10",753,343, 60));
        frame.add(new RoadButton("3_14",945,343, 60));
        frame.add(new RoadButton("5_2",1139,343, 60));
        frame.add(new RoadButton("5_6",1335,343, 60));
        frame.add(new RoadButton("5_10",463,515, 60));
        frame.add(new RoadButton("5_14",659,515, 60));
        frame.add(new RoadButton("5_18",857,515, 60));
        frame.add(new RoadButton("7_4",1040,515, 60));
        frame.add(new RoadButton("7_8",1237,515, 60));
        frame.add(new RoadButton("7_12",1426,515, 60));
        frame.add(new RoadButton("7_16",561,682, 60));
        frame.add(new RoadButton("7_20",753,682, 60));
        frame.add(new RoadButton("9_4",945,682, 60));
        frame.add(new RoadButton("9_8",1139,682, 60));
        frame.add(new RoadButton("9_12",1335,682, 60));
        frame.add(new RoadButton("9_16",652,848, 60));
        frame.add(new RoadButton("11_4",857,848, 60));
        frame.add(new RoadButton("11_8",1040,848, 60));
        frame.add(new RoadButton("11_12",1237,848, 60));
    }
    public void addCityOrTownButtons(JFrame frame) {
        frame.add(new RoundButton("1_1", 648, 103));
        frame.add(new RoundButton("1_3", 724,70));
        frame.add(new RoundButton("1_5", 839, 103));
        frame.add(new RoundButton("1_7", 925,70));
        frame.add(new RoundButton("1_9", 1023, 103));
        frame.add(new RoundButton("1_11", 1124,70));
        frame.add(new RoundButton("1_13", 1216, 103));
        frame.add(new RoundButton("3_1", 541, 294));
        frame.add(new RoundButton("3_3", 633, 237));
        frame.add(new RoundButton("3_5", 734,294));
        frame.add(new RoundButton("3_7", 839, 237));
        frame.add(new RoundButton("3_9", 925,294));
        frame.add(new RoundButton("3_11", 1023, 237));
        frame.add(new RoundButton("3_13", 1124,294));
        frame.add(new RoundButton("3_15", 1216, 237));
        frame.add(new RoundButton("3_17", 1318, 294));
        frame.add(new RoundButton("5_1", 439, 463));
        frame.add(new RoundButton("5_3", 541, 408));
        frame.add(new RoundButton("5_5", 633, 463));
        frame.add(new RoundButton("5_7", 734,408));
        frame.add(new RoundButton("5_9", 839, 463));
        frame.add(new RoundButton("5_11", 925,408));
        frame.add(new RoundButton("5_13", 1023, 463));
        frame.add(new RoundButton("5_15", 1124,408));
        frame.add(new RoundButton("5_17", 1216, 463));
        frame.add(new RoundButton("5_19", 1318, 408));
        frame.add(new RoundButton("5_21", 1411, 463));
        frame.add(new RoundButton("7_1",439,571));
        frame.add(new RoundButton("7_3", 541, 623));
        frame.add(new RoundButton("7_5", 633, 571));
        frame.add(new RoundButton("7_7", 734, 623));
        frame.add(new RoundButton("7_9", 839,571));
        frame.add(new RoundButton("7_11", 925, 623));
        frame.add(new RoundButton("7_13", 1023,571));
        frame.add(new RoundButton("7_15", 1124, 623));
        frame.add(new RoundButton("7_17", 1216,571));
        frame.add(new RoundButton("7_19", 1318, 623));
        frame.add(new RoundButton("7_21", 1411, 571));
        frame.add(new RoundButton("9_1", 541, 748));
        frame.add(new RoundButton("9_3", 633, 799));
        frame.add(new RoundButton("9_5", 734,748));
        frame.add(new RoundButton("9_7", 839, 799));
        frame.add(new RoundButton("9_9", 925,748));
        frame.add(new RoundButton("9_11", 1023, 799));
        frame.add(new RoundButton("9_13", 1124,748));
        frame.add(new RoundButton("9_15", 1216, 799));
        frame.add(new RoundButton("9_17", 1318, 748));
        frame.add(new RoundButton("11_1", 633, 913));
        frame.add(new RoundButton("11_3", 734,968));
        frame.add(new RoundButton("11_5", 839, 913));
        frame.add(new RoundButton("11_7", 925,968));
        frame.add(new RoundButton("11_9", 1023, 913));
        frame.add(new RoundButton("11_11", 1124,968));
        frame.add(new RoundButton("11_13", 1216, 913));
    }
}