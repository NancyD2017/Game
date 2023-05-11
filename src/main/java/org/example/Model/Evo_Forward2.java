package org.example.Model;

import org.example.Controller.StringCatcher;

import java.util.Collections;

class Evo_Forward2 extends EvolutionCards {
    Evo_Forward2(Model model, Player player){
        super(model,player);
    }
    @Override
    boolean action(){
        StringCatcher catcher = new StringCatcher();
        catcher.makeMessage("You can choose 1 resource which <br>will be given to you from competitors: ","Buttons_Forward2");
        String iReceive = null;
        while (iReceive == null || iReceive.isEmpty()) iReceive = (String) catcher.getData("org.example.Controller.Buttons_Forward2");
        String[] ir = iReceive.split(" ");
        int resourcesNumber = 0;
        for (Player value : Model.playerList) {
            resourcesNumber += Collections.frequency(value.cards, ir[0].charAt(0));
            value.cards.removeAll(Collections.singleton(iReceive.charAt(0)));
        }
        player.cards.addAll(Collections.nCopies(resourcesNumber, ir[0].charAt(0)));
        return true;
    }
}
