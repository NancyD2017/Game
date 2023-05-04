package org.example.Model;

import java.util.HashSet;
import java.util.Set;

class Road {
    boolean main(Player player) {
        boolean allRight = false;
        System.out.println("Введите расположение дорожки около города, поселения или другой дорожки ");
        int rowR = Model.isNumber() - 1;
        int columnR = Model.isNumber() - 1;
        Set<FieldItem> toAdd = new HashSet<>();
        Set<FieldItem> playersArsenal = player.available;
        if (CoordinatesCheck.main(rowR, columnR)) {
            for (FieldItem t : playersArsenal) {
                if ((Model.field[rowR][columnR] == Item.P) && (((t.item == Item.R) && (((t.col * 4 + 1 == columnR) && (t.ro <= 5) && (t.ro == rowR + 1)) ||
                        ((t.col * 4 + 1 == columnR) && (t.ro >= 5) && (t.ro + 1 == rowR)) || ((t.col * 4 - 1 == columnR) && (t.ro <= 5) && (t.ro == 1 + rowR)) ||
                        ((t.col * 4 - 1 == columnR) && (t.ro >= 5) && (t.ro + 1 == rowR)) || ((t.col * 4 + 3 == columnR) && (t.ro <= 3) && (t.ro + 1 == rowR)) ||
                        ((t.col * 4 + 3 == columnR) && (t.ro >= 7) && (t.ro == rowR + 1)) || ((t.col * 4 + 1 == columnR) && (t.ro >= 5) && (t.ro == rowR + 1)) ||
                        ((t.col == columnR + 2 && t.ro == rowR) || (t.col == columnR - 2 && t.ro == rowR) && rowR % 2 == 0) ||
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
                    PossiblePorts.main(rowR, columnR, player);
                    allRight = true;
                    System.out.println("Дорожка построена!");
                }
            } if (!allRight){
                System.out.println("Неправильные координаты дорожки. Попробуйте еще раз");
                return false;
            }
        } else System.out.println("Попробуйте еще раз");
        player.available.addAll(toAdd);
        return allRight;
    }
}

