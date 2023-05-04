package org.example.Model;
import org.example.Game;
import org.example.Sprite;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
class EvolutionCards {
    void main(Player player) {                                                                //действие для каждой карточки развития, будь то рыцарь, постройка дорожек, получение очков для победы
        Model m = new Model();
        Game q = new Game();
        Evo_Map map = new Evo_Map();
        Evo_Forward1 one = new Evo_Forward1();
        Evo_Forward2 two = new Evo_Forward2();
        Evo_Knight knight = new Evo_Knight();
        Evo_All all = new Evo_All();
        List<Sprite> toRemove = List.of(q.getSprite('s' + "_card.png"),q.getSprite('f' + "_card.png"), q.getSprite('t' + "_card.png"));
            if (new HashSet<>(player.cards).containsAll(toRemove)) {
                for (Sprite ch : toRemove) {
                    if (Collections.frequency(player.cards, ch) >= 1) {
                        player.cards.remove(ch);
                    }
                }
                String playersCard = m.evolutionCards.get(0);
                boolean allRight = false;
                m.evolutionCards.remove(0);
                System.out.println("Поздравляем! Вы получили " + playersCard);
                while (!allRight) {
                    switch (playersCard) {
                        case "church", "library", "poly", "funfair", "embassy" -> allRight = all.main(player);
                        case "forward1" -> allRight = one.main(player);
                        case "forward2" -> allRight = two.main(player);
                        case "map" -> allRight = map.main(player);
                        case "knight" -> allRight = knight.main(player);
                    }
                }
            } else System.out.println("У вас недостаточно ресурсов");
        }
}
