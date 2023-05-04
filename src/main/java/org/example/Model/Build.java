package org.example.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.lang.System.out;

class Build {
    int main(Player player) {                                                                            //строит поселение, город или дорожку при наступлении хада игрока
        out.println("Что вы хотите построить?\nЕсли дорожку - введите R\nпоселение - t\nгород - c\n");
        String someBuilding = Model.in.next();
        boolean allRight = false;
        switch (someBuilding) {
            case "R" -> {
                if (player.roads > 0) {
                    if (new HashSet<>(player.cards).containsAll(List.of('b', 'w'))) {
                        allRight = Road.main(player);
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
                        int row = Model.isNumber() - 1;
                        int column = Model.isNumber() - 1;
                        if (CoordinatesCheck.main(row, column)) {
                            for (FieldItem t : player.available)
                                if (Model.field[row][column] == Item.E && (((t.ro == row) && (t.col == column - 1 || t.col == column + 1)) ||
                                        (((row == t.ro + 1) || (row == t.ro - 1)) && (t.ro == 4 || t.ro == 6 || (t.ro < 4 && row > t.ro) || (t.ro > 6 && row < t.ro)) && t.col == column * 4) ||
                                        (((row == t.ro + 1) || (row == t.ro - 1)) && ((t.ro < 4 && row < t.ro) || (t.ro > 6 && row > t.ro)) && t.col == column * 4 + 2))) {
                                    player.available.add(new FieldItem(row, column, Item.T));
                                    Model.field[row][column] = Item.T;
                                    allRight = true;
                                    player.points += 1;
                                    Resources.getResources(row, column, player);
                                    out.println("Поселение построено!");
                                    List<Character> toRemove = new ArrayList<>(List.of('b', 'w', 's', 'f'));
                                    for (Character c : toRemove) {
                                        player.cards.remove(c);
                                    }
                                    break;
                                }
                        } else out.println("Неверные координаты. Попробуйте еще раз");
                    } else {
                        allRight = true;
                        out.println("У вас недостаточно ресурсов");
                    }
                } else out.println("У вас закончились поселения");
            }
            case "c" -> {
                if (player.cities > 0) {
                    if (new HashSet<>(player.cards).containsAll(List.of('s', 's', 't', 't', 't'))) {
                        out.println("Введите расположение города на месте поселения ");
                        int rowC = Model.isNumber() - 1;
                        int columnC = Model.isNumber() - 1;
                        FieldItem toDelete = null;
                        if (CoordinatesCheck.main(rowC, columnC)) {
                            for (FieldItem t : player.available)
                                if (Model.field[rowC][columnC] == Item.T) {
                                    player.available.add(t = new FieldItem(rowC, columnC, Item.C));
                                    toDelete = t;
                                    Model.field[rowC][columnC] = Item.C;
                                    allRight = true;
                                    player.points += 1;
                                    Resources.getResources(rowC, columnC, player);
                                    out.println("Город построен!");
                                    List<Character> toRemove = new ArrayList<>(List.of('s', 's', 't', 't', 't'));
                                    for (Character c : toRemove) {
                                        player.cards.remove(c);
                                    }
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
                } else out.println("У вас закончились дорожки");
            }
        }
        if (!allRight) return 1;
        else return 0;
    }
}
