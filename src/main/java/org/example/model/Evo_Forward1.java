package org.example.model;

import org.example.controller.Buttons_Forward1;
import org.example.controller.StringCatcher;

class Evo_Forward1 extends EvolutionCards {
    Evo_Forward1(Model model, Player player) {
        super(model);
    }
    @Override
    boolean action() {
        StringCatcher.makeMessage("You can choose 2 any cards of resources", "Buttons_Forward1");
        String iReceive = null;
        while (iReceive == null || iReceive.isEmpty()) iReceive = (String) StringCatcher.getData("org.example.controller.Buttons_Forward1");
        Buttons_Forward1.message = "";
        Buttons_Forward1.messageToPass = null;
        String[] ir = iReceive.split(" ");
        for (String item : ir) player.cards.add(item.charAt(0));
        return true;
    }
}
