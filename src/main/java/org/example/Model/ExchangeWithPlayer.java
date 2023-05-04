package org.example.Model;

import org.example.Game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static java.lang.System.out;

class ExchangeWithPlayer {
    void main(Player player) {                                                              //осуществляет обмен игрока с другими игроками
        Model m = new Model();
        Game q = new Game();
        out.println("С каким игроком вы хотите обменяться? Введите цифру от 1 до 4 в зависимости от номера игрока");
        int exchanger = m.isNumber();
        Player nameExchanger = m.playerList.get(exchanger - 1);
        if (exchanger <= m.players && player != nameExchanger) {
            out.println("Список ресурсов игрока " + exchanger + ":" + nameExchanger.cards);
            out.println("Выберите свои ресурсы, которые вы готовы отдать ");
            String playerGives = m.in.next();
            String[] playerGivesList = playerGives.split("");
            out.println("Выберите ресурс, который хотите получить");
            String[] playerReceives = m.in.next().split("");
            List<String> cardStrings = player.cards.stream().map(String::valueOf).toList();
            List<String> pg = Arrays.asList(playerGives.split(""));
            List<String> cardStrings1 = nameExchanger.cards.stream().map(String::valueOf).toList();
            List<String> pg1 = Arrays.asList(playerReceives);
            out.println(" игрок " + exchanger + ", вы готовы принять в обмен на " + Arrays.toString(playerReceives) + " ресурс(ы) " + Arrays.toString(playerGivesList) + "? (yes/no)");
            if (m.in.next().equals("yes")) {
                if (m.checkInsExchange(playerReceives) && m.checkInsExchange(playerGivesList) && new HashSet<>(cardStrings).containsAll(pg) && new HashSet<>(cardStrings1).containsAll(pg1)) {
                    for (String item : playerGivesList) {
                        StringBuilder itemToSprite = new StringBuilder();
                        itemToSprite.append(item.charAt(0)).append("_card.png");
                        player.cards.remove(q.getSprite(String.valueOf(itemToSprite)));
                        nameExchanger.cards.add(q.getSprite(String.valueOf(itemToSprite)));
                    }
                    for (String it : playerReceives) {
                        StringBuilder itToSprite = new StringBuilder();
                        itToSprite.append(it.charAt(0)).append("_card.png");
                        player.cards.add(q.getSprite(String.valueOf(itToSprite)));
                        nameExchanger.cards.remove(q.getSprite(String.valueOf(itToSprite)));
                    }
                    out.println("Обмен прошел успешно\nВаши ресурсы " + player.cards + "\nРесурсы соперника: " + nameExchanger.cards);
                } else out.println("Вы неправильно ввели то, что хотите получить взамен или отдаваемые ресурсы");
            } else out.println(" игрок " + exchanger + " не согласен на обмен");
        } else out.println("Вы ввели номер игрока, с которым хотите обменяться, неправильно");
    }
}
