package org.example.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.lang.System.out;

class ExchangeWithPorts {
    void act(Player player) {                                                               //осуществляет обмен игрока с портами
        Model m = new Model();
        if (player.ports.size() == 0) {
            out.println("Выберите ресурсы для обмена 1:4 - введите названия 4 своих ресурсов без пробелов");
            String playerGives = m.in.next();
            String[] playerGivesList = playerGives.split("");
            List<String> pg = Arrays.asList(playerGives.split(""));
            List<String> cardStrings = player.cards.stream().map(String::valueOf).toList();
            if (m.checkInsExchange(playerGivesList) && new HashSet<>(cardStrings).containsAll(pg)) {
                for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                out.println("Выберите ресурс, который хотите получить");
                String[] playerReceives = m.in.next().split("");
                if (m.checkInsExchange(playerReceives) && playerReceives.length == 1) {
                    player.cards.add(playerReceives[0].charAt(0));
                    out.println("Обмен прошел успешно" + player.cards);
                }
            } else out.println("Недостаточно ресурсов для обмена");
        } else {
            out.println("Список доступных обменов: " + player.ports.stream().toList() + " 4\nВыберите любой");
            String playerPort = m.in.next();
            out.println("Что вы хотите получить взамен? ");
            String playerReceives = m.in.next();
            if ((player.ports.contains(playerPort) || playerPort.equals("4")) && m.checkInsExchange(playerReceives.split(""))) {
                if (!playerPort.equals("3") && !playerPort.equals("4") && Collections.frequency(player.cards, playerPort.charAt(0)) >= 2) {
                    player.cards.remove(Character.valueOf(playerPort.charAt(0)));
                    player.cards.remove(Character.valueOf(playerPort.charAt(0)));
                    player.cards.add(playerReceives.charAt(0));
                    out.println("Обмен прошел успешно" + player.cards);
                } else if (playerPort.equals("3") || playerPort.equals("4")) {
                    if (player.cards.size() >= Integer.parseInt(playerPort)) {
                        out.println("Какие ресурсы вы бы хотели отдать? - введите названия " + playerPort + " своих ресурсов без пробелов");
                        String playerGives = m.in.next();
                        String[] playerGivesList = playerGives.split("");
                        List<String> pg = Arrays.asList(playerGives.split(""));
                        List<String> cardStrings = player.cards.stream().map(String::valueOf).toList();
                        if (m.checkInsExchange(playerGivesList) && playerGivesList.length == Integer.parseInt(playerPort) && new HashSet<>(cardStrings).containsAll(pg) && playerReceives.length() == 1) {
                            for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                            player.cards.add(playerReceives.charAt(0));
                            out.println("Обмен прошел успешно. Вот ваши ресурсы " + player.cards);
                        } else out.println("Вы ввели не то количество отдаваемых ресурсов");
                    } else out.println("Недостаточно ресурсов для обмена");
                } else out.println("Недостаточно ресурсов для обмена");
            } else out.println("Вы неправильно ввели порт или то, что хотите получить взамен");
        }
    }
}
