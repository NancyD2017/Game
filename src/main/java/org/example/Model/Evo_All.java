package org.example.Model;

class Evo_All extends EvolutionCards{
    Evo_All(Model model, Player player){
        super(model,player);
    }
    @Override
    boolean action() {
        player.points += 1;
        System.out.println("Вы получили 1 победное очко. Ваш счет: " + player.points);
        if (Model.mostPoints < player.points) {
            Model.mostPoints = player.points;
            Model.mostPointsHolder = player;
        }
        return true;
    }
}
