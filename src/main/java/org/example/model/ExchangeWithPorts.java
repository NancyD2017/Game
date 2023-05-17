package org.example.model;

import org.example.controller.Buttons_4Exchange;
import org.example.controller.Buttons_4ExchangePort;
import org.example.controller.Buttons_ExchangePort;
import org.example.controller.StringCatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.example.model.Model.playerList;

public class ExchangeWithPorts {
    public static Player player;
    public static Integer useExchange;
    void act(Player pl) {                                                               //осуществляет обмен игрока с портами
        player = pl;
        if (player.ports.size() == 0) {
            if (player.cards.size() >= 4) {
                useExchange = 4;
                String[] playerGivesList = getStrings(null, 4);
                for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", choose resource to receive", "Buttons_4ExchangePort");
                String playerGets = null;
                while (playerGets == null || playerGets.equals("null")) {
                    playerGets = (String) StringCatcher.getData("org.example.controller.Buttons_4ExchangePort");
                }
                player.cards.add(playerGets.charAt(0));
                Buttons_4ExchangePort.messageToPass = null;
            } else StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough resources for exchange","");
        } else {
            StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", choose any port you'd like to exchange with", "Buttons_ExchangePort");
            String playerPort = null;
            while (playerPort == null) {
                playerPort = (String) StringCatcher.getData("org.example.controller.Buttons_ExchangePort");
            }
            Buttons_ExchangePort.messageToPass = null;
            StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", choose resource to receive:", "Buttons_4ExchangePort");
            String playerReceives = null;
            while (playerReceives == null) {
                playerReceives = (String) StringCatcher.getData("org.example.controller.Buttons_4ExchangePort");
            }
            Buttons_4ExchangePort.messageToPass = null;
            if (!playerPort.equals("3") && !playerPort.equals("4") && Collections.frequency(player.cards, playerPort.charAt(0)) >= 2) {
                player.cards.remove(Character.valueOf(playerPort.charAt(0)));
                player.cards.remove(Character.valueOf(playerPort.charAt(0)));
                player.cards.add(playerReceives.charAt(0));
            } else if (playerPort.equals("4")) {
                if (player.cards.size() >= 4) {
                    useExchange = 4;
                    String[] playerGivesList = getStrings(null, 4);
                    for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                    player.cards.add(playerReceives.charAt(0));
                } else StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough resources for exchange","");
            } else if (playerPort.equals("3")) {
                if (player.cards.size() >= 3) {
                    useExchange = 3;
                    String[] playerGivesList = null;
                    playerGivesList = getStrings(playerGivesList, 3);
                    for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                    player.cards.add(playerReceives.charAt(0));
                } else StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough resources for exchange","");
            } else StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you don't have enough resources for exchange","");
        }
    }

    private static String[] getStrings(String[] playerGivesList, Integer numberResources) {
        boolean isAllRight = false;
        while (!isAllRight) {
            StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", choose " + numberResources + " resources for exchange 1:" + numberResources, "Buttons_4Exchange");
            String playerGives = null;
            while (playerGives == null) {
                playerGives = (String) StringCatcher.getData("org.example.controller.Buttons_4Exchange");
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
            if (!isAllRight) StringCatcher.makeMessage("Player " + (playerList.indexOf(player) + 1) + ", you've made a mistake while choosing<br> resources to give.<br>Try one more time", "");
        }
        return playerGivesList;
    }
}