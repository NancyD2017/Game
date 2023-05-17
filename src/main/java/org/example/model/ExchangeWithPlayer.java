package org.example.model;

import org.example.controller.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.example.model.Model.playerList;

public class ExchangeWithPlayer {
    public static Player player;
    public static Player nameExchanger;
    void act(Player pl) {                                                              //осуществляет обмен игрока с другими игроками
        player = pl;
        String[] playerReceives = null;
        String[] playerGivesList = null;
        StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", with whom do you wanna<br> exchange your card with?", "Buttons_EPlayer");
        Integer exchanger = null;
        while (exchanger == null) exchanger = (Integer) StringCatcher.getData("org.example.controller.Buttons_EPlayer");
        nameExchanger = Model.playerList.get(exchanger - 1);
        Buttons_EPlayer.messageToPass = null;
        boolean isAllRight = false;
        while (!isAllRight) {
            StringCatcher.makeMessage("You can see list of resources of player " + exchanger + " on the field" +
                    "<br>Choose your resources you'd like to give", "Buttons_ExchangePlayer");
            String playerGives = null;
            while (playerGives == null) {
                playerGives = (String) StringCatcher.getData("org.example.controller.Buttons_ExchangePlayer");
            }
            playerGivesList = playerGives.split(" ");
            Buttons_ExchangePlayer.messageToPass = null;
            Buttons_ExchangePlayer.message = "";
            StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", choose resources you'd<br> like to get from opponent:", "Buttons_ExchangePl");
            while (playerReceives == null) {
                Object o = StringCatcher.getData("org.example.controller.Buttons_ExchangePl");
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
                StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you've made a mistake while choosing<br> resources to give or/and to receive.<br>Try one more time", "");
            }
        }
        StringBuilder exchange = new StringBuilder();
        for (String getting: playerReceives) {
            switch (getting) {
                case "s" -> exchange.append("straw");
                case "f" -> exchange.append("field");
                case "t" -> exchange.append("stone");
                case "w" -> exchange.append("wood");
                case "b" -> exchange.append("brick");
            }
            exchange.append(", ");
        }
        exchange.delete(exchange.lastIndexOf(","), exchange.lastIndexOf(",") + 1);
        StringBuilder receive = new StringBuilder();
        for (String receiving: playerGivesList) {
            switch (receiving) {
                case "s" -> receive.append("straw");
                case "f" -> receive.append("field");
                case "t" -> receive.append("stone");
                case "w" -> receive.append("wood");
                case "b" -> receive.append("brick");
            }
            receive.append(", ");
        }
        receive.delete(receive.lastIndexOf(","), receive.lastIndexOf(",") + 1);
        StringCatcher.makeMessage("Player " + exchanger + ",<br> do you want to get in exchange of " + exchange + "<br>resources " + receive + "?", "Buttons_ExchangeValidation");
        String validation = null;
        while (validation == null) {
            validation = (String) StringCatcher.getData("org.example.controller.Buttons_ExchangeValidation");
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
        } else StringCatcher.makeMessage("Player " + exchanger + " doesn't wanna exchange", "");
    }
}
