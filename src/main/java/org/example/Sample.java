package org.example;

import java.util.*;

public class Sample {
    static Integer players;
    static Scanner in = new Scanner(System.in);
    static ArrayList<Character> hexes = new ArrayList<>();

    static class Triple {
        Integer col;
        Integer ro;
        char item;

        Triple(Integer ro, Integer col, char item) {
            this.ro = ro;
            this.col = col;
            this.item = item;
        }
    }

    static char[][] disposition = {
            {'0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0'},                                          //это само игровое поле, на котором размещаются дороги, поселения и города
            {'r', 'r', 'r', 'r'},                                                                                       //0 означает место построения города или поселения, r - дорожки
            {'0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0'},                      //если игрок что-то построил на конкретном месте, там высветятся нужные буквы,
            {'r', 'r', 'r', 'r', 'r'},                                                                                  //причем R станет большой
            {'0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0'},
            {'r', 'r', 'r', 'r', 'r', 'r'},
            {'0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0'},
            {'r', 'r', 'r', 'r', 'r'},
            {'0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0'},
            {'r', 'r', 'r', 'r'},
            {'0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0', 'r', '0'}
    };
    static ArrayList<String> evolutionCards = new ArrayList<>();                                                 //специальные карточки (для удобства можно посмотреть main/resources/c_..., там они все описаны)
    static List<Player> playerList = List.of(new Player(), new Player(), new Player(), new Player());            //так как максимальное количество игроков - 4, сразу задаем их. После список будет весьма полезен
    static int mostKnights = 0;                                                                                         //эти 6 переменных с говорящими именами так или иначе позволяют определить победителя
    static int mostPoints = 2;                                                                                          //два очка причисляются в самом начале, потому что сразу строятся 2 поселения
    static Player mostKnightsHolder = playerList.get(0);
    static int leastRoadsLeft = 13;
    static Player leastRoadsLeftHolder = playerList.get(0);
    static Player mostPointsHolder = playerList.get(0);
    static class Player {
        String color;
        Set<Triple> available = new HashSet<>();                                                                        //показывает расположение фигурок этого игрока в формате: ряд, столбец, название
        List<Character> cards = new ArrayList<>();                                                                      //карточки ресурсов для каждого игрока, с помощью которых можно что-то купить, обменять
        Integer points = 0;
        HashSet<String> ports = new HashSet<>();                                                                         //показывает порты игрока - это реализует обмен с портом
        HashMap<Integer, List<Character>> element = new HashMap<>();                                                    //показывает, что получает игрок при конкретном броске кубика
        int roads = 15;
        int towns = 5;
        int cities = 4;
        int knights = 0;
    }
    public static void main(String[] args) {
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
        Collections.shuffle(evolutionCards);                                                                            //добавляет нужное количество карточек развития
        System.out.println("Введите количество игроков от 2 до 4:");
        players = isNumber();
        if (!(players >= 2 && players <= 4)) {
            System.out.println("Количество игроков - число от 2 до 4. Попробуйте еще раз");
            players = isNumber();
        }
        polePrinting();
        HashSet<String> colors = new HashSet<>(Set.of("White", "Blue", "Orange", "Green"));                             //осуществляет постройку первого поселения и дорожки игрока
        for (int i = 0; i < players; i++) {
            System.out.println("Выберите цвет фишек для игрока " + (i + 1) + " из возможных: " + colors);
            String choice = in.next();
            if (colors.contains(choice)) {
                playerList.get(i).color = choice;
                i -= firstBuilding(choice, colors, playerList.get(i));
            } else {
                System.out.println("Попробуйте еще раз");
                i -= 1;
            }
        }
        polePrinting();
        for (int i = players; i > 0; i--) {
            System.out.println("\nигрок " + i + ", ваш ход. Постройте еще 1 поселение и дорожку");
            i += firstBuilding(playerList.get(i).color, colors, playerList.get(i));
        }
        polePrinting();
        gameProcess();
    }
    static void gameProcess() {                                                                                  //осушествляет ходы игроков, броски кубиков и определение победителей
        while (mostPoints < 10) {
            for (int k = 0; k < players; k++) {
                Random random = new Random();
                int cubesNumber = 0;
                while (!((cubesNumber >= 2 && cubesNumber <= 12))) {
                    cubesNumber = random.nextInt(11) + 2;
                }
                System.out.println("Выпало число " + cubesNumber);
                for (int pointsForPlayer = 0; pointsForPlayer < players; pointsForPlayer++) {
                    List<Character> elementsToReceive = playerList.get(pointsForPlayer).element.get(cubesNumber);
                    if (elementsToReceive != null) playerList.get(pointsForPlayer).cards.addAll(elementsToReceive);
                }
                if (illusionOfChoice(k, playerList.get(k)) != k) k -= 1;
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
            }
            System.out.println("Конфигурация поля на данный момент:");
            polePrinting();
            System.out.println("Больше всего очков у игрока " + (playerList.indexOf(mostPointsHolder) + 1) + ": " + mostPoints);
            System.out.println("Больше всего рыцарей у игрока " + (playerList.indexOf(mostKnightsHolder) + 1) + ": " + mostKnights);
            System.out.println("Больше всего дорог у игрока " + (playerList.indexOf(leastRoadsLeftHolder) + 1) + ": " + (15 - leastRoadsLeft));
        }
        System.out.println("Поздравляю победителя!");
        System.out.println("Больше всего очков набрал: " + playerList.indexOf(mostPointsHolder));
    }
    static void polePrinting() {                                                                                 //печатает поле для удобства игроков
        int spaces = 8;
        for (int row = 0; row < 11; row++) {
            System.out.print(" ".repeat(spaces));
            for (int column = 0; column < disposition[row].length; column++) {
                if (row % 2 == 0) System.out.print(disposition[row][column] + " ");
                else System.out.print(disposition[row][column] + "       ");
            }
            System.out.println();
            if (row < 5 && row % 2 == 1) spaces -= 4;
            else if (row % 2 == 0 && row > 5) spaces += 4;
        }
    }
    static boolean coordinatesCheck(int row, int column) {                                                       //помогает избежать ошибок, если игрок ввел неправильный ряд или столбик
        if (column >= 0 && row >= 0) {
            return ((row == 0 || row == 10) && (column <= 12)) || ((row == 1 || row == 9) && (column <= 3))
                    || ((row == 2 || row == 8) && (column <= 16)) || ((row == 3 || row == 7) && (column <= 4))
                    || ((row == 4 || row == 6) && (column <= 20)) || (row == 5 && column <= 5);
        }
        return false;
    }
    static int isNumber() {                                                                                      //устраняет ошибки, когда пользователь ввел не число
        boolean isOk = false;
        int number = 0;
        while (!isOk) {
            if (in.hasNextInt()) {
                number = in.nextInt();
                isOk = true;
            } else {
                System.out.println("Некорректный ввод. Попробуйте еще раз:");
                in.next();
            }
        }
        return number;
    }
    static int firstBuilding(String choice, HashSet<String> colors, Player player) {                             //осуществляет постройку первого поселения и дорожки игрока
        System.out.println("Выберите положение поселения на поле. посмотрите на выведенное поле выше и выберите ряд (от 1 до 11) и столбец (от 1 до 4 или больше, в зависимости от ряда)");
        int row = isNumber() - 1;
        int column = isNumber() - 1;
        if (coordinatesCheck(row, column)) {
            if (disposition[row][column] == '0') {
                colors.remove(choice);
                player.available.add(new Triple(row, column, 't'));
                disposition[row][column] = 't';
                for (int j = 0; j < 1; j++) {
                    System.out.println("и расположение дорожки около него ");
                    int rowR = isNumber() - 1;
                    int columnR = isNumber() - 1;
                    if (coordinatesCheck(rowR, columnR)) {
                        if ((disposition[rowR][columnR] == 'r' && ((rowR == row && ((columnR == column - 1) || (columnR == column + 1))) ||
                                ((((row == 6 || row == 4) && rowR == 5) || (row < 4 && rowR == 1 + row) || (row > 6 && rowR + 1 == row)) && columnR * 4 == column) ||
                                (row <= 4 && columnR * 4 + 2 == column && rowR + 1 == row) || (row >= 6 && columnR * 4 + 2 == column && rowR == 1 + row))) && coordinatesCheck(row, column)) {
                            player.available.add(new Triple(rowR, columnR, 'R'));
                            disposition[rowR][columnR] = 'R';
                            possiblePorts(rowR, columnR, player);
                        } else {
                            System.out.println("Попробуйте еще раз");
                            j -= 1;
                        }
                    } else {
                        System.out.println("Попробуйте еще раз");
                        j -= 1;
                    }
                }
            } else {
                System.out.println("Попробуйте еще раз");
                return 1;
            }
        } else {
            System.out.println("Попробуйте еще раз");
            return 1;
        }
        player.points += 1;
        player.roads -= 1;
        player.towns -= 1;
        getResources(row, column, player);
        if (player.cards.isEmpty()) for (List<Character> list : player.element.values()) {
            player.cards.addAll(list);
        }
        return 0;
    }
    static void getResources(Integer row, Integer column, Player player) {                                       //правила для добавления чисел на поля (то есть, какой ресурс получает игрок в зависимости от номера на кубиках)
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
        }
        if ((row == 6 && (column == 2 || column == 4 || column == 6)) || (row == 8 && (column == 0 || column == 2 || column == 4))) {
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
        if ((row == 0 && (column == 8 || column == 10 || column == 12)) || (row == 2 && (column == 10 || column == 12 || column == 14))) {
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
        }
        if (((row == 8) && (column == 10 || column == 12 || column == 14)) || ((row == 10) && (column == 8 || column == 10 || column == 12))) {
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
        }
        if (((row == 6) && (column == 14 || column == 16 || column == 18)) || ((row == 8) && (column == 12 || column == 14 || column == 16))) {
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
    static void possiblePorts(Integer rowR, Integer columnR, Player player) {                                    //смотрит, какие порты есть у игрока для обмена. Правила прописала на основе игры
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
    static int build(Player player) {                                                                            //строит поселение, город или дорожку при наступлении хада игрока
        System.out.println("Что вы хотите построить?\nЕсли дорожку - введите R\nпоселение - t\nгород - c\n");
        String someBuilding = in.next();
        boolean allRight = false;
        switch (someBuilding) {
            case "R" -> {
                if (player.roads > 0) {
                    if (new HashSet<>(player.cards).containsAll(List.of('b', 'w'))) {
                        System.out.println("Введите расположение дорожки около города, поселения или другой дорожки ");
                        int rowR = isNumber() - 1;
                        int columnR = isNumber() - 1;
                        if (coordinatesCheck(rowR, columnR)) {
                            char poseR = disposition[rowR][columnR];
                            for (Triple t : player.available) {
                                if ((poseR == 'r') && (((t.item == 'R') && (((t.col * 4 + 1 == columnR) && (t.ro <= 5) && (t.ro == rowR + 1)) ||
                                        ((t.col * 4 + 1 == columnR) && (t.ro >= 5) && (t.ro + 1 == rowR)) || ((t.col * 4 - 1 == columnR) && (t.ro <= 5) && (t.ro == 1 + rowR)) ||
                                        ((t.col * 4 - 1 == columnR) && (t.ro >= 5) && (t.ro + 1 == rowR)) || ((t.col * 4 + 3 == columnR) && (t.ro <= 3) && (t.ro + 1 == rowR)) ||
                                        ((t.col * 4 + 3 == columnR) && (t.ro >= 7) && (t.ro == rowR + 1)) || ((t.col * 4 + 1 == columnR) && (t.ro >= 5) && (t.ro == rowR + 1)) ||
                                        ((t.col == columnR + 2 && t.ro == rowR) || (t.col == columnR - 2 && t.ro == rowR) && rowR % 2 == 0) ||
                                        ((columnR * 4 + 1 == t.col) && (rowR <= 5) && (rowR == t.ro + 1)) || ((t.col * 4 + 1 == columnR) && (t.ro <= 5) && (t.ro + 1 == rowR)) ||
                                        ((columnR * 4 + 1 == t.col) && (rowR >= 5) && (rowR + 1 == t.ro)) || ((columnR * 4 - 1 == t.col) && (rowR <= 5) && (rowR == 1 + t.ro)) ||
                                        ((columnR * 4 - 1 == t.col) && (rowR >= 5) && (rowR + 1 == t.ro)) || ((columnR * 4 + 3 == t.col) && (rowR <= 3) && (rowR + 1 == t.ro)) ||
                                        ((columnR * 4 + 3 == t.col) && (rowR >= 7) && (rowR == t.ro + 1)) || ((columnR * 4 + 1 == t.col) && (rowR <= 5) && (rowR + 1 == t.ro)) ||
                                        ((columnR * 4 + 1 == t.col) && (rowR >= 5) && (rowR == t.ro + 1)))) ||
                                        ((t.item == 't' || t.item == 'c') && ((rowR == t.ro && ((columnR == t.col - 1) || (columnR == t.col + 1))) ||
                                                ((((t.ro == 6 || t.ro == 4) && rowR == 5) || (t.ro < 4 && rowR == 1 + t.ro) || (t.ro > 6 && rowR + 1 == t.ro)) && columnR * 4 == t.col) ||
                                                (t.ro <= 4 && columnR * 4 + 2 == t.col && rowR + 1 == t.ro) || (t.ro >= 6 && columnR * 4 + 2 == t.col && rowR == 1 + t.ro))))) {
                                    player.available.add(new Triple(rowR, columnR, 'R'));
                                    disposition[rowR][columnR] = 'R';
                                    allRight = true;
                                    possiblePorts(rowR, columnR, player);
                                    System.out.println("Дорожка построена!");
                                    List<Character> toRemove = new ArrayList<>(List.of('b', 'w'));
                                    for (Character c : toRemove) {
                                        player.cards.remove(c);
                                    }
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Неверные координаты. Попробуйте еще раз");
                        }
                    } else {
                        allRight = true;
                        System.out.println("У вас недостаточно ресурсов");
                    }
                } else System.out.println("У вас закончились дорожки");
                if (player.roads < leastRoadsLeft) leastRoadsLeft = player.roads;
            }
            case "t" -> {
                if (player.towns > 0) {
                    if (new HashSet<>(player.cards).containsAll(List.of('b', 'w', 's', 'f'))) {
                        System.out.println("Введите расположение поселения около дорожек ");
                        int row = isNumber() - 1;
                        int column = isNumber() - 1;
                        if (coordinatesCheck(row, column)) {
                            for (Triple t : player.available)
                                if (disposition[row][column] == '0' && (((t.ro == row) && (t.col == column - 1 || t.col == column + 1)) ||
                                        (((row == t.ro + 1) || (row == t.ro - 1)) && (t.ro == 4 || t.ro == 6 || (t.ro < 4 && row > t.ro) || (t.ro > 6 && row < t.ro)) && t.col == column * 4) ||
                                        (((row == t.ro + 1) || (row == t.ro - 1)) && ((t.ro < 4 && row < t.ro) || (t.ro > 6 && row > t.ro)) && t.col == column * 4 + 2))) {
                                    player.available.add(new Triple(row, column, 't'));
                                    disposition[row][column] = 't';
                                    allRight = true;
                                    player.points += 1;
                                    getResources(row, column, player);
                                    System.out.println("Поселение построено!");
                                    List<Character> toRemove = new ArrayList<>(List.of('b', 'w', 's', 'f'));
                                    for (Character c : toRemove) {
                                        player.cards.remove(c);
                                    }
                                    break;
                                }
                        } else System.out.println("Неверные координаты. Попробуйте еще раз");
                    } else {
                        allRight = true;
                        System.out.println("У вас недостаточно ресурсов");
                    }
                } else System.out.println("У вас закончились поселения");
            }
            case "c" -> {
                if (player.cities > 0) {
                    if (new HashSet<>(player.cards).containsAll(List.of('s', 's', 't', 't', 't'))) {
                        System.out.println("Введите расположение города на месте поселения ");
                        int rowC = isNumber() - 1;
                        int columnC = isNumber() - 1;
                        Triple toDelete = null;
                        if (coordinatesCheck(rowC, columnC)) {
                            for (Triple t : player.available)
                                if (disposition[rowC][columnC] == 't') {
                                    player.available.add(t = new Triple(rowC, columnC, 'c'));
                                    toDelete = t;
                                    disposition[rowC][columnC] = 'c';
                                    allRight = true;
                                    player.points += 1;
                                    getResources(rowC, columnC, player);
                                    System.out.println("Город построен!");
                                    List<Character> toRemove = new ArrayList<>(List.of('s', 's', 't', 't', 't'));
                                    for (Character c : toRemove) {
                                        player.cards.remove(c);
                                    }
                                    break;
                                }
                            player.available.remove(toDelete);
                        } else {
                            System.out.println("Попробуйте еще раз");
                            return (1);
                        }
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
        } else return 0;
    }
    static void exchangeWithPorts(Player player) {                                                               //осуществляет обмен игрока с портами
        if (player.ports.size() == 0) {
            System.out.println("Выберите ресурсы для обмена 1:4 - введите названия 4 своих ресурсов без пробелов");
            String playerGives = in.next();
            String[] playerGivesList = playerGives.split("");
            List<String> pg = Arrays.asList(playerGives.split(""));
            List<String> cardStrings = player.cards.stream().map(String::valueOf).toList();
            if (checkInsExchange(playerGivesList) && new HashSet<>(cardStrings).containsAll(pg)) {
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
                    System.out.println("Обмен прошел успешно" + player.cards);
                } else if (playerPort.equals("3") || playerPort.equals("4")) {
                    if (player.cards.size() >= Integer.parseInt(playerPort)) {
                        System.out.println("Какие ресурсы вы бы хотели отдать? - введите названия " + playerPort + " своих ресурсов без пробелов");
                        String playerGives = in.next();
                        String[] playerGivesList = playerGives.split("");
                        List<String> pg = Arrays.asList(playerGives.split(""));
                        List<String> cardStrings = player.cards.stream().map(String::valueOf).toList();
                        if (checkInsExchange(playerGivesList) && playerGivesList.length == Integer.parseInt(playerPort) && new HashSet<>(cardStrings).containsAll(pg) && playerReceives.length() == 1) {
                            for (String item : playerGivesList) player.cards.remove(Character.valueOf(item.charAt(0)));
                            player.cards.add(playerReceives.charAt(0));
                            System.out.println("Обмен прошел успешно" + player.cards);
                        } else System.out.println("Вы ввели не то количество отдаваемых ресурсов");
                    } else System.out.println("Недостаточно ресурсов для обмена");
                } else System.out.println("Недостаточно ресурсов для обмена");
            } else System.out.println("Вы неправильно ввели порт или то, что хотите получить взамен");
        }
    }
    static void exchangeWithPlayer(Player player) {                                                              //осуществляет обмен игрока с другими игроками
        System.out.println("С каким игроком вы хотите обменяться? Введите цифру от 1 до 4 в зависимости от номера игрока");
        int exchanger = isNumber();
        Player nameExchanger = playerList.get(exchanger - 1);
        if (exchanger <= players && player != nameExchanger) {
            System.out.println("Список ресурсов игрока " + exchanger + ":" + nameExchanger.cards);
            System.out.println("Выберите свои ресурсы, которые вы готовы отдать ");
            String playerGives = in.next();
            String[] playerGivesList = playerGives.split("");
            System.out.println("Выберите ресурс, который хотите получить");
            String[] playerReceives = in.next().split("");
            List<String> cardStrings = player.cards.stream().map(String::valueOf).toList();
            List<String> pg = Arrays.asList(playerGives.split(""));
            List<String> cardStrings1 = nameExchanger.cards.stream().map(String::valueOf).toList();
            List<String> pg1 = Arrays.asList(playerReceives);
            System.out.println(" игрок " + exchanger + ", вы готовы принять в обмен на " + Arrays.toString(playerReceives) + " ресурс(ы) " + Arrays.toString(playerGivesList) + "? (yes/no)");
            if (in.next().equals("yes")) {
                if (checkInsExchange(playerReceives) && checkInsExchange(playerGivesList) && new HashSet<>(cardStrings).containsAll(pg) && new HashSet<>(cardStrings1).containsAll(pg1)) {
                    for (String item : playerGivesList) {
                        player.cards.remove(Character.valueOf(item.charAt(0)));
                        nameExchanger.cards.add(item.charAt(0));
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
    static boolean checkInsExchange(String[] input) {                                                            //так как ресурсы при обмене должны быть введены верно, я ввела обозначения и добавила эту функцию
        for (String item : input) {                                                                                     //b - кирпич, w - дерево, t - камень, s - сено, f - овцы,
            if (!(item.equals("b") || item.equals("w") || item.equals("t") || item.equals("s") || item.equals("f")))
                return false;
        }
        return true;
    }
    static void buyEvolutionCard(Player player) {                                                                //действие для каждой карточки развития, будь то рыцарь, постройка дорожек, получение очков для победы
        List<Character> toRemove = List.of('s', 'f', 't');
        if (new HashSet<>(player.cards).containsAll(toRemove)) {
            for (Character ch : toRemove) {
                if (Collections.frequency(player.cards, ch) >= 1) {
                    player.cards.remove(ch);
                }
            }
            String playersCard = evolutionCards.get(0);
            boolean allRight = false;
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
                            for (String item : ir) player.cards.add(item.charAt(0));
                            allRight = true;
                            System.out.println("Ваши ресурсы: " + player.cards);
                        } else System.out.println("Вы ввели карты ресурсов неправильно");
                    }
                    case "forward2" -> {
                        System.out.println("Можете выбрать 1 ресурс, который вам отдадут все соперники: ");
                        String iReceive = in.next();
                        String[] ir = iReceive.split("");
                        if (checkInsExchange(ir) && ir.length == 1) {
                            int resourcesNumber = 0;
                            for (Player value : playerList) {
                                resourcesNumber += Collections.frequency(value.cards, ir[0].charAt(0));
                                value.cards.removeAll(Collections.singleton(iReceive.charAt(0)));
                            }
                            player.cards.addAll(Collections.nCopies(resourcesNumber, ir[0].charAt(0)));
                            allRight = true;
                            System.out.println("Ваши ресурсы: " + player.cards);
                        }
                    }
                    case "map" -> {
                        System.out.println("Можете построить 2 дороги без затраты ресурсов");
                        if (player.roads > 0) {
                            for (int i = 0; i < 2; i++) {
                                allRight = false;
                                System.out.println("Введите расположение дорожки около города, поселения или другой дорожки ");
                                int rowR = isNumber() - 1;
                                int columnR = isNumber() - 1;
                                Set<Triple> toAdd = new HashSet<>();
                                Set<Triple> playersArsenal = player.available;
                                if (coordinatesCheck(rowR, columnR)) {
                                    for (Triple t : playersArsenal) {
                                        if ((disposition[rowR][columnR] == 'r') && (((t.item == 'R') && (((t.col * 4 + 1 == columnR) && (t.ro <= 5) && (t.ro == rowR + 1)) ||
                                                ((t.col * 4 + 1 == columnR) && (t.ro >= 5) && (t.ro + 1 == rowR)) || ((t.col * 4 - 1 == columnR) && (t.ro <= 5) && (t.ro == 1 + rowR)) ||
                                                ((t.col * 4 - 1 == columnR) && (t.ro >= 5) && (t.ro + 1 == rowR)) || ((t.col * 4 + 3 == columnR) && (t.ro <= 3) && (t.ro + 1 == rowR)) ||
                                                ((t.col * 4 + 3 == columnR) && (t.ro >= 7) && (t.ro == rowR + 1)) || ((t.col * 4 + 1 == columnR) && (t.ro >= 5) && (t.ro == rowR + 1)) ||
                                                ((t.col == columnR + 2 && t.ro == rowR) || (t.col == columnR - 2 && t.ro == rowR) && rowR % 2 == 0) ||
                                                ((columnR * 4 + 1 == t.col) && (rowR <= 5) && (rowR == t.ro + 1)) || ((t.col * 4 + 1 == columnR) && (t.ro <= 5) && (t.ro + 1 == rowR)) ||
                                                ((columnR * 4 + 1 == t.col) && (rowR >= 5) && (rowR + 1 == t.ro)) || ((columnR * 4 - 1 == t.col) && (rowR <= 5) && (rowR == 1 + t.ro)) ||
                                                ((columnR * 4 - 1 == t.col) && (rowR >= 5) && (rowR + 1 == t.ro)) || ((columnR * 4 + 3 == t.col) && (rowR <= 3) && (rowR + 1 == t.ro)) ||
                                                ((columnR * 4 + 3 == t.col) && (rowR >= 7) && (rowR == t.ro + 1)) || ((columnR * 4 + 1 == t.col) && (rowR <= 5) && (rowR + 1 == t.ro)) ||
                                                ((columnR * 4 + 1 == t.col) && (rowR >= 5) && (rowR == t.ro + 1)))) ||
                                                ((t.item == 't' || t.item == 'c') && ((rowR == t.ro && ((columnR == t.col - 1) || (columnR == t.col + 1))) ||
                                                        ((((t.ro == 6 || t.ro == 4) && rowR == 5) || (t.ro < 4 && rowR == 1 + t.ro) || (t.ro > 6 && rowR + 1 == t.ro)) && columnR * 4 == t.col) ||
                                                        (t.ro <= 4 && columnR * 4 + 2 == t.col && rowR + 1 == t.ro) || (t.ro >= 6 && columnR * 4 + 2 == t.col && rowR == 1 + t.ro))))) {
                                            toAdd.add(new Triple(rowR, columnR, 'R'));
                                            disposition[rowR][columnR] = 'R';
                                            possiblePorts(rowR, columnR, player);
                                            allRight = true;
                                            System.out.println("Дорожка построена!");
                                        } else {
                                            System.out.println("Неправильные координаты дорожки. Попробуйте еще раз");
                                        }
                                        break;
                                    }
                                } else {
                                    System.out.println("Попробуйте еще раз");
                                    break;
                                }
                                player.available.addAll(toAdd);
                                if (!allRight) i -= 1;
                            }
                            player.roads -= 2;
                            if (player.roads < leastRoadsLeft) leastRoadsLeft = player.roads;
                        } else System.out.println("У вас закончились дорожки");
                    }
                    case "knight" -> {
                        System.out.println("Выберите соперника, чтобы получить от него 1 случайно выбранный ресурс:");
                        int victim = isNumber();
                        Player nameVictim = playerList.get(victim - 1);
                        if (nameVictim != player) {
                            player.cards.add(nameVictim.cards.get(0));
                            nameVictim.cards.remove(0);
                            player.knights += 1;
                            allRight = true;
                            if (player.knights > mostKnights) mostKnights = player.knights;
                        } else {
                            System.out.println("Вы не можете забрать ресурсы у самого себя");
                        }
                    }
                }
            }
        } else System.out.println("У вас недостаточно ресурсов");
    }
    static Integer illusionOfChoice(Integer l, Player player) {                                                  //каждый ход игрока он может что-то купить, что-то построить или обменяться
        boolean allRight = false;
        while (!allRight) {
            System.out.println(" игрок " + (l + 1) + ", ваш ход. Вот ваши ресурсы " + player.cards);
            System.out.println("Если хотите что-то построить, введите 1\nобменяться с портом - 2\nобменяться с соперником - 3\nкупить карточку развития - 4\nЧтобы пропустить ход - 0");
            int action = isNumber();
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
                default -> System.out.println("Вы ввели не то действие. Попробуйте еще раз");
            }
        }
        return l;
    }
}