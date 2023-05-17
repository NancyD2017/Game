package org.example.model;

import org.example.controller.StringCatcher;

import static org.example.model.Model.playerList;

class Evo_Map extends EvolutionCards{
    Evo_Map(){
    }
    @Override
    boolean action() {
        Road r = new Road();
        StringCatcher.makeMessage("Player " + (playerList.indexOf(gamer) + 1) + ", you can build 2 roads<br> without spending resources","Default");
        if (gamer.roads > 1) {
            for (int i = 0; i < 2; i++) if (!r.main(gamer)) i -= 1;
            if (gamer.roads < Model.leastRoadsLeft) Model.leastRoadsLeft = gamer.roads;
        } else {
            StringCatcher.makeMessage("You have no roads left", "");
        }
        return true;
    }
}
