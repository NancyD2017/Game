package org.example.Model;

import org.example.Controller.Buttons_Forward1;
import org.example.Controller.StringCatcher;

class Evo_Forward1 extends EvolutionCards {
    Evo_Forward1(Model model, Player player) {
        super(model, player);
    }
    @Override
    boolean action() {
        StringCatcher catcher = new StringCatcher();
        catcher.makeMessage("You can choose 2 any cards of resources", "Buttons_Forward1");
        String iReceive = null;
        while (iReceive == null || iReceive.isEmpty()) iReceive = (String) catcher.getData("org.example.Controller.Buttons_Forward1");
        Buttons_Forward1.message = "";
        Buttons_Forward1.messageToPass = null;
        String[] ir = iReceive.split(" ");
        for (String item : ir) player.cards.add(item.charAt(0));
        catcher.showData("Your new resources", player.cards);
        return true;
    }
}
