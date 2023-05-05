package org.example.Model;

import static org.example.Model.Model.players;

class Evo_Knight extends EvolutionCards{
    Evo_Knight(Model model, Player player){
        super(model,player);
    }
    @Override
    boolean action() {
        System.out.println("Выберите соперника, чтобы получить от него 1 случайно выбранный ресурс:");
        int victim = model.isNumber();
        Player nameVictim = Model.playerList.get(victim - 1);
        if (nameVictim != player && victim <= players && victim > 0) {
            player.cards.add(nameVictim.cards.get(0));
            System.out.println("Вы получаете " + nameVictim.cards.get(0) + ". Bот ваш новый список ресурсов: " + player.cards);
            nameVictim.cards.remove(0);
            player.knights += 1;
            if (player.knights > Model.mostKnights) {
                Model.mostKnights = player.knights;
                Model.mostKnightsHolder = player;
            }
            return true;
        } else {
            System.out.println("Вы не можете забрать ресурсы у самого себя или у несуществующего игрока");
        }
        return false;
    }
}
