package org.example.Model;

import org.example.Game;

import java.util.Collections;

class Evo_Forward2 {
    boolean main(Player player){
        Model m = new Model();
        Game q = new Game();
        System.out.println("Можете выбрать 1 ресурс, который вам отдадут все соперники: ");
        String iReceive = m.in.next();
        String[] ir = iReceive.split("");
        StringBuilder irToSprite = new StringBuilder();
        if (m.checkInsExchange(ir) && ir.length == 1) {
            int resourcesNumber = 0;
            irToSprite.append(ir[0].charAt(0)).append("_card.png");
            for (Player value : m.playerList) {
                resourcesNumber += Collections.frequency(value.cards, q.getSprite(String.valueOf(irToSprite)));
                value.cards.removeAll(Collections.singleton(q.getSprite(String.valueOf(irToSprite))));
            }
            player.cards.addAll(Collections.nCopies(resourcesNumber, q.getSprite(String.valueOf(irToSprite))));
            System.out.println("Ваши ресурсы: " + player.cards);
        }
        return true;
    }
}
