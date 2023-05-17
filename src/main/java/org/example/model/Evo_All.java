package org.example.model;

import org.example.controller.StringCatcher;

import static org.example.model.Model.playerList;

class Evo_All extends EvolutionCards{
    Evo_All(){
    }
    @Override
    boolean action() {
        gamer.points += 1;
        StringCatcher.makeMessage("Player " + (playerList.indexOf(gamer) + 1) + ", You've got one point of win<br>Your points: " + gamer.points, "Removal");
        if (Model.mostPoints < gamer.points) {
            Model.mostPoints = gamer.points;
            Model.mostPointsHolder = gamer;
        }
        return true;
    }
}
