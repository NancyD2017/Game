package org.example.model;

import org.example.controller.StringCatcher;

class Evo_All extends EvolutionCards{
    Evo_All(Model model, Player player){
        super(model);
    }
    @Override
    boolean action() {
        player.points += 1;
        StringCatcher.makeMessage("You've got one point of win<br>Your points: " + player.points, "Removal");
        if (Model.mostPoints < player.points) {
            Model.mostPoints = player.points;
            Model.mostPointsHolder = player;
        }
        return true;
    }
}
