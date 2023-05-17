package org.example.model;
import org.example.controller.List_Locations;
import org.example.controller.StringCatcher;

import java.util.List;

import static org.example.model.Model.playerList;

class FirstBuilding {
    public static String addText = "";
    int main(Integer player, Colors mcolor)  {                             //осуществляет постройку первого поселения и дорожки игрока
        Resources e = new Resources();
        PossiblePorts p = new PossiblePorts();
        List_Locations locations;
        locations = new List_Locations("<html><div style='text-align: center;'>" + addText + "Choose the location of town");
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
            if (Model.field[row][column] == Item.O) {
                playerList.get(player).available.add(new FieldItem(row, column, Item.T));
                StringCatcher.passGraphics(row, column, mcolor, "town");
                playerList.get(player).color = mcolor;
                Model.field[row][column] = Item.T;
                for (int j = 0; j < 1; j++) {
                    locations = new List_Locations("choose location of road near town    ");
                    Integer columnR = null;
                    while (columnR == null) {
                        try {
                            columnR = Integer.parseInt(locations.selectedColumn) - 1;
                        } catch (Exception ignored) {
                        }
                    }
                    Integer rowR = null;
                    while (rowR == null) {
                        rowR = Integer.parseInt(locations.selectedRow) - 1;
                    }
                        if (Model.field[rowR][columnR] == Item.r && ((rowR.equals(row) && ((columnR == column - 1) || (columnR == column + 1))) ||
                                ((((row == 6 || row == 4) && rowR == 5) || (row < 4 && rowR == 1 + row) || (row > 6 && rowR + 1 == row)) && columnR * 4 == column) ||
                                (row <= 4 && columnR * 4 + 2 == column && rowR + 1 == row) || (row >= 6 && columnR * 4 + 2 == column && rowR == 1 + row))) {
                            playerList.get(player).available.add(new FieldItem(rowR, columnR, Item.R));
                            Model.field[rowR][columnR] = Item.R;
                            p.act(rowR, columnR, playerList.get(player));
                            Model.colors.remove(mcolor);
                            StringCatcher.passGraphics(rowR, columnR, mcolor, "road");
                        } else {
                            StringCatcher.makeMessage("Player " + (player + 1) + ", wrong coordinates of the road<br>Try one more time","");
                            j -= 1;
                        }
                }
            } else {
                StringCatcher.makeMessage("Player " + (player + 1) + ", try one more time","");
                return 1;
            }
        playerList.get(player).points += 1;
        playerList.get(player).roads -= 1;
        playerList.get(player).towns -= 1;
        e.getResources(row, column, playerList.get(player));
        if (playerList.get(player).cards.isEmpty()) for (List<Character> list : playerList.get(player).element.values()) {
            playerList.get(player).cards.addAll(list);
        }
        return 0;
    }
}
