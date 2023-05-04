package org.example.Model;

import org.example.Sprite;

import java.util.*;

class Player {
    Color color;
    Set<FieldItem> available = new HashSet<>();                                                                        //показывает расположение фигурок этого игрока в формате: ряд, столбец, название
    List<Sprite> cards = new ArrayList<>();                                                                      //карточки ресурсов для каждого игрока, с помощью которых можно что-то купить, обменять
    Integer points = 0;
    HashSet<String> ports = new HashSet<>();                                                                         //показывает порты игрока - это реализует обмен с портом
    HashMap<Integer, List<Sprite>> element = new HashMap<>();                                                    //показывает, что получает игрок при конкретном броске кубика
    int roads = 15;
    int towns = 5;
    int cities = 4;
    int knights = 0;
}
