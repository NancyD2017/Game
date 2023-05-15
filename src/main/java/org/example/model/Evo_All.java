package org.example.model;

import org.example.controller.StringCatcher;

class Evo_All extends EvolutionCards{
    Evo_All(){
    }
    @Override
    boolean action() {
        gamer.points += 1;
        StringCatcher.makeMessage("You've got one point of win<br>Your points: " + gamer.points, "Removal");
        if (Model.mostPoints < gamer.points) {
            Model.mostPoints = gamer.points;
            Model.mostPointsHolder = gamer;
        }
        return true;
    }
}
