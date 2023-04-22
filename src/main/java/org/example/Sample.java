package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Sample {
    public static Integer players;
    public static Scanner in = new Scanner(System.in);
    public static ArrayList<Character> hexes = new ArrayList<>();
    static class Triple{
        Integer col;
        Integer ro;
        char item;
        Triple(Integer ro, Integer col, char item) {
            this.ro = ro;
            this.col = col;
            this.item = item;
        }
    }
    public static char[][] disposition = {
            {'0','r','0','r','0','r','0','r','0','r','0','r','0'},
            {'r','r','r','r'},
            {'0','r','0','r','0','r','0','r','0','r','0','r','0','r','0','r','0'},
            {'r','r','r','r','r'},
            {'0','r','0','r','0','r','0','r','0','r','0','r','0','r','0','r','0','r','0','r','0'},
            {'r','r','r','r','r','r'},
            {'0','r','0','r','0','r','0','r','0','r','0','r','0','r','0','r','0','r','0','r','0'},
            {'r','r','r','r','r'},
            {'0','r','0','r','0','r','0','r','0','r','0','r','0','r','0','r','0'},
            {'r','r','r','r'},
            {'0','r','0','r','0','r','0','r','0','r','0','r','0'}
    };
    public static ArrayList<String> evolutionCards = new ArrayList<>();
    public static String thief;
    static Player first = new Player();
    static Player second = new Player();
    static Player third = new Player();
    static Player fourth = new Player();
    static class Player {
        String color;
        Set<Triple> available = new HashSet<>();
        List<Character> cards = new ArrayList<>();
        List<String> cardsEvo = new ArrayList<>();
        Integer points = 0;
        HashSet<String> ports = new HashSet<>();
        HashMap<Integer, List<Character>> element = new HashMap<>();
        int roads = 15;
        int towns = 5;
        int cities = 4;
        public String getColor() {
            return color;
        }
        public Set<Triple> getAvailable() {
            return available;
        }
    }
    public static void main(String[] args) {
        hexes.addAll(Arrays.asList('f','f','f','f','s','s','s','s','t','t','t','w','w','w','w','b','b','b'));
        List<Integer> values = List.of(5,2,6,10,9,4,3,8,11,5,8,4,3,6,10,11,12,9);
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
        Collections.shuffle(evolutionCards);
        System.out.println("Введите количество игроков ");
        players = in.nextInt();
        first.available.add(new Triple(2, 7, 'R'));
        disposition[2][7] = 'R';
        buyEvolutionCard(first);
        System.out.println(first.available + "\n" + disposition);
        HashSet<String> colors = new HashSet<>(Set.of("White", "Blue", "Orange", "Green"));
        for (int i = 0; i<players; i++) {
            System.out.printf("Выберите цвет фишек для игрока %d ", i + 1);
            String choice = in.next();
            if (colors.contains(choice)) {
                if (i == 0) {first.color = choice; i -= firstBuilding(choice, colors, first);}
                if (i == 1) {second.color = choice; i -= firstBuilding(choice, colors, second);}
                if (i == 2) {third.color = choice; i -= firstBuilding(choice, colors, third);}
                if (i == 3) {fourth.color = choice; i -= firstBuilding(choice, colors, fourth);}
            } else {
                System.out.println("Попробуйте еще раз");
                i-=1;
            }
        }
        for (int i = players; i > 0; i--) {
            if (i == 1) {
                System.out.println(" игрок " + i + ", ваш ход. постройте еще 1 поселение и дорожку");
                i += firstBuilding(first.getColor(), colors, first);
            }
            if (i == 2) {
                System.out.println(" игрок " + i + ", ваш ход. постройте еще 1 поселение и дорожку");
                i += firstBuilding(second.getColor(), colors, second);
            }
            if (i == 3) {
                    System.out.println(" игрок " + i + ", ваш ход. постройте еще 1 поселение и дорожку");
                    i += firstBuilding(third.getColor(), colors, third);
            }
            if (i == 4) {
                System.out.println(" игрок " + i + ", ваш ход. постройте еще 1 поселение и дорожку");
                i += firstBuilding(fourth.getColor(), colors, fourth);
            }
        }
        while (first.points < 10 || second.points < 10 || third.points < 10 || fourth.points < 10) {
            for (int k = 0; k < players; k++) {
                Random random = new Random();
                int cubesNumber = 0;
                while (!((cubesNumber >= 2 && cubesNumber <= 12))) {
                    cubesNumber = random.nextInt(11) + 2;
                }
                List<Character> playerFirstHas = first.element.get(cubesNumber);
                if (playerFirstHas != null) first.cards.addAll(playerFirstHas);
                List<Character> playerSecondHas = second.element.get(cubesNumber);
                if (playerSecondHas != null) second.cards.addAll(playerSecondHas);
                List<Character> playerThirdHas = third.element.get(cubesNumber);
                if (playerThirdHas != null) third.cards.addAll(playerThirdHas);
                List<Character> playerFourthHas = fourth.element.get(cubesNumber);
                if (playerFourthHas != null) fourth.cards.addAll(playerFourthHas);
                if (k == 0) {
                    if (illusionOfChoice(k, first) != k) k -= 1;
                } else if (k == 1) {
                    if (illusionOfChoice(k, second) != k) k -= 1;
                } else if (k == 2) {
                    if (illusionOfChoice(k, third) != k) k -= 1;
                } else if (k == 3) {
                    if (illusionOfChoice(k, fourth) != k) k -= 1;
                }
            }
        }
    }
    public static int firstBuilding(String choice, HashSet<String> colors, Player player) {
        System.out.println("Выберите положение поселения на поле ");
        int row = in.nextInt();
        int column = in.nextInt();
        char pose = disposition[row][column];
        if (pose == '0') {
            colors.remove(choice);
            player.available.add(new Triple(row, column, 't'));
            disposition[row][column] = 't';
            for (int j = 0; j < 1; j++) {
                System.out.println("и расположение дорожки около него ");
                j-= roadBuilding(row, column, player);
            }
            } else {
            System.out.println("Попробуйте еще раз");
            return 1;
        }
        player.points +=1;
        player.roads -=1;
        player.towns -=1;
        getResources(row, column,player);
        if (player.cards.isEmpty()) for (List<Character> list : player.element.values()) {
            player.cards.addAll(list);
        }
        return 0;
    }
    public static void getResources(Integer row, Integer column, Player player) {
        if ((row == 0 && (column == 4 || column == 6 || column == 8)) || (row == 2 && (column == 6 || column == 8 || column == 10))) {
            player.element.put(2, List.of(hexes.get(1)));
        }
        if ((row == 2 && (column == 12 || column == 14 || column == 16)) || (row == 4 && (column == 14 || column == 16 || column == 18))) {
                if (!player.element.containsKey(3)) {
                    player.element.put(3, new ArrayList<>());
                }
                player.element.get(3).add(hexes.get(6));
            }
        if ((row == 6 && (column == 6 || column == 8 || column == 10)) || (row == 8 && (column == 4 || column == 6 || column == 8))) {
                if (!player.element.containsKey(3)) {
                    player.element.put(3, new ArrayList<>());
                }
                player.element.get(3).add(hexes.get(12));
            }
        if ((row == 2 && (column == 8 || column == 10 || column == 12)) || (row == 4 && (column == 10 || column == 12 || column == 14))) {
            if (!player.element.containsKey(4)) {
                player.element.put(4, new ArrayList<>());
            }
            player.element.get(4).add(hexes.get(5));
        } if ((row == 6 && (column == 2 || column == 4 || column == 6)) || (row == 8 && (column == 0 || column == 2 || column == 4))) {
            if (!player.element.containsKey(4)) {
                player.element.put(4, new ArrayList<>());
            }
            player.element.get(4).add(hexes.get(11));
        }
        if ((row == 0 && (column == 0 || column == 2 || column == 4)) || (row == 2 && (column == 2 || column == 4 || column == 6))) {
            if (!player.element.containsKey(5)) {
                player.element.put(5, new ArrayList<>());
            }
            player.element.get(5).add(hexes.get(0));
        }
        if ((row == 4 || row == 6) && (column == 12 || column == 14 || column == 16)) {
            if (!player.element.containsKey(5)) {
                player.element.put(5, new ArrayList<>());
            }
            player.element.get(5).add(hexes.get(9));
        }
            if ((row == 0 && (column == 8 || column == 10 || column == 12)) || (row == 2 && (column == 10 || column == 12 || column == 14))){
            if (!player.element.containsKey(6)) {
                player.element.put(6, new ArrayList<>());
            }
            player.element.get(6).add(hexes.get(2));
        }
            if (((row == 6) && (column == 10 || column == 12 || column == 14)) || ((row == 8) && (column == 8 || column == 10 || column == 12))) {
                if (!player.element.containsKey(6)) {
                    player.element.put(6, new ArrayList<>());
                }
                player.element.get(6).add(hexes.get(13));
            }
    if ((row == 4 || row == 6) && (column == 0 || column == 2 || column == 4)) {
            if (!player.element.containsKey(8)) {
                player.element.put(8, new ArrayList<>());
            }
            player.element.get(8).add(hexes.get(7));
        }
        if ((row == 4 || row == 6) && (column == 16 || column == 18 || column == 20)) {
            if (!player.element.containsKey(8)) {
                player.element.put(8, new ArrayList<>());
            }
            player.element.get(8).add(hexes.get(10));
        }
        if (((row == 2) && (column == 4 || column == 6 || column == 8)) || ((row == 4) && (column == 6 || column == 8 || column == 10))) {
            if (!player.element.containsKey(9)) {
                player.element.put(9, new ArrayList<>());
            }
            player.element.get(9).add(hexes.get(4));
        } if (((row == 8) && (column == 10 || column == 12 || column == 14)) || ((row == 10) && (column == 8 || column == 10 || column == 12))) {
            if (!player.element.containsKey(9)) {
                player.element.put(9, new ArrayList<>());
            }
            player.element.get(9).add(hexes.get(17));
        }
    if (((row == 2) && (column == 0 || column == 2 || column == 4)) || ((row == 4) && (column == 2 || column == 4 || column == 6))) {
            if (!player.element.containsKey(10)) {
                player.element.put(10, new ArrayList<>());
            }
            player.element.get(10).add(hexes.get(3));
        } if (((row == 6) && (column == 14 || column == 16 || column == 18)) || ((row == 8) && (column == 12 || column == 14 || column == 16))) {
            if (!player.element.containsKey(10)) {
                player.element.put(10, new ArrayList<>());
            }
            player.element.get(10).add(hexes.get(14));
        }
        if ((row == 4 || row == 6) && (column == 4 || column == 6 || column == 8)) {
            if (!player.element.containsKey(11)) {
                player.element.put(11, new ArrayList<>());
            }
            player.element.get(11).add(hexes.get(8));
        }
        if (((row == 8) && (column == 2 || column == 4 || column == 6)) || ((row == 10) && (column == 0 || column == 2 || column == 4))) {
            if (!player.element.containsKey(11)) {
                player.element.put(11, new ArrayList<>());
            }
            player.element.get(11).add(hexes.get(15));
        }
        if (((row == 8) && (column == 6 || column == 8 || column == 10)) || ((row == 10) && (column == 4 || column == 6 || column == 8))) {
            player.element.put(12, List.of(hexes.get(16)));
        }
    }
    public static int roadBuilding(Integer row, Integer column, Player player) {
            int rowR = in.nextInt();
            int columnR = in.nextInt();
            char poseR = disposition[rowR][columnR];
            if (poseR == 'r' && ((rowR == row && ((columnR == column - 1) || (columnR == column + 1))) ||
                    ((((row == 6 || row == 4) && rowR == 5) || (row < 4 && rowR == 1 + row) || (row > 6 && rowR + 1 == row)) && columnR * 4 == column) ||
                    (row <= 4 && columnR * 4 + 2 == column && rowR + 1 == row) || (row >= 6 && columnR * 4 + 2 == column && rowR == 1 + row))) {
                player.available.add(new Triple(rowR, columnR, 'R'));
                disposition[rowR][columnR] = 'R';
                possiblePorts(rowR, columnR, player);
            } else {
                System.out.println("Попробуйте еще раз");
                return (1);
            }
        return 0;
    }
    public static void possiblePorts(Integer rowR, Integer columnR, Player player) {
        switch (rowR) {
            case 0 -> {
                if (columnR == 5 || columnR == 7 || columnR == 9) player.ports.add("s");
                if (columnR == 1 || columnR == 3) player.ports.add("3");
            }
            case 1 -> {
                if (columnR == 2) player.ports.add("s");
                if (columnR == 0) player.ports.add("3");
                if (columnR == 3) player.ports.add("t");
            }
            case 2 -> {
                if (columnR == 1) player.ports.add("w");
                if (columnR == 13 || columnR == 15) player.ports.add("t");
            }
            case 3 -> {
                if (columnR == 4) player.ports.add("t");
                if (columnR == 0) player.ports.add("w");
            }
            case 4 -> {
                if (columnR == 19) player.ports.add("3");
                if (columnR == 1 || columnR == 3) player.ports.add("w");
            }
            case 5 -> {
                if (columnR == 5) player.ports.add("3");
            }
            case 6 -> {
                if (columnR == 19) player.ports.add("3");
                if (columnR == 1 || columnR == 3) player.ports.add("b");
            }
            case 7 -> {
                if (columnR == 0) player.ports.add("b");
                if (columnR == 4) player.ports.add("f");
            }
            case 8 -> {
                if (columnR == 1) player.ports.add("b");
                if (columnR == 13 || columnR == 15) player.ports.add("f");
            }
            case 9 -> {
                if (columnR == 0) player.ports.add("3");
                if (columnR == 3) player.ports.add("f");
            }
            case 10 -> {
                if (columnR == 1 || columnR == 3 || columnR == 5 || columnR == 7 || columnR == 9)
                    player.ports.add("3");
            }
        }
    }
    public static int build(Player player) {
        System.out.printf("%s, что вы хотите построить?", player.toString());
        String someBuilding = in.next();
        boolean allRight = false;
        switch (someBuilding) {
            case "R" -> {
                if (player.roads > 0) {
                    if (player.cards.containsAll(List.of('b', 'w'))) {
                        List<Character> toRemove = new ArrayList<>(List.of('b', 'w'));
                        for (Character c : toRemove) {
                            player.cards.remove(c);
                        }
                        System.out.println("Введите расположение дорожки около города, поселения или другой дорожки ");
                        int rowR = in.nextInt();
                        int columnR = in.nextInt();
                        char poseR = disposition[rowR][columnR];
                        Set<Triple> playersArsenal = player.getAvailable();
                        for (Triple t : playersArsenal) {
                            Integer isBigger;
                            if (columnR > t.col) isBigger = 1;
                            else isBigger = -1;
                            if (poseR == 'r' && ((rowR == t.ro && ((columnR == t.col - 1) || (columnR == t.col + 1))) ||
                                    ((((t.ro == 6 || t.ro == 4) && rowR == 5) || (t.ro < 4 && rowR == 1 + t.ro) || (t.ro > 6 && rowR + 1 == t.ro)) && columnR * 4 == t.col) ||
                                    (t.ro <= 4 && columnR * 4 + 2 == t.col && rowR + 1 == t.ro) || (t.ro >= 6 && columnR * 4 + 2 == t.col && rowR == 1 + t.ro) ||
                                    ((t.item == 'R') && (((t.ro == rowR + 1) && (t.col * 4 + 1 == columnR) && (t.ro < 6)) || ((t.ro + 1 == rowR) && (t.col == columnR * 4 + 1) && (t.ro < 6)) ||
                                            ((t.ro == rowR + 1) && (t.col * 4 + 3 == columnR) && (t.ro > 5)) || ((t.ro + 1 == rowR) && (t.col == columnR * 4 + 3) && (t.ro > 5)) ||
                                            ((t.ro == rowR + 1) && (t.col * 4 - 1 == columnR) && (t.ro > 6)) || ((t.ro + 1 == rowR) && (t.col == columnR * 4 - 1) && (t.ro > 6)) ||
                                            ((t.ro == rowR + 1) && (t.col == columnR * 4 + 1) && (t.ro > 5)) || ((t.ro + 1 == rowR) && (t.col * 4 + 1 == columnR) && (t.ro > 5)))))) {
                                player.available.add(new Triple(rowR, columnR, 'R'));
                                disposition[rowR][columnR] = 'R';
                                allRight = true;
                                possiblePorts(rowR, columnR, player);
                                break;
                            }
                        }
                    } else {
                        allRight = true;
                        System.out.println("У вас недостаточно ресурсов");
                    }
                } else System.out.println("У вас закончились дорожки");
            }
            case "t" -> {
                if (player.towns > 0) {
                    if (player.cards.containsAll(List.of('b', 'w', 's', 'f'))) {
                        List<Character> toRemove = new ArrayList<>(List.of('b', 'w', 's', 'f'));
                        for (Character c : toRemove) {
                            player.cards.remove(c);
                        }
                        System.out.println("Введите расположение поселения около дорожек ");
                        int row = in.nextInt();
                        int column = in.nextInt();
                        char pose = disposition[row][column];
                        Set<Triple> playersArsenal = player.getAvailable();
                        for (Triple t : playersArsenal)
                            if (pose == '0' && (((t.ro == row) && (t.col == column - 1 || t.col == column + 1)) ||
                                    (((row == t.ro + 1) || (row == t.ro - 1)) && (t.ro == 4 || t.ro == 6 || (t.ro < 4 && row > t.ro) || (t.ro > 6 && row < t.ro)) && t.col == column * 4) ||
                                    (((row == t.ro + 1) || (row == t.ro - 1)) && ((t.ro < 4 && row < t.ro) || (t.ro > 6 && row > t.ro)) && t.col == column * 4 + 2))) {
                                player.available.add(new Triple(row, column, 't'));
                                disposition[row][column] = 't';
                                allRight = true;
                                player.points += 1;
                                getResources(row, column, player);
                                break;
                            }
                    } else {
                        allRight = true;
                        System.out.println("У вас недостаточно ресурсов");
                    }
                } else System.out.println("У вас закончились поселения");
            }
            case "c" -> {
                if (player.cities > 0) {
                    if (player.cards.containsAll(List.of('s', 's', 't', 't', 't'))) {
                        List<Character> toRemove = new ArrayList<>(List.of('s', 's', 't', 't', 't'));
                        for (Character c : toRemove) {
                            player.cards.remove(c);
                        }
                        System.out.println("Введите расположение города на месте поселения ");
                        int rowC = in.nextInt();
                        int columnC = in.nextInt();
                        char poseC = disposition[rowC][columnC];
                        Triple toDelete = null;
                        Set<Triple> playersArsenal = player.getAvailable();
                        for (Triple t : playersArsenal)
                            if (poseC == 't') {
                                player.available.add(t = new Triple(rowC, columnC, 'c'));
                                toDelete = t;
                                disposition[rowC][columnC] = 'c';
                                allRight = true;
                                player.points += 1;
                                getResources(rowC, columnC, player);
                                break;
                            }
                        playersArsenal.remove(toDelete);
                    } else {
                        allRight = true;
                        System.out.println("У вас недостаточно ресурсов");
                    }
                } else System.out.println("У вас закончились дорожки");
            }
        }
        if (!allRight) {
            System.out.println("Неправильные координаты или команда. Попробуйте еще раз");
            return 1;
        }
        else return 0;
    }
    public static void exchangeWithPorts(Player player){
        if (player.ports.size() == 0) {
            System.out.println("Выберите ресурсы для обмена 1:4");
            String playerGives = in.next();
            String[] playerGivesList = playerGives.split("");
            List<String> pg = Arrays.asList(playerGives.split(""));
            List<String> cardStrings = player.cards.stream().map(String::valueOf).collect(Collectors.toList());
            if (checkInsExchange(playerGivesList) && cardStrings.containsAll(pg)) {
                for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                System.out.println("Выберите ресурс, который хотите получить");
                String[] playerReceives = in.next().split("");
                if (checkInsExchange(playerReceives) && playerReceives.length == 1) {
                    player.cards.add(playerReceives[0].charAt(0));
                    System.out.println("Обмен прошел успешно" + player.cards);
                }
            } else System.out.println("Недостаточно ресурсов для обмена");
        } else {
            System.out.println("Список доступных обменов: " + player.ports.stream().toList() + " 4\nВыберите любой");
            String playerPort = in.next();
            System.out.println("Что вы хотите получить взамен? ");
            String playerReceives = in.next();
            if ((player.ports.contains(playerPort) || playerPort.equals("4")) && checkInsExchange(playerReceives.split(""))) {
                if (!playerPort.equals("3") && !playerPort.equals("4") && Collections.frequency(player.cards, playerPort.charAt(0)) >= 2) {
                    player.cards.remove(Character.valueOf(playerPort.charAt(0)));
                    player.cards.remove(Character.valueOf(playerPort.charAt(0)));
                    player.cards.add(playerReceives.charAt(0));
                    System.out.println("Обмен прошел успешно");
                } else if (playerPort.equals("3") || playerPort.equals("4")) {
                    if (player.cards.size() >= Integer.parseInt(playerPort)){
                        System.out.println("Какие ресурсы вы бы хотели отдать? ");
                        String playerGives = in.next();
                        String[] playerGivesList = playerGives.split("");
                        List<String> pg = Arrays.asList(playerGives.split(""));
                        List<String> cardStrings = player.cards.stream().map(String::valueOf).collect(Collectors.toList());
                        if (checkInsExchange(playerGivesList) && playerGivesList.length == Integer.parseInt(playerPort) && cardStrings.containsAll(pg) && playerReceives.length() == 1) {
                            for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                            player.cards.add(playerReceives.charAt(0));
                            System.out.println("Обмен прошел успешно");
                        } else System.out.println("Вы ввели не то количество отдаваемых ресурсов");
                    } else System.out.println("Недостаточно ресурсов для обмена");
                } else System.out.println("Недостаточно ресурсов для обмена");
            } else System.out.println("Вы неправильно ввели порт или то, что хотите получить взамен");
        }
    }
    public static void exchangeWithPlayer(Player player) {
        System.out.println("С каким игроком вы хотите обменяться?");
        int exchanger = in.nextInt();
        Player nameExchanger = null;
        switch (exchanger) {
            case 1 -> nameExchanger = first;
            case 2 -> nameExchanger = second;
            case 3 -> nameExchanger = third;
            case 4 -> nameExchanger = fourth;
        }
        if (exchanger <= players && player != nameExchanger) {
            System.out.println("Список ресурсов игрока " + exchanger + ":" + nameExchanger.cards);
            System.out.println("Выберите свои ресурсы, которые вы готовы отдать ");
            String playerGives = in.next();
            String[] playerGivesList = playerGives.split("");
            System.out.println("Выберите ресурс, который хотите получить");
            String[] playerReceives = in.next().split("");
            List<String> cardStrings = player.cards.stream().map(String::valueOf).collect(Collectors.toList());
            List<String> pg = Arrays.asList(playerGives.split(""));
            List<String> cardStrings1 = nameExchanger.cards.stream().map(String::valueOf).collect(Collectors.toList());
            List<String> pg1 = Arrays.asList(playerReceives);
            System.out.println(" игрок " + exchanger + ", вы готовы принять в обмен на " + Arrays.toString(playerReceives) + " ресурс(ы) " + Arrays.toString(playerGivesList) + "?");
            if (in.next().equals("yes")) {
                if (checkInsExchange(playerReceives) && checkInsExchange(playerGivesList) && cardStrings.containsAll(pg) && cardStrings1.containsAll(pg1)) {
                    for (String item : playerGivesList) {
                        player.cards.remove(Character.valueOf(item.charAt(0)));
                        nameExchanger.cards.add(Character.valueOf(item.charAt(0)));
                    }
                    for (String it : playerReceives) {
                        player.cards.add(it.charAt(0));
                        nameExchanger.cards.remove(Character.valueOf(it.charAt(0)));
                    }
                    System.out.println("Обмен прошел успешно\nВаши ресурсы " + player.cards + "\nРесурсы соперника: " + nameExchanger.cards);
                } else System.out.println("Вы неправильно ввели то, что хотите получить взамен или отдаваемые ресурсы");
            } else System.out.println(" игрок " + exchanger + " не согласен на обмен");
            } else System.out.println("Вы ввели номер игрока, с которым хотите обменяться, неправильно");
    }
    public static boolean checkInsExchange(String[] input) {
        for (String item : input) {
            if (!(item.equals("b") || item.equals("w") || item.equals("t") || item.equals("s") || item.equals("f")))
                return false;
        }
        return true;
    }
    public static void buyEvolutionCard(Player player) {
        List<Character> toRemove = List.of('s', 'f', 't');
        for (Character ch : toRemove) {
            if (Collections.frequency(player.cards, ch) >= 1) {
                player.cards.remove(ch);
            }
        }
        //String playersCard = evolutionCards.get(0);
        System.out.println(player.cards);//!!!!!!!!!!!
        String playersCard = "map";
        boolean allRight = false;
        player.cardsEvo.add(playersCard);
        evolutionCards.remove(0);
        System.out.println("Поздравляем! Вы получили " + playersCard);
        while (!allRight) {
        switch (playersCard) {
            case "church", "library", "poly", "funfair", "embassy" -> {
                player.points += 1;
                System.out.println("Вы получили 1 победное очко. Ваш счет: " + player.points);
                allRight = true;
            }
            case "forward1" -> {
                System.out.println("Можете выбрать 2 любые карты ресурсов: ");
                String iReceive = in.next();
                String[] ir = iReceive.split("");
                if (checkInsExchange(ir) && ir.length == 2) {
                    for (String item : ir) player.cards.add(Character.valueOf(item.charAt(0)));
                    allRight = true;
                    System.out.println("Ваши ресурсы: " + player.cards);
                } else {
                    allRight = false;
                    System.out.println("Вы ввели карты ресурсов неправильно");
                }
            }
            case "forward2" -> {
                System.out.println("Можете выбрать 1 ресурс, который вам отдадут все соперники: ");
                String iReceive = in.next();
                String[] ir = iReceive.split("");
                if (checkInsExchange(ir) && ir.length == 1) {
                    int resourcesNumber = 0;
                    resourcesNumber += Collections.frequency(first.cards, Character.valueOf(ir[0].charAt(0)));
                    resourcesNumber += Collections.frequency(second.cards, Character.valueOf(ir[0].charAt(0)));
                    resourcesNumber += Collections.frequency(third.cards, Character.valueOf(ir[0].charAt(0)));
                    resourcesNumber += Collections.frequency(fourth.cards, Character.valueOf(ir[0].charAt(0)));
                    first.cards.removeAll(Collections.singleton(Character.valueOf(iReceive.charAt(0))));
                    second.cards.removeAll(Collections.singleton(Character.valueOf(iReceive.charAt(0))));
                    third.cards.removeAll(Collections.singleton(Character.valueOf(iReceive.charAt(0))));
                    fourth.cards.removeAll(Collections.singleton(Character.valueOf(iReceive.charAt(0))));
                    player.cards.addAll(Collections.nCopies(resourcesNumber, Character.valueOf(ir[0].charAt(0))));
                    allRight = true;
                    System.out.println("Ваши ресурсы: " + player.cards);
                } else allRight = false;
            }
            case "map" -> {
                System.out.println("Можете построить 2 дороги без затраты ресурсов");
                if (player.roads > 0) {
                    System.out.println("Введите расположение дорожки около города, поселения или другой дорожки ");
                    int rowR = in.nextInt();
                    int columnR = in.nextInt();
                    char poseR = disposition[rowR][columnR];
                    Set<Triple> playersArsenal = player.getAvailable();
                    for (Triple t : playersArsenal) {
                        Integer isBigger;
                        if (columnR * 4 > t.col) isBigger = 1;
                        else isBigger = -1;
                        if (poseR == 'r' && ((rowR == t.ro && ((columnR == t.col - 1) || (columnR == t.col + 1))) ||
                                ((((t.ro == 6 || t.ro == 4) && rowR == 5) || (t.ro < 4 && rowR == 1 + t.ro) || (t.ro > 6 && rowR + 1 == t.ro)) && columnR * 4 == t.col) ||
                                (t.ro <= 4 && columnR * 4 + 2 == t.col && rowR + 1 == t.ro) || (t.ro >= 6 && columnR * 4 + 2 == t.col && rowR == 1 + t.ro) ||
                                ((t.item == 'R') && (((t.ro == rowR + 1) && (t.col * 4 + 1 == columnR) && (t.ro < 6)) || ((t.ro + 1 == rowR) && (t.col == columnR * 4 + 1) && (t.ro < 6)) ||
                                        ((t.ro == rowR + 1) && (t.col * 4 + 3 == columnR) && (t.ro > 5)) || ((t.ro + 1 == rowR) && (t.col == columnR * 4 + 3) && (t.ro > 4)) ||
                                        ((t.ro == rowR + 1) && (t.col * 4 - 1 == columnR) && (t.ro > 6)) || ((t.ro + 1 == rowR) && (t.col == columnR * 4 - 1) && (t.ro > 6)) ||
                                        ((t.ro == rowR + 1) && (t.col == columnR * 4 + 1) && (t.ro > 5)) || ((t.ro + 1 == rowR) && (t.col * 4 + 1 == columnR) && (t.ro > 4)))))) {
                            player.available.add(new Triple(rowR, columnR, 'R'));
                            disposition[rowR][columnR] = 'R';
                            allRight = true;
                            possiblePorts(rowR, columnR, player);
                        } else System.out.println("Неправильные координаты дорожки");
                    }
                } else System.out.println("У вас закончились дорожки");
            }
        }
        }
    }
    public static Integer illusionOfChoice(Integer l, Player player) {
        boolean allRight = false;
        while (!allRight) {
            System.out.println(" игрок " + (l + 1) + ", ваш ход. Вот ваши ресурсы " + player.cards);
            System.out.println("Если хотите что-то построить, введите 1\nобменяться с портом - 2\nобменяться с соперником - 3\nкупить карточку развития - 4\nЧтобы пропустить ход - 0");
            int action = in.nextInt();
            switch (action) {
                case 1 -> {
                    allRight = true;
                    l -= build(player);
                }
                case 2 -> {
                    allRight = true;
                    exchangeWithPorts(player);
                }
                case 3 -> {
                    allRight = true;
                    exchangeWithPlayer(player);
                }
                case 4 -> {
                    allRight = true;
                    buyEvolutionCard(player);
                }
                case 0 -> allRight = true;
                default -> allRight = false;
            }
        }
            return l;
    }
}
