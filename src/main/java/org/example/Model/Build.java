package org.example.Model;

import org.example.Controller.Buttons_Build;
import org.example.Controller.List_Locations;
import org.example.Controller.StringCatcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Build {
    StringCatcher catcher = new StringCatcher();
    List<Character> toRemove;
    List_Locations locations;
    int act(Player player) {                                                                            //строит поселение, город или дорожку при наступлении хада игрока
        catcher.makeMessage("What do you wanna build?","Buttons_Build");
        Road r = new Road();
        Resources e = new Resources();
        String someBuilding = null;
        while (someBuilding == null || someBuilding.isEmpty()) someBuilding = (String) catcher.getData("org.example.Controller.Buttons_Build");
        Buttons_Build.messageToPass = null;
        boolean allRight = false;
        switch (someBuilding) {
            case "Road" -> {
                if (player.roads > 0) {
                    toRemove = new ArrayList<>(List.of('b', 'w'));
                    if (new HashSet<>(player.cards).containsAll(toRemove)) {
                        allRight = r.main(player);
                    } else {
                        allRight = true;
                        catcher.makeMessage("You don't have enough resources", "Removal");
                    }
                } else catcher.makeMessage("You don't have enough roads", "Removal");
                if (player.roads < Model.leastRoadsLeft) Model.leastRoadsLeft = player.roads;
            }
            case "Town" -> {
                if (player.towns > 0) {
                    if (new HashSet<>(player.cards).containsAll(List.of('b', 'w', 's', 'f'))) {
                        locations = new List_Locations("Choose row and column of your town:");
                        Integer column = null;
                        while (column == null) {
                            try {
                                column = Integer.parseInt(locations.selectedColumn) - 1;
                            } catch (Exception ignored) {
                            }
                        }
                        Integer row = null;
                        while (row == null) {
                            row = Integer.parseInt(locations.selectedRow) - 1;
                        }
                            for (FieldItem t : player.available)
                                if (Model.field[row][column] == Item.O && (((t.ro.equals(row)) && (t.col == column - 1 || t.col == column + 1)) ||
                                        (((row == t.ro + 1) || (row == t.ro - 1)) && (t.ro == 4 || t.ro == 6 || (t.ro < 4 && row > t.ro) || (t.ro > 6 && row < t.ro)) && t.col == column * 4) ||
                                        (((row == t.ro + 1) || (row == t.ro - 1)) && ((t.ro < 4 && row < t.ro) || (t.ro > 6 && row > t.ro)) && t.col == column * 4 + 2))) {
                                    player.available.add(new FieldItem(row, column, Item.T));
                                    Model.field[row][column] = Item.T;
                                    allRight = true;
                                    player.points += 1;
                                    e.getResources(row, column, player);
                                    catcher.passGraphics(row, column, player.color, "town");
                                    player.roads -= 1;
                                    toRemove = new ArrayList<>(List.of('b', 'w', 's', 'f'));
                                    player.towns -= 1;
                                    break;
                                }
                    } else {
                        allRight = true;
                        catcher.makeMessage("You don't have enough resources", "Removal");
                    }
                } else {
                    catcher.makeMessage("You don't have enough towns", "Removal");
                    allRight = true;
                }
                if (!allRight) catcher.makeMessage("Wrong coordinates. Try one more time", "Removal");
            }
            case "City" -> {
                if (player.cities > 0) {
                    if (countOccurrences(player.cards, 's') >= 2 && countOccurrences(player.cards, 't') >= 3) {
                        locations = new List_Locations("Choose the location of city");
                        Integer columnC = null;
                        while (columnC == null) {
                            try {
                                columnC = Integer.parseInt(locations.selectedColumn) - 1;
                            } catch (Exception ignored) {
                            }
                        }
                        Integer rowC = null;
                        while (rowC == null) {
                            rowC = Integer.parseInt(locations.selectedRow) - 1;
                        }
                        FieldItem toDelete = null;
                            for (FieldItem t : player.available)
                                if (Model.field[rowC][columnC] == Item.T) {
                                    player.available.add(t = new FieldItem(rowC, columnC, Item.C));
                                    toDelete = t;
                                    Model.field[rowC][columnC] = Item.C;
                                    allRight = true;
                                    player.points += 1;
                                    e.getResources(rowC, columnC, player);
                                    catcher.passGraphics(rowC, columnC, player.color, "city");
                                    toRemove = new ArrayList<>(List.of('s', 's', 't', 't', 't'));
                                    player.cities -= 1;
                                    player.towns += 1;
                                    break;
                                }
                            player.available.remove(toDelete);
                    } else {
                        allRight = true;
                        catcher.makeMessage("You don't have enough resources", "Removal");
                    }
                } else {
                    catcher.makeMessage("You don't have enough cities", "Removal");
                    allRight = true;
                }
                if (!allRight) catcher.makeMessage("Wrong coordinates. Try one more time", "Removal");
            }
        }
        if (!allRight) return 1;
        else {
            for (Character c : toRemove) {
                player.cards.remove(c);
            }
            return 0;
        }
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
