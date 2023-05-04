package org.example.Model;

class Evo_Map {
    boolean main(Player player) {
        System.out.println("Можете построить 2 дороги без затраты ресурсов");
        if (player.roads > 1) {
            for (int i = 0; i < 2; i++) if (!Road.main(player)) i -= 1;
            player.roads -= 2;
            if (player.roads < Model.leastRoadsLeft) Model.leastRoadsLeft = player.roads;
        } else {
            System.out.println("У вас закончились дорожки");
        }
        return true;
    }
}
