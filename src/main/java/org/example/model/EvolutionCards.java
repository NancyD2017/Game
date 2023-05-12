package org.example.model;
import org.example.controller.StringCatcher;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
public class EvolutionCards {
    boolean action() {
        return true;
    }
    Model model;
    public static Player player;
    EvolutionCards(Model model) {
        this.model = model;
    }
    void main(Player player) {                                                                //действие для каждой карточки развития, будь то рыцарь, постройка дорожек, получение очков для победы
        Model m = new Model();
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
                while (!allRight) {
                    EvolutionCards card;
                    switch (playersCard) {
                        case "church", "library", "poly", "funfair", "embassy" -> card = new Evo_All(m, player);
                        case "forward1" -> card = new Evo_Forward1(m, player);
                        case "forward2" -> card = new Evo_Forward2(m, player);
                        case "map" -> card = new Evo_Map(m, player);
                        case "knight" -> card = new Evo_Knight(m, player);
                        default -> card = new EvolutionCards(m);
                    }
                    allRight = card.action();
                }
            } else StringCatcher.makeMessage("You don't have enough resources","");
        }
}
