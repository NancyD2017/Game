package org.example.Model;

import java.util.*;
import java.util.List;


import static java.lang.System.out;

class Model {
    Integer players;
    Scanner in = new Scanner(System.in);
    ArrayList<Character> hexes = new ArrayList<>();
    Item[][] field = {
            {Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E},                                          //это само игровое поле, на котором размещаются дороги, поселения и города
            {Item.P, Item.P, Item.P, Item.P},                                                                                                                   //E означает место построения города или поселения, NR - дорожки
            {Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E},       //если игрок что-то построил на конкретном месте, там высветятся нужные буквы,
            {Item.P, Item.P, Item.P, Item.P, Item.P},                                                                                                         //причем P (No road) изменится на R
            {Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E},
            {Item.P, Item.P, Item.P, Item.P, Item.P, Item.P},
            {Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E},
            {Item.P, Item.P, Item.P, Item.P, Item.P},
            {Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E},
            {Item.P, Item.P, Item.P, Item.P},
            {Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E, Item.P, Item.E}
    };
    List<Player> playerList = List.of(new Player(), new Player(), new Player(), new Player());
    ArrayList<String> evolutionCards = new ArrayList<>();                                                        //так как максимальное количество игроков - 4, сразу задаем их. После список будет весьма полезен
    int mostKnights = 0;                                                                                         //эти 6 переменных с говорящими именами так или иначе позволяют определить победителя
    int mostPoints = 2;                                                                                          //два очка причисляются в самом начале, потому что сразу строятся 2 поселения
    Player mostKnightsHolder = playerList.get(0);
    int leastRoadsLeft = 13;
    Player leastRoadsLeftHolder = playerList.get(0);
    Player mostPointsHolder = playerList.get(0);
    List<Color> colors = new ArrayList<>(Arrays.asList(Color.values()));
    FirstBuilding f = new FirstBuilding();
    ExchangeWithPlayer p = new ExchangeWithPlayer();
    ExchangeWithPorts ports = new ExchangeWithPorts();
    EvolutionCards c = new EvolutionCards();
    Build b = new Build();
    public void main(String[] args) {
        hexes.addAll(Arrays.asList('f', 'f', 'f', 'f', 's', 's', 's', 's', 't', 't', 't', 'w', 'w', 'w', 'w', 'b', 'b', 'b')); //случайным образом создает поле
        List<Integer> values = List.of(5, 2, 6, 10, 9, 4, 3, 8, 11, 5, 8, 4, 3, 6, 10, 11, 12, 9);
        Collections.shuffle(hexes);
        Map<Integer, List<Character>> description = new HashMap<>();
        for (int i = 0; i < hexes.size(); i++) {
            if (!description.containsKey(values.get(i))) {
                description.put(values.get(i), new ArrayList<>());
            }
            description.get(values.get(i)).add(hexes.get(i));
        }
        evolutionCards.addAll(List.of("church", "embassy", "funfair", "library", "poly"));
        evolutionCards.addAll(Collections.nCopies(20, "knight"));
        evolutionCards.addAll(Collections.nCopies(3, "map"));
        evolutionCards.addAll(Collections.nCopies(3, "forward1"));
        evolutionCards.addAll(Collections.nCopies(3, "forward2"));
        Collections.shuffle(evolutionCards);                                                                                                 //добавляет нужное количество карточек развития
        out.println("Введите количество игроков от 2 до 4:");
        players = isNumber();
        if (!(players >= 2 && players <= 4)) {
            out.println("Количество игроков - число от 2 до 4. Попробуйте еще раз");
            players = isNumber();
        }
        polePrinting();

        for (int i = 0; i < players; i++) {
            out.println("Выберите цвет фишек для игрока " + (i + 1) + " из возможных: " + colors);
            String choice = in.next();
            try {
                Color mcolor = Color.valueOf(choice);
                playerList.get(i).color = mcolor;
                i -= f.main(playerList.get(i), mcolor);
            } catch (IllegalArgumentException e) {
                out.println("Попробуйте еще раз");
                i -= 1;
            }
        }
        polePrinting();
        for (int i = players; i > 0; i--) {
            out.println("\nигрок " + i + ", ваш ход. Постройте еще 1 поселение и дорожку");
            i += f.main(playerList.get(i), playerList.get(i).color);
        }
        polePrinting();
        gameProcess();
    }
    void gameProcess() {                                                                                  //осушествляет ходы игроков, броски кубиков и определение победителей
        while (mostPoints < 10) {
            boolean retakeTurn = false;
            for (int k = 0; k < players; k++) {
                if (!retakeTurn) {
                    Random random = new Random();
                    int cubesNumber = 0;
                    while (!((cubesNumber >= 2 && cubesNumber <= 12))) {
                        cubesNumber = random.nextInt(11) + 2;
                    }
                    out.println("Выпало число " + cubesNumber);
                    for (int pointsForPlayer = 0; pointsForPlayer < players; pointsForPlayer++) {
                        List<Character> elementsToReceive = playerList.get(pointsForPlayer).element.get(cubesNumber);
                        if (elementsToReceive != null) playerList.get(pointsForPlayer).cards.addAll(elementsToReceive);
                    }
                }
                if (illusionOfChoice(k, playerList.get(k)) != k) {
                    k -= 1;
                    retakeTurn = true;
                }
                else {
                    if (playerList.get(k).knights >= 3 && mostKnights < playerList.get(k).knights) {
                        playerList.get(k).points += 2;
                        if (mostKnightsHolder == null) mostKnightsHolder = playerList.get(k);
                        else mostKnightsHolder.points -= 2;
                    }
                    if (playerList.get(k).roads <= 15 - 5 && leastRoadsLeft > playerList.get(k).roads) {
                        playerList.get(k).points += 2;
                        if (leastRoadsLeftHolder == null) leastRoadsLeftHolder = playerList.get(k);
                        else leastRoadsLeftHolder.points -= 2;
                    }
                    if (mostPoints < playerList.get(k).points) {
                        mostPointsHolder = playerList.get(k);
                    }
                    retakeTurn = false;
                }
            }
            out.println("Конфигурация поля на данный момент:");
            polePrinting();
            out.println("Больше всего очков у игрока " + (playerList.indexOf(mostPointsHolder) + 1) + ": " + mostPoints);
            out.println("Больше всего рыцарей у игрока " + (playerList.indexOf(mostKnightsHolder) + 1) + ": " + mostKnights);
            out.println("Больше всего дорог у игрока " + (playerList.indexOf(leastRoadsLeftHolder) + 1) + ": " + (15 - leastRoadsLeft));
        }
        out.println("Поздравляю победителя!");
        out.println("Больше всего очков набрал: " + playerList.indexOf(mostPointsHolder));
    }
    void polePrinting() {                                                                                 //печатает поле для удобства игроков
        int spaces = 8;
        for (int row = 0; row < 11; row++) {
            out.print(" ".repeat(spaces));
            for (int column = 0; column < field[row].length; column++) {
                if (row % 2 == 0) out.print(field[row][column] + " ");
                else out.print(field[row][column] + "       ");
            }
            out.println();
            if (row < 5 && row % 2 == 1) spaces -= 4;
            else if (row % 2 == 0 && row > 5) spaces += 4;
        }
    }
    int isNumber() {                                                                                      //устраняет ошибки, когда пользователь ввел не число
        boolean isOk = false;
        int number = 0;
        while (!isOk) {
            if (in.hasNextInt()) {
                number = in.nextInt();
                isOk = true;
            } else {
                out.println("Некорректный ввод. Попробуйте еще раз:");
                in.next();
            }
        }
        return number;
    }
    boolean checkInsExchange(String[] input) {                                                            //так как ресурсы при обмене должны быть введены верно, я ввела обозначения и добавила эту функцию
        for (String item : input) {                                                                                     //b - кирпич, w - дерево, t - камень, s - сено, f - овцы,
            if (!(item.equals("b") || item.equals("w") || item.equals("t") || item.equals("s") || item.equals("f")))
                return false;
        }
        return true;
    }
    Integer illusionOfChoice(Integer l, Player player) {                                                  //каждый ход игрока он может что-то купить, что-то построить или обменяться
        boolean allRight = false;
        while (!allRight) {
            out.println(" игрок " + (l + 1) + ", ваш ход. Вот ваши ресурсы " + player.cards);
            out.println("Если хотите что-то построить, введите 1\nобменяться с портом - 2\nобменяться с соперником - 3\nкупить карточку развития - 4\nЧтобы пропустить ход - 0");
            int action = isNumber();
            switch (action) {
                case 1 -> {
                    allRight = true;
                    l -= b.main(player);
                }
                case 2 -> {
                    allRight = true;
                    ports.main(player);
                }
                case 3 -> {
                    allRight = true;
                    p.main(player);
                }
                case 4 -> {
                    allRight = true;
                    c.main(player);
                }
                case 0 -> allRight = true;
                default -> out.println("Вы ввели не то действие. Попробуйте еще раз");
            }
        }
        return l;
    }
}