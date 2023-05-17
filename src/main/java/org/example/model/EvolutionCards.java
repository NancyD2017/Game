package org.example.model;
import org.example.controller.StringCatcher;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.example.model.Model.playerList;

public class EvolutionCards {
    boolean action() {
        return true;
    }
    public static Player gamer;
    EvolutionCards() {
    }
    void main(Player player) {//действие для каждой карточки развития, будь то рыцарь, постройка дорожек, получение очков для победы
        gamer = player;
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
                        case "church", "library", "poly", "funfair", "embassy" -> card = new Evo_All();
                        case "forward1" -> card = new Evo_Forward1();
                        case "forward2" -> card = new Evo_Forward2();
                        case "map" -> card = new Evo_Map();
                        case "knight" -> card = new Evo_Knight();
                        default -> card = new EvolutionCards();
                    }
                    allRight = card.action();
                }
            } else StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough resources","");
        }
}
