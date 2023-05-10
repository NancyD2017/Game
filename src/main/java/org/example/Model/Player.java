package org.example.Model;

import java.util.*;

public class Player {
    Colors color;
    Set<FieldItem> available = new HashSet<>();                                                                        //показывает расположение фигурок этого игрока в формате: ряд, столбец, название
    public List<Character> cards = new ArrayList<>();                                                                      //карточки ресурсов для каждого игрока, с помощью которых можно что-то купить, обменять
    Integer points = 0;
    public HashSet<String> ports = new HashSet<>();                                                                         //показывает порты игрока - это реализует обмен с портом
    HashMap<Integer, List<Character>> element = new HashMap<>();                                                    //показывает, что получает игрок при конкретном броске кубика
    int roads = 15;
    int towns = 5;
    int cities = 4;
    int knights = 0;
}
