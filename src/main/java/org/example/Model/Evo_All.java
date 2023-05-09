package org.example.Model;

import org.example.Controller.StringCatcher;

class Evo_All extends EvolutionCards{
    Evo_All(Model model, Player player){
        super(model,player);
    }
    @Override
    boolean action() {
        player.points += 1;
        (new StringCatcher()).makeMessage("You've got one point of win<br>Your points: " + player.points, "Removal");
        if (Model.mostPoints < player.points) {
            Model.mostPoints = player.points;
            Model.mostPointsHolder = player;
        }
        return true;
    }
}
