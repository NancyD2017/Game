package org.example.model;

import org.example.controller.Buttons_Colors;
import org.example.controller.Buttons_Turns;
import org.example.controller.StringCatcher;
import org.example.Game;

import java.util.*;
import java.util.List;

public class Model {
    public static Integer players;
    public static int cubesNumber = 0;
    static public ArrayList<Character> hexes = new ArrayList<>();
    static Item[][] field = {
            {Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O},                                          //это само игровое поле, на котором размещаются дороги, поселения и города
            {Item.r, Item.r, Item.r, Item.r},                                                                                                                   //O означает место построения города или поселения, r - дорожки
            {Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O},       //если игрок что-то построил на конкретном месте, там высветятся нужные буквы,
            {Item.r, Item.r, Item.r, Item.r, Item.r},                                                                                                         //причем r (No road) изменится на R
            {Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O},
            {Item.r, Item.r, Item.r, Item.r, Item.r, Item.r},
            {Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O},
            {Item.r, Item.r, Item.r, Item.r, Item.r},
            {Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O},
            {Item.r, Item.r, Item.r, Item.r},
            {Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O, Item.r, Item.O}
    };
    public static List<Player> playerList = List.of(new Player(), new Player(), new Player(), new Player());
    static ArrayList<String> evolutionCards = new ArrayList<>();                                                        //так как максимальное количество игроков - 4, сразу задаем их. После список будет весьма полезен
    public static int mostKnights = 0;                                                                                         //эти 6 переменных с говорящими именами так или иначе позволяют определить победителя
    public static int mostPoints = 2;                                                                                          //два очка причисляются в самом начале, потому что сразу строятся 2 поселения
    public static Player mostKnightsHolder = playerList.get(0);
    public static int leastRoadsLeft = 13;
    public static Player leastRoadsLeftHolder = playerList.get(0);
    public static List<Colors> colors = new ArrayList<>(Arrays.asList(Colors.values()));
    public static Player mostPointsHolder = playerList.get(0);
    FirstBuilding f = new FirstBuilding();
    ExchangeWithPlayer p = new ExchangeWithPlayer();
    ExchangeWithPorts ports = new ExchangeWithPorts();
    public static Game q = new Game();
    Build b = new Build();
    public void main() {
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
        StringCatcher.makeMessage("Welcome to the game Catan: colonists!<br>Choose the number<br> of players between 2 and 4:", "GreetingWindow");
        while (players == null) players = (Integer) StringCatcher.getData("org.example.controller.GreetingWindow");
        q.main();
        for (int i = 0; i < players ; i++) {
            String choice = null;
            StringCatcher.makeMessage("<html><div style='text-align: center;'>Choose the color of dibs for player " + (i + 1) + " from possible ", "Buttons_Colors");
            while (choice == null || choice.isEmpty()) choice = (String) StringCatcher.getData("org.example.controller.Buttons_Colors");
            Buttons_Colors.messageToPass = null;
            i -= f.main(i, Colors.valueOf(choice));
        }
        for (int i = players; i > 0; i--) {
            FirstBuilding.addText = ("player " + i + ", build one more town and road<br>");
            i += f.main(i - 1, playerList.get(i - 1).color);
        }
        gameProcess();
    }
    void gameProcess() {                                                                                  //осушествляет ходы игроков, броски кубиков и определение победителей
        while (mostPoints < 10) {
            boolean retakeTurn = false;
            for (int k = 0; k < players; k++) {
                if (!retakeTurn) {
                    Random random = new Random();
                    cubesNumber = 0;
                    while (!((cubesNumber >= 2 && cubesNumber <= 12))) {
                        cubesNumber = random.nextInt(11) + 2;
                    }
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
                        mostPoints = playerList.get(k).points;
                    }
                    retakeTurn = false;
                }
            }
        }
        StringCatcher.makeMessage("Congratulates to winner!<br>and the winner is...<br><br> " + playerList.indexOf(mostPointsHolder) + "!", "Default");
    }
    Integer illusionOfChoice(Integer l, Player player) {                                                  //каждый ход игрока он может что-то купить, что-то построить или обменяться
        boolean allRight = false;
        StringCatcher.makeMessage("Player " + (l + 1) + ", it's your turn.Choose your next action", "Turn");
        while (!allRight) {
            Integer action = null;
            while (action == null) action = (Integer) StringCatcher.getData("org.example.controller.Buttons_Turns");
            Buttons_Turns.messageToPass = null;
            switch (action) {
                case 1 -> {
                    allRight = true;
                    l -= b.act(player);
                }
                case 2 -> {
                    allRight = true;
                    ports.act(player);
                }
                case 3 -> {
                    allRight = true;
                    p.act(player);
                }
                case 4 -> {
                    allRight = true;
                    new EvolutionCards().main(player);
                }
                case 5 -> allRight = true;
            }
        }
        return l;
    }
}