package org.example.model;

import org.example.controller.StringCatcher;

class Evo_Knight extends EvolutionCards{
    Evo_Knight(){
    }
    @Override
    boolean action() {
        StringCatcher.makeMessage("Choose an opponent<br> to get 1 random resource from them:", "Buttons_Knight");
        Integer victim = null;
        while (victim == null) victim = (Integer) StringCatcher.getData("org.example.controller.Buttons_Knight");
        Player nameVictim = Model.playerList.get(victim - 1);
        gamer.cards.add(nameVictim.cards.get(0));
        nameVictim.cards.remove(0);
        gamer.knights += 1;
        if (gamer.knights > Model.mostKnights) {
            Model.mostKnights = gamer.knights;
            Model.mostKnightsHolder = gamer;
        }
        return true;
    }
}
