package org.example;

import org.example.Model.Colors;
import org.example.Model.Model;
import java.awt.*;
import java.util.*;

import static org.example.Game.*;

public class Pole {
    public static ArrayList<Sprite> hexes = new ArrayList<>();
    public static Sprite pole;
    public static Sprite background;
    public static Sprite road;
    public static Sprite town;
    public static Sprite city;
    public static Sprite f;
    public static Sprite s;
    public static Sprite t;
    public static Sprite a;
    public static Sprite w;
    public static Sprite b;
    public static ArrayList<Dib> dibs = new ArrayList<>();
    public void init(){
        Game q = new Game();
        pole = q.getSprite("pole.png");
        background = q.getSprite("background.png");
        road = q.getSprite("road.png");
        town = q.getSprite("town.png");
        city = q.getSprite("city.png");
        f = q.getSprite("field_hex.png");
        s = q.getSprite("straw_hex.png");
        t = q.getSprite("stone_hex.png");
        a = q.getSprite("sand_hex.png");
        w = q.getSprite("woods_hex.png");
        b = q.getSprite("brick_hex.png");
        for (Character character: Model.hexes) {
            switch (character) {
                case 's' -> hexes.add(s);
                case 'f' -> hexes.add(f);
                case 't' -> hexes.add(t);
                case 'b' -> hexes.add(b);
                case 'w' -> hexes.add(w);
            }
        }
        hexes.add(9, a);
    }
    public void perform(Integer x, Graphics g){
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
            it.draw(g, (int) (weight * percent), (int) (height * percent));
            number++;
        }
        pole.draw(g,x,0);
    }
    public void drawDib(Integer row, Integer column, Colors color, Graphics g, String item) {
        Sprite drawSprite;
        if (item.equals("town")) drawSprite = town;
        else if (item.equals("city")) drawSprite = city;
        else drawSprite = road;
        int x = 0;
        int y = 0;
        switch (row) {
            case 0 -> {
                x = 638;
                y = 45;
            }
            case 1 -> {
                x = 656;
                y = 150;
            }
            case 2 -> {
                x = 541;
                y = 215;
            }
            case 3 -> {
                x = 554;
                y = 320;
            }
            case 4 -> {
                x = 444;
                y = 385;
            }
            case 5 -> {
                x = 460;
                y = 485;
            }
            case 6 -> {
                x = 444;
                y = 550;
            }
            case 7 -> {
                x = 554;
                y = 580;
            }
            case 8 -> {
                x = 540;
                y = 715;
            }
            case 9 -> {
                x = 656;
                y = 832;
            }
            case 10 -> {
                x = 638;
                y = 878;
            }
        }
        if (row % 2 == 0) {
            if (column % 2 == 0 && row < 5) {
                if (column % 4 == 0) drawSprite.draw(g, percent(x + (97 * (column / 2))), percent(y + 56));
                else drawSprite.draw(g, percent(x + (97 * (column / 2))), percent(y));
            } else if (column % 2 == 0 && row > 5) {
                if (column % 4 == 0) drawSprite.draw(g, percent(x + (97 * (column / 2))), percent(y));
                else drawSprite.draw(g, percent(x + (97 * (column / 2))), percent(y + 56));
            } else if ((column % 4 == 1 && row < 5) || (column % 4 == 3 && row > 5)) {
                Graphics2D g2d = (Graphics2D) g;
                if (column % 4 == 1 && row < 5) x += 90 + (195 * (column / 4));
                else x += 45 + (195 * column / 4);
                g2d.rotate(Math.toRadians(60), percent(x), percent(y + 23));
                drawSprite.draw(g, percent(x), percent(y + 23));
                g2d.rotate(Math.toRadians(-60), percent(x), percent(y + 23));
            } else {
                Graphics2D g2d = (Graphics2D) g;
                if (column % 4 == 3 && row < 5) x += 130 + (195 * (column / 4));
                else x += (195 * column / 4);
                g2d.rotate(Math.toRadians(-60), percent(x), percent(y + 56));
                drawSprite.draw(g, percent(x), percent(y + 56));
                g2d.rotate(Math.toRadians(60), percent(x), percent(y + 56));
            }
        } else drawSprite.draw(g, percent(x + (194 * column)), percent(y));
    }
    private Color getColor(Colors color) {
        switch (color) {
            case Red -> {
                return Color.RED;
            }
            case Blue -> {
                return Color.BLUE;
            }
            case Gray -> {
                return Color.GRAY;
            }
            case Orange -> {
                return Color.ORANGE;
            }
        }
        return null;
    }
    Integer percent(Integer number) {
        return (int) (number * percent);
    }
}
