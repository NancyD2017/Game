package org.example.model;

import org.example.controller.Buttons_Knight;
import org.example.controller.StringCatcher;

import java.util.Random;

import static org.example.model.Model.playerList;

class Evo_Knight extends EvolutionCards{
    Evo_Knight(){
    }
    @Override
    boolean action() {
        StringCatcher.makeMessage("Player " + (playerList.indexOf(gamer) + 1) + ", choose an opponent<br> to get 1 random resource from them:", "Buttons_Knight");
        Integer victim = null;
        while (victim == null) victim = (Integer) StringCatcher.getData("org.example.controller.Buttons_Knight");
        Buttons_Knight.messageToPass = null;
        Player nameVictim = Model.playerList.get(victim - 1);
        Random random = new Random();
        int cardsNumber = 0;
        while (!((cardsNumber >= 1 && cardsNumber <= nameVictim.cards.size()))) {
            cardsNumber = 1 + random.nextInt(nameVictim.cards.size());
        }
        gamer.cards.add(nameVictim.cards.get(cardsNumber - 1));
        nameVictim.cards.remove(cardsNumber - 1);
        gamer.knights += 1;
        return true;
    }
}
