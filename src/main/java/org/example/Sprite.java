package org.example;

import java.awt.*;

import static org.example.Game.percent;

public class Sprite {
    private Image image;
    public Sprite(Image image) {
        this.image=image;
    }
    public int getWidth(){
        return image.getWidth(null);
    }
    public int getHeight(){
        return image.getHeight(null);
    }
    public void draw(Graphics g, int x, int y){
        g.drawImage(image.getScaledInstance((int) (getWidth() * percent), (int) (getHeight() * percent),0),x,y,null);
    }
}
