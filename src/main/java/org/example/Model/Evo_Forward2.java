package org.example.Model;

import java.util.Collections;

class Evo_Forward2 extends EvolutionCards {
    Evo_Forward2(Model model, Player player){
        super(model,player);
    }
    @Override
    boolean action(){
        System.out.println("Можете выбрать 1 ресурс, который вам отдадут все соперники: ");
        String iReceive = model.in.next();
        String[] ir = iReceive.split("");
        if (model.checkInsExchange(ir) && ir.length == 1) {
            int resourcesNumber = 0;
            for (Player value : Model.playerList) {
                resourcesNumber += Collections.frequency(value.cards, ir[0].charAt(0));
                value.cards.removeAll(Collections.singleton(iReceive.charAt(0)));
            }
            player.cards.addAll(Collections.nCopies(resourcesNumber, ir[0].charAt(0)));
            System.out.println("Ваши ресурсы: " + player.cards);
        }
        return true;
    }
}
