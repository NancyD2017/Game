package org.example.Model;

import org.example.Controller.Buttons_Colors;
import org.example.Controller.Buttons_Turns;
import org.example.Controller.StringCatcher;

import java.util.*;
import java.util.List;


import static java.lang.System.out;

public class Model {
    public static Integer players;
    Scanner in = new Scanner(System.in);
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
    static int mostKnights = 0;                                                                                         //эти 6 переменных с говорящими именами так или иначе позволяют определить победителя
    static int mostPoints = 2;                                                                                          //два очка причисляются в самом начале, потому что сразу строятся 2 поселения
    static Player mostKnightsHolder = playerList.get(0);
    static int leastRoadsLeft = 13;
    static Player leastRoadsLeftHolder = playerList.get(0);
    public static List<Colors> colors = new ArrayList<>(Arrays.asList(Colors.values()));
    static Player mostPointsHolder = playerList.get(0);
    FirstBuilding f = new FirstBuilding();
    ExchangeWithPlayer p = new ExchangeWithPlayer();
    ExchangeWithPorts ports = new ExchangeWithPorts();
    StringCatcher catcher = new StringCatcher();
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
        playerList.get(0).cards.add('s');
        playerList.get(0).cards.add('f');
        playerList.get(0).cards.add('t');
        evolutionCards.addAll(List.of("church", "embassy", "funfair", "library", "poly"));
        evolutionCards.addAll(Collections.nCopies(20, "knight"));
        evolutionCards.addAll(Collections.nCopies(3, "map"));
        evolutionCards.addAll(Collections.nCopies(3, "forward1"));
        evolutionCards.addAll(Collections.nCopies(3, "forward2"));
        Collections.shuffle(evolutionCards);                                                                                                 //добавляет нужное количество карточек развития
        catcher.makeMessage("Welcome to the game Catan: colonists!<br>Choose the number<br> of players between 2 and 4:", "GreetingWindow");
        while (players == null) players = (Integer) catcher.getData("org.example.Controller.GreetingWindow");
        polePrinting();
        for (int i = 0; i < players ; i++) {
            String choice = null;
            catcher.makeMessage("Choose the color of dibs <br> for player " + (i + 1) + " from possible ", "Buttons_Colors");
            while (choice == null || choice.isEmpty()) choice = (String) catcher.getData("org.example.Controller.Buttons_Colors");
            Buttons_Colors.messageToPass = null;
            i -= f.main(i, Colors.valueOf(choice));
        }
        polePrinting();
        for (int i = players; i > 0; i--) {
            FirstBuilding.addText = ("<br> player " + i + ", it's your turn. Build one more town and road<b>");
            i += f.main(i - 1, playerList.get(i - 1).color);
        }
        polePrinting();
        gameProcess();
    }
    void gameProcess() {                                                                                  //осушествляет ходы игроков, броски кубиков и определение победителей
        StringCatcher catcher2 = new StringCatcher();
        while (mostPoints < 10) {
            boolean retakeTurn = false;
            for (int k = 0; k < players; k++) {
                if (!retakeTurn) {
                    Random random = new Random();
                    int cubesNumber = 0;
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
            catcher2.makeMessage("Most points holder is player " + (playerList.indexOf(mostPointsHolder) + 1) + ": " + mostPoints +
            "<br>Most knights holder is player " + (playerList.indexOf(mostKnightsHolder) + 1) + ": " + mostKnights +
            "<br>Most roads holder is player " + (playerList.indexOf(leastRoadsLeftHolder) + 1) + ": " + (15 - leastRoadsLeft),"Removal");
        }
        catcher2.makeMessage("Congratulates to winner!<br>and the winner is...<br><br> " + playerList.indexOf(mostPointsHolder) + "!", "Default");
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
        catcher.makeMessage("Player " + (l + 1) + ", it's your turn. these are your resources:<br> " + player.cards +
                "<br>Choose your next action", "Turn");
        while (!allRight) {
            Integer action = null;
            while (action == null) action = (Integer) catcher.getData("org.example.Controller.Buttons_Turns");
            Buttons_Turns.messageToPass = null;
            switch (action) {
                case 1 -> {
                    allRight = true;
                    l -= b.act(player, hexes);
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
                    new EvolutionCards(new Model(), player).main(player);
                }
                case 5 -> allRight = true;
            }
        }
        return l;
    }
}