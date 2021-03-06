package com.game;

import java.util.*;

public class Card {
    private final static String[] numbersOfCard = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; // номер карты B
    private final static String[] suits = {"♠", "♥", "♦", "♣"}; //масть пики червы крести буби в unicode
    private final static int length = numbersOfCard.length * suits.length;
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
    } // получение номера  конкретной карты

    public String getSuit() {
        return suit;
    } // получение конкретной масти

    public static int rankOfCard(Card card) { //метод для присвоения карте ее значения
        String number = card.getNumberOfCard();
        return rankMap.get(number);
    }

    @Override
    public String toString() {
        return String.format("[%s%s]", numberOfCard, suit);
    } //переопределяем метод для вывода

    public static ArrayList<Card> deck() {// создание колоды
        ArrayList<Card> result = new ArrayList<>(length);
        for (String suit : suits) {
            for (String number : numbersOfCard) {
                result.add(new Card(number, suit));
            }
        }
        return result;
    }

    public static void shuffle(ArrayList<Card> deck) { // метод тасующий колоду
        Collections.shuffle(deck);
    }

    public static Card pullOutCard(List<Card> deck) { // метод вытаскивает карту и удаляет ее из колоды
        return deck.remove(0);
    }

    public static ArrayList<Card> pullOutCards(ArrayList<Card> deck, int amount) { // вытаскиваем необходимое количество кард
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(deck.remove(0));
        }
        return cards;
    }

    public boolean isAce() {
        return numberOfCard.equals("A");
    }

    public static int pointsCount(ArrayList<Card> cards) { // метод для подсчета очков
        int aceCount = 0;
        for (Card card :cards) {
            if (card.isAce()) aceCount++;
        }
        if (aceCount == 3) return 13;

        int sum = 0;

        for (Card card : cards) {
            sum += rankOfCard(card);
        }
        for (Card card : cards) {
            if (card.isAce() && sum > 21) {
                sum -= 10;
            }
        }
        return sum;
    }

    public String[] showCard() {
        String[] cardRender = {
                              "┌─────────┐",
                String.format("│ %2s      │",numberOfCard),
                              "│         │",
                              "│         │",
                String.format("│    %s    │",suit),
                              "│         │",
                              "│         │",
                String.format("│      %2s │",numberOfCard),
                              "└─────────┘"};
        return cardRender;
    }

    public String[] showHiddenCard() {
        return new String[]{
                "┌─────────┐",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "│░░░░░░░░░│",
                "└─────────┘",};
    }

    @Override public boolean equals(Object o) {
        if(o instanceof Card) {
            Card card = (Card) o;
            return this.numberOfCard.equals(card.numberOfCard) && this.suit.equals(card.suit);
        } else {
            return false;
        }
    }
}
