package org.example.Model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static java.lang.System.out;

class ExchangeWithPlayer {
    void main(Player player) {                                                              //осуществляет обмен игрока с другими игроками
        out.println("С каким игроком вы хотите обменяться? Введите цифру от 1 до 4 в зависимости от номера игрока");
        int exchanger = Model.isNumber();
        Player nameExchanger = Model.playerList.get(exchanger - 1);
        if (exchanger <= Model.players && player != nameExchanger) {
            out.println("Список ресурсов игрока " + exchanger + ":" + nameExchanger.cards);
            out.println("Выберите свои ресурсы, которые вы готовы отдать ");
            String playerGives = Model.in.next();
            String[] playerGivesList = playerGives.split("");
            out.println("Выберите ресурс, который хотите получить");
            String[] playerReceives = Model.in.next().split("");
            List<String> cardStrings = player.cards.stream().map(String::valueOf).toList();
            List<String> pg = Arrays.asList(playerGives.split(""));
            List<String> cardStrings1 = nameExchanger.cards.stream().map(String::valueOf).toList();
            List<String> pg1 = Arrays.asList(playerReceives);
            out.println(" игрок " + exchanger + ", вы готовы принять в обмен на " + Arrays.toString(playerReceives) + " ресурс(ы) " + Arrays.toString(playerGivesList) + "? (yes/no)");
            if (Model.in.next().equals("yes")) {
                if (Model.checkInsExchange(playerReceives) && Model.checkInsExchange(playerGivesList) && new HashSet<>(cardStrings).containsAll(pg) && new HashSet<>(cardStrings1).containsAll(pg1)) {
                    for (String item : playerGivesList) {
                        player.cards.remove(Character.valueOf(item.charAt(0)));
                        nameExchanger.cards.add(item.charAt(0));
                    }
                    for (String it : playerReceives) {
                        player.cards.add(it.charAt(0));
                        nameExchanger.cards.remove(Character.valueOf(it.charAt(0)));
                    }
                    out.println("Обмен прошел успешно\nВаши ресурсы " + player.cards + "\nРесурсы соперника: " + nameExchanger.cards);
                } else out.println("Вы неправильно ввели то, что хотите получить взамен или отдаваемые ресурсы");
            } else out.println(" игрок " + exchanger + " не согласен на обмен");
        } else out.println("Вы ввели номер игрока, с которым хотите обменяться, неправильно");
    }
}
