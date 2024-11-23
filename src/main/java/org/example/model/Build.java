package org.example.model;

import org.example.controller.Buttons_Build;
import org.example.controller.List_Locations;
import org.example.controller.StringCatcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.example.model.Model.playerList;

class Build {
    List<Character> toRemove;
    List_Locations locations;
    int act(Player player) {                                                                            //строит поселение, город или дорожку при наступлении хода игрока
        StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", what do you wanna build?","Buttons_Build");
        Road r = new Road();
        Resources e = new Resources();
        String someBuilding = null;
        while (someBuilding == null || someBuilding.isEmpty()) someBuilding = (String) StringCatcher.getData("org.example.controller.Buttons_Build");
        Buttons_Build.messageToPass = null;
        int allRight = -1;
        switch (someBuilding) {
            case "Road" -> {
                if (player.roads > 0) {
                    toRemove = new ArrayList<>(List.of('b', 'w'));
                    if (new HashSet<>(player.cards).containsAll(toRemove)) {
                        allRight = r.main(player, true);
                    } else {
                        StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough resources", "Removal");
                    }
                } else StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough roads", "Removal");
                if (player.roads < Model.leastRoadsLeft) Model.leastRoadsLeft = player.roads;
            }
            case "Town" -> {
                if (player.towns > 0) {
                    if (new HashSet<>(player.cards).containsAll(List.of('b', 'w', 's', 'f'))) {
                        locations = new List_Locations("Choose row and column of your town:", true);
                        Integer column = null;
                        while (column == null) {
                            try {
                                column = Integer.parseInt(locations.selectedColumn) - 1;
                            } catch (Exception ignored) {
                            }
                        }
                        if (column == -1) return 0;
                        Integer row = null;
                        while (row == null) {
                            row = Integer.parseInt(locations.selectedRow) - 1;
                        }
                        if (row == -1) return 0;
                        for (FieldItem t : player.available)
                            if (Model.field[row][column] == Item.O && (((t.ro.equals(row)) && (t.col == column - 1 || t.col == column + 1)) ||
                                    ((row == t.ro + 1) && ((t.col * 4 + 2 == column && row <= 4) || (t.col * 4 == column && row >= 6))) ||
                                    ((row + 1 == t.ro) && ((t.col * 4 + 2 == column && row >= 6) || (t.col * 4 == column && row <= 6))))) {
                                player.available.add(new FieldItem(row, column, Item.T));
                                Model.field[row][column] = Item.T;
                                allRight = 1;
                                player.points += 1;
                                e.getResources(row, column, player);
                                StringCatcher.passGraphics(row, column, player.color, "town");
                                player.towns -= 1;
                                toRemove = new ArrayList<>(List.of('b', 'w', 's', 'f'));
                                player.towns -= 1;
                                break;
                            }
                    } else {
                        allRight = 1;
                        StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough resources", "Removal");
                    }
                } else {
                    StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough towns", "Removal");
                    allRight = 1;
                }
                if (allRight == -1) StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", wrong coordinates. Try one more time", "Removal");
            }
            case "City" -> {
                if (player.cities > 0) {
                    if (countOccurrences(player.cards, 's') >= 2 && countOccurrences(player.cards, 't') >= 3) {
                        locations = new List_Locations("Choose row and column of your city", true);
                        Integer columnC = null;
                        while (columnC == null) {
                            try {
                                columnC = Integer.parseInt(locations.selectedColumn) - 1;
                            } catch (Exception ignored) {
                            }
                        }
                        if (columnC == -1) return 0;
                        Integer rowC = null;
                        while (rowC == null) {
                            rowC = Integer.parseInt(locations.selectedRow) - 1;
                        }
                        if (rowC == -1) return 0;
                        for (FieldItem t : player.available)
                            if (t.item == Item.T && t.col.equals(columnC) && t.ro.equals(rowC)) {
                                player.available.add(new FieldItem(rowC, columnC, Item.C));
                                Model.field[rowC][columnC] = Item.C;
                                allRight = 1;
                                player.points += 1;
                                e.getResources(rowC, columnC, player);
                                StringCatcher.passGraphics(rowC, columnC, player.color, "city");
                                toRemove = new ArrayList<>(List.of('s', 's', 't', 't', 't'));
                                player.cities -= 1;
                                player.towns += 1;
                                break;
                            }
                    } else {
                        allRight = 1;
                        StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough resources", "Removal");
                    }
                } else {
                    StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough cities", "Removal");
                    allRight = 1;
                }
                if (allRight == -1) StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", wrong coordinates. Try one more time", "Removal");
            }
            case "Cancel" -> allRight = 1;
        }
        if (allRight == 1) {
            if (toRemove != null) for (Character c : toRemove) {
                player.cards.remove(c);
            }
            toRemove = null;
        }
        return 0;
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
