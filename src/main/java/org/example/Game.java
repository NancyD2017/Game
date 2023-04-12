package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean running;
    public static Sprite pole;
    public static Sprite background;
    public static int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static int HEIGHT= (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static String NAME = "Catan";
    private int x = (WIDTH - getSprite("pole.png").getWidth())/2;
    private static int y = 0;
    public ArrayList<Sprite> hexes = new ArrayList<>();
    public static Sprite f;
    public static Sprite s;
    public static Sprite t;
    public static Sprite a;
    public static Sprite w;
    public static Sprite b;

    static Integer howManyGamers;


    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;
        init();
        while (running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
            render();
        }
    }
    public void init(){
        pole = getSprite("pole.png");
        background = getSprite("background.png");
        f = getSprite("field_hex.png");
        s = getSprite("straw_hex.png");
        t = getSprite("stone_hex.png");
        a = getSprite("sand_hex.png");
        w = getSprite("woods_hex.png");
        b = getSprite("brick_hex.png");
        hexes.addAll(Arrays.asList(f,f,f,f,s,s,s,s,t,t,t,w,w,w,w,b,b,b));
        List<Integer> values = List.of(5,2,6,10,9,4,3,8,11,0,5,8,4,3,6,10,11,12,9);
        Collections.shuffle(hexes);
        hexes.add(9, a);
        Map<Integer, Sprite> description = new HashMap<>();
        for (int i = 0; i < hexes.size() - 1; i++) {
            description.put(values.get(i), hexes.get(i));
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
        g.fillRect(0,0, getWidth(), getHeight());
        background.draw(g,0,0);
        int weight = 466;
        int height = 70;
        int number = 1;
        for (Sprite it: hexes) {
            if (number != 4 && number!=8 && number!=13 && number!= 17) {
                weight += 195;
            } else if (number == 4) {
                weight = 660 - 98;
                height += 170;
            } else if (number == 8) {
                weight = 660 - 197;
                height += 170;
            } else if (number == 13) {
                weight = 660 - 98;
                height += 170;
            } else if (number == 17) {
                weight = 660;
                height += 170;
            }
            it.draw(g, weight, height);
            number++;
        }
        pole.draw(g,x,y);
        g.dispose();
        bs.show();
    }
    public void update(long delta) {
        if (leftPressed) {
            x--;
        }
        if (rightPressed) {
            x++;
        }
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JFrame frame = new JFrame(Game.NAME);
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
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));
        return sprite;
    }
}
