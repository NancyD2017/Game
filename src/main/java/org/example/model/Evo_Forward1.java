package org.example.model;

import org.example.controller.Buttons_Forward1;
import org.example.controller.StringCatcher;

import static org.example.model.Model.playerList;

class Evo_Forward1 extends EvolutionCards {
    Evo_Forward1() {
    }
    @Override
    boolean action() {
        StringCatcher.makeMessage("Player " + (playerList.indexOf(gamer) + 1) + ", you can choose 2 any cards of resources", "Buttons_Forward1");
        String iReceive = null;
        while (iReceive == null || iReceive.isEmpty()) iReceive = (String) StringCatcher.getData("org.example.controller.Buttons_Forward1");
        Buttons_Forward1.message = "";
        Buttons_Forward1.messageToPass = null;
        String[] ir = iReceive.split(" ");
        for (String item : ir) gamer.cards.add(item.charAt(0));
        return true;
    }
}
