package org.example.Model;

class CoordinatesCheck {
    boolean act(int row, int column) {                                                       //помогает избежать ошибок, если игрок ввел неправильный ряд или столбик
        if (column >= 0 && row >= 0) {
            return ((row == 0 || row == 10) && (column <= 12)) || ((row == 1 || row == 9) && (column <= 3))
                    || ((row == 2 || row == 8) && (column <= 16)) || ((row == 3 || row == 7) && (column <= 4))
                    || ((row == 4 || row == 6) && (column <= 20)) || (row == 5 && column <= 5);
        }
        return false;
    }
}
