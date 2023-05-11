package org.example.Model;

import org.example.Controller.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExchangeWithPlayer {
    public static Player player;
    public static Player nameExchanger;
    void act(Player pl) {                                                              //осуществляет обмен игрока с другими игроками
        player = pl;
        String[] playerReceives = null;
        String[] playerGivesList = null;
        StringCatcher catcher = new StringCatcher();
        catcher.makeMessage("With whom do you wanna<br> exchange your card with?", "Buttons_EPlayer");
        Integer exchanger = null;
        while (exchanger == null) exchanger = (Integer) catcher.getData("org.example.Controller.Buttons_EPlayer");
        nameExchanger = Model.playerList.get(exchanger - 1);
        Buttons_EPlayer.messageToPass = null;
        boolean isAllRight = false;
        while (!isAllRight) {
            catcher.makeMessage("You can see list of resources of player " + exchanger + " on the field" +
                    "<br>Choose your resources you'd like to give", "Buttons_ExchangePlayer");
            String playerGives = null;
            while (playerGives == null) {
                playerGives = (String) catcher.getData("org.example.Controller.Buttons_ExchangePlayer");
            }
            playerGivesList = playerGives.split(" ");
            Buttons_ExchangePlayer.messageToPass = null;
            Buttons_ExchangePlayer.message = "";
            catcher.makeMessage("Choose resources you'd<br> like to get from opponent:", "Buttons_ExchangePl");
            while (playerReceives == null) {
                Object o = catcher.getData("org.example.Controller.Buttons_ExchangePl");
                if (o != null) playerReceives = o.toString().split(" ");
            }
            Buttons_ExchangePl.message = "";
            Buttons_ExchangePl.messageToPass = null;
            List<String> cardsPlayer = player.cards.stream().map(String::valueOf).toList();
            List<String> givesPlayer = Arrays.asList(playerGives.split(" "));
            List<String> cardExchanger = nameExchanger.cards.stream().map(String::valueOf).toList();
            List<String> receivesPlayer = Arrays.asList(playerReceives);
            isAllRight = true;
            for(String card : givesPlayer){
                if(Collections.frequency(cardsPlayer, card) < Collections.frequency(givesPlayer, card)){
                    isAllRight = false;
                    break;
                }
            }
            for(String card : receivesPlayer){
                if(Collections.frequency(cardExchanger, card) < Collections.frequency(receivesPlayer, card)){
                    isAllRight = false;
                    break;
                }
            }
            if (!isAllRight) {
                playerReceives = null;
                catcher.makeMessage("You've made a mistake while choosing<br> resources to give or/and to receive.<br>Try one more time", "");
            }
        }
        catcher.makeMessage("Player " + exchanger + ",<br> do you want to get in exchange of " + Arrays.toString(playerReceives) + "<br>resources " + Arrays.toString(playerGivesList), "Buttons_ExchangeValidation");
        String validation = null;
        while (validation == null) {
            validation = (String) catcher.getData("org.example.Controller.Buttons_ExchangeValidation");
        }
        Buttons_ExchangeValidation.messageToPass = null;
        if (validation.equals("yes")) {
            for (String item : playerGivesList) {
                player.cards.remove(Character.valueOf(item.charAt(0)));
                nameExchanger.cards.add(item.charAt(0));
            }
            for (String it : playerReceives) {
                player.cards.add(it.charAt(0));
                nameExchanger.cards.remove(Character.valueOf(it.charAt(0)));
            }
        } else catcher.makeMessage("Player " + exchanger + " doesn't wanna exchange", "");
    }
}
