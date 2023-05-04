package org.example.Model;

import org.example.Game;

class Evo_Forward1 {
    boolean main(Player player) {
        Model m = new Model();
        Game q = new Game();
        System.out.println("Можете выбрать 2 любые карты ресурсов: ");
        String iReceive = m.in.next();
        String[] ir = iReceive.split("");
        if (m.checkInsExchange(ir) && ir.length == 2) {
            for (String item : ir) {
                player.cards.add(q.getSprite(item.charAt(0) + "_card.png"));
            }
            System.out.println("Ваши ресурсы: " + player.cards);
            return true;
        } else System.out.println("Вы ввели карты ресурсов неправильно");
        return false;
    }
}
