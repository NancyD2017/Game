package org.example.Model;

import java.util.ArrayList;

import static org.example.Model.Model.hexes;

class Resources {
    void getResources(Integer row, Integer column, Player player) {                                        //правила для добавления чисел на поля (то есть, какой ресурс получает игрок в зависимости от номера на кубиках)
        switch (row) {
            case 0 -> {
                switch (column) {
                    case 0, 2 -> addResource(player, 5, 0);
                    case 4 -> {
                        addResource(player, 5, 0);
                        addResource(player, 2, 1);
                    }
                    case 6 -> addResource(player, 2, 1);
                    case 8 -> {
                        addResource(player, 2, 1);
                        addResource(player, 6, 2);
                    }
                    case 10, 12 -> addResource(player, 6, 2);
                }
            }
            case 2 -> {
                switch (column) {
                    case 0 -> addResource(player, 10, 3);
                    case 2 -> {
                        addResource(player, 5, 0);
                        addResource(player, 10, 3);
                    }
                    case 4 -> {
                        addResource(player, 10, 3);
                        addResource(player, 5, 0);
                        addResource(player, 9, 4);
                    }
                    case 6 -> {
                        addResource(player,5,0);
                        addResource(player, 2, 1);
                        addResource(player, 9, 4);
                    }
                    case 8 -> {
                        addResource(player, 2, 1);
                        addResource(player,4, 3);
                        addResource(player, 9, 4);
                    }
                    case 10 -> {
                        addResource(player, 2, 1);
                        addResource(player, 4, 3);
                        addResource(player, 6, 2);
                    }
                    case 12 -> {
                        addResource(player, 4, 3);
                        addResource(player, 6, 2);
                        addResource(player, 3, 6);
                    }
                    case 14 -> {
                        addResource(player, 6, 2);
                        addResource(player, 3, 6);
                    }
                    case 16 -> addResource(player, 3, 6);
                }
            }
            case 4 -> {
                switch (column) {
                    case 0 -> addResource(player,8,7);
                    case 2 -> {
                        addResource(player, 8, 7);
                        addResource(player, 10, 3);
                    }
                    case 4 -> {
                        addResource(player, 8, 7);
                        addResource(player, 10, 3);
                        addResource(player, 11, 8);
                    }
                    case 6 -> {
                        addResource(player, 10, 3);
                        addResource(player, 9, 4);
                        addResource(player, 11, 8);
                    }
                    case 8 -> {
                        addResource(player, 9, 4);
                        addResource(player, 11, 8);
                    }
                    case 10 -> {
                        addResource(player, 9, 4);
                        addResource(player, 4, 5);
                    }
                    case 12 -> {
                        addResource(player, 4, 5);
                        addResource(player, 5, 9);
                    }
                    case 14 -> {
                        addResource(player, 4, 5);
                        addResource(player, 3, 6);
                        addResource(player, 5, 9);
                    }
                    case 16 -> {
                        addResource(player, 3, 6);
                        addResource(player, 5, 9);
                        addResource(player, 8, 10);
                    }
                    case 18 -> {
                        addResource(player, 3, 6);
                        addResource(player, 8, 10);
                    }
                    case 20 -> addResource(player, 8, 10);
                }
            }
            case 6 -> {
                switch (column) {
                    case 0 -> addResource(player,8,7);
                    case 2 -> {
                        addResource(player, 8, 7);
                        addResource(player, 4, 11);
                    }
                    case 4 -> {
                        addResource(player, 8, 7);
                        addResource(player, 4, 11);
                        addResource(player, 11, 8);
                    }
                    case 6 -> {
                        addResource(player, 3, 12);
                        addResource(player, 4, 11);
                        addResource(player, 11, 8);
                    }
                    case 8 -> {
                        addResource(player, 3, 12);
                        addResource(player, 11, 8);
                    }
                    case 10 -> {
                        addResource(player, 3, 12);
                        addResource(player, 6, 13);
                    }
                    case 12 -> {
                        addResource(player, 6, 13);
                        addResource(player, 5, 9);
                    }
                    case 14 -> {
                        addResource(player, 10, 14);
                        addResource(player, 6, 13);
                        addResource(player, 5, 9);
                    }
                    case 16 -> {
                        addResource(player, 10, 14);
                        addResource(player, 5, 9);
                        addResource(player, 8, 10);
                    }
                    case 18 -> {
                        addResource(player, 10, 14);
                        addResource(player, 8, 10);
                    }
                    case 20 -> addResource(player, 8, 10);
                }
            }
            case 8 -> {
                switch (column) {
                    case 0 -> addResource(player,4,11);
                    case 2 -> {
                        addResource(player, 11, 15);
                        addResource(player, 4, 11);
                    }
                    case 4 -> {
                        addResource(player, 3, 12);
                        addResource(player, 4, 11);
                        addResource(player, 11, 15);
                    }
                    case 6 -> {
                        addResource(player, 3, 12);
                        addResource(player, 12, 15);
                        addResource(player, 11, 16);
                    }
                    case 8 -> {
                        addResource(player, 3, 12);
                        addResource(player, 6, 13);
                        addResource(player, 12, 16);
                    }
                    case 10 -> {
                        addResource(player, 6, 13);
                        addResource(player, 12, 16);
                        addResource(player, 9, 17);
                    }
                    case 12 -> {
                        addResource(player, 6, 13);
                        addResource(player, 10, 14);
                        addResource(player, 9, 17);
                    }
                    case 14 -> {
                        addResource(player, 10, 14);
                        addResource(player, 9, 17);
                    }
                    case 16 -> addResource(player, 10, 14);
                }
            }
            case 10 -> {
                switch (column) {
                    case 0, 2 -> addResource(player,11,15);
                    case 4 -> {
                        addResource(player, 11, 15);
                        addResource(player, 12, 16);
                    }
                    case 6 -> addResource(player, 12, 16);
                    case 8 -> {
                        addResource(player, 12, 16);
                        addResource(player, 9, 17);
                    }
                    case 10, 12 -> addResource(player, 9, 17);
                }
            }
        }
    }
    static void addResource(Player player, int element, int hexIndex) {
        player.element.put(element, new ArrayList<>());
        player.element.get(element).add(hexes.get(hexIndex));
    }
}
