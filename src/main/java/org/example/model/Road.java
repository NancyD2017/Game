package org.example.model;

import org.example.controller.List_Locations;
import org.example.controller.StringCatcher;

import java.util.ArrayList;
import java.util.List;

import static org.example.model.Model.playerList;

class Road {
    int main(Player player, boolean possibleToCancel) {
        PossiblePorts p = new PossiblePorts();
        boolean allRight = false;
        List_Locations locations = new List_Locations("Choose row and column of your road:", possibleToCancel);
        Integer columnR = null;
        while (columnR == null) {
            try {
                columnR = Integer.parseInt(locations.selectedColumn) - 1;
            } catch (Exception ignored) {
            }
        }
        if (columnR == -1) return 0;
        Integer rowR = null;
        while (rowR == null) {
            rowR = Integer.parseInt(locations.selectedRow) - 1;
        }
        if (rowR == -1) return 0;
        List<FieldItem> toAdd = new ArrayList<>();
        List<FieldItem> playersArsenal = player.available;
            for (FieldItem t : playersArsenal) {
                if ((Model.field[rowR][columnR] == Item.r) && (((t.item == Item.R) && (((t.col * 4 + 1 == columnR) && (t.ro <= 5) && (t.ro == rowR + 1)) ||
                        ((t.col * 4 + 1 == columnR) && (t.ro >= 5) && (t.ro + 1 == rowR)) || ((t.col * 4 - 1 == columnR) && (t.ro <= 5) && (t.ro == 1 + rowR)) ||
                        ((t.col * 4 - 1 == columnR) && (t.ro >= 5) && (t.ro + 1 == rowR)) || ((t.col * 4 + 3 == columnR) && (t.ro <= 3) && (t.ro + 1 == rowR)) ||
                        ((t.col * 4 + 3 == columnR) && (t.ro >= 7) && (t.ro == rowR + 1)) || ((t.col * 4 + 1 == columnR) && (t.ro >= 5) && (t.ro == rowR + 1)) ||
                        ((t.col == columnR + 2 && t.ro.equals(rowR)) || (t.col == columnR - 2 && t.ro.equals(rowR)) && rowR % 2 == 0) ||
                        ((columnR * 4 + 1 == t.col) && (rowR <= 5) && (rowR == t.ro + 1)) || ((t.col * 4 + 1 == columnR) && (t.ro <= 5) && (t.ro + 1 == rowR)) ||
                        ((columnR * 4 + 1 == t.col) && (rowR >= 5) && (rowR + 1 == t.ro)) || ((columnR * 4 - 1 == t.col) && (rowR <= 5) && (rowR == 1 + t.ro)) ||
                        ((columnR * 4 - 1 == t.col) && (rowR >= 5) && (rowR + 1 == t.ro)) || ((columnR * 4 + 3 == t.col) && (rowR <= 3) && (rowR + 1 == t.ro)) ||
                        ((columnR * 4 + 3 == t.col) && (rowR >= 7) && (rowR == t.ro + 1)) || ((columnR * 4 + 1 == t.col) && (rowR <= 5) && (rowR + 1 == t.ro)) ||
                        ((columnR * 4 + 1 == t.col) && (rowR >= 5) && (rowR == t.ro + 1)))) ||
                        ((t.item == Item.T || t.item == Item.C) && ((rowR == t.ro && ((columnR == t.col - 1) || (columnR == t.col + 1))) ||
                                ((((t.ro == 6 || t.ro == 4) && rowR == 5) || (t.ro < 4 && rowR == 1 + t.ro) || (t.ro > 6 && rowR + 1 == t.ro)) && columnR * 4 == t.col) ||
                                (t.ro <= 4 && columnR * 4 + 2 == t.col && rowR + 1 == t.ro) || (t.ro >= 6 && columnR * 4 + 2 == t.col && rowR == 1 + t.ro))))) {
                    toAdd.add(new FieldItem(rowR, columnR, Item.R));
                    Model.field[rowR][columnR] = Item.R;
                    p.act(rowR, columnR, player);
                    player.roads -= 1;
                    allRight = true;
                    StringCatcher.passGraphics(rowR, columnR, player.color, "road");
                }
            } if (!allRight){
                StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", wrong coordinates of the road<br>Try one more time","");
                return -1;
            }
        player.available.addAll(toAdd);
        return 1;
    }
}

