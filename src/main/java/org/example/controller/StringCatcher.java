package org.example.controller;

import org.example.Dib;
import org.example.model.Colors;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static org.example.Game.percent;
import static org.example.Pole.dibs;

public class StringCatcher {
    static JLabel label;
    public static void makeMessage(String string, String className){
        string = ("<html><div style='text-align: center;'>" + string + "<br>");
        label = new JLabel(string);
        label.setFont(new Font("Arial", Font.PLAIN, (int) (42 * percent)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        Buttons.init(label,className);
        label = null;
    }
    public static Object getData(String className){
        try {
            Class<?> clazz = Class.forName(className);
            Field field = clazz.getField("messageToPass");
            return field.get(null);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void passGraphics(Integer rowR, Integer columnR, Colors color, String item) {
        if (item.equals("city")) {
            dibs.removeIf(dib -> dib.getRow().equals(rowR) && dib.getColumn().equals(columnR) && dib.getType().equals("town"));
        }
        Dib d = new Dib(rowR, columnR, color, item);
        dibs.add(d);
    }
}
