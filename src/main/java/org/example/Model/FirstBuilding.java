package org.example.Model;

import java.util.List;

import static java.lang.System.out;

class FirstBuilding {
    int main(Player player, Color mcolor)  {                             //осуществляет постройку первого поселения и дорожки игрока
        Model m = new Model();
        Resources e = new Resources();
        CoordinatesCheck o = new CoordinatesCheck();
        PossiblePorts p = new PossiblePorts();
        out.println("Выберите положение поселения на поле. посмотрите на выведенное поле выше и выберите ряд (от 1 до 11) и столбец (от 1 до 4 или больше, в зависимости от ряда)");
        int row = m.isNumber() - 1;
        int column = m.isNumber() - 1;
        if (o.main(row, column)) {
            if (m.field[row][column] == Item.E) {
                player.available.add(new FieldItem(row, column, Item.T));
                m.field[row][column] = Item.T;
                for (int j = 0; j < 1; j++) {
                    out.println("и расположение дорожки около него ");
                    int rowR = m.isNumber() - 1;
                    int columnR = m.isNumber() - 1;
                    if (o.main(rowR, columnR)) {
                        if ((m.field[rowR][columnR] == Item.P && ((rowR == row && ((columnR == column - 1) || (columnR == column + 1))) ||
                                ((((row == 6 || row == 4) && rowR == 5) || (row < 4 && rowR == 1 + row) || (row > 6 && rowR + 1 == row)) && columnR * 4 == column) ||
                                (row <= 4 && columnR * 4 + 2 == column && rowR + 1 == row) || (row >= 6 && columnR * 4 + 2 == column && rowR == 1 + row))) && o.main(row, column)) {
                            player.available.add(new FieldItem(rowR, columnR, Item.R));
                            m.field[rowR][columnR] = Item.R;
                            p.main(rowR, columnR, player);
                            m.colors.remove(mcolor);
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
        e.getResources(row, column, player);
        if (player.cards.isEmpty()) for (List<Character> list : player.element.values()) {
            player.cards.addAll(list);
        }
        return 0;
    }
}
