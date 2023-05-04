package org.example.Model;

class Evo_All {
    boolean main(Player player) {
        player.points += 1;
        System.out.println("Вы получили 1 победное очко. Ваш счет: " + player.points);
        return true;
    }
}
