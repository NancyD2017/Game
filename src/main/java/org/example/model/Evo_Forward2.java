package org.example.model;

import org.example.controller.StringCatcher;

import java.util.Collections;

import static org.example.model.Model.playerList;

class Evo_Forward2 extends EvolutionCards {
    Evo_Forward2(){}
    @Override
    boolean action(){
        StringCatcher.makeMessage("Player " + (playerList.indexOf(gamer) + 1) + ", you can choose 1 resource which <br>will be given to you from competitors: ","Buttons_Forward2");
        String iReceive = null;
        while (iReceive == null || iReceive.isEmpty()) iReceive = (String) StringCatcher.getData("org.example.controller.Buttons_Forward2");
        String[] ir = iReceive.split(" ");
        int resourcesNumber = 0;
        for (Player value : Model.playerList) {
            resourcesNumber += Collections.frequency(value.cards, ir[0].charAt(0));
            value.cards.removeAll(Collections.singleton(iReceive.charAt(0)));
        }
        gamer.cards.addAll(Collections.nCopies(resourcesNumber, ir[0].charAt(0)));
        return true;
    }
}
