package org.example.Model;

import java.util.List;

import static java.lang.System.out;

class FirstBuilding {
    int main(Player player, Color mcolor)  {                             //осуществляет постройку первого поселения и дорожки игрока
        out.println("Выберите положение поселения на поле. посмотрите на выведенное поле выше и выберите ряд (от 1 до 11) и столбец (от 1 до 4 или больше, в зависимости от ряда)");
        int row = Model.isNumber() - 1;
        int column = Model.isNumber() - 1;
        if (CoordinatesCheck.main(row, column)) {
            if (Model.field[row][column] == Item.E) {
                player.available.add(new FieldItem(row, column, Item.T));
                Model.field[row][column] = Item.T;
                for (int j = 0; j < 1; j++) {
                    out.println("и расположение дорожки около него ");
                    int rowR = Model.isNumber() - 1;
                    int columnR = Model.isNumber() - 1;
                    if (CoordinatesCheck.main(rowR, columnR)) {
                        if ((Model.field[rowR][columnR] == Item.P && ((rowR == row && ((columnR == column - 1) || (columnR == column + 1))) ||
                                ((((row == 6 || row == 4) && rowR == 5) || (row < 4 && rowR == 1 + row) || (row > 6 && rowR + 1 == row)) && columnR * 4 == column) ||
                                (row <= 4 && columnR * 4 + 2 == column && rowR + 1 == row) || (row >= 6 && columnR * 4 + 2 == column && rowR == 1 + row))) && CoordinatesCheck.main(row, column)) {
                            player.available.add(new FieldItem(rowR, columnR, Item.R));
                            Model.field[rowR][columnR] = Item.R;
                            PossiblePorts.main(rowR, columnR, player);
                            Model.colors.remove(mcolor);
                        } else {
                            out.println("Попробуйте еще раз");
                            j -= 1;
                        }
                    } else {
                        out.println("Попробуйте еще раз");
                        j -= 1;
                    }
                }
            } else {
                out.println("Попробуйте еще раз");
                return 1;
            }
        } else {
            out.println("Попробуйте еще раз");
            return 1;
        }
        player.points += 1;
        player.roads -= 1;
        player.towns -= 1;
        Resources.getResources(row, column, player);
        if (player.cards.isEmpty()) for (List<Character> list : player.element.values()) {
            player.cards.addAll(list);
        }
        return 0;
    }
}
