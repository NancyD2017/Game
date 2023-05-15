package org.example;

import org.example.model.Colors;
import org.example.model.Model;
import org.example.model.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.example.Game.*;
import static org.example.model.Model.playerList;

public class Pole {
    ArrayList<Sprite> hexes = new ArrayList<>();
    Sprite pole;
    Sprite background;
    Sprite road_r;
    Sprite town_r;
    Sprite city_r;
    Sprite road_b;
    Sprite town_b;
    Sprite city_b;
    Sprite road_g;
    Sprite town_g;
    Sprite city_g;
    Sprite road_o;
    Sprite town_o;
    Sprite city_o;
    Sprite f;
    Sprite s;
    Sprite t;
    Sprite a;
    Sprite w;
    Sprite b;
    Sprite fc;
    Sprite sc;
    Sprite tc;
    Sprite wc;
    Sprite bc;
    Sprite price;
    public static ArrayList<Dib> dibs = new ArrayList<>();
    public void init(){
        Game q = new Game();
        pole = q.getSprite("pole.png");
        background = q.getSprite("background.png");
        road_r = q.getSprite("road_r.png");
        town_r = q.getSprite("town_r.png");
        city_r = q.getSprite("city_r.png");
        road_b = q.getSprite("road_b.png");
        town_b = q.getSprite("town_b.png");
        city_b = q.getSprite("city_b.png");
        road_g = q.getSprite("road_g.png");
        town_g = q.getSprite("town_g.png");
        city_g = q.getSprite("city_g.png");
        road_o = q.getSprite("road_o.png");
        town_o = q.getSprite("town_o.png");
        city_o = q.getSprite("city_o.png");
        price = q.getSprite("price.png");
        f = q.getSprite("field_hex.png");
        s = q.getSprite("straw_hex.png");
        t = q.getSprite("stone_hex.png");
        a = q.getSprite("sand_hex.png");
        w = q.getSprite("woods_hex.png");
        b = q.getSprite("brick_hex.png");
        fc = q.getSprite("f_card.png");
        sc = q.getSprite("s_card.png");
        tc = q.getSprite("t_card.png");
        wc = q.getSprite("w_card.png");
        bc = q.getSprite("b_card.png");
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
        price.draw(g,0, (int) (365 * percent));
    }
    public void drawDib(Integer row, Integer column, Colors color, Graphics g, String item) {
        Sprite drawSprite = null;
        if (item.equals("town")) {
            switch (color) {
                case Orange ->  drawSprite = town_o;
                case Blue ->  drawSprite = town_b;
                case Red ->  drawSprite = town_r;
                case Gray ->  drawSprite = town_g;
            }
        }
        else if (item.equals("city")) {
            switch (color) {
                case Orange ->  drawSprite = city_o;
                case Blue->  drawSprite = city_b;
                case Red ->  drawSprite = city_r;
                case Gray ->  drawSprite = city_g;
            }
        }
        else {
            switch (color) {
                case Orange ->  drawSprite = road_o;
                case Blue ->  drawSprite = road_b;
                case Red ->  drawSprite = road_r;
                case Gray ->  drawSprite = road_g;
            }
        }
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
                y = 655;
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
    public void drawCard(Player player, List<Character> cards, Graphics g) {
        int number = playerList.indexOf(player);
        int x = 0;
        int y = 0;
        Sprite drawSprite = null;
        switch (number) {
            case 1 -> x = 1382;
            case 2 -> y = 786;
            case 3 -> {
                x = 1382;
                y = 786;
            }
        }
        for (Character card: cards) {
            switch (card) {
                case 's' -> drawSprite = sc;
                case 'f' -> drawSprite = fc;
                case 't' -> drawSprite = tc;
                case 'w' -> drawSprite = wc;
                case 'b' -> drawSprite = bc;
            }
            drawSprite.draw(g, percent(x), percent(y));
            x += ((percent(373) / cards.size()));
        }
    }
    Integer percent(Integer number) {
        return (int) (number * percent);
    }
}
