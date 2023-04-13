package org.example;

import java.awt.*;
import java.util.List;
import java.util.*;

import static org.example.Game.*;

public class Pole {
    public static ArrayList<Sprite> hexes = new ArrayList<>();
    public static Sprite pole;
    public static Sprite background;
    public static Sprite f;
    public static Sprite s;
    public static Sprite t;
    public static Sprite a;
    public static Sprite w;
    public static Sprite b;
    public static void init(){
        Game q = new Game();
        pole = q.getSprite("pole.png");
        background = q.getSprite("background.png");
        f = q.getSprite("field_hex.png");
        s = q.getSprite("straw_hex.png");
        t = q.getSprite("stone_hex.png");
        a = q.getSprite("sand_hex.png");
        w = q.getSprite("woods_hex.png");
        b = q.getSprite("brick_hex.png");
        hexes.addAll(Arrays.asList(f,f,f,f,s,s,s,s,t,t,t,w,w,w,w,b,b,b));
        List<Integer> values = List.of(5,2,6,10,9,4,3,8,11,0,5,8,4,3,6,10,11,12,9);
        Collections.shuffle(hexes);
        hexes.add(9, a);
        Map<Integer, Sprite> description = new HashMap<>();
        for (int i = 0; i < hexes.size() - 1; i++) {
            description.put(values.get(i), hexes.get(i));
        }
    }
    public static void perform(Integer x, Graphics g){
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
}
