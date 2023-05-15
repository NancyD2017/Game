package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.example.Pole.dibs;
import static org.example.model.Model.*;
import static org.junit.jupiter.api.Assertions.*;

class TestModel {
    @Test
    void RoadTest() {
        Road r = new Road();
        Player player = new Player();
        player.available.clear();
        System.out.println("Please, coordinates 3 and 2");//мне пришлось делать вывод таких сообщений, потому что для каких-то классов нужны некоторые заблаговременные действия, которые отображаются на поле. А здесь поле не показать
        assertFalse(r.main(player));
        player.available.add(new FieldItem(2,2,Item.T)); //необходимо построить город, чтобы построить дорожку
        System.out.println("Please, enter coordinates 3 and 2\n");
        assertTrue(r.main(player) && Model.field[2][1] == Item.R && dibs.size() == 1 && player.ports.contains("w")); //задаем правильные координаты (3 и 2) и проверяем
    }
    @Test
    void ResourcesTest() {
        Resources r = new Resources();
        Player player = new Player();
        hexes.clear();
        player.cards.clear();
        hexes.addAll(Arrays.asList('0', '0', '0', 't', '0', '0', '0', 's', 'f', '0', '0'));
        r.getResources(4,4, player);
        assertTrue(player.element.get(10).contains('t') && player.element.get(8).contains('s') && player.element.get(11).contains('f'));
        assertFalse(player.element.get(10).contains('0') && player.element.get(8).contains('0') && player.element.get(11).contains('0'));
    }
    @Test
    void PossiblePortsTest() {
        PossiblePorts possiblePorts = new PossiblePorts();
        Player player = new Player();
        player.available.clear();
        possiblePorts.act(1,2,player);
        assertTrue(player.ports.contains("s"));
        assertFalse(player.ports.contains("3"));
    }
    @Test
    void FirstBuildingTest() {
        FirstBuilding firstBuilding = new FirstBuilding();
        Player player = playerList.get(0);
        hexes.clear();
        player.available.clear();
        player.points = 0;
        player.towns = 5;
        player.roads = 15;
        System.out.println("Please, enter coordinates 3 and 3, then enter 3 and 4\n");
        hexes.addAll(Arrays.asList('0', '0', '0', 't', '0', '0', '0', 's', 'f', '0', '0')); //лучше всего ввести для города 3 и 3, для дорожки - 3 и 4
        if (firstBuilding.main(0, Colors.Blue) == 0) assertTrue(Model.colors.size() == 3 && player.points == 1 &&
                player.towns == 4 && player.roads == 14);
    }
    @Test
    void ExchangeWithPortsTest() {
        Player player = playerList.get(0);
        ExchangeWithPorts exchangeWithPorts = new ExchangeWithPorts();
        System.out.println("Firstly, the window will close quickly.\nThen, please, press the button of straw 4 times\nThirdly, choose any exchange and resources you like\n");
        player.ports.clear();
        exchangeWithPorts.act(player);
        assertEquals(player.cards.size(),0);
        player.cards.addAll(List.of('s','s','s','s'));
        exchangeWithPorts.act(player);
        assertTrue(player.cards.size() > 0);
        player.ports.add("s");
        player.cards.addAll(List.of('s','f','s','f'));
        exchangeWithPorts.act(player);
        assertTrue(player.cards.size() > 1);
    }
    @Test
    void ExchangeWithPlayerTest() {
        Player player = playerList.get(0);
        player.cards.clear();
        player.cards.add('s');
        playerList.get(1).cards.clear();
        playerList.get(1).cards.add('f');
        players = 2;
        ExchangeWithPlayer exchangeWithPlayer = new ExchangeWithPlayer();
        exchangeWithPlayer.act(player);
        System.out.println("Press anything you want\n");
        assertTrue(player.cards.size() == 1 && playerList.get(1).cards.size() == 1);
    }
    @Test
    void EvolutionCardsTest() {
        Player player = playerList.get(0);
        EvolutionCards evolutionCards1 = new EvolutionCards();
        player.cards.clear();
        player.points = 0;
        player.cards.addAll(List.of('s','f','t'));
        evolutionCards.add("church");
        evolutionCards1.main(player);  //окно закрывается так быстро, потому что мы сразу проверяем условия и на этом
        assertEquals(1, (int) player.points); //программа завершает работу. если вставить до этой строки бесконечный цикл, можно рассмотреть окно
    }
    @Test
    void Evo_MapTest() {
        Player player = playerList.get(0);
        EvolutionCards evolutionCards1 = new EvolutionCards();
        player.cards.clear();
        player.cards.addAll(List.of('s','f','t'));
        evolutionCards.add("map");
        player.available.add(new FieldItem(2,2,Item.T)); //необходимо построить город, чтобы построить дорожку
        System.out.println("Please, enter coordinates 3 and 6 and then enter 3 and 8\n");
        evolutionCards1.main(player); //здесь нужно будет ввести 3 и 4, а потом 3 и 2
        assertEquals(13, player.roads);
    }
    @Test
    void Evo_KnightTest() {
        Player player = playerList.get(0);
        EvolutionCards evolutionCards1 = new EvolutionCards();
        player.cards.clear();
        playerList.get(1).cards.clear();
        player.cards.addAll(List.of('s','f','t'));
        playerList.get(1).cards.add('s');
        players = 2;
        evolutionCards.add("knight");
        evolutionCards1.main(player);
        assertTrue(1 == player.knights && player.cards.get(0) == 's' && playerList.get(1).cards.size() == 0);
    }
    @Test
    void Evo_Forward1Test() {
        Player player = playerList.get(0);
        EvolutionCards evolutionCards1 = new EvolutionCards();
        player.cards.clear();
        player.cards.addAll(List.of('s','f','t'));
        evolutionCards.add("forward1");
        evolutionCards1.main(player);
        assertEquals(2, player.cards.size());
    }
    @Test
    void Evo_Forward2Test() {
        Player player = playerList.get(0);
        EvolutionCards evolutionCards1 = new EvolutionCards();
        player.cards.clear();
        player.cards.addAll(List.of('s','f','t'));
        playerList.get(1).cards.addAll(List.of('s','f','t','b','w'));
        evolutionCards.add("forward2");
        evolutionCards1.main(player);
        assertEquals(1, player.cards.size());
    }
    @Test
    void Evo_AllTest() {
        Player player = playerList.get(0);
        EvolutionCards evolutionCards1 = new EvolutionCards();
        player.cards.clear();
        player.cards.addAll(List.of('s','f','t'));
        evolutionCards.add("funfair");
        evolutionCards1.main(player);  //окно закрывается так быстро, потому что мы сразу проверяем условия и на этом
        assertEquals(1, (int) player.points); //Программа завершает работу. Если вставить до этой строки бесконечный цикл, можно рассмотреть окно
    }
    @Test
    void BuildTest() {
        Player player = playerList.get(0);
        Build build = new Build();
        player.available.clear();
        player.roads = 15;
        player.towns = 5;
        player.cities = 4;
        player.available.add(new FieldItem(2,3,Item.R));
        player.available.add(new FieldItem(2,2,Item.T)); //необходимо построить город, чтобы построить дорожку
        assertEquals(0, build.act(player));
        hexes.addAll(Arrays.asList('0', '0', '0', 't', '0', '0', '0', 's', 'f', '0', '0'));
        player.cards.addAll(List.of('s','f','s','f','s','s','b','w','t','b','w'));
        System.out.println("Please, enter coordinates 3 and 2 if you wanna build road,\n 3 and 3 - town\n 3 and 5 - city\n");
        build.act(player);//постройте дорожку на координатах 3 и 2, город - 3 и 3, деревню - 3 и 5
        assertTrue(player.roads == 15 || player.towns == 3 || (player.cities == 4 && player.towns == 5));
    }
}