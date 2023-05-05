package org.example.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.lang.System.out;

class Build {
    int act(Player player, ArrayList<Character> hexes) {                                                                            //строит поселение, город или дорожку при наступлении хада игрока
        out.println("Что вы хотите построить?\nЕсли дорожку - введите R\nпоселение - t\nгород - c\n");
        Model m = new Model();
        Road r = new Road();
        Resources e = new Resources();
        CoordinatesCheck o = new CoordinatesCheck();
        String someBuilding = m.in.next();
        boolean allRight = false;
        switch (someBuilding) {
            case "R" -> {
                if (player.roads > 0) {
                    List<Character> toRemove = new ArrayList<>(List.of('b', 'w'));
                    if (new HashSet<>(player.cards).containsAll(toRemove)) {
                        allRight = r.main(player);
                        for (Character c : toRemove) {
                            player.cards.remove(c);
                        }
                    } else {
                        allRight = true;
                        out.println("У вас недостаточно ресурсов");
                    }
                } else out.println("У вас закончились дорожки");
                if (player.roads < Model.leastRoadsLeft) Model.leastRoadsLeft = player.roads;
            }
            case "t" -> {
                if (player.towns > 0) {
                    if (new HashSet<>(player.cards).containsAll(List.of('b', 'w', 's', 'f'))) {
                        out.println("Введите расположение поселения около дорожек ");
                        int row = m.isNumber() - 1;
                        int column = m.isNumber() - 1;
                        if (o.act(row, column)) {
                            for (FieldItem t : player.available)
                                if (Model.field[row][column] == Item.O && (((t.ro == row) && (t.col == column - 1 || t.col == column + 1)) ||
                                        (((row == t.ro + 1) || (row == t.ro - 1)) && (t.ro == 4 || t.ro == 6 || (t.ro < 4 && row > t.ro) || (t.ro > 6 && row < t.ro)) && t.col == column * 4) ||
                                        (((row == t.ro + 1) || (row == t.ro - 1)) && ((t.ro < 4 && row < t.ro) || (t.ro > 6 && row > t.ro)) && t.col == column * 4 + 2))) {
                                    player.available.add(new FieldItem(row, column, Item.T));
                                    Model.field[row][column] = Item.T;
                                    allRight = true;
                                    player.points += 1;
                                    e.getResources(row, column, player);
                                    out.println("Поселение построено!");
                                    player.roads -= 1;
                                    List<Character> toRemove = new ArrayList<>(List.of('b', 'w', 's', 'f'));
                                    for (Character c : toRemove) {
                                        player.cards.remove(c);
                                    }
                                    player.towns -= 1;
                                    break;
                                }
                        }
                    } else {
                        allRight = true;
                        out.println("У вас недостаточно ресурсов");
                    }
                } else {
                    out.println("У вас закончились поселения");
                    allRight = true;
                }
                if (!allRight) out.println("Неверные координаты. Попробуйте еще раз");
            }
            case "c" -> {
                if (player.cities > 0) {
                    if (countOccurrences(player.cards, 's') >= 2 && countOccurrences(player.cards, 't') >= 3) {
                        out.println("Введите расположение города на месте поселения ");
                        int rowC = m.isNumber() - 1;
                        int columnC = m.isNumber() - 1;
                        FieldItem toDelete = null;
                        if (o.act(rowC, columnC)) {
                            for (FieldItem t : player.available)
                                if (Model.field[rowC][columnC] == Item.T) {
                                    player.available.add(t = new FieldItem(rowC, columnC, Item.C));
                                    toDelete = t;
                                    Model.field[rowC][columnC] = Item.C;
                                    allRight = true;
                                    player.points += 1;
                                    e.getResources(rowC, columnC, player);
                                    out.println("Город построен!");
                                    List<Character> toRemove = new ArrayList<>(List.of('s', 's', 't', 't', 't'));
                                    for (Character c : toRemove) {
                                        player.cards.remove(c);
                                    }
                                    player.cities -= 1;
                                    player.towns += 1;
                                    break;
                                }
                            player.available.remove(toDelete);
                        } else {
                            out.println("Попробуйте еще раз");
                            return (1);
                        }
                    } else {
                        allRight = true;
                        out.println("У вас недостаточно ресурсов");
                    }
                } else {
                    out.println("У вас закончились дорожки");
                    allRight = true;
                }
                if (!allRight) out.println("Неверные координаты. Попробуйте еще раз");
            }
            default -> out.println("Невозможно построить " + someBuilding + ". Попробуйте еще раз");
        }
        if (!allRight) return 1;
        else return 0;
    }
    public int countOccurrences(List<Character> cards, char ch) {
        int count = 0;
        for (char c : cards) {
            if (c == ch) {
                count++;
            }
        }
        return count;
    }
}
