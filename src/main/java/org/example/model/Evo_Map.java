package org.example.model;

import org.example.controller.StringCatcher;

class Evo_Map extends EvolutionCards{
    Evo_Map(Model model, Player player){
        super(model);
    }
    @Override
    boolean action() {
        Road r = new Road();
        StringCatcher.makeMessage("You can build 2 roads<br> without spending resources","Default");
        if (player.roads > 1) {
            for (int i = 0; i < 2; i++) if (!r.main(player)) i -= 1;
            player.roads -= 2;
            if (player.roads < Model.leastRoadsLeft) Model.leastRoadsLeft = player.roads;
        } else {
            StringCatcher.makeMessage("You have no roads left", "");
        }
        return true;
    }
}