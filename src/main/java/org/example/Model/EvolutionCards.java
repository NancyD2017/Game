package org.example.Model;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
class EvolutionCards {
    void main(Player player) {                                                                //действие для каждой карточки развития, будь то рыцарь, постройка дорожек, получение очков для победы
            List<Character> toRemove = List.of('s', 'f', 't');
            if (new HashSet<>(player.cards).containsAll(toRemove)) {
                for (Character ch : toRemove) {
                    if (Collections.frequency(player.cards, ch) >= 1) {
                        player.cards.remove(ch);
                    }
                }
                String playersCard = Model.evolutionCards.get(0);
                boolean allRight = false;
                Model.evolutionCards.remove(0);
                System.out.println("Поздравляем! Вы получили " + playersCard);
                while (!allRight) {
                    switch (playersCard) {
                        case "church", "library", "poly", "funfair", "embassy" -> allRight = Evo_All.main(player);
                        case "forward1" -> allRight = Evo_Forward1.main(player);
                        case "forward2" -> allRight = Evo_Forward2.main(player);
                        case "map" -> allRight = Evo_Map.main(player);
                        case "knight" -> allRight = Evo_Knight.main(player);
                    }
                }
            } else System.out.println("У вас недостаточно ресурсов");
        }
}
