package org.example.Model;
import java.util.List;

import static java.lang.System.out;
import static org.example.Model.Model.playerList;

class FirstBuilding {
    public static String addText = "";
    int main(Integer player, Colors mcolor)  {                             //осуществляет постройку первого поселения и дорожки игрока
        Model m = new Model();
        Resources e = new Resources();
        CoordinatesCheck o = new CoordinatesCheck();
        PossiblePorts p = new PossiblePorts();
        out.println(addText + "Выберите положение поселения на поле. посмотрите на выведенное поле выше и выберите ряд (от 1 до 11) и столбец (от 1 до 4 или больше, в зависимости от ряда)");
        int row = m.isNumber() - 1;
        int column = m.isNumber() - 1;
        if (o.act(row, column)) {
            if (Model.field[row][column] == Item.O) {
                playerList.get(player).available.add(new FieldItem(row, column, Item.T));
                Model.field[row][column] = Item.T;
                for (int j = 0; j < 1; j++) {
                    out.println("и расположение дорожки около него ");
                    int rowR = m.isNumber() - 1;
                    int columnR = m.isNumber() - 1;
                    if (o.act(rowR, columnR)) {
                        if ((Model.field[rowR][columnR] == Item.r && ((rowR == row && ((columnR == column - 1) || (columnR == column + 1))) ||
                                ((((row == 6 || row == 4) && rowR == 5) || (row < 4 && rowR == 1 + row) || (row > 6 && rowR + 1 == row)) && columnR * 4 == column) ||
                                (row <= 4 && columnR * 4 + 2 == column && rowR + 1 == row) || (row >= 6 && columnR * 4 + 2 == column && rowR == 1 + row))) && o.act(row, column)) {
                            playerList.get(player).available.add(new FieldItem(rowR, columnR, Item.R));
                            Model.field[rowR][columnR] = Item.R;
                            p.act(rowR, columnR, playerList.get(player));
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
