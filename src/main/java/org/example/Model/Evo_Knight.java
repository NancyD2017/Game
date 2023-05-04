package org.example.Model;

class Evo_Knight {
    boolean main(Player player) {
        Model m = new Model();
        System.out.println("Выберите соперника, чтобы получить от него 1 случайно выбранный ресурс:");
        int victim = m.isNumber();
        Player nameVictim = m.playerList.get(victim - 1);
        if (nameVictim != player) {
            player.cards.add(nameVictim.cards.get(0));
            System.out.println("Вы получаете " + nameVictim.cards.get(0) + " вот ваш новый список ресурсов: " + player.cards);
            nameVictim.cards.remove(0);
            player.knights += 1;
            if (player.knights > m.mostKnights) {
                m.mostKnights = player.knights;
                m.mostKnightsHolder = player;
            }
            return true;
        } else {
            System.out.println("Вы не можете забрать ресурсы у самого себя");
        }
        return false;
    }
}
