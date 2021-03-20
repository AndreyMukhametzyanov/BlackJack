package com.game;

import java.lang.reflect.Array;
import java.util.*;

public class Card {
    private final static String[] numbersOfCard = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; // номер карты B
    private final static String[] suits = {"♠", "♥", "♦", "♣"}; //масть пики червы крести буби в unicode
    private final static Map<String, Integer> rankMap = new HashMap<>();
    {
        rankMap.put("2", 2);
        rankMap.put("3", 3);
        rankMap.put("4", 4);
        rankMap.put("5", 5);
        rankMap.put("6", 6);
        rankMap.put("7", 7);
        rankMap.put("8", 8);
        rankMap.put("9", 9);
        rankMap.put("10", 10);
        rankMap.put("J", 10);
        rankMap.put("Q", 10);
        rankMap.put("K", 10);
        rankMap.put("A", 11);
    }


    private final String numberOfCard; //для конкретной карты
    private final String suit; // для конкретной масти

    public Card(String numberOfCard, String suit) { // конструтор класса Card
        this.numberOfCard = numberOfCard;
        this.suit = suit;
    }

    public String getNumberOfCard() {
        return numberOfCard;
    } // получение конкретной карты
    public String getSuit() {
        return suit;
    } // получение конкретной масти



    public static Integer rankOfCard(Card card) { //
        String number = card.getNumberOfCard();
        return rankMap.get(number);
    }

    @Override
    public String toString() {
        return String.format("[%s%s]", numberOfCard, suit);
    } //переопределяем метод для вывода


    public static List<Card> deck () {// создание колоды
        List<Card> result = new ArrayList<>(numbersOfCard.length * suits.length);
        for (String suit : suits) {
            for (String number : numbersOfCard) {
                result.add(new Card(number, suit));
            }
        }
        return result;

    }
    public static List<Card> shuffle (List<Card> deck){
        List<Card> shuffleDeck = new ArrayList<>(deck);
        Collections.shuffle(shuffleDeck);
        return shuffleDeck;
    }
    public static Integer pointsCount (ArrayList<Card> cards){ // метод для подсчета очков

        return 0;
    }




}

