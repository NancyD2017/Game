package org.example.Model;

import org.example.Controller.Buttons_4Exchange;
import org.example.Controller.Buttons_4ExchangePort;
import org.example.Controller.Buttons_ExchangePort;
import org.example.Controller.StringCatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.lang.System.out;

public class ExchangeWithPorts {
    public static Player player;
    static StringCatcher catcher = new StringCatcher();
    public static Integer useExchange;
    void act(Player pl) {                                                               //осуществляет обмен игрока с портами
        player = pl;
        if (player.ports.size() == 0) {
            if (player.cards.size() >= 4) {
                useExchange = 4;
                String[] playerGivesList = null;
                playerGivesList = getStrings(playerGivesList, 4);
                for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                catcher.makeMessage("Choose resource to receive", "Buttons_4ExchangePort");
                String playerGets = null;
                while (playerGets == null) {
                    playerGets = catcher.getData("Buttons_4ExchangePort").toString();
                }
                player.cards.add(playerGets.charAt(0));
                Buttons_4ExchangePort.messageToPass = null;
            } else catcher.makeMessage("You don't have enough resources for exchange","");
        } else {
            catcher.makeMessage("Choose any port you'd like to exchange with", "Buttons_ExchangePort");
            String playerPort = null;
            while (playerPort == null) {
                playerPort = (String) catcher.getData("org.example.Controller.Buttons_ExchangePort");
            }
            Buttons_ExchangePort.messageToPass = null;
            catcher.makeMessage("Choose resource to receive:", "Buttons_4ExchangePort");
            String playerReceives = null;
            while (playerReceives == null) {
                playerReceives = (String) catcher.getData("org.example.Controller.Buttons_4ExchangePort");
            }
            Buttons_4ExchangePort.messageToPass = null;
            if (!playerPort.equals("3") && !playerPort.equals("4") && Collections.frequency(player.cards, playerPort.charAt(0)) >= 2) {
                player.cards.remove(Character.valueOf(playerPort.charAt(0)));
                player.cards.remove(Character.valueOf(playerPort.charAt(0)));
                player.cards.add(playerReceives.charAt(0));
            } else if (playerPort.equals("4")) {
                if (player.cards.size() >= 4) {
                    useExchange = 4;
                    String[] playerGivesList = null;
                    playerGivesList = getStrings(playerGivesList, 4);
                    for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                    player.cards.add(playerReceives.charAt(0));
                } else catcher.makeMessage("You don't have enough resources for exchange","");
            } else if (playerPort.equals("3")) {
                if (player.cards.size() >= 3) {
                    useExchange = 3;
                    String[] playerGivesList = null;
                    playerGivesList = getStrings(playerGivesList, 3);
                    for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                    player.cards.add(playerReceives.charAt(0));
                } else catcher.makeMessage("You don't have enough resources for exchange","");
            } else catcher.makeMessage("You don't have enough resources for exchange","");
        }
    }

    private static String[] getStrings(String[] playerGivesList, Integer numberResources) {
        boolean isAllRight = false;
        while (!isAllRight) {
            catcher.makeMessage("Choose " + numberResources + " resources for exchange 1:" + numberResources, "Buttons_4Exchange");
            String playerGives = null;
            while (playerGives == null) {
                playerGives = (String) catcher.getData("org.example.Controller.Buttons_4Exchange");
            }
            Buttons_4Exchange.messageToPass = null;
            Buttons_4Exchange.message = "";
            playerGivesList = playerGives.split(" ");
            List<String> givesPlayer = Arrays.asList(playerGives.split(" "));
            List<String> cardsPlayer = player.cards.stream().map(String::valueOf).toList();
            isAllRight = true;
            for (String card : givesPlayer) {
                if (Collections.frequency(cardsPlayer, card) < Collections.frequency(givesPlayer, card)) {
                    isAllRight = false;
                    break;
                }
            }
            if (!isAllRight) catcher.makeMessage("You've made a mistake while choosing<br> resources to give.<br>Try one more time", "");
        }
        return playerGivesList;
    }
}