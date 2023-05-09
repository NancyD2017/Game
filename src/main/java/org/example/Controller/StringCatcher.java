package org.example.Controller;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

import static org.example.Game.percent;

public class StringCatcher {
    JLabel label;
    public void makeMessage(String string, String className){
        string = ("<html><div style='text-align: center;'>" + string + "<br>");
        label = new JLabel(string);
        label.setFont(new Font("Arial", Font.PLAIN, (int) (42 * percent)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        Buttons.init(label,className);
        label = null;
    }
    public Object getData(String className){
        try {
            Class<?> clazz = Class.forName(className);
            Field field = clazz.getField("messageToPass");
            return field.get(null);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showData(String string, List<Character> data) {
        string = ("<html><div style='text-align: center;'>" + string + "<br>");
        label = new JLabel(string);
        label.setFont(new Font("Arial", Font.PLAIN, (int) (42 * percent)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        Buttons.dataToShow = data;
        Buttons.init(label,"Resources");
        label = null;
    }
}
