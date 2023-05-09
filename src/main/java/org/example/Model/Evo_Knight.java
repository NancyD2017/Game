package org.example.Model;

import org.example.Controller.StringCatcher;
class Evo_Knight extends EvolutionCards{
    Evo_Knight(Model model, Player player){
        super(model,player);
    }
    @Override
    boolean action() {
        StringCatcher catcher = new StringCatcher();
        catcher.makeMessage("Choose an opponent<br> to get 1 random resource from them:", "Buttons_Knight");
        Integer victim = null;
        while (victim == null) victim = (Integer) catcher.getData("org.example.Controller.Buttons_Knight");
        Player nameVictim = Model.playerList.get(victim - 1);
        player.cards.add(nameVictim.cards.get(0));
        catcher.showData("Your new list of resources:", player.cards);
        nameVictim.cards.remove(0);
        player.knights += 1;
        if (player.knights > Model.mostKnights) {
            Model.mostKnights = player.knights;
            Model.mostKnightsHolder = player;
        }
        return true;
    }
}
