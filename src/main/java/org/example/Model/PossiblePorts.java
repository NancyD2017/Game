package org.example.Model;

class PossiblePorts {
    void main(Integer rowR, Integer columnR, Player player) {                                    //смотрит, какие порты есть у игрока для обмена. Правила прописала на основе игры
        switch (rowR) {
            case 0 -> {
                if (columnR == 5 || columnR == 7 || columnR == 9) player.ports.add("s");
                if (columnR == 1 || columnR == 3) player.ports.add("3");
            }
            case 1 -> {
                if (columnR == 2) player.ports.add("s");
                if (columnR == 0) player.ports.add("3");
                if (columnR == 3) player.ports.add("t");
            }
            case 2 -> {
                if (columnR == 1) player.ports.add("w");
                if (columnR == 13 || columnR == 15) player.ports.add("t");
            }
            case 3 -> {
                if (columnR == 4) player.ports.add("t");
                if (columnR == 0) player.ports.add("w");
            }
            case 4 -> {
                if (columnR == 19) player.ports.add("3");
                if (columnR == 1 || columnR == 3) player.ports.add("w");
            }
            case 5 -> {
                if (columnR == 5) player.ports.add("3");
            }
            case 6 -> {
                if (columnR == 19) player.ports.add("3");
                if (columnR == 1 || columnR == 3) player.ports.add("b");
            }
            case 7 -> {
                if (columnR == 0) player.ports.add("b");
                if (columnR == 4) player.ports.add("f");
            }
            case 8 -> {
                if (columnR == 1) player.ports.add("b");
                if (columnR == 13 || columnR == 15) player.ports.add("f");
            }
            case 9 -> {
                if (columnR == 0) player.ports.add("3");
                if (columnR == 3) player.ports.add("f");
            }
            case 10 -> {
                if (columnR == 1 || columnR == 3 || columnR == 5 || columnR == 7 || columnR == 9)
                    player.ports.add("3");
            }
        }
    }
}