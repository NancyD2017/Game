package org.example.model;

import org.example.controller.StringCatcher;
class Evo_Knight extends EvolutionCards{
    Evo_Knight(Model model, Player player){
        super(model);
    }
    @Override
    boolean action() {
        StringCatcher.makeMessage("Choose an opponent<br> to get 1 random resource from them:", "Buttons_Knight");
        Integer victim = null;
        while (victim == null) victim = (Integer) StringCatcher.getData("org.example.controller.Buttons_Knight");
        Player nameVictim = Model.playerList.get(victim - 1);
        player.cards.add(nameVictim.cards.get(0));
        nameVictim.cards.remove(0);
        player.knights += 1;
        if (player.knights > Model.mostKnights) {
            Model.mostKnights = player.knights;
            Model.mostKnightsHolder = player;
        }
        return true;
    }
}
